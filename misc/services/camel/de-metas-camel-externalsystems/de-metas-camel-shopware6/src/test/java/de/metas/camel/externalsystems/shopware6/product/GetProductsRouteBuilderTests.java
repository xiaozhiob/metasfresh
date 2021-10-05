/*
 * #%L
 * de-metas-camel-shopware6
 * %%
 * Copyright (C) 2021 metas GmbH
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

package de.metas.camel.externalsystems.shopware6.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.collect.ImmutableMap;
import de.metas.camel.externalsystems.common.ExternalSystemCamelConstants;
import de.metas.camel.externalsystems.common.PInstanceLogger;
import de.metas.camel.externalsystems.common.ProcessLogger;
import de.metas.camel.externalsystems.common.v2.ProductUpsertCamelRequest;
import de.metas.camel.externalsystems.common.v2.UpsertProductPriceList;
import de.metas.camel.externalsystems.shopware6.api.ShopwareClient;
import de.metas.camel.externalsystems.shopware6.api.model.product.JsonProducts;
import de.metas.camel.externalsystems.shopware6.unit.UOMInfoProvider;
import de.metas.common.externalsystem.ExternalSystemConstants;
import de.metas.common.externalsystem.JsonESRuntimeParameterUpsertRequest;
import de.metas.common.externalsystem.JsonExternalSystemRequest;
import lombok.Getter;
import lombok.NonNull;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Properties;
import java.util.stream.Collectors;

import static de.metas.camel.externalsystems.common.ExternalSystemCamelConstants.MF_PRICE_LIST_UPSERT_PRODUCT_PRICE_V2_CAMEL_URI;
import static de.metas.camel.externalsystems.common.ExternalSystemCamelConstants.MF_UPSERT_PRODUCT_V2_CAMEL_URI;
import static de.metas.camel.externalsystems.shopware6.Shopware6Constants.ROUTE_PROPERTY_IMPORT_PRODUCTS_CONTEXT;
import static de.metas.camel.externalsystems.shopware6.ShopwareTestConstants.MOCK_UNIT_CODE;
import static de.metas.camel.externalsystems.shopware6.ShopwareTestConstants.MOCK_UNIT_ID;
import static de.metas.camel.externalsystems.shopware6.product.GetProductsRouteBuilder.ATTACH_CONTEXT_PROCESSOR_ID;
import static de.metas.camel.externalsystems.shopware6.product.GetProductsRouteBuilder.GET_PRODUCTS_ROUTE_ID;
import static de.metas.camel.externalsystems.shopware6.product.GetProductsRouteBuilder.PROCESS_PRODUCT_ROUTE_ID;
import static de.metas.camel.externalsystems.shopware6.product.GetProductsRouteBuilder.UPSERT_PRODUCT_PRICE_ROUTE_ID;
import static de.metas.camel.externalsystems.shopware6.product.GetProductsRouteBuilder.UPSERT_RUNTIME_PARAMS_ROUTE_ID;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

public class GetProductsRouteBuilderTests extends CamelTestSupport
{
	private static final String MOCK_UPSERT_PRODUCT = "mock:UpsertProduct";
	private static final String MOCK_UPSERT_PRODUCT_PRICE = "mock:UpsertProductPrice";
	private static final String MOCK_UPSERT_RUNTIME_PARAMETERS = "mock:upsertRuntimeParams";

	private static final String JSON_EXTERNAL_SYSTEM_REQUEST = "10_JsonExternalSystemRequest.json";
	private static final String JSON_PRODUCTS_RESOURCE_PATH = "20_JsonProducts.json";
	private static final String JSON_UPSERT_PRODUCT_REQUEST = "30_CamelUpsertProductRequest.json";
	private static final String JSON_UPSERT_PRODUCT_PRICE_REQUEST = "40_UpsertProductPriceList.json";
	private static final String JSON_UPSERT_RUNTIME_PARAMS_REQUEST = "50_JsonESRuntimeParameterUpsertRequest.json";

	private final static ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

	@Override
	protected Properties useOverridePropertiesWithPropertiesComponent()
	{
		final var properties = new Properties();
		try
		{
			properties.load(GetProductsRouteBuilderTests.class.getClassLoader().getResourceAsStream("application.properties"));
			return properties;
		}
		catch (final IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	@Override
	protected RouteBuilder createRouteBuilder()
	{
		final ProcessLogger processLogger = Mockito.mock(ProcessLogger.class);
		final ProducerTemplate producerTemplate = Mockito.mock(ProducerTemplate.class);
		return new GetProductsRouteBuilder(processLogger, producerTemplate);
	}

	@Override
	public boolean isUseAdviceWith()
	{
		return true;
	}

	@Test
	void happyFlow() throws Exception
	{
		final MockUpsertProductProcessor mockUpsertProductProcessor = new MockUpsertProductProcessor();
		final MockUpsertPriceProcessor mockUpsertPriceProcessor = new MockUpsertPriceProcessor();
		final MockSuccessfullyUpsertRuntimeParamsProcessor runtimeParamsProcessor = new MockSuccessfullyUpsertRuntimeParamsProcessor();

		//given
		prepareRouteForTesting(mockUpsertProductProcessor,
							   runtimeParamsProcessor,
							   mockUpsertPriceProcessor);

		context.start();

		final InputStream expectedUpsertProductRequestIS = this.getClass().getResourceAsStream(JSON_UPSERT_PRODUCT_REQUEST);
		final MockEndpoint productMockEndpoint = getMockEndpoint(MOCK_UPSERT_PRODUCT);
		productMockEndpoint.expectedBodiesReceived(mapper.readValue(expectedUpsertProductRequestIS, ProductUpsertCamelRequest.class));

		final InputStream expectedUpsertProductPriceRequestIS = this.getClass().getResourceAsStream(JSON_UPSERT_PRODUCT_PRICE_REQUEST);
		final MockEndpoint productPriceMockEndpoint = getMockEndpoint(MOCK_UPSERT_PRODUCT_PRICE);
		productPriceMockEndpoint.expectedBodiesReceived(mapper.readValue(expectedUpsertProductPriceRequestIS, UpsertProductPriceList.class));

		final InputStream expectedRuntimeParamIS = this.getClass().getResourceAsStream(JSON_UPSERT_RUNTIME_PARAMS_REQUEST);
		final MockEndpoint mockUpsertRuntimeParamEP = getMockEndpoint(MOCK_UPSERT_RUNTIME_PARAMETERS);
		mockUpsertRuntimeParamEP.expectedBodiesReceived(mapper.readValue(expectedRuntimeParamIS, JsonESRuntimeParameterUpsertRequest.class));

		final InputStream invokeExternalSystemRequestIS = this.getClass().getResourceAsStream(JSON_EXTERNAL_SYSTEM_REQUEST);
		final JsonExternalSystemRequest invokeExternalSystemRequest = mapper
				.readValue(invokeExternalSystemRequestIS, JsonExternalSystemRequest.class);
		//when
		template.sendBody("direct:" + GetProductsRouteBuilder.GET_PRODUCTS_ROUTE_ID, invokeExternalSystemRequest);

		//then
		assertMockEndpointsSatisfied();
		assertThat(mockUpsertProductProcessor.called).isEqualTo(1);
		assertThat(mockUpsertPriceProcessor.called).isEqualTo(1);
		assertThat(runtimeParamsProcessor.called).isEqualTo(1);
	}

	private void prepareRouteForTesting(final MockUpsertProductProcessor mockUpsertProductProcessor,
			final MockSuccessfullyUpsertRuntimeParamsProcessor runtimeParamsProcessor,
			final MockUpsertPriceProcessor mockUpsertPriceProcessor) throws Exception
	{
		AdviceWith.adviceWith(context, GET_PRODUCTS_ROUTE_ID,
							  advice -> advice.weaveById(ATTACH_CONTEXT_PROCESSOR_ID)
									  .replace()
									  .process(new BuildContextMockProcessor()));

		AdviceWith.adviceWith(context, PROCESS_PRODUCT_ROUTE_ID,
							  advice -> advice.interceptSendToEndpoint("direct:" + MF_UPSERT_PRODUCT_V2_CAMEL_URI)
									  .skipSendToOriginalEndpoint()
									  .to(MOCK_UPSERT_PRODUCT)
									  .process(mockUpsertProductProcessor));

		AdviceWith.adviceWith(context, UPSERT_PRODUCT_PRICE_ROUTE_ID,
							  advice -> advice.interceptSendToEndpoint("direct:" + MF_PRICE_LIST_UPSERT_PRODUCT_PRICE_V2_CAMEL_URI)
									  .skipSendToOriginalEndpoint()
									  .to(MOCK_UPSERT_PRODUCT_PRICE)
									  .process(mockUpsertPriceProcessor));

		AdviceWith.adviceWith(context, UPSERT_RUNTIME_PARAMS_ROUTE_ID,
							  advice -> advice.interceptSendToEndpoint("direct:" + ExternalSystemCamelConstants.MF_UPSERT_RUNTIME_PARAMETERS_ROUTE_ID)
									  .skipSendToOriginalEndpoint()
									  .to(MOCK_UPSERT_RUNTIME_PARAMETERS)
									  .process(runtimeParamsProcessor));
	}

	public static class BuildContextMockProcessor implements Processor
	{
		@Override
		public void process(final Exchange exchange) throws JsonProcessingException
		{
			final JsonExternalSystemRequest request = exchange.getIn().getBody(JsonExternalSystemRequest.class);

			final ShopwareClient shopwareClient = prepareShopwareClientMock();

			final String updatedAfter = request.getParameters().get(ExternalSystemConstants.PARAM_UPDATED_AFTER);

			final UOMInfoProvider shopwareUomInfoProvider = UOMInfoProvider.builder()
					.unitId2Code(ImmutableMap.of(MOCK_UNIT_ID, MOCK_UNIT_CODE))
					.build();

			final ImportProductsRouteContext productsContext = ImportProductsRouteContext.builder()
					.shopwareClient(shopwareClient)
					.externalSystemRequest(request)
					.orgCode(request.getOrgCode())
					.nextImportStartingTimestamp(Instant.parse(updatedAfter))
					.shopwareUomInfoProvider(shopwareUomInfoProvider)
					.uomMappings(GetProductsRouteHelper.getUOMMappingRules(request))
					.taxCategoryProvider(GetProductsRouteHelper.getTaxCategoryProvider(request))
					.build();

			exchange.setProperty(ROUTE_PROPERTY_IMPORT_PRODUCTS_CONTEXT, productsContext);
		}

		@NonNull
		private ShopwareClient prepareShopwareClientMock() throws JsonProcessingException
		{
			final ProcessLogger processLogger = Mockito.mock(ProcessLogger.class);
			final PInstanceLogger pInstanceLogger = PInstanceLogger.of(processLogger);

			final ShopwareClient dumbShopwareClient = ShopwareClient.of("does", "not", "https://www.matter.com", pInstanceLogger);
			final ShopwareClient shopwareClientSpy = Mockito.spy(dumbShopwareClient);

			Mockito.doNothing().when(shopwareClientSpy).refreshTokenIfExpired();

			final String productsResponse = loadAsString(JSON_PRODUCTS_RESOURCE_PATH);

			Mockito.doReturn(ResponseEntity.ok(mapper.readValue(productsResponse, JsonProducts.class)))
					.when(shopwareClientSpy)
					.performWithRetry(any(), eq(HttpMethod.POST), eq(JsonProducts.class), any());

			return shopwareClientSpy;
		}
	}

	private static String loadAsString(@NonNull final String name)
	{
		final InputStream inputStream = GetProductsRouteBuilderTests.class.getResourceAsStream(name);
		return new BufferedReader(
				new InputStreamReader(inputStream, StandardCharsets.UTF_8))
				.lines()
				.collect(Collectors.joining("\n"));
	}

	private static class MockUpsertProductProcessor implements Processor
	{
		@Getter
		private int called = 0;

		@Override
		public void process(final Exchange exchange)
		{
			called++;
		}
	}

	private static class MockUpsertPriceProcessor implements Processor
	{
		@Getter
		private int called = 0;

		@Override
		public void process(final Exchange exchange)
		{
			called++;
		}
	}

	private static class MockSuccessfullyUpsertRuntimeParamsProcessor implements Processor
	{
		@Getter
		private int called = 0;

		@Override
		public void process(final Exchange exchange)
		{
			called++;
		}
	}
}
