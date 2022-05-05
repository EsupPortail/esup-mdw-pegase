/**
 *
 *  ESUP-Portail ESUP-MONDOSSIERWEB-PEGASE - Copyright (c) 2021 ESUP-Portail consortium
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
/*
 * API CHC v5
 * Liste l'ensemble des services et des opérations disponibles dans le module choix des cursus v5 
 *
 * The version of the OpenAPI document: 5.0.0
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


import fr.univlorraine.pegase.model.chc.Nomenclature;
import fr.univlorraine.pegase.model.chc.TypeAmenagement;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParametrageApi {
    private ApiClient localVarApiClient;

    public ParametrageApi() {
        this(Configuration.getDefaultApiClient());
    }

    public ParametrageApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for lireListeParametrage
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codeNomenclature Le code de la nomenclature ou du paramétrage (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Liste des paramétrages issues du référentiel </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireListeParametrageCall(String codeStructure, String codeNomenclature, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/parametrages/{codeStructure}/{codeNomenclature}"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "codeNomenclature" + "\\}", localVarApiClient.escapeString(codeNomenclature.toString()));

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
    private okhttp3.Call lireListeParametrageValidateBeforeCall(String codeStructure, String codeNomenclature, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling lireListeParametrage(Async)");
        }
        
        // verify the required parameter 'codeNomenclature' is set
        if (codeNomenclature == null) {
            throw new ApiException("Missing the required parameter 'codeNomenclature' when calling lireListeParametrage(Async)");
        }
        

        okhttp3.Call localVarCall = lireListeParametrageCall(codeStructure, codeNomenclature, _callback);
        return localVarCall;

    }

    /**
     * Liste des paramétrages valides à la date du jour
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codeNomenclature Le code de la nomenclature ou du paramétrage (required)
     * @return List&lt;Nomenclature&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Liste des paramétrages issues du référentiel </td><td>  -  </td></tr>
     </table>
     */
    public List<Nomenclature> lireListeParametrage(String codeStructure, String codeNomenclature) throws ApiException {
        ApiResponse<List<Nomenclature>> localVarResp = lireListeParametrageWithHttpInfo(codeStructure, codeNomenclature);
        return localVarResp.getData();
    }

    /**
     * Liste des paramétrages valides à la date du jour
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codeNomenclature Le code de la nomenclature ou du paramétrage (required)
     * @return ApiResponse&lt;List&lt;Nomenclature&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Liste des paramétrages issues du référentiel </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<Nomenclature>> lireListeParametrageWithHttpInfo(String codeStructure, String codeNomenclature) throws ApiException {
        okhttp3.Call localVarCall = lireListeParametrageValidateBeforeCall(codeStructure, codeNomenclature, null);
        Type localVarReturnType = new TypeToken<List<Nomenclature>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Liste des paramétrages valides à la date du jour (asynchronously)
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codeNomenclature Le code de la nomenclature ou du paramétrage (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Liste des paramétrages issues du référentiel </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireListeParametrageAsync(String codeStructure, String codeNomenclature, final ApiCallback<List<Nomenclature>> _callback) throws ApiException {

        okhttp3.Call localVarCall = lireListeParametrageValidateBeforeCall(codeStructure, codeNomenclature, _callback);
        Type localVarReturnType = new TypeToken<List<Nomenclature>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for lireListeTypeAmenagement
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Liste des types d&#39;amenagement issues du référentiel </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireListeTypeAmenagementCall(String codeStructure, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/parametrages/amenagements/{codeStructure}"
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
    private okhttp3.Call lireListeTypeAmenagementValidateBeforeCall(String codeStructure, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling lireListeTypeAmenagement(Async)");
        }
        

        okhttp3.Call localVarCall = lireListeTypeAmenagementCall(codeStructure, _callback);
        return localVarCall;

    }

    /**
     * Liste des types d&#39;amenagement valides à la date du jour depuis le referentiel (ref)
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @return List&lt;TypeAmenagement&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Liste des types d&#39;amenagement issues du référentiel </td><td>  -  </td></tr>
     </table>
     */
    public List<TypeAmenagement> lireListeTypeAmenagement(String codeStructure) throws ApiException {
        ApiResponse<List<TypeAmenagement>> localVarResp = lireListeTypeAmenagementWithHttpInfo(codeStructure);
        return localVarResp.getData();
    }

    /**
     * Liste des types d&#39;amenagement valides à la date du jour depuis le referentiel (ref)
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @return ApiResponse&lt;List&lt;TypeAmenagement&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Liste des types d&#39;amenagement issues du référentiel </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<TypeAmenagement>> lireListeTypeAmenagementWithHttpInfo(String codeStructure) throws ApiException {
        okhttp3.Call localVarCall = lireListeTypeAmenagementValidateBeforeCall(codeStructure, null);
        Type localVarReturnType = new TypeToken<List<TypeAmenagement>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Liste des types d&#39;amenagement valides à la date du jour depuis le referentiel (ref) (asynchronously)
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Liste des types d&#39;amenagement issues du référentiel </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireListeTypeAmenagementAsync(String codeStructure, final ApiCallback<List<TypeAmenagement>> _callback) throws ApiException {

        okhttp3.Call localVarCall = lireListeTypeAmenagementValidateBeforeCall(codeStructure, _callback);
        Type localVarReturnType = new TypeToken<List<TypeAmenagement>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for lireParametrage
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codeNomenclature Le code de la nomenclature ou du paramétrage (required)
     * @param code Le code métier de la nomenclature ou du paramétrage (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Le paramétrage a été récupéré </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireParametrageCall(String codeStructure, String codeNomenclature, String code, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/parametrages/{codeStructure}/{codeNomenclature}/{code}"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "codeNomenclature" + "\\}", localVarApiClient.escapeString(codeNomenclature.toString()))
            .replaceAll("\\{" + "code" + "\\}", localVarApiClient.escapeString(code.toString()));

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
    private okhttp3.Call lireParametrageValidateBeforeCall(String codeStructure, String codeNomenclature, String code, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling lireParametrage(Async)");
        }
        
        // verify the required parameter 'codeNomenclature' is set
        if (codeNomenclature == null) {
            throw new ApiException("Missing the required parameter 'codeNomenclature' when calling lireParametrage(Async)");
        }
        
        // verify the required parameter 'code' is set
        if (code == null) {
            throw new ApiException("Missing the required parameter 'code' when calling lireParametrage(Async)");
        }
        

        okhttp3.Call localVarCall = lireParametrageCall(codeStructure, codeNomenclature, code, _callback);
        return localVarCall;

    }

    /**
     * Lire le paramétrage avec le code valide à la date du jour
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codeNomenclature Le code de la nomenclature ou du paramétrage (required)
     * @param code Le code métier de la nomenclature ou du paramétrage (required)
     * @return Nomenclature
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Le paramétrage a été récupéré </td><td>  -  </td></tr>
     </table>
     */
    public Nomenclature lireParametrage(String codeStructure, String codeNomenclature, String code) throws ApiException {
        ApiResponse<Nomenclature> localVarResp = lireParametrageWithHttpInfo(codeStructure, codeNomenclature, code);
        return localVarResp.getData();
    }

    /**
     * Lire le paramétrage avec le code valide à la date du jour
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codeNomenclature Le code de la nomenclature ou du paramétrage (required)
     * @param code Le code métier de la nomenclature ou du paramétrage (required)
     * @return ApiResponse&lt;Nomenclature&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Le paramétrage a été récupéré </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Nomenclature> lireParametrageWithHttpInfo(String codeStructure, String codeNomenclature, String code) throws ApiException {
        okhttp3.Call localVarCall = lireParametrageValidateBeforeCall(codeStructure, codeNomenclature, code, null);
        Type localVarReturnType = new TypeToken<Nomenclature>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Lire le paramétrage avec le code valide à la date du jour (asynchronously)
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codeNomenclature Le code de la nomenclature ou du paramétrage (required)
     * @param code Le code métier de la nomenclature ou du paramétrage (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Le paramétrage a été récupéré </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireParametrageAsync(String codeStructure, String codeNomenclature, String code, final ApiCallback<Nomenclature> _callback) throws ApiException {

        okhttp3.Call localVarCall = lireParametrageValidateBeforeCall(codeStructure, codeNomenclature, code, _callback);
        Type localVarReturnType = new TypeToken<Nomenclature>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
