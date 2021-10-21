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


import java.io.File;
import java.time.LocalDate;
import fr.univlorraine.pegase.model.pai.Pageable;
import fr.univlorraine.pegase.model.pai.PagedQuittances;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuittanceApi {
    private ApiClient localVarApiClient;

    public QuittanceApi() {
        this(Configuration.getDefaultApiClient());
    }

    public QuittanceApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for lireRecapitulatifQuittances
     * @param codeStructure le code de l&#39;établissement (required)
     * @param codeStructureBudgetaire Code de la Structure Budgetaire (required)
     * @param dateDebut La date de début au format \&quot;2018-01-31\&quot; (optional)
     * @param dateFin La date de fin au format \&quot;2018-01-31\&quot; (optional)
     * @param numeroQuittanceDebut le numéro de quittance de début (optional)
     * @param numeroQuittanceFin Le numéro de quittance de fin (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Récapitulatif des quittances récupéré </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Récapitulatif des quittances introuvable </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireRecapitulatifQuittancesCall(String codeStructure, String codeStructureBudgetaire, LocalDate dateDebut, LocalDate dateFin, Long numeroQuittanceDebut, Long numeroQuittanceFin, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/pai/recapitulatif-quittances/{codeStructure}"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (codeStructureBudgetaire != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("codeStructureBudgetaire", codeStructureBudgetaire));
        }

        if (dateDebut != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("dateDebut", dateDebut));
        }

        if (dateFin != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("dateFin", dateFin));
        }

        if (numeroQuittanceDebut != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("numeroQuittanceDebut", numeroQuittanceDebut));
        }

        if (numeroQuittanceFin != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("numeroQuittanceFin", numeroQuittanceFin));
        }

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "text/csv"
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
    private okhttp3.Call lireRecapitulatifQuittancesValidateBeforeCall(String codeStructure, String codeStructureBudgetaire, LocalDate dateDebut, LocalDate dateFin, Long numeroQuittanceDebut, Long numeroQuittanceFin, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling lireRecapitulatifQuittances(Async)");
        }
        
        // verify the required parameter 'codeStructureBudgetaire' is set
        if (codeStructureBudgetaire == null) {
            throw new ApiException("Missing the required parameter 'codeStructureBudgetaire' when calling lireRecapitulatifQuittances(Async)");
        }
        

        okhttp3.Call localVarCall = lireRecapitulatifQuittancesCall(codeStructure, codeStructureBudgetaire, dateDebut, dateFin, numeroQuittanceDebut, numeroQuittanceFin, _callback);
        return localVarCall;

    }

    /**
     * Récupération du récapitulatif des quittances au format csv
     * Récupération du récapitulatif des quittances au format csv
     * @param codeStructure le code de l&#39;établissement (required)
     * @param codeStructureBudgetaire Code de la Structure Budgetaire (required)
     * @param dateDebut La date de début au format \&quot;2018-01-31\&quot; (optional)
     * @param dateFin La date de fin au format \&quot;2018-01-31\&quot; (optional)
     * @param numeroQuittanceDebut le numéro de quittance de début (optional)
     * @param numeroQuittanceFin Le numéro de quittance de fin (optional)
     * @return File
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Récapitulatif des quittances récupéré </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Récapitulatif des quittances introuvable </td><td>  -  </td></tr>
     </table>
     */
    public File lireRecapitulatifQuittances(String codeStructure, String codeStructureBudgetaire, LocalDate dateDebut, LocalDate dateFin, Long numeroQuittanceDebut, Long numeroQuittanceFin) throws ApiException {
        ApiResponse<File> localVarResp = lireRecapitulatifQuittancesWithHttpInfo(codeStructure, codeStructureBudgetaire, dateDebut, dateFin, numeroQuittanceDebut, numeroQuittanceFin);
        return localVarResp.getData();
    }

    /**
     * Récupération du récapitulatif des quittances au format csv
     * Récupération du récapitulatif des quittances au format csv
     * @param codeStructure le code de l&#39;établissement (required)
     * @param codeStructureBudgetaire Code de la Structure Budgetaire (required)
     * @param dateDebut La date de début au format \&quot;2018-01-31\&quot; (optional)
     * @param dateFin La date de fin au format \&quot;2018-01-31\&quot; (optional)
     * @param numeroQuittanceDebut le numéro de quittance de début (optional)
     * @param numeroQuittanceFin Le numéro de quittance de fin (optional)
     * @return ApiResponse&lt;File&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Récapitulatif des quittances récupéré </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Récapitulatif des quittances introuvable </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<File> lireRecapitulatifQuittancesWithHttpInfo(String codeStructure, String codeStructureBudgetaire, LocalDate dateDebut, LocalDate dateFin, Long numeroQuittanceDebut, Long numeroQuittanceFin) throws ApiException {
        okhttp3.Call localVarCall = lireRecapitulatifQuittancesValidateBeforeCall(codeStructure, codeStructureBudgetaire, dateDebut, dateFin, numeroQuittanceDebut, numeroQuittanceFin, null);
        Type localVarReturnType = new TypeToken<File>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Récupération du récapitulatif des quittances au format csv (asynchronously)
     * Récupération du récapitulatif des quittances au format csv
     * @param codeStructure le code de l&#39;établissement (required)
     * @param codeStructureBudgetaire Code de la Structure Budgetaire (required)
     * @param dateDebut La date de début au format \&quot;2018-01-31\&quot; (optional)
     * @param dateFin La date de fin au format \&quot;2018-01-31\&quot; (optional)
     * @param numeroQuittanceDebut le numéro de quittance de début (optional)
     * @param numeroQuittanceFin Le numéro de quittance de fin (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Récapitulatif des quittances récupéré </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Récapitulatif des quittances introuvable </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireRecapitulatifQuittancesAsync(String codeStructure, String codeStructureBudgetaire, LocalDate dateDebut, LocalDate dateFin, Long numeroQuittanceDebut, Long numeroQuittanceFin, final ApiCallback<File> _callback) throws ApiException {

        okhttp3.Call localVarCall = lireRecapitulatifQuittancesValidateBeforeCall(codeStructure, codeStructureBudgetaire, dateDebut, dateFin, numeroQuittanceDebut, numeroQuittanceFin, _callback);
        Type localVarReturnType = new TypeToken<File>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for listerQuittances
     * @param codeStructure le code de l&#39;établissement (required)
     * @param pageable  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> liste des quittances </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Structure introuvable </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call listerQuittancesCall(String codeStructure, Pageable pageable, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/pai/quittances/{codeStructure}"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
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
    private okhttp3.Call listerQuittancesValidateBeforeCall(String codeStructure, Pageable pageable, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling listerQuittances(Async)");
        }
        

        okhttp3.Call localVarCall = listerQuittancesCall(codeStructure, pageable, _callback);
        return localVarCall;

    }

    /**
     * liste des quittances
     * Lister les quittances pour un établissement
     * @param codeStructure le code de l&#39;établissement (required)
     * @param pageable  (optional)
     * @return PagedQuittances
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> liste des quittances </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Structure introuvable </td><td>  -  </td></tr>
     </table>
     */
    public PagedQuittances listerQuittances(String codeStructure, Pageable pageable) throws ApiException {
        ApiResponse<PagedQuittances> localVarResp = listerQuittancesWithHttpInfo(codeStructure, pageable);
        return localVarResp.getData();
    }

    /**
     * liste des quittances
     * Lister les quittances pour un établissement
     * @param codeStructure le code de l&#39;établissement (required)
     * @param pageable  (optional)
     * @return ApiResponse&lt;PagedQuittances&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> liste des quittances </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Structure introuvable </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<PagedQuittances> listerQuittancesWithHttpInfo(String codeStructure, Pageable pageable) throws ApiException {
        okhttp3.Call localVarCall = listerQuittancesValidateBeforeCall(codeStructure, pageable, null);
        Type localVarReturnType = new TypeToken<PagedQuittances>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * liste des quittances (asynchronously)
     * Lister les quittances pour un établissement
     * @param codeStructure le code de l&#39;établissement (required)
     * @param pageable  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> liste des quittances </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Structure introuvable </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call listerQuittancesAsync(String codeStructure, Pageable pageable, final ApiCallback<PagedQuittances> _callback) throws ApiException {

        okhttp3.Call localVarCall = listerQuittancesValidateBeforeCall(codeStructure, pageable, _callback);
        Type localVarReturnType = new TypeToken<PagedQuittances>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
