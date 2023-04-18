/*
 * Selling Partner API for Orders
 * The Selling Partner API for Orders helps you programmatically retrieve order information. These APIs let you develop fast, flexible, custom applications in areas like order synchronization, order research, and demand-based decision support tools.
 *
 * OpenAPI spec version: v0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.adekia.exchange.amazonsp.client.orders.api;

import com.adekia.exchange.amazonsp.client.orders.model.GetOrderAddressResponse;
import com.adekia.exchange.amazonsp.client.orders.model.GetOrderBuyerInfoResponse;
import com.adekia.exchange.amazonsp.client.orders.model.GetOrderItemsBuyerInfoResponse;
import com.adekia.exchange.amazonsp.client.orders.model.GetOrderItemsResponse;
import com.adekia.exchange.amazonsp.client.orders.model.GetOrderRegulatedInfoResponse;
import com.adekia.exchange.amazonsp.client.orders.model.GetOrderResponse;
import com.adekia.exchange.amazonsp.client.orders.model.GetOrdersResponse;
import com.adekia.exchange.amazonsp.client.orders.model.UpdateVerificationStatusErrorResponse;
import com.adekia.exchange.amazonsp.client.orders.model.UpdateVerificationStatusRequest;
import org.junit.Test;
import org.junit.Ignore;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for OrdersV0Api
 */
@Ignore
public class OrdersV0ApiTest {

    private final OrdersV0Api api = new OrdersV0Api();

    
    /**
     * 
     *
     * Returns the order indicated by the specified order ID.  **Usage Plans:**  | Plan type | Rate (requests per second) | Burst | | ---- | ---- | ---- | |Default| 0.0167 | 20 | |Selling partner specific| Variable | Variable |  The x-amzn-RateLimit-Limit response header returns the usage plan rate limits that were applied to the requested operation. Rate limits for some selling partners will vary from the default rate and burst shown in the table above. For more information, see \&quot;Usage Plans and Rate Limits\&quot; in the Selling Partner API documentation.
     *
     * @throws Exception
     *          if the Api call fails
     */
    @Test
    public void getOrderTest() throws Exception {
        String orderId = "1234";
        GetOrderResponse response = api.getOrder(orderId);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * Returns the shipping address for the specified order.  **Usage Plans:**  | Plan type | Rate (requests per second) | Burst | | ---- | ---- | ---- | |Default| 0.0167 | 20 | |Selling partner specific| Variable | Variable |  The x-amzn-RateLimit-Limit response header returns the usage plan rate limits that were applied to the requested operation. Rate limits for some selling partners will vary from the default rate and burst shown in the table above. For more information, see \&quot;Usage Plans and Rate Limits\&quot; in the Selling Partner API documentation.
     *
     * @throws Exception
     *          if the Api call fails
     */
    @Test
    public void getOrderAddressTest() throws Exception {
        String orderId = "1234";
        GetOrderAddressResponse response = api.getOrderAddress(orderId);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * Returns buyer information for the specified order.  **Usage Plans:**  | Plan type | Rate (requests per second) | Burst | | ---- | ---- | ---- | |Default| 0.0167 | 20 | |Selling partner specific| Variable | Variable |  The x-amzn-RateLimit-Limit response header returns the usage plan rate limits that were applied to the requested operation. Rate limits for some selling partners will vary from the default rate and burst shown in the table above. For more information, see \&quot;Usage Plans and Rate Limits\&quot; in the Selling Partner API documentation.
     *
     * @throws Exception
     *          if the Api call fails
     */
    @Test
    public void getOrderBuyerInfoTest() throws Exception {
        String orderId = "1234";
        GetOrderBuyerInfoResponse response = api.getOrderBuyerInfo(orderId);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * Returns detailed order item information for the order indicated by the specified order ID. If NextToken is provided, it&#39;s used to retrieve the next page of order items.  Note: When an order is in the Pending state (the order has been placed but payment has not been authorized), the getOrderItems operation does not return information about pricing, taxes, shipping charges, gift status or promotions for the order items in the order. After an order leaves the Pending state (this occurs when payment has been authorized) and enters the Unshipped, Partially Shipped, or Shipped state, the getOrderItems operation returns information about pricing, taxes, shipping charges, gift status and promotions for the order items in the order.  **Usage Plans:**  | Plan type | Rate (requests per second) | Burst | | ---- | ---- | ---- | |Default| 0.5 | 30 | |Selling partner specific| Variable | Variable |  The x-amzn-RateLimit-Limit response header returns the usage plan rate limits that were applied to the requested operation. Rate limits for some selling partners will vary from the default rate and burst shown in the table above. For more information, see \&quot;Usage Plans and Rate Limits\&quot; in the Selling Partner API documentation.
     *
     * @throws Exception
     *          if the Api call fails
     */
    @Test
    public void getOrderItemsTest() throws Exception {
        String orderId = "1234";
        String nextToken = null;
        GetOrderItemsResponse response = api.getOrderItems(orderId, nextToken);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * Returns buyer information for the order items in the specified order.  **Usage Plans:**  | Plan type | Rate (requests per second) | Burst | | ---- | ---- | ---- | |Default| 0.5 | 30 | |Selling partner specific| Variable | Variable |  The x-amzn-RateLimit-Limit response header returns the usage plan rate limits that were applied to the requested operation. Rate limits for some selling partners will vary from the default rate and burst shown in the table above. For more information, see \&quot;Usage Plans and Rate Limits\&quot; in the Selling Partner API documentation.
     *
     * @throws Exception
     *          if the Api call fails
     */
    @Test
    public void getOrderItemsBuyerInfoTest() throws Exception {
        String orderId = "1234";
        String nextToken = null;
        GetOrderItemsBuyerInfoResponse response = api.getOrderItemsBuyerInfo(orderId, nextToken);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * Returns regulated information for the order indicated by the specified order ID.  **Usage Plans:**  | Plan type | Rate (requests per second) | Burst | | ---- | ---- | ---- | |Default| 0.5 | 30 | |Selling partner specific| Variable | Variable |  The x-amzn-RateLimit-Limit response header returns the usage plan rate limits that were applied to the requested operation. Rate limits for some selling partners will vary from the default rate and burst shown in the table above. For more information, see \&quot;Usage Plans and Rate Limits\&quot; in the Selling Partner API documentation.
     *
     * @throws Exception
     *          if the Api call fails
     */
    @Test
    public void getOrderRegulatedInfoTest() throws Exception {
        String orderId = "1234";
        GetOrderRegulatedInfoResponse response = api.getOrderRegulatedInfo(orderId);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * Returns orders created or updated during the time frame indicated by the specified parameters. You can also apply a range of filtering criteria to narrow the list of orders returned. If NextToken is present, that will be used to retrieve the orders instead of other criteria.  **Usage Plans:**  | Plan type | Rate (requests per second) | Burst | | ---- | ---- | ---- | |Default| 0.0167 | 20 | |Selling partner specific| Variable | Variable |  The x-amzn-RateLimit-Limit response header returns the usage plan rate limits that were applied to the requested operation. Rate limits for some selling partners will vary from the default rate and burst shown in the table above. For more information, see \&quot;Usage Plans and Rate Limits\&quot; in the Selling Partner API documentation.
     *
     * @throws Exception
     *          if the Api call fails
     */
    @Test
    public void getOrdersTest() throws Exception {
        final List<String> marketplaceIds = Arrays.asList("marketplaceIds_example"); // List<String> | A list of MarketplaceId values. Used to select orders that were placed in the specified marketplaces.  See the [Selling Partner API Developer Guide](doc:marketplace-ids) for a complete list of marketplaceId values.
        final String lastUpdatedAfter = "lastUpdatedAfter_example"; // String | A date used for selecting orders that were last updated after (or at) a specified time. An update is defined as any change in order status, including the creation of a new order. Includes updates made by Amazon and by the seller. The date must be in ISO 8601 format.
        // Technical one
        final Integer maxResultsPerPage = 100; // Integer | A number that indicates the maximum number of orders that can be returned per page. Value must be 1 - 100. Default 100.
        // todo : take this one from parameter of the route ? For the moment we consider only first 100 Orders in time intervalle
        final String nextToken = null; // String | A string token returned in the response of your previous request.
        String createdAfter = null;
        String createdBefore = null;
        String lastUpdatedBefore = null;
        List<String> orderStatuses = null;
        List<String> fulfillmentChannels = null;
        List<String> paymentMethods = null;
        String buyerEmail = null;
        String sellerOrderId = null;
        List<String> easyShipShipmentStatuses = null;
        List<String> amazonOrderIds = null;
        String actualFulfillmentSupplySourceId = null;
        Boolean isISPU = null;
        String storeChainStoreId = null;
        GetOrdersResponse response = api.getOrders(marketplaceIds, createdAfter, createdBefore, lastUpdatedAfter, lastUpdatedBefore, orderStatuses, fulfillmentChannels, paymentMethods, buyerEmail, sellerOrderId, maxResultsPerPage, easyShipShipmentStatuses, nextToken, amazonOrderIds, actualFulfillmentSupplySourceId, isISPU, storeChainStoreId);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * Updates (approves or rejects) the verification status of an order containing regulated products.  **Usage Plans:**  | Plan type | Rate (requests per second) | Burst | | ---- | ---- | ---- | |Default| 0.5 | 30 | |Selling partner specific| Variable | Variable |  The x-amzn-RateLimit-Limit response header returns the usage plan rate limits that were applied to the requested operation. Rate limits for some selling partners will vary from the default rate and burst shown in the table above. For more information, see \&quot;Usage Plans and Rate Limits\&quot; in the Selling Partner API documentation.
     *
     * @throws Exception
     *          if the Api call fails
     */
    @Test
    public void updateVerificationStatusTest() throws Exception {
        String orderId = "1234";
        UpdateVerificationStatusRequest payload = null;
        api.updateVerificationStatus(orderId, payload);

        // TODO: test validations
    }
    
}
