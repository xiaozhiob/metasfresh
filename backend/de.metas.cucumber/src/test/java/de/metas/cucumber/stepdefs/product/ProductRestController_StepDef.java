/*
 * #%L
 * de.metas.cucumber
 * %%
 * Copyright (C) 2022 metas GmbH
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

package de.metas.cucumber.stepdefs.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.metas.JsonObjectMapperHolder;
import de.metas.common.product.v2.response.JsonGetProductsResponse;
import de.metas.common.product.v2.response.JsonProduct;
import de.metas.common.product.v2.response.JsonProductBPartner;
import de.metas.common.util.Check;
import de.metas.cucumber.stepdefs.DataTableUtil;
import de.metas.cucumber.stepdefs.StepDefConstants;
import de.metas.cucumber.stepdefs.StepDefData;
import de.metas.cucumber.stepdefs.context.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import lombok.NonNull;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_C_BPartner_Product;
import org.compiere.model.I_C_UOM;
import org.compiere.model.I_M_Product;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ProductRestController_StepDef
{
	private static final String BPARTNER_PRODUCT_RESPONSE_PATH = "bpartners.";

	private final TestContext testContext;
	private final StepDefData<I_M_Product> productTable;
	private final StepDefData<I_C_BPartner> bPartnerTable;

	public ProductRestController_StepDef(
			@NonNull final TestContext testContext,
			@NonNull final StepDefData<I_M_Product> productTable,
			@NonNull final StepDefData<I_C_BPartner> bPartnerTable)
	{
		this.testContext = testContext;
		this.productTable = productTable;
		this.bPartnerTable = bPartnerTable;
	}

	@Then("validate get products response")
	public void verify_getProducts_response_v2(@NonNull final DataTable dataTable) throws JsonProcessingException
	{
		final List<Map<String, String>> productTableList = dataTable.asMaps();
		for (final Map<String, String> dataTableRow : productTableList)
		{
			verifyGetProductsResponseV2(dataTableRow);
		}
	}

	private void verifyGetProductsResponseV2(@NonNull final Map<String, String> row) throws JsonProcessingException
	{
		final String value = DataTableUtil.extractStringForColumnName(row, I_M_Product.COLUMNNAME_Value);
		final String name = DataTableUtil.extractStringForColumnName(row, I_M_Product.COLUMNNAME_Name);
		final String x12de355Code = DataTableUtil.extractStringForColumnName(row, I_C_UOM.COLUMNNAME_UOMSymbol);
		final String ean = DataTableUtil.extractStringForColumnName(row, I_M_Product.COLUMNNAME_UPC);
		final String description = DataTableUtil.extractStringForColumnName(row, I_M_Product.COLUMNNAME_Description);

		final String productIdentifier = DataTableUtil.extractStringForColumnName(row, I_M_Product.COLUMNNAME_M_Product_ID + "." + StepDefConstants.TABLECOLUMN_IDENTIFIER);
		final I_M_Product productRecord = productTable.get(productIdentifier);

		final JsonGetProductsResponse jsonGetProductsResponse = JsonObjectMapperHolder.sharedJsonObjectMapper().readValue(testContext.getApiResponse().getContent(), JsonGetProductsResponse.class);

		final JsonProduct returnedProduct = jsonGetProductsResponse.getProducts().stream()
				.filter(product -> product.getId().getValue() == productRecord.getM_Product_ID())
				.findFirst()
				.orElse(null);

		assertThat(returnedProduct).isNotNull();

		assertThat(returnedProduct.getProductNo()).isEqualTo(value);
		assertThat(returnedProduct.getName()).isEqualTo(name);
		assertThat(returnedProduct.getUom()).isEqualTo(x12de355Code);
		assertThat(returnedProduct.getEan()).isEqualTo(ean);
		assertThat(returnedProduct.getDescription()).isEqualTo(description);

		final JsonProductBPartner bpartnerProduct = Check.singleElement(returnedProduct.getBpartners());

		verifyBPartnerProduct(bpartnerProduct, row);
	}

	private void verifyBPartnerProduct(@NonNull final JsonProductBPartner returnedProductBPartner, @NonNull final Map<String, String> row)
	{
		final String productNo = DataTableUtil.extractStringForColumnName(row, BPARTNER_PRODUCT_RESPONSE_PATH + I_C_BPartner_Product.COLUMNNAME_ProductNo);
		final boolean isExcludedFromSale = DataTableUtil.extractBooleanForColumnName(row, BPARTNER_PRODUCT_RESPONSE_PATH + I_C_BPartner_Product.COLUMNNAME_IsExcludedFromSale);
		final String exclusionFromSaleReason = DataTableUtil.extractStringOrNullForColumnName(row, BPARTNER_PRODUCT_RESPONSE_PATH + I_C_BPartner_Product.COLUMNNAME_ExclusionFromSaleReason);
		final boolean isExcludedFromPurchase = DataTableUtil.extractBooleanForColumnName(row, BPARTNER_PRODUCT_RESPONSE_PATH + I_C_BPartner_Product.COLUMNNAME_IsExcludedFromPurchase);
		final String exclusionFromPurchaseReason = DataTableUtil.extractStringOrNullForColumnName(row, BPARTNER_PRODUCT_RESPONSE_PATH + I_C_BPartner_Product.COLUMNNAME_ExclusionFromPurchaseReason);

		final String bpartnerIdentifier = DataTableUtil.extractStringForColumnName(row, I_C_BPartner.COLUMNNAME_C_BPartner_ID + "." + StepDefConstants.TABLECOLUMN_IDENTIFIER);
		final I_C_BPartner bPartnerRecord = bPartnerTable.get(bpartnerIdentifier);

		assertThat(returnedProductBPartner.getBpartnerId().getValue()).isEqualTo(bPartnerRecord.getC_BPartner_ID());
		assertThat(returnedProductBPartner.getProductNo()).isEqualTo(productNo);
		assertThat(returnedProductBPartner.isExcludedFromSale()).isEqualTo(isExcludedFromSale);
		assertThat(returnedProductBPartner.getExclusionFromSaleReason()).isEqualTo(exclusionFromSaleReason);
		assertThat(returnedProductBPartner.isExcludedFromPurchase()).isEqualTo(isExcludedFromPurchase);
		assertThat(returnedProductBPartner.getExclusionFromPurchaseReason()).isEqualTo(exclusionFromPurchaseReason);
	}
}