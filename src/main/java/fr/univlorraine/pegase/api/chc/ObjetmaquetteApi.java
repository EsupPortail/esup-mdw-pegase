/*
 * API CHC v4
 * Liste l'ensemble des services et des opérations disponibles dans le module choix des cursus v4
 *
 * The version of the OpenAPI document: 4.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.api.chc;

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


import fr.univlorraine.pegase.model.chc.ObjetMaquette;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjetmaquetteApi {
    private ApiClient localVarApiClient;

    public ObjetmaquetteApi() {
        this(Configuration.getDefaultApiClient());
    }

    public ObjetmaquetteApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for lireListe4
     * @param codePeriode Le code de la période (required)
     * @param codeStructure Le code de la structure (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Les objets maquettes ont été récupérées </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireListe4Call(String codePeriode, String codeStructure, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/api/chc/v4/objets-maquette/{codeStructure}/{codePeriode}"
            .replaceAll("\\{" + "codePeriode" + "\\}", localVarApiClient.escapeString(codePeriode.toString()))
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
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "idTokenAuth" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call lireListe4ValidateBeforeCall(String codePeriode, String codeStructure, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codePeriode' is set
        if (codePeriode == null) {
            throw new ApiException("Missing the required parameter 'codePeriode' when calling lireListe4(Async)");
        }
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling lireListe4(Async)");
        }
        

        okhttp3.Call localVarCall = lireListe4Call(codePeriode, codeStructure, _callback);
        return localVarCall;

    }

    /**
     * Liste des objets maquettes pour une periode
     * 
     * @param codePeriode Le code de la période (required)
     * @param codeStructure Le code de la structure (required)
     * @return List&lt;ObjetMaquette&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Les objets maquettes ont été récupérées </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public List<ObjetMaquette> lireListe4(String codePeriode, String codeStructure) throws ApiException {
        ApiResponse<List<ObjetMaquette>> localVarResp = lireListe4WithHttpInfo(codePeriode, codeStructure);
        return localVarResp.getData();
    }

    /**
     * Liste des objets maquettes pour une periode
     * 
     * @param codePeriode Le code de la période (required)
     * @param codeStructure Le code de la structure (required)
     * @return ApiResponse&lt;List&lt;ObjetMaquette&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Les objets maquettes ont été récupérées </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<ObjetMaquette>> lireListe4WithHttpInfo(String codePeriode, String codeStructure) throws ApiException {
        okhttp3.Call localVarCall = lireListe4ValidateBeforeCall(codePeriode, codeStructure, null);
        Type localVarReturnType = new TypeToken<List<ObjetMaquette>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Liste des objets maquettes pour une periode (asynchronously)
     * 
     * @param codePeriode Le code de la période (required)
     * @param codeStructure Le code de la structure (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Les objets maquettes ont été récupérées </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireListe4Async(String codePeriode, String codeStructure, final ApiCallback<List<ObjetMaquette>> _callback) throws ApiException {

        okhttp3.Call localVarCall = lireListe4ValidateBeforeCall(codePeriode, codeStructure, _callback);
        Type localVarReturnType = new TypeToken<List<ObjetMaquette>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for lireListeFormationAll2
     * @param codePeriode Le code de la période (required)
     * @param codeStructure Le code de la structure (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Les objets maquettes ont été récupérées </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireListeFormationAll2Call(String codePeriode, String codeStructure, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/api/chc/v4/objets-maquette/formation/{codeStructure}/{codePeriode}"
            .replaceAll("\\{" + "codePeriode" + "\\}", localVarApiClient.escapeString(codePeriode.toString()))
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
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "idTokenAuth" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call lireListeFormationAll2ValidateBeforeCall(String codePeriode, String codeStructure, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codePeriode' is set
        if (codePeriode == null) {
            throw new ApiException("Missing the required parameter 'codePeriode' when calling lireListeFormationAll2(Async)");
        }
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling lireListeFormationAll2(Async)");
        }
        

        okhttp3.Call localVarCall = lireListeFormationAll2Call(codePeriode, codeStructure, _callback);
        return localVarCall;

    }

    /**
     * Liste des objets maquettes
     * 
     * @param codePeriode Le code de la période (required)
     * @param codeStructure Le code de la structure (required)
     * @return List&lt;ObjetMaquette&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Les objets maquettes ont été récupérées </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public List<ObjetMaquette> lireListeFormationAll2(String codePeriode, String codeStructure) throws ApiException {
        ApiResponse<List<ObjetMaquette>> localVarResp = lireListeFormationAll2WithHttpInfo(codePeriode, codeStructure);
        return localVarResp.getData();
    }

    /**
     * Liste des objets maquettes
     * 
     * @param codePeriode Le code de la période (required)
     * @param codeStructure Le code de la structure (required)
     * @return ApiResponse&lt;List&lt;ObjetMaquette&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Les objets maquettes ont été récupérées </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<ObjetMaquette>> lireListeFormationAll2WithHttpInfo(String codePeriode, String codeStructure) throws ApiException {
        okhttp3.Call localVarCall = lireListeFormationAll2ValidateBeforeCall(codePeriode, codeStructure, null);
        Type localVarReturnType = new TypeToken<List<ObjetMaquette>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Liste des objets maquettes (asynchronously)
     * 
     * @param codePeriode Le code de la période (required)
     * @param codeStructure Le code de la structure (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Les objets maquettes ont été récupérées </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireListeFormationAll2Async(String codePeriode, String codeStructure, final ApiCallback<List<ObjetMaquette>> _callback) throws ApiException {

        okhttp3.Call localVarCall = lireListeFormationAll2ValidateBeforeCall(codePeriode, codeStructure, _callback);
        Type localVarReturnType = new TypeToken<List<ObjetMaquette>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for lireListeGroupementAll2
     * @param codePeriode Le code de la période (required)
     * @param codeStructure Le code de la structure (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Les objets maquettes ont été récupérées </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireListeGroupementAll2Call(String codePeriode, String codeStructure, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/api/chc/v4/objets-maquette/groupement/{codeStructure}/{codePeriode}"
            .replaceAll("\\{" + "codePeriode" + "\\}", localVarApiClient.escapeString(codePeriode.toString()))
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
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "idTokenAuth" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call lireListeGroupementAll2ValidateBeforeCall(String codePeriode, String codeStructure, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codePeriode' is set
        if (codePeriode == null) {
            throw new ApiException("Missing the required parameter 'codePeriode' when calling lireListeGroupementAll2(Async)");
        }
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling lireListeGroupementAll2(Async)");
        }
        

        okhttp3.Call localVarCall = lireListeGroupementAll2Call(codePeriode, codeStructure, _callback);
        return localVarCall;

    }

    /**
     * Liste des objets maquettes
     * 
     * @param codePeriode Le code de la période (required)
     * @param codeStructure Le code de la structure (required)
     * @return List&lt;ObjetMaquette&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Les objets maquettes ont été récupérées </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public List<ObjetMaquette> lireListeGroupementAll2(String codePeriode, String codeStructure) throws ApiException {
        ApiResponse<List<ObjetMaquette>> localVarResp = lireListeGroupementAll2WithHttpInfo(codePeriode, codeStructure);
        return localVarResp.getData();
    }

    /**
     * Liste des objets maquettes
     * 
     * @param codePeriode Le code de la période (required)
     * @param codeStructure Le code de la structure (required)
     * @return ApiResponse&lt;List&lt;ObjetMaquette&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Les objets maquettes ont été récupérées </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<ObjetMaquette>> lireListeGroupementAll2WithHttpInfo(String codePeriode, String codeStructure) throws ApiException {
        okhttp3.Call localVarCall = lireListeGroupementAll2ValidateBeforeCall(codePeriode, codeStructure, null);
        Type localVarReturnType = new TypeToken<List<ObjetMaquette>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Liste des objets maquettes (asynchronously)
     * 
     * @param codePeriode Le code de la période (required)
     * @param codeStructure Le code de la structure (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Les objets maquettes ont été récupérées </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireListeGroupementAll2Async(String codePeriode, String codeStructure, final ApiCallback<List<ObjetMaquette>> _callback) throws ApiException {

        okhttp3.Call localVarCall = lireListeGroupementAll2ValidateBeforeCall(codePeriode, codeStructure, _callback);
        Type localVarReturnType = new TypeToken<List<ObjetMaquette>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for lireListeObjetFormationAll2
     * @param codePeriode Le code de la période (required)
     * @param codeStructure Le code de la structure (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Les objets maquettes ont été récupérées </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireListeObjetFormationAll2Call(String codePeriode, String codeStructure, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/api/chc/v4/objets-maquette/objets-formation/{codeStructure}/{codePeriode}"
            .replaceAll("\\{" + "codePeriode" + "\\}", localVarApiClient.escapeString(codePeriode.toString()))
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
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "idTokenAuth" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call lireListeObjetFormationAll2ValidateBeforeCall(String codePeriode, String codeStructure, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codePeriode' is set
        if (codePeriode == null) {
            throw new ApiException("Missing the required parameter 'codePeriode' when calling lireListeObjetFormationAll2(Async)");
        }
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling lireListeObjetFormationAll2(Async)");
        }
        

        okhttp3.Call localVarCall = lireListeObjetFormationAll2Call(codePeriode, codeStructure, _callback);
        return localVarCall;

    }

    /**
     * Liste des objets maquettes
     * 
     * @param codePeriode Le code de la période (required)
     * @param codeStructure Le code de la structure (required)
     * @return List&lt;ObjetMaquette&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Les objets maquettes ont été récupérées </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public List<ObjetMaquette> lireListeObjetFormationAll2(String codePeriode, String codeStructure) throws ApiException {
        ApiResponse<List<ObjetMaquette>> localVarResp = lireListeObjetFormationAll2WithHttpInfo(codePeriode, codeStructure);
        return localVarResp.getData();
    }

    /**
     * Liste des objets maquettes
     * 
     * @param codePeriode Le code de la période (required)
     * @param codeStructure Le code de la structure (required)
     * @return ApiResponse&lt;List&lt;ObjetMaquette&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Les objets maquettes ont été récupérées </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<ObjetMaquette>> lireListeObjetFormationAll2WithHttpInfo(String codePeriode, String codeStructure) throws ApiException {
        okhttp3.Call localVarCall = lireListeObjetFormationAll2ValidateBeforeCall(codePeriode, codeStructure, null);
        Type localVarReturnType = new TypeToken<List<ObjetMaquette>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Liste des objets maquettes (asynchronously)
     * 
     * @param codePeriode Le code de la période (required)
     * @param codeStructure Le code de la structure (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Les objets maquettes ont été récupérées </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireListeObjetFormationAll2Async(String codePeriode, String codeStructure, final ApiCallback<List<ObjetMaquette>> _callback) throws ApiException {

        okhttp3.Call localVarCall = lireListeObjetFormationAll2ValidateBeforeCall(codePeriode, codeStructure, _callback);
        Type localVarReturnType = new TypeToken<List<ObjetMaquette>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}