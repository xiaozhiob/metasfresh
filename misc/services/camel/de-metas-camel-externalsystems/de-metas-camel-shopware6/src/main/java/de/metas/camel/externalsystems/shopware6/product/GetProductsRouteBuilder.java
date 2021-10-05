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

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import de.metas.camel.externalsystems.common.ExternalSystemCamelConstants;
import de.metas.camel.externalsystems.common.PInstanceLogger;
import de.metas.camel.externalsystems.common.ProcessLogger;
import de.metas.camel.externalsystems.common.ProcessorHelper;
import de.metas.camel.externalsystems.shopware6.api.ShopwareClient;
import de.metas.camel.externalsystems.shopware6.product.processor.ProductUpsertProcessor;
import de.metas.camel.externalsystems.shopware6.product.processor.GetProductsProcessor;
import de.metas.camel.externalsystems.shopware6.product.processor.ProductPriceProcessor;
import de.metas.camel.externalsystems.shopware6.unit.GetUnitsRequest;
import de.metas.camel.externalsystems.shopware6.unit.UOMInfoProvider;
import de.metas.common.externalsystem.ExternalSystemConstants;
import de.metas.common.externalsystem.JsonESRuntimeParameterUpsertRequest;
import de.metas.common.externalsystem.JsonExternalSystemRequest;
import de.metas.common.externalsystem.JsonRuntimeParameterUpsertItem;
import de.metas.common.util.CoalesceUtil;
import lombok.NonNull;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.LoggingLevel;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.time.Instant;

import static de.metas.camel.externalsystems.common.ExternalSystemCamelConstants.HEADER_PINSTANCE_ID;
import static de.metas.camel.externalsystems.common.ExternalSystemCamelConstants.MF_ERROR_ROUTE_ID;
import static de.metas.camel.externalsystems.common.ExternalSystemCamelConstants.MF_PRICE_LIST_UPSERT_PRODUCT_PRICE_V2_CAMEL_URI;
import static de.metas.camel.externalsystems.common.ExternalSystemCamelConstants.MF_UPSERT_PRODUCT_V2_CAMEL_URI;
import static de.metas.camel.externalsystems.shopware6.Shopware6Constants.ROUTE_PROPERTY_IMPORT_PRODUCTS_CONTEXT;
import static de.metas.camel.externalsystems.shopware6.unit.GetUnitsRouteBuilder.GET_UOM_MAPPINGS_ROUTE_ID;
import static de.metas.common.externalsystem.ExternalSystemConstants.PARAM_UPDATED_AFTER;
import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.direct;

@Component
public class GetProductsRouteBuilder extends RouteBuilder
{
	public static final String GET_PRODUCTS_ROUTE_ID = "Shopware6-getProducts";
	public static final String PROCESS_PRODUCT_ROUTE_ID = "Shopware6-processProduct";
	public static final String UPSERT_RUNTIME_PARAMS_ROUTE_ID = "Shopware6-getProducts-upsertRuntimeParams";
	public static final String UPSERT_PRODUCT_PRICE_ROUTE_ID = "Shopware6-getProducts-upsertProductPrice";

	public static final String GET_PRODUCTS_PROCESSOR_ID = "Shopware6-Products-getProductsProcessorId";
	public static final String UPSERT_PRODUCT_PROCESSOR_ID = "Shopware6-Products-upsertProductProcessorId";
	public static final String UPSERT_PRODUCT_PRICE_PROCESSOR_ID = "Shopware6-Products-upsertProductPriceProcessorId";
	public static final String UPSERT_RUNTIME_PARAMS_PROCESSOR_ID = "Shopware6-Products-upsertRuntimeParamsProcessorId";
	public static final String ATTACH_CONTEXT_PROCESSOR_ID = "Shopware6-Products-contextProcessorId";

	private final ProcessLogger processLogger;
	private final ProducerTemplate producerTemplate;

	public GetProductsRouteBuilder(
			@NonNull final ProcessLogger processLogger,
			@NonNull final ProducerTemplate producerTemplate)
	{
		this.processLogger = processLogger;
		this.producerTemplate = producerTemplate;
	}

	@Override
	public void configure() throws Exception
	{
		errorHandler(defaultErrorHandler());
		onException(Exception.class)
				.to(direct(MF_ERROR_ROUTE_ID));

		//@formatter:off
		from(direct(GET_PRODUCTS_ROUTE_ID))
				.routeId(GET_PRODUCTS_ROUTE_ID)
				.log("Route invoked")
				.streamCaching()

				.process(this::buildAndAttachRouteContext).id(ATTACH_CONTEXT_PROCESSOR_ID)

				.process(new GetProductsProcessor()).id(GET_PRODUCTS_PROCESSOR_ID)
				.choice()
					.when(body().isNull())
						.log(LoggingLevel.INFO, "Nothing to do! No new or updated Products!")
					.otherwise()
						.split(body())
							.to(direct(PROCESS_PRODUCT_ROUTE_ID))
						.end()
				.endChoice()
				.to(direct(UPSERT_RUNTIME_PARAMS_ROUTE_ID))
				.process((exchange) -> processLogger.logMessage("Shopware6:GetProducts process ended!" + Instant.now(),
															exchange.getIn().getHeader(HEADER_PINSTANCE_ID, Integer.class)));

		from(direct(PROCESS_PRODUCT_ROUTE_ID))
				.routeId(PROCESS_PRODUCT_ROUTE_ID)
				.log("Route invoked")
				.process(new ProductUpsertProcessor(processLogger)).id(UPSERT_PRODUCT_PROCESSOR_ID)
				.choice()
					.when(body().isNull())
						.log(LoggingLevel.INFO, "Nothing to do ! Product was skipped !")
					.otherwise()
						.log(LoggingLevel.DEBUG, "Calling metasfresh-api to upsert Product: ${body}")
						.to(direct(MF_UPSERT_PRODUCT_V2_CAMEL_URI))
						.to(direct(UPSERT_PRODUCT_PRICE_ROUTE_ID))
				.endChoice()
		.end();

		from(direct(UPSERT_PRODUCT_PRICE_ROUTE_ID))
				.routeId(UPSERT_PRODUCT_PRICE_ROUTE_ID)
				.log("Route invoked")
				.process(new ProductPriceProcessor(processLogger)).id(UPSERT_PRODUCT_PRICE_PROCESSOR_ID)
				.choice()
					.when(body().isNull())
						.log(LoggingLevel.INFO, "Nothing to do ! Product price was skipped!")
					.otherwise()
						.log(LoggingLevel.DEBUG, "Calling metasfresh-api to upsert Product price: ${body}")
						.to(direct(MF_PRICE_LIST_UPSERT_PRODUCT_PRICE_V2_CAMEL_URI))
				.endChoice()
		.end();

		from(direct(UPSERT_RUNTIME_PARAMS_ROUTE_ID))
				.routeId(UPSERT_RUNTIME_PARAMS_ROUTE_ID)
				.log("Route invoked")
				.process(this::prepareUpsertRuntimeParameterRequest).id(UPSERT_RUNTIME_PARAMS_PROCESSOR_ID)
				.choice()
					.when(body().isNull())
						.log(LoggingLevel.DEBUG, "Nothing to do! No runtime parameters to insert!")
					.otherwise()
						.log(LoggingLevel.DEBUG, "Calling metasfresh-api to upsert runtime parameters: ${body}")
						.to(direct(ExternalSystemCamelConstants.MF_UPSERT_RUNTIME_PARAMETERS_ROUTE_ID))
				.endChoice()
		.end();
	}

	@VisibleForTesting
	public void buildAndAttachRouteContext(@NonNull final Exchange exchange)
	{
		final JsonExternalSystemRequest request = exchange.getIn().getBody(JsonExternalSystemRequest.class);

		if (request.getAdPInstanceId() != null)
		{
			exchange.getIn().setHeader(HEADER_PINSTANCE_ID, request.getAdPInstanceId().getValue());

			processLogger.logMessage("Shopware6:GetProducts process started!" + Instant.now(), request.getAdPInstanceId().getValue());
		}

		final String clientId = request.getParameters().get(ExternalSystemConstants.PARAM_CLIENT_ID);
		final String clientSecret = request.getParameters().get(ExternalSystemConstants.PARAM_CLIENT_SECRET);
		final String basePath = request.getParameters().get(ExternalSystemConstants.PARAM_BASE_PATH);

		final PInstanceLogger pInstanceLogger = PInstanceLogger.builder()
				.processLogger(processLogger)
				.pInstanceId(request.getAdPInstanceId())
				.build();

		final ShopwareClient shopwareClient = ShopwareClient.of(clientId, clientSecret, basePath, pInstanceLogger);

		final String updatedAfter = CoalesceUtil.coalesceNotNull(
				request.getParameters().get(ExternalSystemConstants.PARAM_UPDATED_AFTER_OVERRIDE),
				request.getParameters().get(ExternalSystemConstants.PARAM_UPDATED_AFTER),
				Instant.ofEpochSecond(0).toString());

		final GetUnitsRequest getUnitsRequest = GetUnitsRequest.builder()
				.baseUrl(basePath)
				.clientId(clientId)
				.clientSecret(clientSecret)
				.build();

		final UOMInfoProvider uomInfoProvider = (UOMInfoProvider) producerTemplate
				.sendBody("direct:" + GET_UOM_MAPPINGS_ROUTE_ID, ExchangePattern.InOut, getUnitsRequest);

		final ImportProductsRouteContext productsContext = ImportProductsRouteContext.builder()
				.shopwareClient(shopwareClient)
				.externalSystemRequest(request)
				.orgCode(request.getOrgCode())
				.nextImportStartingTimestamp(Instant.parse(updatedAfter))
				.shopwareUomInfoProvider(uomInfoProvider)
				.uomMappings(GetProductsRouteHelper.getUOMMappingRules(request))
				.taxCategoryProvider(GetProductsRouteHelper.getTaxCategoryProvider(request))
				.build();

		exchange.setProperty(ROUTE_PROPERTY_IMPORT_PRODUCTS_CONTEXT, productsContext);
	}

	private void prepareUpsertRuntimeParameterRequest(@NonNull final Exchange exchange)
	{
		final ImportProductsRouteContext context = ProcessorHelper.getPropertyOrThrowError(exchange, ROUTE_PROPERTY_IMPORT_PRODUCTS_CONTEXT, ImportProductsRouteContext.class);

		final JsonRuntimeParameterUpsertItem runtimeParameterUpsertItem = JsonRuntimeParameterUpsertItem.builder()
				.externalSystemParentConfigId(context.getExternalSystemRequest().getExternalSystemConfigId())
				.name(PARAM_UPDATED_AFTER)
				.request(context.getExternalSystemRequest().getCommand())
				.value(context.getNextImportStartingTimestamp().toString())
				.build();

		final JsonESRuntimeParameterUpsertRequest request = JsonESRuntimeParameterUpsertRequest.builder()
				.runtimeParameterUpsertItems(ImmutableList.of(runtimeParameterUpsertItem))
				.build();

		exchange.getIn().setBody(request);
	}
}
