package de.metas.allocation.api;

import org.compiere.model.I_C_AllocationHdr;
import org.compiere.model.I_C_Payment;

import de.metas.adempiere.model.I_C_Invoice;
import de.metas.util.ISingletonService;

public interface IAllocationBL extends ISingletonService
{
	/**
	 * Creates a new allocation builder.
	 */
	C_AllocationHdr_Builder newBuilder();

	/**
	 * This method creates an allocation between the given invoice and incoming payments that belong to the same C_BPartner, have the {@link I_C_Payment#isAutoAllocateAvailableAmt()} flag set and are
	 * not yet fully allocated.
	 *
	 * @param invoice the invoice to allocate against.
	 * @return the created an completed allocation or <code>null</code>, if the invoice is already fully paid, or is a PO-invoice, or is a credit memo.
	 * task 04193
	 */
	I_C_AllocationHdr autoAllocateAvailablePayments(I_C_Invoice invoice);

	/**
	 * This method creates an allocation between the given invoice and incoming payment that belong to the same C_BPartner, have the {@link I_C_Payment#isAutoAllocateAvailableAmt()} flag set and is
	 * not yet fully allocated.
	 *
	 * @param invoice the invoice to allocate against.
	 * @param payment to allocate
	 * @param ignoreIsAutoAllocateAvailableAmt if <code>false</code> then we only create the allocation if the payment has {@link I_C_Payment#COLUMN_IsAutoAllocateAvailableAmt} <code>='Y'</code>.
	 * @return the created an completed allocation or <code>null</code>, if the invoice is already fully paid, or is a PO-invoice, or is a credit memo or payment and invoice are not matching
	 * task 07783
	 */
	I_C_AllocationHdr autoAllocateSpecificPayment(org.compiere.model.I_C_Invoice invoice,
			I_C_Payment payment,
			boolean ignoreIsAutoAllocateAvailableAmt);

	/**
	 * @return <code>true</code> if the given allocationHdr is the reversal of another allocationHdr.
	 */
	boolean isReversal(I_C_AllocationHdr allocationHdr);
}
