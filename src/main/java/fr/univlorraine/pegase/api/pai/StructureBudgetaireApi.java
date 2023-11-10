/*
 * PAI v1 - Paiement
 * API pour la gestion des paiements
 *
 * The version of the OpenAPI document: 21.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.api.pai;

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


import fr.univlorraine.pegase.model.pai.StructureBudgetaireNumeroQuittance;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StructureBudgetaireApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public StructureBudgetaireApi() {
        this(Configuration.getDefaultApiClient());
    }

    public StructureBudgetaireApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public int getHostIndex() {
        return localHostIndex;
    }

    public void setHostIndex(int hostIndex) {
        this.localHostIndex = hostIndex;
    }

    public String getCustomBaseUrl() {
        return localCustomBaseUrl;
    }

    public void setCustomBaseUrl(String customBaseUrl) {
        this.localCustomBaseUrl = customBaseUrl;
    }

    /**
     * Build call for creerStructureBudgetaireNumeroQuittance
     * @param codeStructure le code de la structure budgétaire (required)
     * @param body Le numéro de quittance initial (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> La structure budgétaire associée à un numéro de quittance sauvegardée </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call creerStructureBudgetaireNumeroQuittanceCall(String codeStructure, Long body, final ApiCallback _callback) throws ApiException {
        String basePath = null;

        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = body;

        // create path and map variables
        String localVarPath = "/pai/numero-quittance/transition/initialiser/{codeStructure}"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
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
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "idTokenAuth" };
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call creerStructureBudgetaireNumeroQuittanceValidateBeforeCall(String codeStructure, Long body, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling creerStructureBudgetaireNumeroQuittance(Async)");
        }
        
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new ApiException("Missing the required parameter 'body' when calling creerStructureBudgetaireNumeroQuittance(Async)");
        }
        

        okhttp3.Call localVarCall = creerStructureBudgetaireNumeroQuittanceCall(codeStructure, body, _callback);
        return localVarCall;

    }

    /**
     * Sauvegarde d&#39;une structure budgétaire associée à un numéro de quittance
     * Sauvegarde d&#39;une structure budgétaire associée à un numéro de quittance à partir du code structure et du numéro de quittance
     * @param codeStructure le code de la structure budgétaire (required)
     * @param body Le numéro de quittance initial (required)
     * @return StructureBudgetaireNumeroQuittance
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> La structure budgétaire associée à un numéro de quittance sauvegardée </td><td>  -  </td></tr>
     </table>
     */
    public StructureBudgetaireNumeroQuittance creerStructureBudgetaireNumeroQuittance(String codeStructure, Long body) throws ApiException {
        ApiResponse<StructureBudgetaireNumeroQuittance> localVarResp = creerStructureBudgetaireNumeroQuittanceWithHttpInfo(codeStructure, body);
        return localVarResp.getData();
    }

    /**
     * Sauvegarde d&#39;une structure budgétaire associée à un numéro de quittance
     * Sauvegarde d&#39;une structure budgétaire associée à un numéro de quittance à partir du code structure et du numéro de quittance
     * @param codeStructure le code de la structure budgétaire (required)
     * @param body Le numéro de quittance initial (required)
     * @return ApiResponse&lt;StructureBudgetaireNumeroQuittance&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> La structure budgétaire associée à un numéro de quittance sauvegardée </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<StructureBudgetaireNumeroQuittance> creerStructureBudgetaireNumeroQuittanceWithHttpInfo(String codeStructure, Long body) throws ApiException {
        okhttp3.Call localVarCall = creerStructureBudgetaireNumeroQuittanceValidateBeforeCall(codeStructure, body, null);
        Type localVarReturnType = new TypeToken<StructureBudgetaireNumeroQuittance>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Sauvegarde d&#39;une structure budgétaire associée à un numéro de quittance (asynchronously)
     * Sauvegarde d&#39;une structure budgétaire associée à un numéro de quittance à partir du code structure et du numéro de quittance
     * @param codeStructure le code de la structure budgétaire (required)
     * @param body Le numéro de quittance initial (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> La structure budgétaire associée à un numéro de quittance sauvegardée </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call creerStructureBudgetaireNumeroQuittanceAsync(String codeStructure, Long body, final ApiCallback<StructureBudgetaireNumeroQuittance> _callback) throws ApiException {

        okhttp3.Call localVarCall = creerStructureBudgetaireNumeroQuittanceValidateBeforeCall(codeStructure, body, _callback);
        Type localVarReturnType = new TypeToken<StructureBudgetaireNumeroQuittance>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for lireStructureBudgetaireNumeroQuittance
     * @param codeStructure le code de la structure budgétaire (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La structure budgétaire associée à son numéro de quittance </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireStructureBudgetaireNumeroQuittanceCall(String codeStructure, final ApiCallback _callback) throws ApiException {
        String basePath = null;

        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/pai/numero-quittance/transition/initialiser/{codeStructure}"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
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
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "idTokenAuth" };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call lireStructureBudgetaireNumeroQuittanceValidateBeforeCall(String codeStructure, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling lireStructureBudgetaireNumeroQuittance(Async)");
        }
        

        okhttp3.Call localVarCall = lireStructureBudgetaireNumeroQuittanceCall(codeStructure, _callback);
        return localVarCall;

    }

    /**
     * Récupération d&#39;une structure budgétaire associée à un numéro de quittance
     * Récupération d&#39;une structure budgétaire associée à un numéro de quittance à partir du code de la structure budgétaire
     * @param codeStructure le code de la structure budgétaire (required)
     * @return StructureBudgetaireNumeroQuittance
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La structure budgétaire associée à son numéro de quittance </td><td>  -  </td></tr>
     </table>
     */
    public StructureBudgetaireNumeroQuittance lireStructureBudgetaireNumeroQuittance(String codeStructure) throws ApiException {
        ApiResponse<StructureBudgetaireNumeroQuittance> localVarResp = lireStructureBudgetaireNumeroQuittanceWithHttpInfo(codeStructure);
        return localVarResp.getData();
    }

    /**
     * Récupération d&#39;une structure budgétaire associée à un numéro de quittance
     * Récupération d&#39;une structure budgétaire associée à un numéro de quittance à partir du code de la structure budgétaire
     * @param codeStructure le code de la structure budgétaire (required)
     * @return ApiResponse&lt;StructureBudgetaireNumeroQuittance&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La structure budgétaire associée à son numéro de quittance </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<StructureBudgetaireNumeroQuittance> lireStructureBudgetaireNumeroQuittanceWithHttpInfo(String codeStructure) throws ApiException {
        okhttp3.Call localVarCall = lireStructureBudgetaireNumeroQuittanceValidateBeforeCall(codeStructure, null);
        Type localVarReturnType = new TypeToken<StructureBudgetaireNumeroQuittance>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Récupération d&#39;une structure budgétaire associée à un numéro de quittance (asynchronously)
     * Récupération d&#39;une structure budgétaire associée à un numéro de quittance à partir du code de la structure budgétaire
     * @param codeStructure le code de la structure budgétaire (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La structure budgétaire associée à son numéro de quittance </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireStructureBudgetaireNumeroQuittanceAsync(String codeStructure, final ApiCallback<StructureBudgetaireNumeroQuittance> _callback) throws ApiException {

        okhttp3.Call localVarCall = lireStructureBudgetaireNumeroQuittanceValidateBeforeCall(codeStructure, _callback);
        Type localVarReturnType = new TypeToken<StructureBudgetaireNumeroQuittance>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
