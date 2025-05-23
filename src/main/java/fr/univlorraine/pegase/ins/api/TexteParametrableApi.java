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


import fr.univlorraine.pegase.ins.model.TexteParametrable;
import fr.univlorraine.pegase.ins.model.TexteParametrableRequest;
import fr.univlorraine.pegase.ins.model.TypeTexte;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TexteParametrableApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public TexteParametrableApi() {
        this(Configuration.getDefaultApiClient());
    }

    public TexteParametrableApi(ApiClient apiClient) {
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
     * Build call for listerTextesParametrables
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Liste des textes parametrables </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Structure introuvable. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Accès refusé. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Aucun texte parametrable trouvé </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Erreur serveur. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call listerTextesParametrablesCall(String codeStructure, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/etablissement/{codeStructure}/textes-parametrables"
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

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call listerTextesParametrablesValidateBeforeCall(String codeStructure, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling listerTextesParametrables(Async)");
        }
        

        okhttp3.Call localVarCall = listerTextesParametrablesCall(codeStructure, _callback);
        return localVarCall;

    }

    /**
     * Liste des textes parametrables
     * API permettant de lire la liste des texte parametrables
     * @param codeStructure Le code de l&#39;établissement (required)
     * @return List&lt;TexteParametrable&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Liste des textes parametrables </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Structure introuvable. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Accès refusé. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Aucun texte parametrable trouvé </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Erreur serveur. </td><td>  -  </td></tr>
     </table>
     */
    public List<TexteParametrable> listerTextesParametrables(String codeStructure) throws ApiException {
        ApiResponse<List<TexteParametrable>> localVarResp = listerTextesParametrablesWithHttpInfo(codeStructure);
        return localVarResp.getData();
    }

    /**
     * Liste des textes parametrables
     * API permettant de lire la liste des texte parametrables
     * @param codeStructure Le code de l&#39;établissement (required)
     * @return ApiResponse&lt;List&lt;TexteParametrable&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Liste des textes parametrables </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Structure introuvable. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Accès refusé. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Aucun texte parametrable trouvé </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Erreur serveur. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<TexteParametrable>> listerTextesParametrablesWithHttpInfo(String codeStructure) throws ApiException {
        okhttp3.Call localVarCall = listerTextesParametrablesValidateBeforeCall(codeStructure, null);
        Type localVarReturnType = new TypeToken<List<TexteParametrable>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Liste des textes parametrables (asynchronously)
     * API permettant de lire la liste des texte parametrables
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Liste des textes parametrables </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Structure introuvable. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Accès refusé. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Aucun texte parametrable trouvé </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Erreur serveur. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call listerTextesParametrablesAsync(String codeStructure, final ApiCallback<List<TexteParametrable>> _callback) throws ApiException {

        okhttp3.Call localVarCall = listerTextesParametrablesValidateBeforeCall(codeStructure, _callback);
        Type localVarReturnType = new TypeToken<List<TexteParametrable>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for modifierTexteParametrable
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param typeTexte Le type de texte saisissable (required)
     * @param texteParametrableRequest Le contenu HTML du texte saisie (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Texte paramétrable enregistré </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Structure introuvable. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Accès refusé. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Texte introuvable </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Erreur serveur. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call modifierTexteParametrableCall(String codeStructure, TypeTexte typeTexte, TexteParametrableRequest texteParametrableRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = texteParametrableRequest;

        // create path and map variables
        String localVarPath = "/etablissement/{codeStructure}/{typeTexte}/texte-parametrable"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "typeTexte" + "\\}", localVarApiClient.escapeString(typeTexte.toString()));

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
    private okhttp3.Call modifierTexteParametrableValidateBeforeCall(String codeStructure, TypeTexte typeTexte, TexteParametrableRequest texteParametrableRequest, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling modifierTexteParametrable(Async)");
        }
        
        // verify the required parameter 'typeTexte' is set
        if (typeTexte == null) {
            throw new ApiException("Missing the required parameter 'typeTexte' when calling modifierTexteParametrable(Async)");
        }
        
        // verify the required parameter 'texteParametrableRequest' is set
        if (texteParametrableRequest == null) {
            throw new ApiException("Missing the required parameter 'texteParametrableRequest' when calling modifierTexteParametrable(Async)");
        }
        

        okhttp3.Call localVarCall = modifierTexteParametrableCall(codeStructure, typeTexte, texteParametrableRequest, _callback);
        return localVarCall;

    }

    /**
     * Mise à jour ou ajout d&#39;un texte paramétrable saisie
     * Mise à jour ou ajout d&#39;un texte paramétrable saisie
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param typeTexte Le type de texte saisissable (required)
     * @param texteParametrableRequest Le contenu HTML du texte saisie (required)
     * @return TexteParametrable
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Texte paramétrable enregistré </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Structure introuvable. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Accès refusé. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Texte introuvable </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Erreur serveur. </td><td>  -  </td></tr>
     </table>
     */
    public TexteParametrable modifierTexteParametrable(String codeStructure, TypeTexte typeTexte, TexteParametrableRequest texteParametrableRequest) throws ApiException {
        ApiResponse<TexteParametrable> localVarResp = modifierTexteParametrableWithHttpInfo(codeStructure, typeTexte, texteParametrableRequest);
        return localVarResp.getData();
    }

    /**
     * Mise à jour ou ajout d&#39;un texte paramétrable saisie
     * Mise à jour ou ajout d&#39;un texte paramétrable saisie
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param typeTexte Le type de texte saisissable (required)
     * @param texteParametrableRequest Le contenu HTML du texte saisie (required)
     * @return ApiResponse&lt;TexteParametrable&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Texte paramétrable enregistré </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Structure introuvable. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Accès refusé. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Texte introuvable </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Erreur serveur. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<TexteParametrable> modifierTexteParametrableWithHttpInfo(String codeStructure, TypeTexte typeTexte, TexteParametrableRequest texteParametrableRequest) throws ApiException {
        okhttp3.Call localVarCall = modifierTexteParametrableValidateBeforeCall(codeStructure, typeTexte, texteParametrableRequest, null);
        Type localVarReturnType = new TypeToken<TexteParametrable>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Mise à jour ou ajout d&#39;un texte paramétrable saisie (asynchronously)
     * Mise à jour ou ajout d&#39;un texte paramétrable saisie
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param typeTexte Le type de texte saisissable (required)
     * @param texteParametrableRequest Le contenu HTML du texte saisie (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Texte paramétrable enregistré </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Structure introuvable. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Accès refusé. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Texte introuvable </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Erreur serveur. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call modifierTexteParametrableAsync(String codeStructure, TypeTexte typeTexte, TexteParametrableRequest texteParametrableRequest, final ApiCallback<TexteParametrable> _callback) throws ApiException {

        okhttp3.Call localVarCall = modifierTexteParametrableValidateBeforeCall(codeStructure, typeTexte, texteParametrableRequest, _callback);
        Type localVarReturnType = new TypeToken<TexteParametrable>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for recupererTexteParametrable
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param typeTexte Le type de texte saisissable (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Texte paramétrable récupéré </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Structure introuvable. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Accès refusé. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Texte introuvable </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Erreur serveur. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call recupererTexteParametrableCall(String codeStructure, TypeTexte typeTexte, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/etablissement/{codeStructure}/{typeTexte}/texte-parametrable"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "typeTexte" + "\\}", localVarApiClient.escapeString(typeTexte.toString()));

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

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call recupererTexteParametrableValidateBeforeCall(String codeStructure, TypeTexte typeTexte, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling recupererTexteParametrable(Async)");
        }
        
        // verify the required parameter 'typeTexte' is set
        if (typeTexte == null) {
            throw new ApiException("Missing the required parameter 'typeTexte' when calling recupererTexteParametrable(Async)");
        }
        

        okhttp3.Call localVarCall = recupererTexteParametrableCall(codeStructure, typeTexte, _callback);
        return localVarCall;

    }

    /**
     * Récupération du texte paramétrable pour un type donnée et pour un établissement
     * Récupération du texte paramétrable pour un type donnée et pour un établissement
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param typeTexte Le type de texte saisissable (required)
     * @return TexteParametrable
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Texte paramétrable récupéré </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Structure introuvable. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Accès refusé. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Texte introuvable </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Erreur serveur. </td><td>  -  </td></tr>
     </table>
     */
    public TexteParametrable recupererTexteParametrable(String codeStructure, TypeTexte typeTexte) throws ApiException {
        ApiResponse<TexteParametrable> localVarResp = recupererTexteParametrableWithHttpInfo(codeStructure, typeTexte);
        return localVarResp.getData();
    }

    /**
     * Récupération du texte paramétrable pour un type donnée et pour un établissement
     * Récupération du texte paramétrable pour un type donnée et pour un établissement
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param typeTexte Le type de texte saisissable (required)
     * @return ApiResponse&lt;TexteParametrable&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Texte paramétrable récupéré </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Structure introuvable. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Accès refusé. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Texte introuvable </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Erreur serveur. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<TexteParametrable> recupererTexteParametrableWithHttpInfo(String codeStructure, TypeTexte typeTexte) throws ApiException {
        okhttp3.Call localVarCall = recupererTexteParametrableValidateBeforeCall(codeStructure, typeTexte, null);
        Type localVarReturnType = new TypeToken<TexteParametrable>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Récupération du texte paramétrable pour un type donnée et pour un établissement (asynchronously)
     * Récupération du texte paramétrable pour un type donnée et pour un établissement
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param typeTexte Le type de texte saisissable (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Texte paramétrable récupéré </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Structure introuvable. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Accès refusé. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Texte introuvable </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Erreur serveur. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call recupererTexteParametrableAsync(String codeStructure, TypeTexte typeTexte, final ApiCallback<TexteParametrable> _callback) throws ApiException {

        okhttp3.Call localVarCall = recupererTexteParametrableValidateBeforeCall(codeStructure, typeTexte, _callback);
        Type localVarReturnType = new TypeToken<TexteParametrable>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
