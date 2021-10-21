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
 * PAI v1 - Paiement
 * API pour la gestion des paiements
 *
 * The version of the OpenAPI document: 2.1.2
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


import fr.univlorraine.pegase.model.pai.Facture;
import fr.univlorraine.pegase.model.pai.Pageable;
import fr.univlorraine.pegase.model.pai.PagedFactures;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FactureApi {
    private ApiClient localVarApiClient;

    public FactureApi() {
        this(Configuration.getDefaultApiClient());
    }

    public FactureApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for lireFacture
     * @param codeStructure le code de l&#39;établissement (required)
     * @param numeroFacture le numéro de la facture (required)
     * @param codeStructureBudgetaire le code de la structure budgetaire (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La facture récupérée </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> facture introuvable </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireFactureCall(String codeStructure, Long numeroFacture, String codeStructureBudgetaire, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/pai/factures/{codeStructure}/{numeroFacture}/{codeStructureBudgetaire}"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "numeroFacture" + "\\}", localVarApiClient.escapeString(numeroFacture.toString()))
            .replaceAll("\\{" + "codeStructureBudgetaire" + "\\}", localVarApiClient.escapeString(codeStructureBudgetaire.toString()));

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
    private okhttp3.Call lireFactureValidateBeforeCall(String codeStructure, Long numeroFacture, String codeStructureBudgetaire, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling lireFacture(Async)");
        }
        
        // verify the required parameter 'numeroFacture' is set
        if (numeroFacture == null) {
            throw new ApiException("Missing the required parameter 'numeroFacture' when calling lireFacture(Async)");
        }
        
        // verify the required parameter 'codeStructureBudgetaire' is set
        if (codeStructureBudgetaire == null) {
            throw new ApiException("Missing the required parameter 'codeStructureBudgetaire' when calling lireFacture(Async)");
        }
        

        okhttp3.Call localVarCall = lireFactureCall(codeStructure, numeroFacture, codeStructureBudgetaire, _callback);
        return localVarCall;

    }

    /**
     * Récupération d&#39;une facture pour un établissement
     * Récupération d&#39;une facture pour un établissement à partir d&#39;un code
     * @param codeStructure le code de l&#39;établissement (required)
     * @param numeroFacture le numéro de la facture (required)
     * @param codeStructureBudgetaire le code de la structure budgetaire (required)
     * @return Facture
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La facture récupérée </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> facture introuvable </td><td>  -  </td></tr>
     </table>
     */
    public Facture lireFacture(String codeStructure, Long numeroFacture, String codeStructureBudgetaire) throws ApiException {
        ApiResponse<Facture> localVarResp = lireFactureWithHttpInfo(codeStructure, numeroFacture, codeStructureBudgetaire);
        return localVarResp.getData();
    }

    /**
     * Récupération d&#39;une facture pour un établissement
     * Récupération d&#39;une facture pour un établissement à partir d&#39;un code
     * @param codeStructure le code de l&#39;établissement (required)
     * @param numeroFacture le numéro de la facture (required)
     * @param codeStructureBudgetaire le code de la structure budgetaire (required)
     * @return ApiResponse&lt;Facture&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La facture récupérée </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> facture introuvable </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Facture> lireFactureWithHttpInfo(String codeStructure, Long numeroFacture, String codeStructureBudgetaire) throws ApiException {
        okhttp3.Call localVarCall = lireFactureValidateBeforeCall(codeStructure, numeroFacture, codeStructureBudgetaire, null);
        Type localVarReturnType = new TypeToken<Facture>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Récupération d&#39;une facture pour un établissement (asynchronously)
     * Récupération d&#39;une facture pour un établissement à partir d&#39;un code
     * @param codeStructure le code de l&#39;établissement (required)
     * @param numeroFacture le numéro de la facture (required)
     * @param codeStructureBudgetaire le code de la structure budgetaire (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La facture récupérée </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> facture introuvable </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireFactureAsync(String codeStructure, Long numeroFacture, String codeStructureBudgetaire, final ApiCallback<Facture> _callback) throws ApiException {

        okhttp3.Call localVarCall = lireFactureValidateBeforeCall(codeStructure, numeroFacture, codeStructureBudgetaire, _callback);
        Type localVarReturnType = new TypeToken<Facture>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for listerFactures
     * @param codeStructure le code de l&#39;établissement (required)
     * @param codesApprenants les codes apprenants (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> liste des factures </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Structure introuvable </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call listerFacturesCall(String codeStructure, List<String> codesApprenants, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/pai/factures/{codeStructure}"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (codesApprenants != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "codesApprenants", codesApprenants));
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
    private okhttp3.Call listerFacturesValidateBeforeCall(String codeStructure, List<String> codesApprenants, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling listerFactures(Async)");
        }
        

        okhttp3.Call localVarCall = listerFacturesCall(codeStructure, codesApprenants, _callback);
        return localVarCall;

    }

    /**
     * liste des factures
     * Lister les factures pour un établissement
     * @param codeStructure le code de l&#39;établissement (required)
     * @param codesApprenants les codes apprenants (optional)
     * @return List&lt;Facture&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> liste des factures </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Structure introuvable </td><td>  -  </td></tr>
     </table>
     */
    public List<Facture> listerFactures(String codeStructure, List<String> codesApprenants) throws ApiException {
        ApiResponse<List<Facture>> localVarResp = listerFacturesWithHttpInfo(codeStructure, codesApprenants);
        return localVarResp.getData();
    }

    /**
     * liste des factures
     * Lister les factures pour un établissement
     * @param codeStructure le code de l&#39;établissement (required)
     * @param codesApprenants les codes apprenants (optional)
     * @return ApiResponse&lt;List&lt;Facture&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> liste des factures </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Structure introuvable </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<Facture>> listerFacturesWithHttpInfo(String codeStructure, List<String> codesApprenants) throws ApiException {
        okhttp3.Call localVarCall = listerFacturesValidateBeforeCall(codeStructure, codesApprenants, null);
        Type localVarReturnType = new TypeToken<List<Facture>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * liste des factures (asynchronously)
     * Lister les factures pour un établissement
     * @param codeStructure le code de l&#39;établissement (required)
     * @param codesApprenants les codes apprenants (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> liste des factures </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Structure introuvable </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call listerFacturesAsync(String codeStructure, List<String> codesApprenants, final ApiCallback<List<Facture>> _callback) throws ApiException {

        okhttp3.Call localVarCall = listerFacturesValidateBeforeCall(codeStructure, codesApprenants, _callback);
        Type localVarReturnType = new TypeToken<List<Facture>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for listerFacturesPagination
     * @param codeStructure le code de l&#39;établissement (required)
     * @param codesApprenants les codes apprenants (optional)
     * @param pageable  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> liste des factures </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Structure introuvable </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call listerFacturesPaginationCall(String codeStructure, List<String> codesApprenants, Pageable pageable, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/pai/factures/{codeStructure}/pagination"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (codesApprenants != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "codesApprenants", codesApprenants));
        }

        if (pageable != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("pageable", pageable));
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
    private okhttp3.Call listerFacturesPaginationValidateBeforeCall(String codeStructure, List<String> codesApprenants, Pageable pageable, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling listerFacturesPagination(Async)");
        }
        

        okhttp3.Call localVarCall = listerFacturesPaginationCall(codeStructure, codesApprenants, pageable, _callback);
        return localVarCall;

    }

    /**
     * liste des factures avec pagination
     * Pagination des listes de factures pour un établissement
     * @param codeStructure le code de l&#39;établissement (required)
     * @param codesApprenants les codes apprenants (optional)
     * @param pageable  (optional)
     * @return PagedFactures
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> liste des factures </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Structure introuvable </td><td>  -  </td></tr>
     </table>
     */
    public PagedFactures listerFacturesPagination(String codeStructure, List<String> codesApprenants, Pageable pageable) throws ApiException {
        ApiResponse<PagedFactures> localVarResp = listerFacturesPaginationWithHttpInfo(codeStructure, codesApprenants, pageable);
        return localVarResp.getData();
    }

    /**
     * liste des factures avec pagination
     * Pagination des listes de factures pour un établissement
     * @param codeStructure le code de l&#39;établissement (required)
     * @param codesApprenants les codes apprenants (optional)
     * @param pageable  (optional)
     * @return ApiResponse&lt;PagedFactures&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> liste des factures </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Structure introuvable </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<PagedFactures> listerFacturesPaginationWithHttpInfo(String codeStructure, List<String> codesApprenants, Pageable pageable) throws ApiException {
        okhttp3.Call localVarCall = listerFacturesPaginationValidateBeforeCall(codeStructure, codesApprenants, pageable, null);
        Type localVarReturnType = new TypeToken<PagedFactures>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * liste des factures avec pagination (asynchronously)
     * Pagination des listes de factures pour un établissement
     * @param codeStructure le code de l&#39;établissement (required)
     * @param codesApprenants les codes apprenants (optional)
     * @param pageable  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> liste des factures </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Structure introuvable </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call listerFacturesPaginationAsync(String codeStructure, List<String> codesApprenants, Pageable pageable, final ApiCallback<PagedFactures> _callback) throws ApiException {

        okhttp3.Call localVarCall = listerFacturesPaginationValidateBeforeCall(codeStructure, codesApprenants, pageable, _callback);
        Type localVarReturnType = new TypeToken<PagedFactures>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for modifierFacture
     * @param codeStructure le code de l&#39;établissement (required)
     * @param numeroFacture le code de la facture (required)
     * @param codeStructureBudgetaire le code de la structure budgetaire (required)
     * @param facture La facture à ajouter (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> facture modifiée </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Données invalides </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> facture introuvable </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call modifierFactureCall(String codeStructure, Long numeroFacture, String codeStructureBudgetaire, Facture facture, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = facture;

        // create path and map variables
        String localVarPath = "/pai/factures/{codeStructure}/{numeroFacture}/{codeStructureBudgetaire}"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "numeroFacture" + "\\}", localVarApiClient.escapeString(numeroFacture.toString()))
            .replaceAll("\\{" + "codeStructureBudgetaire" + "\\}", localVarApiClient.escapeString(codeStructureBudgetaire.toString()));

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
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "idTokenAuth" };
        return localVarApiClient.buildCall(localVarPath, "PUT", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call modifierFactureValidateBeforeCall(String codeStructure, Long numeroFacture, String codeStructureBudgetaire, Facture facture, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling modifierFacture(Async)");
        }
        
        // verify the required parameter 'numeroFacture' is set
        if (numeroFacture == null) {
            throw new ApiException("Missing the required parameter 'numeroFacture' when calling modifierFacture(Async)");
        }
        
        // verify the required parameter 'codeStructureBudgetaire' is set
        if (codeStructureBudgetaire == null) {
            throw new ApiException("Missing the required parameter 'codeStructureBudgetaire' when calling modifierFacture(Async)");
        }
        
        // verify the required parameter 'facture' is set
        if (facture == null) {
            throw new ApiException("Missing the required parameter 'facture' when calling modifierFacture(Async)");
        }
        

        okhttp3.Call localVarCall = modifierFactureCall(codeStructure, numeroFacture, codeStructureBudgetaire, facture, _callback);
        return localVarCall;

    }

    /**
     * Modification d&#39;une facture (paiements) pour un établissement et un code donnés
     * Modification d&#39;une facture (paiements) pour un établissement et un code donnés
     * @param codeStructure le code de l&#39;établissement (required)
     * @param numeroFacture le code de la facture (required)
     * @param codeStructureBudgetaire le code de la structure budgetaire (required)
     * @param facture La facture à ajouter (required)
     * @return Facture
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> facture modifiée </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Données invalides </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> facture introuvable </td><td>  -  </td></tr>
     </table>
     */
    public Facture modifierFacture(String codeStructure, Long numeroFacture, String codeStructureBudgetaire, Facture facture) throws ApiException {
        ApiResponse<Facture> localVarResp = modifierFactureWithHttpInfo(codeStructure, numeroFacture, codeStructureBudgetaire, facture);
        return localVarResp.getData();
    }

    /**
     * Modification d&#39;une facture (paiements) pour un établissement et un code donnés
     * Modification d&#39;une facture (paiements) pour un établissement et un code donnés
     * @param codeStructure le code de l&#39;établissement (required)
     * @param numeroFacture le code de la facture (required)
     * @param codeStructureBudgetaire le code de la structure budgetaire (required)
     * @param facture La facture à ajouter (required)
     * @return ApiResponse&lt;Facture&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> facture modifiée </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Données invalides </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> facture introuvable </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Facture> modifierFactureWithHttpInfo(String codeStructure, Long numeroFacture, String codeStructureBudgetaire, Facture facture) throws ApiException {
        okhttp3.Call localVarCall = modifierFactureValidateBeforeCall(codeStructure, numeroFacture, codeStructureBudgetaire, facture, null);
        Type localVarReturnType = new TypeToken<Facture>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Modification d&#39;une facture (paiements) pour un établissement et un code donnés (asynchronously)
     * Modification d&#39;une facture (paiements) pour un établissement et un code donnés
     * @param codeStructure le code de l&#39;établissement (required)
     * @param numeroFacture le code de la facture (required)
     * @param codeStructureBudgetaire le code de la structure budgetaire (required)
     * @param facture La facture à ajouter (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> facture modifiée </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Données invalides </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> facture introuvable </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call modifierFactureAsync(String codeStructure, Long numeroFacture, String codeStructureBudgetaire, Facture facture, final ApiCallback<Facture> _callback) throws ApiException {

        okhttp3.Call localVarCall = modifierFactureValidateBeforeCall(codeStructure, numeroFacture, codeStructureBudgetaire, facture, _callback);
        Type localVarReturnType = new TypeToken<Facture>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
