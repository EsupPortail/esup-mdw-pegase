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


import fr.univlorraine.pegase.model.chc.ApprenantExtention;
import fr.univlorraine.pegase.model.chc.ParamsCursusApprenants;
import fr.univlorraine.pegase.model.chc.ParamsCursusObjetMaquettes;
import fr.univlorraine.pegase.model.chc.ReponseAffectation;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CursusApi {
    private ApiClient localVarApiClient;

    public CursusApi() {
        this(Configuration.getDefaultApiClient());
    }

    public CursusApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for affecterApprennantLstObjectMaquette
     * @param paramsCursusApprenants Le choix de cursus des apprenants dans un objet de maquette (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> L&#39;affectation de l&#39;apprenant à l&#39;objet de maquette a été effectuée </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call affecterApprennantLstObjectMaquetteCall(ParamsCursusApprenants paramsCursusApprenants, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = paramsCursusApprenants;

        // create path and map variables
        String localVarPath = "/cursus/apprenants/objets-maquette";

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
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call affecterApprennantLstObjectMaquetteValidateBeforeCall(ParamsCursusApprenants paramsCursusApprenants, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'paramsCursusApprenants' is set
        if (paramsCursusApprenants == null) {
            throw new ApiException("Missing the required parameter 'paramsCursusApprenants' when calling affecterApprennantLstObjectMaquette(Async)");
        }
        

        okhttp3.Call localVarCall = affecterApprennantLstObjectMaquetteCall(paramsCursusApprenants, _callback);
        return localVarCall;

    }

    /**
     * Affecter des apprenants à un objet de maquette
     * 
     * @param paramsCursusApprenants Le choix de cursus des apprenants dans un objet de maquette (required)
     * @return ReponseAffectation
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> L&#39;affectation de l&#39;apprenant à l&#39;objet de maquette a été effectuée </td><td>  -  </td></tr>
     </table>
     */
    public ReponseAffectation affecterApprennantLstObjectMaquette(ParamsCursusApprenants paramsCursusApprenants) throws ApiException {
        ApiResponse<ReponseAffectation> localVarResp = affecterApprennantLstObjectMaquetteWithHttpInfo(paramsCursusApprenants);
        return localVarResp.getData();
    }

    /**
     * Affecter des apprenants à un objet de maquette
     * 
     * @param paramsCursusApprenants Le choix de cursus des apprenants dans un objet de maquette (required)
     * @return ApiResponse&lt;ReponseAffectation&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> L&#39;affectation de l&#39;apprenant à l&#39;objet de maquette a été effectuée </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<ReponseAffectation> affecterApprennantLstObjectMaquetteWithHttpInfo(ParamsCursusApprenants paramsCursusApprenants) throws ApiException {
        okhttp3.Call localVarCall = affecterApprennantLstObjectMaquetteValidateBeforeCall(paramsCursusApprenants, null);
        Type localVarReturnType = new TypeToken<ReponseAffectation>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Affecter des apprenants à un objet de maquette (asynchronously)
     * 
     * @param paramsCursusApprenants Le choix de cursus des apprenants dans un objet de maquette (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> L&#39;affectation de l&#39;apprenant à l&#39;objet de maquette a été effectuée </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call affecterApprennantLstObjectMaquetteAsync(ParamsCursusApprenants paramsCursusApprenants, final ApiCallback<ReponseAffectation> _callback) throws ApiException {

        okhttp3.Call localVarCall = affecterApprennantLstObjectMaquetteValidateBeforeCall(paramsCursusApprenants, _callback);
        Type localVarReturnType = new TypeToken<ReponseAffectation>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for affecterObjectMaquetteLstApprenant
     * @param paramsCursusObjetMaquettes Le choix de cursus de l&#39;apprenant dans les objets de maquette (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> L&#39;affectation de l&#39;apprenant à l&#39;objet de maquette a été effectuée </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call affecterObjectMaquetteLstApprenantCall(ParamsCursusObjetMaquettes paramsCursusObjetMaquettes, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = paramsCursusObjetMaquettes;

        // create path and map variables
        String localVarPath = "/cursus/objets-maquette/apprenant";

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
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call affecterObjectMaquetteLstApprenantValidateBeforeCall(ParamsCursusObjetMaquettes paramsCursusObjetMaquettes, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'paramsCursusObjetMaquettes' is set
        if (paramsCursusObjetMaquettes == null) {
            throw new ApiException("Missing the required parameter 'paramsCursusObjetMaquettes' when calling affecterObjectMaquetteLstApprenant(Async)");
        }
        

        okhttp3.Call localVarCall = affecterObjectMaquetteLstApprenantCall(paramsCursusObjetMaquettes, _callback);
        return localVarCall;

    }

    /**
     * Affecter un apprenant aux objets de maquettes
     * 
     * @param paramsCursusObjetMaquettes Le choix de cursus de l&#39;apprenant dans les objets de maquette (required)
     * @return ReponseAffectation
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> L&#39;affectation de l&#39;apprenant à l&#39;objet de maquette a été effectuée </td><td>  -  </td></tr>
     </table>
     */
    public ReponseAffectation affecterObjectMaquetteLstApprenant(ParamsCursusObjetMaquettes paramsCursusObjetMaquettes) throws ApiException {
        ApiResponse<ReponseAffectation> localVarResp = affecterObjectMaquetteLstApprenantWithHttpInfo(paramsCursusObjetMaquettes);
        return localVarResp.getData();
    }

    /**
     * Affecter un apprenant aux objets de maquettes
     * 
     * @param paramsCursusObjetMaquettes Le choix de cursus de l&#39;apprenant dans les objets de maquette (required)
     * @return ApiResponse&lt;ReponseAffectation&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> L&#39;affectation de l&#39;apprenant à l&#39;objet de maquette a été effectuée </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<ReponseAffectation> affecterObjectMaquetteLstApprenantWithHttpInfo(ParamsCursusObjetMaquettes paramsCursusObjetMaquettes) throws ApiException {
        okhttp3.Call localVarCall = affecterObjectMaquetteLstApprenantValidateBeforeCall(paramsCursusObjetMaquettes, null);
        Type localVarReturnType = new TypeToken<ReponseAffectation>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Affecter un apprenant aux objets de maquettes (asynchronously)
     * 
     * @param paramsCursusObjetMaquettes Le choix de cursus de l&#39;apprenant dans les objets de maquette (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> L&#39;affectation de l&#39;apprenant à l&#39;objet de maquette a été effectuée </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call affecterObjectMaquetteLstApprenantAsync(ParamsCursusObjetMaquettes paramsCursusObjetMaquettes, final ApiCallback<ReponseAffectation> _callback) throws ApiException {

        okhttp3.Call localVarCall = affecterObjectMaquetteLstApprenantValidateBeforeCall(paramsCursusObjetMaquettes, _callback);
        Type localVarReturnType = new TypeToken<ReponseAffectation>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for lireListeApprenantsObjetMaquette
     * @param codeChemin Le code chemin de l&#39;objet de maquette (required)
     * @param codePeriode Le code de lapériode de l&#39;objet de maquette (required)
     * @param codeStructure Le code structure de l&#39;objet de maquette (required)
     * @param statutInscription Le statut inscription de l&#39;apprenant (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Les inscriptions des apprenants qui ont une IA valide ont été récupérées </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireListeApprenantsObjetMaquetteCall(String codeChemin, String codePeriode, String codeStructure, String statutInscription, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/cursus/apprenants/{codeChemin}/{codePeriode}/{codeStructure}/{statutInscription}"
            .replaceAll("\\{" + "codeChemin" + "\\}", localVarApiClient.escapeString(codeChemin.toString()))
            .replaceAll("\\{" + "codePeriode" + "\\}", localVarApiClient.escapeString(codePeriode.toString()))
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "statutInscription" + "\\}", localVarApiClient.escapeString(statutInscription.toString()));

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
    private okhttp3.Call lireListeApprenantsObjetMaquetteValidateBeforeCall(String codeChemin, String codePeriode, String codeStructure, String statutInscription, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeChemin' is set
        if (codeChemin == null) {
            throw new ApiException("Missing the required parameter 'codeChemin' when calling lireListeApprenantsObjetMaquette(Async)");
        }
        
        // verify the required parameter 'codePeriode' is set
        if (codePeriode == null) {
            throw new ApiException("Missing the required parameter 'codePeriode' when calling lireListeApprenantsObjetMaquette(Async)");
        }
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling lireListeApprenantsObjetMaquette(Async)");
        }
        
        // verify the required parameter 'statutInscription' is set
        if (statutInscription == null) {
            throw new ApiException("Missing the required parameter 'statutInscription' when calling lireListeApprenantsObjetMaquette(Async)");
        }
        

        okhttp3.Call localVarCall = lireListeApprenantsObjetMaquetteCall(codeChemin, codePeriode, codeStructure, statutInscription, _callback);
        return localVarCall;

    }

    /**
     * Liste des apprenants qui ont un curscus dans un objet de maquette en fonction du statut d&#39;insccription des apprenants
     * 
     * @param codeChemin Le code chemin de l&#39;objet de maquette (required)
     * @param codePeriode Le code de lapériode de l&#39;objet de maquette (required)
     * @param codeStructure Le code structure de l&#39;objet de maquette (required)
     * @param statutInscription Le statut inscription de l&#39;apprenant (required)
     * @return List&lt;ApprenantExtention&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Les inscriptions des apprenants qui ont une IA valide ont été récupérées </td><td>  -  </td></tr>
     </table>
     */
    public List<ApprenantExtention> lireListeApprenantsObjetMaquette(String codeChemin, String codePeriode, String codeStructure, String statutInscription) throws ApiException {
        ApiResponse<List<ApprenantExtention>> localVarResp = lireListeApprenantsObjetMaquetteWithHttpInfo(codeChemin, codePeriode, codeStructure, statutInscription);
        return localVarResp.getData();
    }

    /**
     * Liste des apprenants qui ont un curscus dans un objet de maquette en fonction du statut d&#39;insccription des apprenants
     * 
     * @param codeChemin Le code chemin de l&#39;objet de maquette (required)
     * @param codePeriode Le code de lapériode de l&#39;objet de maquette (required)
     * @param codeStructure Le code structure de l&#39;objet de maquette (required)
     * @param statutInscription Le statut inscription de l&#39;apprenant (required)
     * @return ApiResponse&lt;List&lt;ApprenantExtention&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Les inscriptions des apprenants qui ont une IA valide ont été récupérées </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<ApprenantExtention>> lireListeApprenantsObjetMaquetteWithHttpInfo(String codeChemin, String codePeriode, String codeStructure, String statutInscription) throws ApiException {
        okhttp3.Call localVarCall = lireListeApprenantsObjetMaquetteValidateBeforeCall(codeChemin, codePeriode, codeStructure, statutInscription, null);
        Type localVarReturnType = new TypeToken<List<ApprenantExtention>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Liste des apprenants qui ont un curscus dans un objet de maquette en fonction du statut d&#39;insccription des apprenants (asynchronously)
     * 
     * @param codeChemin Le code chemin de l&#39;objet de maquette (required)
     * @param codePeriode Le code de lapériode de l&#39;objet de maquette (required)
     * @param codeStructure Le code structure de l&#39;objet de maquette (required)
     * @param statutInscription Le statut inscription de l&#39;apprenant (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Les inscriptions des apprenants qui ont une IA valide ont été récupérées </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireListeApprenantsObjetMaquetteAsync(String codeChemin, String codePeriode, String codeStructure, String statutInscription, final ApiCallback<List<ApprenantExtention>> _callback) throws ApiException {

        okhttp3.Call localVarCall = lireListeApprenantsObjetMaquetteValidateBeforeCall(codeChemin, codePeriode, codeStructure, statutInscription, _callback);
        Type localVarReturnType = new TypeToken<List<ApprenantExtention>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
