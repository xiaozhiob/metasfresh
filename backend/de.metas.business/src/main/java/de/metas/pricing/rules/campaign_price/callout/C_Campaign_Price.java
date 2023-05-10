package de.metas.pricing.rules.campaign_price.callout;

import de.metas.bpartner.BPGroupId;
import de.metas.bpartner.BPartnerId;
import de.metas.bpartner.service.IBPartnerDAO;
import de.metas.lang.SOTrx;
import de.metas.location.CountryId;
import de.metas.location.ICountryDAO;
import de.metas.pricing.PriceListId;
import de.metas.pricing.PricingSystemId;
import de.metas.pricing.service.IPriceListDAO;
import de.metas.pricing.service.ProductPrices;
import de.metas.pricing.service.impl.PlainPriceListDAO;
import de.metas.product.IProductBL;
import de.metas.product.ProductId;
import de.metas.uom.UomId;
import de.metas.util.Services;
import org.adempiere.ad.callout.annotations.Callout;
import org.adempiere.ad.callout.annotations.Callout.RecursionAvoidanceLevel;
import org.adempiere.ad.callout.annotations.CalloutMethod;
import org.adempiere.ad.callout.spi.IProgramaticCalloutProvider;
import org.adempiere.ad.modelvalidator.annotations.Init;
import org.adempiere.ad.modelvalidator.annotations.Interceptor;
import org.adempiere.ad.modelvalidator.annotations.ModelChange;
import org.adempiere.exceptions.FillMandatoryException;
import org.adempiere.model.CopyRecordFactory;
import org.compiere.model.I_C_Campaign_Price;
import org.compiere.model.I_M_PriceList_Version;
import org.compiere.model.I_M_ProductPrice;
import org.compiere.model.ModelValidator;
import org.springframework.stereotype.Component;

/*
 * #%L
 * de.metas.business
 * %%
 * Copyright (C) 2019 metas GmbH
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

@Interceptor(I_C_Campaign_Price.class)
@Callout(value = I_C_Campaign_Price.class, recursionAvoidanceLevel = RecursionAvoidanceLevel.CalloutClass)
@Component
public class C_Campaign_Price
{
	@Init
	public void init()
	{
		Services.get(IProgramaticCalloutProvider.class).registerAnnotatedCallout(this);
		CopyRecordFactory.enableForTableName(I_C_Campaign_Price.Table_Name);
	}

	@CalloutMethod(columnNames = I_C_Campaign_Price.COLUMNNAME_C_BPartner_ID, skipIfCopying = true)
	public void onBPartnerChanged(final I_C_Campaign_Price record)
	{
		if (record == null)
		{
			return;
		}

		final BPartnerId bpartnerId = BPartnerId.ofRepoIdOrNull(record.getC_BPartner_ID());
		if (bpartnerId == null)
		{
			return;
		}
		updatePricingInfo(record);
		record.setC_BP_Group_ID(-1);
	}

	private static void updatePricingInfo(final I_C_Campaign_Price record)
	{
		final BPartnerId bpartnerId = BPartnerId.ofRepoIdOrNull(record.getC_BPartner_ID());
		if (bpartnerId == null)
		{
			return;
		}
		final IBPartnerDAO bpartnerRepo = Services.get(IBPartnerDAO.class);
		final CountryId countryId = bpartnerRepo.getDefaultShipToLocationCountryIdOrNull(bpartnerId);
		if (countryId != null)
		{
			record.setC_Country_ID(countryId.getRepoId());
			updateCurrency(record, countryId);
		}
		final PricingSystemId pricingSystemId = bpartnerRepo.retrievePricingSystemIdOrNull(bpartnerId, SOTrx.SALES);
		if (pricingSystemId != null)
		{
			record.setM_PricingSystem_ID(pricingSystemId.getRepoId());
			final IPriceListDAO priceListDAO = new PlainPriceListDAO();
			final PriceListId priceListId = priceListDAO.retrievePriceListIdByPricingSyst(pricingSystemId, countryId, SOTrx.SALES);
			if (priceListId != null)
			{
				final I_M_PriceList_Version newestPriceListVersion = priceListDAO.retrieveNewestPriceListVersion(priceListId);
				final ProductId productId = ProductId.ofRepoIdOrNull(record.getM_Product_ID());
				final I_M_ProductPrice productPrice = ProductPrices.retrieveMainProductPriceOrNull(newestPriceListVersion, productId);
				if (productPrice != null)
				{
					record.setC_TaxCategory_ID(productPrice.getC_TaxCategory_ID());
					record.setInvoicableQtyBasedOn(productPrice.getInvoicableQtyBasedOn());
				}
			}
		}
		else {
			record.setM_PricingSystem_ID(-1);
		}
	}

	@CalloutMethod(columnNames = I_C_Campaign_Price.COLUMNNAME_C_BP_Group_ID, skipIfCopying = true)
	public void onBPGroupChanged(final I_C_Campaign_Price record)
	{
		if (record == null)
		{
			return;
		}
		final BPGroupId bpGroupId = BPGroupId.ofRepoIdOrNull(record.getC_BP_Group_ID());
		if (bpGroupId == null)
		{
			return;
		}

		record.setC_BPartner_ID(-1);
	}

	@CalloutMethod(columnNames = I_C_Campaign_Price.COLUMNNAME_C_Country_ID, skipIfCopying = true)
	public void onCountryChanged(final I_C_Campaign_Price record)
	{
		final CountryId countryId = CountryId.ofRepoIdOrNull(record.getC_Country_ID());
		if (countryId == null)
		{
			return;
		}

		updateCurrency(record, countryId);
	}

	private static void updateCurrency(final I_C_Campaign_Price record, final CountryId countryId)
	{
		final ICountryDAO countriesRepo = Services.get(ICountryDAO.class);
		countriesRepo.getCountryCurrencyId(countryId).ifPresent(currencyId -> record.setC_Currency_ID(currencyId.getRepoId()));
	}

	@CalloutMethod(columnNames = I_C_Campaign_Price.COLUMNNAME_M_Product_ID, skipIfCopying = true)
	public void onProductChanged(final I_C_Campaign_Price record)
	{
		final ProductId productId = ProductId.ofRepoIdOrNull(record.getM_Product_ID());
		if (productId == null)
		{
			return;
		}

		final UomId stockUomId = Services.get(IProductBL.class).getStockUOMId(productId);
		record.setC_UOM_ID(stockUomId.getRepoId());
		updatePricingInfo(record);
	}

	@ModelChange(timings = { ModelValidator.TYPE_BEFORE_NEW, ModelValidator.TYPE_BEFORE_CHANGE }, ifColumnsChanged = { I_C_Campaign_Price.COLUMNNAME_C_BPartner_ID, I_C_Campaign_Price.COLUMNNAME_C_BP_Group_ID, I_C_Campaign_Price.COLUMNNAME_M_PricingSystem_ID })
	public void beforeSave(final I_C_Campaign_Price record)
	{
		if (record.getC_BPartner_ID() <= 0 && record.getC_BP_Group_ID() <= 0 && record.getM_PricingSystem_ID() <= 0)
		{
			throw new FillMandatoryException(I_C_Campaign_Price.COLUMNNAME_C_BP_Group_ID);
		}
	}
}
