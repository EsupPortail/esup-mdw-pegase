/*
 * PAI v1 - Paiement
 * API pour la gestion des paiements
 *
 * The version of the OpenAPI document: 27.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.pai.api;

import fr.univlorraine.pegase.pai.invoker.ApiCallback;
import fr.univlorraine.pegase.pai.invoker.ApiClient;
import fr.univlorraine.pegase.pai.invoker.ApiException;
import fr.univlorraine.pegase.pai.invoker.ApiResponse;
import fr.univlorraine.pegase.pai.invoker.Configuration;
import fr.univlorraine.pegase.pai.invoker.Pair;
import fr.univlorraine.pegase.pai.invoker.ProgressRequestBody;
import fr.univlorraine.pegase.pai.invoker.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import java.util.Date;
import java.io.File;
import fr.univlorraine.pegase.pai.model.Pageable;
import fr.univlorraine.pegase.pai.model.PagedQuittances;
import fr.univlorraine.pegase.pai.model.PreinformationRecapPaiement;
import fr.univlorraine.pegase.pai.model.QuittanceRecapitulatifPaiement;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuittanceApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

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
     * Build call for lirePreinformationRecapitulatifPaiement
     * @param codeStructure le code de l&#39;établissement (required)
     * @param anneeCivile année civile (required)
     * @param codeStructureBudgetaire Code de la Structure Budgetaire (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Preinformation du Recapitulatif de Paiement </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lirePreinformationRecapitulatifPaiementCall(String codeStructure, Integer anneeCivile, String codeStructureBudgetaire, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/pai/recapitulatif-quittances/preinformation/{codeStructure}/{anneeCivile}/{codeStructureBudgetaire}"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "anneeCivile" + "\\}", localVarApiClient.escapeString(anneeCivile.toString()))
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
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "idTokenAuth" };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call lirePreinformationRecapitulatifPaiementValidateBeforeCall(String codeStructure, Integer anneeCivile, String codeStructureBudgetaire, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling lirePreinformationRecapitulatifPaiement(Async)");
        }
        
        // verify the required parameter 'anneeCivile' is set
        if (anneeCivile == null) {
            throw new ApiException("Missing the required parameter 'anneeCivile' when calling lirePreinformationRecapitulatifPaiement(Async)");
        }
        
        // verify the required parameter 'codeStructureBudgetaire' is set
        if (codeStructureBudgetaire == null) {
            throw new ApiException("Missing the required parameter 'codeStructureBudgetaire' when calling lirePreinformationRecapitulatifPaiement(Async)");
        }
        

        okhttp3.Call localVarCall = lirePreinformationRecapitulatifPaiementCall(codeStructure, anneeCivile, codeStructureBudgetaire, _callback);
        return localVarCall;

    }

    /**
     * Preinformation du récapitulatif paiement
     * Preinformation du récapitulatif paiement
     * @param codeStructure le code de l&#39;établissement (required)
     * @param anneeCivile année civile (required)
     * @param codeStructureBudgetaire Code de la Structure Budgetaire (required)
     * @return PreinformationRecapPaiement
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Preinformation du Recapitulatif de Paiement </td><td>  -  </td></tr>
     </table>
     */
    public PreinformationRecapPaiement lirePreinformationRecapitulatifPaiement(String codeStructure, Integer anneeCivile, String codeStructureBudgetaire) throws ApiException {
        ApiResponse<PreinformationRecapPaiement> localVarResp = lirePreinformationRecapitulatifPaiementWithHttpInfo(codeStructure, anneeCivile, codeStructureBudgetaire);
        return localVarResp.getData();
    }

    /**
     * Preinformation du récapitulatif paiement
     * Preinformation du récapitulatif paiement
     * @param codeStructure le code de l&#39;établissement (required)
     * @param anneeCivile année civile (required)
     * @param codeStructureBudgetaire Code de la Structure Budgetaire (required)
     * @return ApiResponse&lt;PreinformationRecapPaiement&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Preinformation du Recapitulatif de Paiement </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<PreinformationRecapPaiement> lirePreinformationRecapitulatifPaiementWithHttpInfo(String codeStructure, Integer anneeCivile, String codeStructureBudgetaire) throws ApiException {
        okhttp3.Call localVarCall = lirePreinformationRecapitulatifPaiementValidateBeforeCall(codeStructure, anneeCivile, codeStructureBudgetaire, null);
        Type localVarReturnType = new TypeToken<PreinformationRecapPaiement>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Preinformation du récapitulatif paiement (asynchronously)
     * Preinformation du récapitulatif paiement
     * @param codeStructure le code de l&#39;établissement (required)
     * @param anneeCivile année civile (required)
     * @param codeStructureBudgetaire Code de la Structure Budgetaire (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Preinformation du Recapitulatif de Paiement </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lirePreinformationRecapitulatifPaiementAsync(String codeStructure, Integer anneeCivile, String codeStructureBudgetaire, final ApiCallback<PreinformationRecapPaiement> _callback) throws ApiException {

        okhttp3.Call localVarCall = lirePreinformationRecapitulatifPaiementValidateBeforeCall(codeStructure, anneeCivile, codeStructureBudgetaire, _callback);
        Type localVarReturnType = new TypeToken<PreinformationRecapPaiement>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for lireQuittanceHorsVentilation
     * @param codeStructure le code de l&#39;établissement (required)
     * @param anneeCivile année civile (required)
     * @param codeStructureBudgetaire Code de la Structure Budgetaire (required)
     * @param numeroQuittance Le numero de la quittance (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> la date de la quittance pour le numero demandée, objet vide si non trouvé ou ventilé depuis </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireQuittanceHorsVentilationCall(String codeStructure, Integer anneeCivile, String codeStructureBudgetaire, Integer numeroQuittance, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/pai/recapitulatif-quittances/preinformation/quittances/{codeStructure}/{anneeCivile}/{codeStructureBudgetaire}/{numeroQuittance}"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "anneeCivile" + "\\}", localVarApiClient.escapeString(anneeCivile.toString()))
            .replaceAll("\\{" + "codeStructureBudgetaire" + "\\}", localVarApiClient.escapeString(codeStructureBudgetaire.toString()))
            .replaceAll("\\{" + "numeroQuittance" + "\\}", localVarApiClient.escapeString(numeroQuittance.toString()));

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
    private okhttp3.Call lireQuittanceHorsVentilationValidateBeforeCall(String codeStructure, Integer anneeCivile, String codeStructureBudgetaire, Integer numeroQuittance, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling lireQuittanceHorsVentilation(Async)");
        }
        
        // verify the required parameter 'anneeCivile' is set
        if (anneeCivile == null) {
            throw new ApiException("Missing the required parameter 'anneeCivile' when calling lireQuittanceHorsVentilation(Async)");
        }
        
        // verify the required parameter 'codeStructureBudgetaire' is set
        if (codeStructureBudgetaire == null) {
            throw new ApiException("Missing the required parameter 'codeStructureBudgetaire' when calling lireQuittanceHorsVentilation(Async)");
        }
        
        // verify the required parameter 'numeroQuittance' is set
        if (numeroQuittance == null) {
            throw new ApiException("Missing the required parameter 'numeroQuittance' when calling lireQuittanceHorsVentilation(Async)");
        }
        

        okhttp3.Call localVarCall = lireQuittanceHorsVentilationCall(codeStructure, anneeCivile, codeStructureBudgetaire, numeroQuittance, _callback);
        return localVarCall;

    }

    /**
     * retourne une quittance non ventilée
     * retourne une quittance non ventilée
     * @param codeStructure le code de l&#39;établissement (required)
     * @param anneeCivile année civile (required)
     * @param codeStructureBudgetaire Code de la Structure Budgetaire (required)
     * @param numeroQuittance Le numero de la quittance (required)
     * @return QuittanceRecapitulatifPaiement
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> la date de la quittance pour le numero demandée, objet vide si non trouvé ou ventilé depuis </td><td>  -  </td></tr>
     </table>
     */
    public QuittanceRecapitulatifPaiement lireQuittanceHorsVentilation(String codeStructure, Integer anneeCivile, String codeStructureBudgetaire, Integer numeroQuittance) throws ApiException {
        ApiResponse<QuittanceRecapitulatifPaiement> localVarResp = lireQuittanceHorsVentilationWithHttpInfo(codeStructure, anneeCivile, codeStructureBudgetaire, numeroQuittance);
        return localVarResp.getData();
    }

    /**
     * retourne une quittance non ventilée
     * retourne une quittance non ventilée
     * @param codeStructure le code de l&#39;établissement (required)
     * @param anneeCivile année civile (required)
     * @param codeStructureBudgetaire Code de la Structure Budgetaire (required)
     * @param numeroQuittance Le numero de la quittance (required)
     * @return ApiResponse&lt;QuittanceRecapitulatifPaiement&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> la date de la quittance pour le numero demandée, objet vide si non trouvé ou ventilé depuis </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<QuittanceRecapitulatifPaiement> lireQuittanceHorsVentilationWithHttpInfo(String codeStructure, Integer anneeCivile, String codeStructureBudgetaire, Integer numeroQuittance) throws ApiException {
        okhttp3.Call localVarCall = lireQuittanceHorsVentilationValidateBeforeCall(codeStructure, anneeCivile, codeStructureBudgetaire, numeroQuittance, null);
        Type localVarReturnType = new TypeToken<QuittanceRecapitulatifPaiement>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * retourne une quittance non ventilée (asynchronously)
     * retourne une quittance non ventilée
     * @param codeStructure le code de l&#39;établissement (required)
     * @param anneeCivile année civile (required)
     * @param codeStructureBudgetaire Code de la Structure Budgetaire (required)
     * @param numeroQuittance Le numero de la quittance (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> la date de la quittance pour le numero demandée, objet vide si non trouvé ou ventilé depuis </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireQuittanceHorsVentilationAsync(String codeStructure, Integer anneeCivile, String codeStructureBudgetaire, Integer numeroQuittance, final ApiCallback<QuittanceRecapitulatifPaiement> _callback) throws ApiException {

        okhttp3.Call localVarCall = lireQuittanceHorsVentilationValidateBeforeCall(codeStructure, anneeCivile, codeStructureBudgetaire, numeroQuittance, _callback);
        Type localVarReturnType = new TypeToken<QuittanceRecapitulatifPaiement>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for lireRecapitulatifQuittances
     * @param codeStructure le code de l&#39;établissement (required)
     * @param codeStructureBudgetaire Code de la Structure Budgetaire (required)
     * @param anneeCivile année civile (optional)
     * @param dateDebut DEPRECATED (optional)
     * @param dateTimeDebut La date de début au format \&quot;2018-01-31T05:20:30Z\&quot; (optional)
     * @param dateFin DEPRECATED (optional)
     * @param dateTimeFin La date de fin au format \&quot;2018-01-31T05:20:30Z\&quot; (optional)
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
    public okhttp3.Call lireRecapitulatifQuittancesCall(String codeStructure, String codeStructureBudgetaire, Integer anneeCivile, Date dateDebut, Date dateTimeDebut, Date dateFin, Date dateTimeFin, Long numeroQuittanceDebut, Long numeroQuittanceFin, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/pai/recapitulatif-quittances/{codeStructure}"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (anneeCivile != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("anneeCivile", anneeCivile));
        }

        if (codeStructureBudgetaire != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("codeStructureBudgetaire", codeStructureBudgetaire));
        }

        if (dateDebut != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("dateDebut", dateDebut));
        }

        if (dateTimeDebut != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("dateTimeDebut", dateTimeDebut));
        }

        if (dateFin != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("dateFin", dateFin));
        }

        if (dateTimeFin != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("dateTimeFin", dateTimeFin));
        }

        if (numeroQuittanceDebut != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("numeroQuittanceDebut", numeroQuittanceDebut));
        }

        if (numeroQuittanceFin != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("numeroQuittanceFin", numeroQuittanceFin));
        }

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
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "idTokenAuth" };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call lireRecapitulatifQuittancesValidateBeforeCall(String codeStructure, String codeStructureBudgetaire, Integer anneeCivile, Date dateDebut, Date dateTimeDebut, Date dateFin, Date dateTimeFin, Long numeroQuittanceDebut, Long numeroQuittanceFin, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling lireRecapitulatifQuittances(Async)");
        }
        
        // verify the required parameter 'codeStructureBudgetaire' is set
        if (codeStructureBudgetaire == null) {
            throw new ApiException("Missing the required parameter 'codeStructureBudgetaire' when calling lireRecapitulatifQuittances(Async)");
        }
        

        okhttp3.Call localVarCall = lireRecapitulatifQuittancesCall(codeStructure, codeStructureBudgetaire, anneeCivile, dateDebut, dateTimeDebut, dateFin, dateTimeFin, numeroQuittanceDebut, numeroQuittanceFin, _callback);
        return localVarCall;

    }

    /**
     * Récupération du récapitulatif des quittances au format csv
     * Récupération du récapitulatif des quittances au format csv
     * @param codeStructure le code de l&#39;établissement (required)
     * @param codeStructureBudgetaire Code de la Structure Budgetaire (required)
     * @param anneeCivile année civile (optional)
     * @param dateDebut DEPRECATED (optional)
     * @param dateTimeDebut La date de début au format \&quot;2018-01-31T05:20:30Z\&quot; (optional)
     * @param dateFin DEPRECATED (optional)
     * @param dateTimeFin La date de fin au format \&quot;2018-01-31T05:20:30Z\&quot; (optional)
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
    public File lireRecapitulatifQuittances(String codeStructure, String codeStructureBudgetaire, Integer anneeCivile, Date dateDebut, Date dateTimeDebut, Date dateFin, Date dateTimeFin, Long numeroQuittanceDebut, Long numeroQuittanceFin) throws ApiException {
        ApiResponse<File> localVarResp = lireRecapitulatifQuittancesWithHttpInfo(codeStructure, codeStructureBudgetaire, anneeCivile, dateDebut, dateTimeDebut, dateFin, dateTimeFin, numeroQuittanceDebut, numeroQuittanceFin);
        return localVarResp.getData();
    }

    /**
     * Récupération du récapitulatif des quittances au format csv
     * Récupération du récapitulatif des quittances au format csv
     * @param codeStructure le code de l&#39;établissement (required)
     * @param codeStructureBudgetaire Code de la Structure Budgetaire (required)
     * @param anneeCivile année civile (optional)
     * @param dateDebut DEPRECATED (optional)
     * @param dateTimeDebut La date de début au format \&quot;2018-01-31T05:20:30Z\&quot; (optional)
     * @param dateFin DEPRECATED (optional)
     * @param dateTimeFin La date de fin au format \&quot;2018-01-31T05:20:30Z\&quot; (optional)
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
    public ApiResponse<File> lireRecapitulatifQuittancesWithHttpInfo(String codeStructure, String codeStructureBudgetaire, Integer anneeCivile, Date dateDebut, Date dateTimeDebut, Date dateFin, Date dateTimeFin, Long numeroQuittanceDebut, Long numeroQuittanceFin) throws ApiException {
        okhttp3.Call localVarCall = lireRecapitulatifQuittancesValidateBeforeCall(codeStructure, codeStructureBudgetaire, anneeCivile, dateDebut, dateTimeDebut, dateFin, dateTimeFin, numeroQuittanceDebut, numeroQuittanceFin, null);
        Type localVarReturnType = new TypeToken<File>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Récupération du récapitulatif des quittances au format csv (asynchronously)
     * Récupération du récapitulatif des quittances au format csv
     * @param codeStructure le code de l&#39;établissement (required)
     * @param codeStructureBudgetaire Code de la Structure Budgetaire (required)
     * @param anneeCivile année civile (optional)
     * @param dateDebut DEPRECATED (optional)
     * @param dateTimeDebut La date de début au format \&quot;2018-01-31T05:20:30Z\&quot; (optional)
     * @param dateFin DEPRECATED (optional)
     * @param dateTimeFin La date de fin au format \&quot;2018-01-31T05:20:30Z\&quot; (optional)
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
    public okhttp3.Call lireRecapitulatifQuittancesAsync(String codeStructure, String codeStructureBudgetaire, Integer anneeCivile, Date dateDebut, Date dateTimeDebut, Date dateFin, Date dateTimeFin, Long numeroQuittanceDebut, Long numeroQuittanceFin, final ApiCallback<File> _callback) throws ApiException {

        okhttp3.Call localVarCall = lireRecapitulatifQuittancesValidateBeforeCall(codeStructure, codeStructureBudgetaire, anneeCivile, dateDebut, dateTimeDebut, dateFin, dateTimeFin, numeroQuittanceDebut, numeroQuittanceFin, _callback);
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
        String localVarPath = "/pai/quittances/{codeStructure}"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (pageable != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("pageable", pageable));
        }

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
