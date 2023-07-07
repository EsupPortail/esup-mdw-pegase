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

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import fr.univlorraine.pegase.api.ApiCallback;
import fr.univlorraine.pegase.api.ApiClient;
import fr.univlorraine.pegase.api.ApiException;
import fr.univlorraine.pegase.api.ApiResponse;
import fr.univlorraine.pegase.api.Configuration;
import fr.univlorraine.pegase.api.Pair;
import fr.univlorraine.pegase.model.insgestion.DocumentAApprouver;

public class DocumentAApprouverApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public DocumentAApprouverApi() {
        this(Configuration.getDefaultApiClient());
    }

    public DocumentAApprouverApi(ApiClient apiClient) {
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
     * Build call for creerDocumentAApprouver
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param documentAApprouver L&#39;accord (document à approuver) à ajouter (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Accord (document à approuver) créé </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Structure introuvable </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Données non valides </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call creerDocumentAApprouverCall(String codeStructure, DocumentAApprouver documentAApprouver, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = documentAApprouver;

        // create path and map variables
        String localVarPath = "/gestion/documents-a-approuver/{codeStructure}/"
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
    private okhttp3.Call creerDocumentAApprouverValidateBeforeCall(String codeStructure, DocumentAApprouver documentAApprouver, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling creerDocumentAApprouver(Async)");
        }
        
        // verify the required parameter 'documentAApprouver' is set
        if (documentAApprouver == null) {
            throw new ApiException("Missing the required parameter 'documentAApprouver' when calling creerDocumentAApprouver(Async)");
        }
        

        okhttp3.Call localVarCall = creerDocumentAApprouverCall(codeStructure, documentAApprouver, _callback);
        return localVarCall;

    }

    /**
     * Ajout d&#39;un accord (document à approuver) pour un établissement [usage QA]
     * Ajout d&#39;un accord (document à approuver) pour un établissement
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param documentAApprouver L&#39;accord (document à approuver) à ajouter (required)
     * @return DocumentAApprouver
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Accord (document à approuver) créé </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Structure introuvable </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Données non valides </td><td>  -  </td></tr>
     </table>
     */
    public DocumentAApprouver creerDocumentAApprouver(String codeStructure, DocumentAApprouver documentAApprouver) throws ApiException {
        ApiResponse<DocumentAApprouver> localVarResp = creerDocumentAApprouverWithHttpInfo(codeStructure, documentAApprouver);
        return localVarResp.getData();
    }

    /**
     * Ajout d&#39;un accord (document à approuver) pour un établissement [usage QA]
     * Ajout d&#39;un accord (document à approuver) pour un établissement
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param documentAApprouver L&#39;accord (document à approuver) à ajouter (required)
     * @return ApiResponse&lt;DocumentAApprouver&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Accord (document à approuver) créé </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Structure introuvable </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Données non valides </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<DocumentAApprouver> creerDocumentAApprouverWithHttpInfo(String codeStructure, DocumentAApprouver documentAApprouver) throws ApiException {
        okhttp3.Call localVarCall = creerDocumentAApprouverValidateBeforeCall(codeStructure, documentAApprouver, null);
        Type localVarReturnType = new TypeToken<DocumentAApprouver>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Ajout d&#39;un accord (document à approuver) pour un établissement [usage QA] (asynchronously)
     * Ajout d&#39;un accord (document à approuver) pour un établissement
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param documentAApprouver L&#39;accord (document à approuver) à ajouter (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Accord (document à approuver) créé </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Structure introuvable </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Données non valides </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call creerDocumentAApprouverAsync(String codeStructure, DocumentAApprouver documentAApprouver, final ApiCallback<DocumentAApprouver> _callback) throws ApiException {

        okhttp3.Call localVarCall = creerDocumentAApprouverValidateBeforeCall(codeStructure, documentAApprouver, _callback);
        Type localVarReturnType = new TypeToken<DocumentAApprouver>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for listerDocumentsAApprouver
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Paramétrage des accords (documents à approuver) récupéré </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Structure introuvable </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call listerDocumentsAApprouverCall(String codeStructure, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/gestion/documents-a-approuver/{codeStructure}/"
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
    private okhttp3.Call listerDocumentsAApprouverValidateBeforeCall(String codeStructure, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling listerDocumentsAApprouver(Async)");
        }
        

        okhttp3.Call localVarCall = listerDocumentsAApprouverCall(codeStructure, _callback);
        return localVarCall;

    }

    /**
     * Paramétrage des accords (documents à approuver) pour un établissement
     * Paramétrage des accords (documents à approuver) pour un établissement
     * @param codeStructure Le code de l&#39;établissement (required)
     * @return List&lt;DocumentAApprouver&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Paramétrage des accords (documents à approuver) récupéré </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Structure introuvable </td><td>  -  </td></tr>
     </table>
     */
    public List<DocumentAApprouver> listerDocumentsAApprouver(String codeStructure) throws ApiException {
        ApiResponse<List<DocumentAApprouver>> localVarResp = listerDocumentsAApprouverWithHttpInfo(codeStructure);
        return localVarResp.getData();
    }

    /**
     * Paramétrage des accords (documents à approuver) pour un établissement
     * Paramétrage des accords (documents à approuver) pour un établissement
     * @param codeStructure Le code de l&#39;établissement (required)
     * @return ApiResponse&lt;List&lt;DocumentAApprouver&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Paramétrage des accords (documents à approuver) récupéré </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Structure introuvable </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<DocumentAApprouver>> listerDocumentsAApprouverWithHttpInfo(String codeStructure) throws ApiException {
        okhttp3.Call localVarCall = listerDocumentsAApprouverValidateBeforeCall(codeStructure, null);
        Type localVarReturnType = new TypeToken<List<DocumentAApprouver>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Paramétrage des accords (documents à approuver) pour un établissement (asynchronously)
     * Paramétrage des accords (documents à approuver) pour un établissement
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Paramétrage des accords (documents à approuver) récupéré </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Structure introuvable </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call listerDocumentsAApprouverAsync(String codeStructure, final ApiCallback<List<DocumentAApprouver>> _callback) throws ApiException {

        okhttp3.Call localVarCall = listerDocumentsAApprouverValidateBeforeCall(codeStructure, _callback);
        Type localVarReturnType = new TypeToken<List<DocumentAApprouver>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
