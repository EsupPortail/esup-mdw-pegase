/*
 * INS Gestion V5
 * Il s'agit de l'API v5 de gestion - INS  __Apprenant :__ une personne qui a au moins une inscription validée dans Pegase.  __Inscription :__ se définit par une cible sur une période de mise en œuvre pour un apprenant. Une inscription peut prendre deux états : soit validée, soit annulée.  __Actualisation :__ permet de modifier les données liées à l’apprenant ou à l’inscription alors que la piste a déjà été payée ou validée.   __Gestion des erreurs :__   __200, 201 :__ opération effectuée   __400 :__ erreur de données sur les formats   __403 :__ accès refusé   __404 :__ contenu introuvable   __409 :__ donnée déjà existante   __500 :__ erreur serveur  # Changement majeur/cassant par rapport à V4  1. Suppression de `Inscription.noCandidat`.  1. Ajout de `VoeuBase.noCandidat` et `InscriptionComplete.noCandidat`.  1. Ajout de `VoeuBase.choisi`. 
 *
 * The version of the OpenAPI document: 20.0.0
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


import fr.univlorraine.pegase.model.insgestion.DemandeDePiece;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemandeDePiecesApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public DemandeDePiecesApi() {
        this(Configuration.getDefaultApiClient());
    }

    public DemandeDePiecesApi(ApiClient apiClient) {
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
     * Build call for creerDemandeDePiece
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param demandeDePiece La demande de pièce à ajouter (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Demande de pièce créée </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Structure introuvable </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Données non valides </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call creerDemandeDePieceCall(String codeStructure, DemandeDePiece demandeDePiece, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = demandeDePiece;

        // create path and map variables
        String localVarPath = "/gestion/demandes-de-pieces/{codeStructure}/"
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
    private okhttp3.Call creerDemandeDePieceValidateBeforeCall(String codeStructure, DemandeDePiece demandeDePiece, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling creerDemandeDePiece(Async)");
        }
        
        // verify the required parameter 'demandeDePiece' is set
        if (demandeDePiece == null) {
            throw new ApiException("Missing the required parameter 'demandeDePiece' when calling creerDemandeDePiece(Async)");
        }
        

        okhttp3.Call localVarCall = creerDemandeDePieceCall(codeStructure, demandeDePiece, _callback);
        return localVarCall;

    }

    /**
     * Ajout d&#39;une demande de pièce pour un établissement [usage QA]
     * Ajout d&#39;une demande de pièce pour un établissement
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param demandeDePiece La demande de pièce à ajouter (required)
     * @return DemandeDePiece
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Demande de pièce créée </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Structure introuvable </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Données non valides </td><td>  -  </td></tr>
     </table>
     */
    public DemandeDePiece creerDemandeDePiece(String codeStructure, DemandeDePiece demandeDePiece) throws ApiException {
        ApiResponse<DemandeDePiece> localVarResp = creerDemandeDePieceWithHttpInfo(codeStructure, demandeDePiece);
        return localVarResp.getData();
    }

    /**
     * Ajout d&#39;une demande de pièce pour un établissement [usage QA]
     * Ajout d&#39;une demande de pièce pour un établissement
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param demandeDePiece La demande de pièce à ajouter (required)
     * @return ApiResponse&lt;DemandeDePiece&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Demande de pièce créée </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Structure introuvable </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Données non valides </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<DemandeDePiece> creerDemandeDePieceWithHttpInfo(String codeStructure, DemandeDePiece demandeDePiece) throws ApiException {
        okhttp3.Call localVarCall = creerDemandeDePieceValidateBeforeCall(codeStructure, demandeDePiece, null);
        Type localVarReturnType = new TypeToken<DemandeDePiece>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Ajout d&#39;une demande de pièce pour un établissement [usage QA] (asynchronously)
     * Ajout d&#39;une demande de pièce pour un établissement
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param demandeDePiece La demande de pièce à ajouter (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Demande de pièce créée </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Structure introuvable </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Données non valides </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call creerDemandeDePieceAsync(String codeStructure, DemandeDePiece demandeDePiece, final ApiCallback<DemandeDePiece> _callback) throws ApiException {

        okhttp3.Call localVarCall = creerDemandeDePieceValidateBeforeCall(codeStructure, demandeDePiece, _callback);
        Type localVarReturnType = new TypeToken<DemandeDePiece>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for listerDemandeDePieces
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Paramétrage des demandes de pièces récupéré </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Structure introuvable </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call listerDemandeDePiecesCall(String codeStructure, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/gestion/demandes-de-pieces/{codeStructure}/"
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
    private okhttp3.Call listerDemandeDePiecesValidateBeforeCall(String codeStructure, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling listerDemandeDePieces(Async)");
        }
        

        okhttp3.Call localVarCall = listerDemandeDePiecesCall(codeStructure, _callback);
        return localVarCall;

    }

    /**
     * Paramétrage des demandes de pièces pour un établissement
     * Paramétrage des demandes de pièces pour un établissement
     * @param codeStructure Le code de l&#39;établissement (required)
     * @return List&lt;DemandeDePiece&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Paramétrage des demandes de pièces récupéré </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Structure introuvable </td><td>  -  </td></tr>
     </table>
     */
    public List<DemandeDePiece> listerDemandeDePieces(String codeStructure) throws ApiException {
        ApiResponse<List<DemandeDePiece>> localVarResp = listerDemandeDePiecesWithHttpInfo(codeStructure);
        return localVarResp.getData();
    }

    /**
     * Paramétrage des demandes de pièces pour un établissement
     * Paramétrage des demandes de pièces pour un établissement
     * @param codeStructure Le code de l&#39;établissement (required)
     * @return ApiResponse&lt;List&lt;DemandeDePiece&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Paramétrage des demandes de pièces récupéré </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Structure introuvable </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<DemandeDePiece>> listerDemandeDePiecesWithHttpInfo(String codeStructure) throws ApiException {
        okhttp3.Call localVarCall = listerDemandeDePiecesValidateBeforeCall(codeStructure, null);
        Type localVarReturnType = new TypeToken<List<DemandeDePiece>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Paramétrage des demandes de pièces pour un établissement (asynchronously)
     * Paramétrage des demandes de pièces pour un établissement
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Paramétrage des demandes de pièces récupéré </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Structure introuvable </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call listerDemandeDePiecesAsync(String codeStructure, final ApiCallback<List<DemandeDePiece>> _callback) throws ApiException {

        okhttp3.Call localVarCall = listerDemandeDePiecesValidateBeforeCall(codeStructure, _callback);
        Type localVarReturnType = new TypeToken<List<DemandeDePiece>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for modifierDemandeDePiece
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param codeDemandeDePiece Le code de la demande de pièce (required)
     * @param demandeDePiece La demande de pièce à modifier (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Demande de pièce mise à jour </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Structure ou Demande de pièce introuvable </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Données non valides </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call modifierDemandeDePieceCall(String codeStructure, String codeDemandeDePiece, DemandeDePiece demandeDePiece, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = demandeDePiece;

        // create path and map variables
        String localVarPath = "/gestion/demandes-de-pieces/{codeStructure}/{codeDemandeDePiece}"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "codeDemandeDePiece" + "\\}", localVarApiClient.escapeString(codeDemandeDePiece.toString()));

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
        return localVarApiClient.buildCall(basePath, localVarPath, "PUT", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call modifierDemandeDePieceValidateBeforeCall(String codeStructure, String codeDemandeDePiece, DemandeDePiece demandeDePiece, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling modifierDemandeDePiece(Async)");
        }
        
        // verify the required parameter 'codeDemandeDePiece' is set
        if (codeDemandeDePiece == null) {
            throw new ApiException("Missing the required parameter 'codeDemandeDePiece' when calling modifierDemandeDePiece(Async)");
        }
        
        // verify the required parameter 'demandeDePiece' is set
        if (demandeDePiece == null) {
            throw new ApiException("Missing the required parameter 'demandeDePiece' when calling modifierDemandeDePiece(Async)");
        }
        

        okhttp3.Call localVarCall = modifierDemandeDePieceCall(codeStructure, codeDemandeDePiece, demandeDePiece, _callback);
        return localVarCall;

    }

    /**
     * Modifier une demande de pièce pour un établissement
     * Modifier une demande de pièce pour un établissement
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param codeDemandeDePiece Le code de la demande de pièce (required)
     * @param demandeDePiece La demande de pièce à modifier (required)
     * @return DemandeDePiece
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Demande de pièce mise à jour </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Structure ou Demande de pièce introuvable </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Données non valides </td><td>  -  </td></tr>
     </table>
     */
    public DemandeDePiece modifierDemandeDePiece(String codeStructure, String codeDemandeDePiece, DemandeDePiece demandeDePiece) throws ApiException {
        ApiResponse<DemandeDePiece> localVarResp = modifierDemandeDePieceWithHttpInfo(codeStructure, codeDemandeDePiece, demandeDePiece);
        return localVarResp.getData();
    }

    /**
     * Modifier une demande de pièce pour un établissement
     * Modifier une demande de pièce pour un établissement
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param codeDemandeDePiece Le code de la demande de pièce (required)
     * @param demandeDePiece La demande de pièce à modifier (required)
     * @return ApiResponse&lt;DemandeDePiece&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Demande de pièce mise à jour </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Structure ou Demande de pièce introuvable </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Données non valides </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<DemandeDePiece> modifierDemandeDePieceWithHttpInfo(String codeStructure, String codeDemandeDePiece, DemandeDePiece demandeDePiece) throws ApiException {
        okhttp3.Call localVarCall = modifierDemandeDePieceValidateBeforeCall(codeStructure, codeDemandeDePiece, demandeDePiece, null);
        Type localVarReturnType = new TypeToken<DemandeDePiece>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Modifier une demande de pièce pour un établissement (asynchronously)
     * Modifier une demande de pièce pour un établissement
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param codeDemandeDePiece Le code de la demande de pièce (required)
     * @param demandeDePiece La demande de pièce à modifier (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Demande de pièce mise à jour </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Structure ou Demande de pièce introuvable </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Données non valides </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call modifierDemandeDePieceAsync(String codeStructure, String codeDemandeDePiece, DemandeDePiece demandeDePiece, final ApiCallback<DemandeDePiece> _callback) throws ApiException {

        okhttp3.Call localVarCall = modifierDemandeDePieceValidateBeforeCall(codeStructure, codeDemandeDePiece, demandeDePiece, _callback);
        Type localVarReturnType = new TypeToken<DemandeDePiece>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
