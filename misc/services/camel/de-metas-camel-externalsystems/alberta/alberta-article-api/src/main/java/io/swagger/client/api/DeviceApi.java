/*
 * Artikel - Warenwirtschaft (Basis)
 * Synchronisation der Artikel mit Kumavision
 *
 * OpenAPI spec version: 1.0.2
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.client.api;

import io.swagger.client.ApiCallback;
import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.ApiResponse;
import io.swagger.client.Configuration;
import io.swagger.client.Pair;
import io.swagger.client.ProgressRequestBody;
import io.swagger.client.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import io.swagger.client.model.DeviceMapping;
import io.swagger.client.model.DeviceToCreate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeviceApi {
    private ApiClient apiClient;

    public DeviceApi() {
        this(Configuration.getDefaultApiClient());
    }

    public DeviceApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Build call for postDevice
     * @param albertaApiKey  (required)
     * @param body device to create (optional)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call postDeviceCall(String albertaApiKey, DeviceToCreate body, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = body;
        
        // create path and map variables
        String localVarPath = "/device";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        if (albertaApiKey != null)
        localVarHeaderParams.put("alberta-api-key", apiClient.parameterToString(albertaApiKey));

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call postDeviceValidateBeforeCall(String albertaApiKey, DeviceToCreate body, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        // verify the required parameter 'albertaApiKey' is set
        if (albertaApiKey == null) {
            throw new ApiException("Missing the required parameter 'albertaApiKey' when calling postDevice(Async)");
        }
        
        com.squareup.okhttp.Call call = postDeviceCall(albertaApiKey, body, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Gerätedaten nach Alberta übertragen
     * legt Geräte in Alberta an
     * @param albertaApiKey  (required)
     * @param body device to create (optional)
     * @return DeviceMapping
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public DeviceMapping postDevice(String albertaApiKey, DeviceToCreate body) throws ApiException {
        ApiResponse<DeviceMapping> resp = postDeviceWithHttpInfo(albertaApiKey, body);
        return resp.getData();
    }

    /**
     * Gerätedaten nach Alberta übertragen
     * legt Geräte in Alberta an
     * @param albertaApiKey  (required)
     * @param body device to create (optional)
     * @return ApiResponse&lt;DeviceMapping&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<DeviceMapping> postDeviceWithHttpInfo(String albertaApiKey, DeviceToCreate body) throws ApiException {
        com.squareup.okhttp.Call call = postDeviceValidateBeforeCall(albertaApiKey, body, null, null);
        Type localVarReturnType = new TypeToken<DeviceMapping>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Gerätedaten nach Alberta übertragen (asynchronously)
     * legt Geräte in Alberta an
     * @param albertaApiKey  (required)
     * @param body device to create (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call postDeviceAsync(String albertaApiKey, DeviceToCreate body, final ApiCallback<DeviceMapping> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = postDeviceValidateBeforeCall(albertaApiKey, body, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<DeviceMapping>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for putDevice
     * @param albertaApiKey  (required)
     * @param _id  (required)
     * @param body device to create (optional)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call putDeviceCall(String albertaApiKey, String _id, DeviceToCreate body, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = body;
        
        // create path and map variables
        String localVarPath = "/device/{_id}"
            .replaceAll("\\{" + "_id" + "\\}", apiClient.escapeString(_id.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        if (albertaApiKey != null)
        localVarHeaderParams.put("alberta-api-key", apiClient.parameterToString(albertaApiKey));

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "PUT", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call putDeviceValidateBeforeCall(String albertaApiKey, String _id, DeviceToCreate body, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        // verify the required parameter 'albertaApiKey' is set
        if (albertaApiKey == null) {
            throw new ApiException("Missing the required parameter 'albertaApiKey' when calling putDevice(Async)");
        }
        // verify the required parameter '_id' is set
        if (_id == null) {
            throw new ApiException("Missing the required parameter '_id' when calling putDevice(Async)");
        }
        
        com.squareup.okhttp.Call call = putDeviceCall(albertaApiKey, _id, body, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Gerätedaten in Alberta ändern
     * ändert Geräte in Alberta
     * @param albertaApiKey  (required)
     * @param _id  (required)
     * @param body device to create (optional)
     * @return DeviceMapping
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public DeviceMapping putDevice(String albertaApiKey, String _id, DeviceToCreate body) throws ApiException {
        ApiResponse<DeviceMapping> resp = putDeviceWithHttpInfo(albertaApiKey, _id, body);
        return resp.getData();
    }

    /**
     * Gerätedaten in Alberta ändern
     * ändert Geräte in Alberta
     * @param albertaApiKey  (required)
     * @param _id  (required)
     * @param body device to create (optional)
     * @return ApiResponse&lt;DeviceMapping&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<DeviceMapping> putDeviceWithHttpInfo(String albertaApiKey, String _id, DeviceToCreate body) throws ApiException {
        com.squareup.okhttp.Call call = putDeviceValidateBeforeCall(albertaApiKey, _id, body, null, null);
        Type localVarReturnType = new TypeToken<DeviceMapping>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Gerätedaten in Alberta ändern (asynchronously)
     * ändert Geräte in Alberta
     * @param albertaApiKey  (required)
     * @param _id  (required)
     * @param body device to create (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call putDeviceAsync(String albertaApiKey, String _id, DeviceToCreate body, final ApiCallback<DeviceMapping> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = putDeviceValidateBeforeCall(albertaApiKey, _id, body, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<DeviceMapping>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
}
