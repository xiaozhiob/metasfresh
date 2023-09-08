/*
 * #%L
 * de.metas.contracts
 * %%
 * Copyright (C) 2023 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

package de.metas.contracts.modular.invgroup.interceptor;

import de.metas.contracts.modular.invgroup.InvoicingGroupId;
import de.metas.contracts.modular.invgroup.InvoicingGroupProductId;
import de.metas.i18n.AdMessageKey;
import de.metas.product.ProductId;
import de.metas.util.Services;
import lombok.NonNull;
import org.adempiere.ad.dao.ICompositeQueryFilter;
import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.ad.dao.IQueryBuilder;
import org.adempiere.ad.dao.impl.CompareQueryFilter;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_ModCntr_InvoicingGroup;
import org.compiere.model.I_ModCntr_InvoicingGroup_Product;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.sql.Timestamp;
import java.util.stream.Stream;

@Repository
public class ModCntrInvoicingGroupRepository
{
	private final IQueryBL queryBL = Services.get(IQueryBL.class);
	private final static AdMessageKey MSG_PRODUCT_IN_ANOTHER_INVOICING_GROUP = AdMessageKey.of("de.metas.contracts.modular.invgroup.ProductInAnotherGroup");

	public void validateInvoicingGroupProductNoOverlap(
			@NonNull final ProductId productId,
			@Nullable final InvoicingGroupProductId excludeInvoicingGroupProductId,
			@NonNull final Timestamp validFrom,
			@NonNull final Timestamp validTo)
	{

		final ICompositeQueryFilter<I_ModCntr_InvoicingGroup> validFromFilter = queryBL.createCompositeQueryFilter(I_ModCntr_InvoicingGroup.class)
				.addCompareFilter(I_ModCntr_InvoicingGroup.COLUMNNAME_ValidFrom, CompareQueryFilter.Operator.LESS, validFrom)
				.addCompareFilter(I_ModCntr_InvoicingGroup.COLUMNNAME_ValidTo, CompareQueryFilter.Operator.GREATER, validFrom);
		final ICompositeQueryFilter<I_ModCntr_InvoicingGroup> validToFilter = queryBL.createCompositeQueryFilter(I_ModCntr_InvoicingGroup.class)
				.addCompareFilter(I_ModCntr_InvoicingGroup.COLUMNNAME_ValidFrom, CompareQueryFilter.Operator.LESS, validTo)
				.addCompareFilter(I_ModCntr_InvoicingGroup.COLUMNNAME_ValidTo, CompareQueryFilter.Operator.GREATER, validTo);
		final IQueryBuilder<I_ModCntr_InvoicingGroup_Product> queryBuilder = queryBL.createQueryBuilder(I_ModCntr_InvoicingGroup_Product.class)
				.addOnlyActiveRecordsFilter()
				.addEqualsFilter(I_ModCntr_InvoicingGroup_Product.COLUMNNAME_M_Product_ID, productId);
		if (excludeInvoicingGroupProductId != null)
		{
			queryBuilder.addNotEqualsFilter(I_ModCntr_InvoicingGroup_Product.COLUMNNAME_ModCntr_InvoicingGroup_Product_ID, excludeInvoicingGroupProductId);
		}
		final boolean invoicingGroupsOverlapingForProduct = queryBuilder
				.andCollect(I_ModCntr_InvoicingGroup_Product.COLUMN_ModCntr_InvoicingGroup_ID)
				.addFilter(
						queryBL.createCompositeQueryFilter(I_ModCntr_InvoicingGroup.class)
								.setJoinOr()
								.addFilter(validFromFilter)
								.addFilter(validToFilter))
				.create()
				.anyMatch();
		if (invoicingGroupsOverlapingForProduct)
		{
			throw new AdempiereException(MSG_PRODUCT_IN_ANOTHER_INVOICING_GROUP);
		}
	}

	public Stream<I_ModCntr_InvoicingGroup_Product> streamInvoicingGroupProductsFor(@NonNull final InvoicingGroupId invoicingGroupId)
	{
		return queryBL.createQueryBuilder(I_ModCntr_InvoicingGroup_Product.class)
				.addEqualsFilter(I_ModCntr_InvoicingGroup_Product.COLUMNNAME_ModCntr_InvoicingGroup_ID,invoicingGroupId)
				.addOnlyActiveRecordsFilter()
				.stream();
	}
}
