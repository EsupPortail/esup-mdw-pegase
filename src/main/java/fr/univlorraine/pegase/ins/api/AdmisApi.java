/*
 * API INS V1
 *  # Introduction  Liste l'ensemble des services et des opérations disponibles dans le module INS  Description service INS  # Gestion des erreurs  ## StatusCode  | Code    | Description                                | |---------|--------------------------------------------| | 200     | Opération effectuée                        | |         | Cas particulier: Dans le cas d'APIs de     | |         | type bulk, un 200 peut aussi être retourné | |         | si des données de la requête sont          | |         | considérées en erreur                      | | 201     | Ressource créée                            | | 400     | Données envoyées par le client invalides   | | 403     | Accès refusé                               | | 404     | Ressource inexistante                      | | 409     | donnée déjà existante                      | | 500     | Erreur technique rencontrée par le serveur |   ## Codes d'erreurs  | Code      | Description                                | |-----------|--------------------------------------------| | notNull   | la propriété est obligatoire               | | notBlank  | la propriété ne doit pas être vide         | | size      | la longueur de la propriété est invalide   | | pattern   | les caractères ou la syntaxe de            | |           | la propriété est invalide                  | | genre     | le genre de la personne est invalide       | | dateEntre | la date est invalide                       | | telephone | le téléphone est invalide                  | | email     | le mail est invalide                       | 
 *
 * The version of the OpenAPI document: 1.0.0-rc.20250414083300
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.ins.api;

import fr.univlorraine.pegase.ins.invoker.ApiCallback;
import fr.univlorraine.pegase.ins.invoker.ApiClient;
import fr.univlorraine.pegase.ins.invoker.ApiException;
import fr.univlorraine.pegase.ins.invoker.ApiResponse;
import fr.univlorraine.pegase.ins.invoker.Configuration;
import fr.univlorraine.pegase.ins.invoker.Pair;
import fr.univlorraine.pegase.ins.invoker.ProgressRequestBody;
import fr.univlorraine.pegase.ins.invoker.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import java.io.File;
import fr.univlorraine.pegase.ins.model.FiltreAvancementImports;
import fr.univlorraine.pegase.ins.model.Pageable;
import fr.univlorraine.pegase.ins.model.PagedAvancementImport;
import java.util.UUID;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdmisApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public AdmisApi() {
        this(Configuration.getDefaultApiClient());
    }

    public AdmisApi(ApiClient apiClient) {
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
     * Build call for importerAdmis
     * @param codeStructure Le code structure de l&#39;établissement. (required)
     * @param admis Le csv des admis à importer (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Import des admis lancé avec succès </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Fichier CSV invalide ou fichier non CSV </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> L&#39;import des admis n&#39;a pu être lancé Un fichier CSV est retourné listant toutes les erreurs du CSV importé  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call importerAdmisCall(String codeStructure, File admis, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/etablissement/{codeStructure}/admis/import"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (admis != null) {
            localVarFormParams.put("admis", admis);
        }

        final String[] localVarAccepts = {
            "text/csv"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "multipart/form-data"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "idTokenAuth" };
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call importerAdmisValidateBeforeCall(String codeStructure, File admis, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling importerAdmis(Async)");
        }
        
        // verify the required parameter 'admis' is set
        if (admis == null) {
            throw new ApiException("Missing the required parameter 'admis' when calling importerAdmis(Async)");
        }
        

        okhttp3.Call localVarCall = importerAdmisCall(codeStructure, admis, _callback);
        return localVarCall;

    }

    /**
     * Importer les admis
     * 
     * @param codeStructure Le code structure de l&#39;établissement. (required)
     * @param admis Le csv des admis à importer (required)
     * @return File
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Import des admis lancé avec succès </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Fichier CSV invalide ou fichier non CSV </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> L&#39;import des admis n&#39;a pu être lancé Un fichier CSV est retourné listant toutes les erreurs du CSV importé  </td><td>  -  </td></tr>
     </table>
     */
    public File importerAdmis(String codeStructure, File admis) throws ApiException {
        ApiResponse<File> localVarResp = importerAdmisWithHttpInfo(codeStructure, admis);
        return localVarResp.getData();
    }

    /**
     * Importer les admis
     * 
     * @param codeStructure Le code structure de l&#39;établissement. (required)
     * @param admis Le csv des admis à importer (required)
     * @return ApiResponse&lt;File&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Import des admis lancé avec succès </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Fichier CSV invalide ou fichier non CSV </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> L&#39;import des admis n&#39;a pu être lancé Un fichier CSV est retourné listant toutes les erreurs du CSV importé  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<File> importerAdmisWithHttpInfo(String codeStructure, File admis) throws ApiException {
        okhttp3.Call localVarCall = importerAdmisValidateBeforeCall(codeStructure, admis, null);
        Type localVarReturnType = new TypeToken<File>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Importer les admis (asynchronously)
     * 
     * @param codeStructure Le code structure de l&#39;établissement. (required)
     * @param admis Le csv des admis à importer (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Import des admis lancé avec succès </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Fichier CSV invalide ou fichier non CSV </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> L&#39;import des admis n&#39;a pu être lancé Un fichier CSV est retourné listant toutes les erreurs du CSV importé  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call importerAdmisAsync(String codeStructure, File admis, final ApiCallback<File> _callback) throws ApiException {

        okhttp3.Call localVarCall = importerAdmisValidateBeforeCall(codeStructure, admis, _callback);
        Type localVarReturnType = new TypeToken<File>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for listerAvancementImports
     * @param codeStructure Le code structure de l&#39;établissement. (required)
     * @param filtreAvancementImports  (optional)
     * @param pageable  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Retourne la liste des avancements d&#39;imports </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Un paramètre de filtrage est invalide </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call listerAvancementImportsCall(String codeStructure, FiltreAvancementImports filtreAvancementImports, Pageable pageable, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/etablissement/{codeStructure}/admis/import"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (filtreAvancementImports != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("filtreAvancementImports", filtreAvancementImports));
        }

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
    private okhttp3.Call listerAvancementImportsValidateBeforeCall(String codeStructure, FiltreAvancementImports filtreAvancementImports, Pageable pageable, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling listerAvancementImports(Async)");
        }
        

        okhttp3.Call localVarCall = listerAvancementImportsCall(codeStructure, filtreAvancementImports, pageable, _callback);
        return localVarCall;

    }

    /**
     * Récupérer l&#39;état des imports
     * 
     * @param codeStructure Le code structure de l&#39;établissement. (required)
     * @param filtreAvancementImports  (optional)
     * @param pageable  (optional)
     * @return PagedAvancementImport
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Retourne la liste des avancements d&#39;imports </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Un paramètre de filtrage est invalide </td><td>  -  </td></tr>
     </table>
     */
    public PagedAvancementImport listerAvancementImports(String codeStructure, FiltreAvancementImports filtreAvancementImports, Pageable pageable) throws ApiException {
        ApiResponse<PagedAvancementImport> localVarResp = listerAvancementImportsWithHttpInfo(codeStructure, filtreAvancementImports, pageable);
        return localVarResp.getData();
    }

    /**
     * Récupérer l&#39;état des imports
     * 
     * @param codeStructure Le code structure de l&#39;établissement. (required)
     * @param filtreAvancementImports  (optional)
     * @param pageable  (optional)
     * @return ApiResponse&lt;PagedAvancementImport&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Retourne la liste des avancements d&#39;imports </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Un paramètre de filtrage est invalide </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<PagedAvancementImport> listerAvancementImportsWithHttpInfo(String codeStructure, FiltreAvancementImports filtreAvancementImports, Pageable pageable) throws ApiException {
        okhttp3.Call localVarCall = listerAvancementImportsValidateBeforeCall(codeStructure, filtreAvancementImports, pageable, null);
        Type localVarReturnType = new TypeToken<PagedAvancementImport>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Récupérer l&#39;état des imports (asynchronously)
     * 
     * @param codeStructure Le code structure de l&#39;établissement. (required)
     * @param filtreAvancementImports  (optional)
     * @param pageable  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Retourne la liste des avancements d&#39;imports </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Un paramètre de filtrage est invalide </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call listerAvancementImportsAsync(String codeStructure, FiltreAvancementImports filtreAvancementImports, Pageable pageable, final ApiCallback<PagedAvancementImport> _callback) throws ApiException {

        okhttp3.Call localVarCall = listerAvancementImportsValidateBeforeCall(codeStructure, filtreAvancementImports, pageable, _callback);
        Type localVarReturnType = new TypeToken<PagedAvancementImport>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for telechargerRapportErreursImport
     * @param codeStructure Le code structure de l&#39;établissement. (required)
     * @param idCsvJob  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Rapport d&#39;erreurs au format CSV du Job d&#39;id idCsvJob </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call telechargerRapportErreursImportCall(String codeStructure, UUID idCsvJob, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/etablissement/{codeStructure}/admis/import/rapport-erreurs"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (idCsvJob != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("idCsvJob", idCsvJob));
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
    private okhttp3.Call telechargerRapportErreursImportValidateBeforeCall(String codeStructure, UUID idCsvJob, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling telechargerRapportErreursImport(Async)");
        }
        

        okhttp3.Call localVarCall = telechargerRapportErreursImportCall(codeStructure, idCsvJob, _callback);
        return localVarCall;

    }

    /**
     * Télécharger le rapport d&#39;erreurs au format CSV pour un Job d&#39;import donné
     * 
     * @param codeStructure Le code structure de l&#39;établissement. (required)
     * @param idCsvJob  (optional)
     * @return File
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Rapport d&#39;erreurs au format CSV du Job d&#39;id idCsvJob </td><td>  -  </td></tr>
     </table>
     */
    public File telechargerRapportErreursImport(String codeStructure, UUID idCsvJob) throws ApiException {
        ApiResponse<File> localVarResp = telechargerRapportErreursImportWithHttpInfo(codeStructure, idCsvJob);
        return localVarResp.getData();
    }

    /**
     * Télécharger le rapport d&#39;erreurs au format CSV pour un Job d&#39;import donné
     * 
     * @param codeStructure Le code structure de l&#39;établissement. (required)
     * @param idCsvJob  (optional)
     * @return ApiResponse&lt;File&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Rapport d&#39;erreurs au format CSV du Job d&#39;id idCsvJob </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<File> telechargerRapportErreursImportWithHttpInfo(String codeStructure, UUID idCsvJob) throws ApiException {
        okhttp3.Call localVarCall = telechargerRapportErreursImportValidateBeforeCall(codeStructure, idCsvJob, null);
        Type localVarReturnType = new TypeToken<File>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Télécharger le rapport d&#39;erreurs au format CSV pour un Job d&#39;import donné (asynchronously)
     * 
     * @param codeStructure Le code structure de l&#39;établissement. (required)
     * @param idCsvJob  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Rapport d&#39;erreurs au format CSV du Job d&#39;id idCsvJob </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call telechargerRapportErreursImportAsync(String codeStructure, UUID idCsvJob, final ApiCallback<File> _callback) throws ApiException {

        okhttp3.Call localVarCall = telechargerRapportErreursImportValidateBeforeCall(codeStructure, idCsvJob, _callback);
        Type localVarReturnType = new TypeToken<File>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
