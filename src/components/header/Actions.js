import React, { Component } from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import counterpart from 'counterpart';

import Loader from '../app/Loader';

import {
    actionsRequest,
    rowActionsRequest
} from '../../actions/GenericActions';

class Actions extends Component {
    state = {
        data: null,
    }

    componentDidMount = () => {
        const {
            windowType, entity, docId, rowId, notfound, activeTab,
            activeTabSelected,
        } = this.props;

        if(!windowType || docId === 'notfound' || notfound){
            this.setState({
                data: []
            });
            return;
        }

        const requestRowActions = activeTab && activeTabSelected && (
            activeTabSelected.length > 0
        );

        if (entity === 'board') {
            this.setState({
                data: []
            });

            return;
        }

        actionsRequest({
            entity,
            type: windowType,
            id: docId,
            selected: rowId
        }).then((response) => {
            let actions = response.data.actions;

            if (requestRowActions) {
                rowActionsRequest({
                    windowId: windowType,
                    documentId: docId,
                    tabId: activeTab,
                    selected: activeTabSelected
                }).then((response) => {
                    if (response.data && response.data.actions && (
                        response.data.actions.length > 0)
                    ) {
                        let mergeActions = response.data.actions.map((item) => {
                            return Object.assign(item, {
                                rowId: activeTabSelected,
                                tabId: activeTab
                            });
                        });
                        actions = actions.concat([null], mergeActions);
                    }

                    this.setState({
                        data: actions
                    });
                }).catch(() => {
                    this.setState({
                        data: actions
                    });
                });
            }
            else {
                this.setState({
                    data: actions
                });
            }
        }).catch(() => {
            this.setState({
                data: []
            })
        })
    }

    renderData = () => {
        const { closeSubheader, openModal, openModalRow } = this.props;
        const { data } = this.state;

        return (data && data.length) ? data.map((item, key) => {
            if (item) {
                return (
                    <div
                        key={key}
                        tabIndex={0}
                        className={'subheader-item js-subheader-item' + (
                          item.disabled ? ' subheader-item-disabled' : ''
                        )}
                        onClick={item.disabled ? null : () => {
                            if (item.tabId && item.rowId) {
                                openModalRow(
                                    item.processId + '', 'process',
                                    item.caption, item.tabId, item.rowId[0]
                                );
                            }
                            else {
                                openModal(
                                    item.processId + '', 'process', item.caption
                                );
                            }

                            closeSubheader()
                        }}
                    >
                        {item.caption}

                        {item.disabled && item.disabledReason && (
                          <p className="one-line">
                            <small>({item.disabledReason})</small>
                          </p>
                        )}
                    </div>
                );
            }

            return (
                <hr
                    key={key}
                    tabIndex={0}
                />
            );
        }) : (
            <div className="subheader-item subheader-item-disabled">
                {counterpart.translate('window.actions.emptyText')}
            </div>
        )
    }

    render() {
        const {data} = this.state;
        return (
            <div
                className="subheader-column js-subheader-column"
                tabIndex={0}
            >
                <div className="subheader-header">
                    {counterpart.translate('window.actions.caption')}
                </div>
                <div className="subheader-break" />
                {!data ?
                    <Loader /> :
                    this.renderData()
                }
            </div>
        );
    }
}

Actions.propTypes = {
    windowType: PropTypes.string,
    dispatch: PropTypes.func.isRequired
}

Actions = connect()(Actions);

export default Actions;
