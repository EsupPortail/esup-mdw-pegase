/*
 * Swagger Gestion - INS
 * Il s'agit de l'API de gestion - INS.
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.api.insgestion;

import fr.univlorraine.pegase.api.ApiCallback;
import fr.univlorraine.pegase.api.ApiClient;
import fr.univlorraine.pegase.api.ApiException;
import fr.univlorraine.pegase.api.ApiResponse;
import fr.univlorraine.pegase.api.Configuration;
import fr.univlorraine.pegase.api.Pair;
import fr.univlorraine.pegase.api.ProgressRequestBody;
import fr.univlorraine.pegase.api.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;



import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthzDemoApi {
    private ApiClient localVarApiClient;

    public AuthzDemoApi() {
        this(Configuration.getDefaultApiClient());
    }

    public AuthzDemoApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for authZDemo
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> la réponse : ok </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> forbidden </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call authZDemoCall(String codeStructure, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/gestion/authz-demo";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (codeStructure != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("codeStructure", codeStructure));
        }

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "idTokenAuth" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call authZDemoValidateBeforeCall(String codeStructure, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling authZDemo(Async)");
        }
        

        okhttp3.Call localVarCall = authZDemoCall(codeStructure, _callback);
        return localVarCall;

    }

    /**
     * Démonstration de authz
     * Démonstration de authz
     * @param codeStructure Le code de l&#39;établissement (required)
     * @return String
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> la réponse : ok </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> forbidden </td><td>  -  </td></tr>
     </table>
     */
    public String authZDemo(String codeStructure) throws ApiException {
        ApiResponse<String> localVarResp = authZDemoWithHttpInfo(codeStructure);
        return localVarResp.getData();
    }

    /**
     * Démonstration de authz
     * Démonstration de authz
     * @param codeStructure Le code de l&#39;établissement (required)
     * @return ApiResponse&lt;String&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> la réponse : ok </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> forbidden </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<String> authZDemoWithHttpInfo(String codeStructure) throws ApiException {
        okhttp3.Call localVarCall = authZDemoValidateBeforeCall(codeStructure, null);
        Type localVarReturnType = new TypeToken<String>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Démonstration de authz (asynchronously)
     * Démonstration de authz
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> la réponse : ok </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> forbidden </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call authZDemoAsync(String codeStructure, final ApiCallback<String> _callback) throws ApiException {

        okhttp3.Call localVarCall = authZDemoValidateBeforeCall(codeStructure, _callback);
        Type localVarReturnType = new TypeToken<String>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
