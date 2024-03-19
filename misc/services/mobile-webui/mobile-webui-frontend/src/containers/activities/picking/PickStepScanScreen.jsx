import React, { useEffect } from 'react';
import { useHistory, useRouteMatch } from 'react-router-dom';
import { shallowEqual, useDispatch, useSelector } from 'react-redux';

import { trl } from '../../../utils/translations';
import { pushHeaderEntry } from '../../../actions/HeaderActions';
import { getActivityById, getQtyRejectedReasonsFromActivity, getStepById } from '../../../reducers/wfProcesses';
import { toastError } from '../../../utils/toast';
import { getPickFromForStep, getQtyToPickForStep } from '../../../utils/picking';
import { postStepPicked } from '../../../api/picking';
import { updatePickingStepQty } from '../../../actions/PickingActions';

import ScanHUAndGetQtyComponent from '../../../components/ScanHUAndGetQtyComponent';
import { toQRCodeString } from '../../../utils/qrCode/hu';

const PickStepScanScreen = () => {
  const {
    url,
    params: { workflowId: wfProcessId, activityId, lineId, stepId, altStepId },
  } = useRouteMatch();

  const { eligibleQRCode, qtyToPick, uom, qtyRejectedReasons } = useSelector(
    (state) => getPropsFromState({ state, wfProcessId, activityId, lineId, stepId, altStepId }),
    shallowEqual
  );

  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(
      pushHeaderEntry({
        location: url,
        caption: trl('activities.picking.scanQRCode'),
        values: [],
      })
    );
  }, []);

  const history = useHistory();
  const onResult = ({ qty = 0, reason = null, scannedBarcode = null }) => {
    const qtyRejected = qtyToPick - qty;

    postStepPicked({
      wfProcessId,
      activityId,
      lineId,
      stepId,
      huQRCode: scannedBarcode,
      qtyPicked: qty,
      qtyRejectedReasonCode: reason,
      qtyRejected,
    })
      .then(() => {
        dispatch(
          updatePickingStepQty({
            wfProcessId,
            activityId,
            lineId,
            stepId,
            altStepId,
            qtyPicked: qty,
            qtyRejected,
            qtyRejectedReasonCode: reason,
          })
        );
        history.go(-2); // go to picking line screen
      })
      .catch((axiosError) => toastError({ axiosError }));
  };

  return (
    <ScanHUAndGetQtyComponent
      eligibleBarcode={eligibleQRCode}
      qtyCaption={trl('general.QtyToPick')}
      qtyMax={qtyToPick}
      qtyTarget={qtyToPick}
      uom={uom}
      qtyRejectedReasons={qtyRejectedReasons}
      //
      onResult={onResult}
    />
  );
};

const getPropsFromState = ({ state, wfProcessId, activityId, lineId, stepId, altStepId }) => {
  const activity = getActivityById(state, wfProcessId, activityId);
  const qtyRejectedReasons = getQtyRejectedReasonsFromActivity(activity);

  const stepProps = getStepById(state, wfProcessId, activityId, lineId, stepId);
  const eligibleQRCode = toQRCodeString(getPickFromForStep({ stepProps, altStepId }).huQRCode);
  const qtyToPick = getQtyToPickForStep({ stepProps, altStepId });

  return {
    eligibleQRCode,
    qtyToPick,
    uom: stepProps.uom,
    qtyRejectedReasons,
  };
};

export default PickStepScanScreen;
