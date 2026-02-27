/*
 * CHC Externe v1 - API Externe choix du cursus
 * ### Introduction L’API répertorie l'ensemble des services et des opérations disponibles dans le module CHC (Choix du Cursus).  ### Authentification/autorisation obligatoire Pour tout appel à une opération, vous devez être authentifié/autorisé à l’aide d’un token JWT. Ainsi, chaque requête HTTP doit contenir un token valide dans le header HTTP `Authorization`. #### Répertoire de partage contenant la documentation décrivant l'authentification Pégase https://share.pc-scol.fr/f/4487c726ade84022ae16/?dl=1  Le format est `Authorization: Bearer <token-jwt>`. Par exemple : `Authorization: Bearer xxxx.yyyy.zzzz`. Vous pouvez recevoir l'un de ces codes retour si vous n’êtes pas authentifié ou autorisé : * 401 - Unauthorized - Vous n’êtes pas authentifié     * Il n’y a pas de token passé dans le header HTTP `Authorization`     * Le token passé n’est pas au bon format (Bearer <token-jwt>) * 403 - Forbidden - Vous êtes authentifié mais pas autorisé à exécuter cette opération     * La signature du token est incorrecte / n’a pas pu être vérifiée     * Le token est expiré     * Vérifier les droits de l’utilisateur * 500 - Internal Server Error     * Il n’est pas encore actif  ### Type de données Sauf indications spécifiques données au niveau de l'opération, les types de données utilisés dans cette API sont les suivants :  * string - Chaîne de caractères encodée en UTF8 (ex : `Une chaîne de caractère`)    * Dans le cas des descripteurs de type `codeXxx`, seuls les caractères de A à Z, de 0 à 9 et le tiret(-) sont autorisés  * string($date) - Une date sous la forme d'une chaîne de caractères (ex : `2020-02-25`, norme [ISO-8601](https://fr.wikipedia.org/wiki/ISO_8601))  * integer($int64) - Un entier sur 64 bits (de -9 223 372 036 854 775 808 à 9 223 372 036 854 775 807) (ex : `2542`)  * integer($int32) - Un entier sur 32 bits (de –2 147 483 648 à 2 147 483 647) (ex : `2542`)  * number($double) - Un nombre à virgule flottante en double précision  * boolean - Un booléen représenté par `true` ou `false`  ### Code retour      | Code    | Description                                                                                                         |     |---------|---------------------------------------------------------------------------------------------------------------------|     | 200     | L'opération s'est déroulée avec succès                                                                              |     | 201     | L'opération a abouti à la création d'une ressource                                                                  |     | 400     | Un ou des paramètres d'entrée sont erronés                                                                          |     |         | Une erreur fonctionnelle s'est produite                                                                             |     | 404     | La ressource demandée n'est pas trouvée                                                                             |     |         | Remarque : Dans le cas des opérations retournant une liste, on recevra un code 200 avec en résultat une liste vide  |     | 500     | Erreur technique rencontrée par le serveur                                                                          |   ## Notions métiers  ### Objet Maquette (OM) Un objet maquette représente n'importe quel nœud d'un arbre de formation : Formation, objet de formation ou groupement.  Un objet maquette est identifié par le codeChemin (chemin pédagogique), le codePeriode et le codeStructure.  ### Formation Une formation est un arbre dont les nœuds sont des objets de formation et dont la racine est la formation elle-même.  Pour apparaître dans le module CHC, la formation doit être validée dans ODF.  Pour l'utiliser dans les différents actes métiers, il faut que chaque noeud et sa descendance soit ouvert au choix du cursus et qu'au moins une inscription administrative soit validée sur un objet maquette de l’arbre de la formation.  ### Objet formation Un objet de formation est l’un des nœuds de l’arbre de formation : année, semestre, UE, EC, enseignement, etc. (hors groupement).  Pour apparaître dans le Module CHC, un objet de formation doit être validé dans ODF.  ### Groupement Un groupement est une possibilité de structurer et d'organiser une formation. Il peut contenir des objets de formations de tous les types, être lié pour décomposer des objets pères de tous les types, être avec ou sans plage de choix.  ### Plage de choix Une plage de choix permet de contraindre l’apprenant lorsqu’il effectue son CHC dans Pégase. Cette plage de choix est matérialisée par un nombre minimum et un nombre maximum d’objets de formation à sélectionner. La plage de choix est contrôlée au cours du CHC.  ### Groupe Un Groupe est une entité permettant de diviser une population d’étudiants ou d'identifier une population spécifique d’étudiants inscrits administrativement et pédagogiquement.  ### Composition Une composition est une entité permettant de rassembler des groupes. Une composition contient obligatoirement au moins un groupe.  ### Période Une période de mise en œuvre correspond à la période pendant laquelle se déroule la formation.  Elle est le point d’entrée pour chaque acte métier du module CHC.  ### Apprenant Un apprenant est un usager qui suit un cursus et pour lequel des choix pédagogiques devront être réalisés.  ### Inscription L’inscription est l’ensemble des étapes de saisie des données concernant l’apprenant : état-civil, coordonnées, situation précédente, cursus, montant de l’inscription, mode de paiement. Cette saisie peut être faite par le gestionnaire ou l’apprenant.  Elle doit être vérifiée et validée par le gestionnaire. Une inscription n'est prise en compte dans CHC qu'à partir du moment où elle est validée ou annulée.  ### Cursus Le cursus est un arbre (une arborescence) composé d'objets maquette pour lequel des choix pédagogiques doivent être réalisés à chaque période de mise en oeuvre.  Un cursus est défini par le code de l’apprenant, un objet maquette lui-même identifié par le codeChemin (chemin pédagogique), le codePeriode et le codeStructure. Un choix pédagogique est une association entre un objet maquette et un apprenant. On recense parmi les choix pédagogiques des affectations, des aménagements et des acquis capitalisés.  ### Acquis capitalisé Un acquis capitalisé est un objet de formation dont les modalités de contrôle des connaissances sont capitalisables et dont le résultat positif a été obtenu sur une période antérieure. L'acquis capitalisé est créé et stocké dans CHC après délibération de jury du module COC. Il est utilisable sur une période postérieure à son acquisition  ### Chemin pédagogique Un chemin pédagogique identifie le lien d'un objet maquette à un autre objet maquette de sa descendance.  **Exemple** ``` MASTER-RH>MASTER-1>SEMESTRE-1>UE-OPTIONS>ESPAGNOL ```  ### Règles communes pour réaliser un choix de cursus * L’affectation est possible à partir de l'objet maquette porteur du point d'inscription administrative et sur les objets maquette de sa descendance à condition que l'inscription administrative soit validée dans le module INS. * Un choix du cursus sur un objet maquette est réalisable si le témoin ouvertChoixCursus est  à true. * La désaffectation d’un apprenant à un objet maquette provoque sa désaffectation automatique à tous les objets maquette de la descendance. * Pour chaque apprenant, il est possible de réaliser un choix du cursus sur un objet maquette dans un groupement à plage de choix tant que le nombre maximum autorisé (de la plage de choix) n'est pas atteint. La valeur minimum de plage de choix n'est  pas contrôlée. * Un choix pédagogique sur un objet maquette présent plusieurs fois dans un arbre de formation ne peut être réalisée qu’une fois pour un même cursus. * La capacité d’accueil d’un objet maquette n’est pas contrôlée dans l'API car non bloquante. Les capacités d’accueil dépassées sont négatives. * Les aménagements avec prise en compte acquis et aucun ne sont pas décomptés de la capacité d’accueil d’un objet maquette. * L'utilisation d'un acquis capitalisé empêche son affectation et supprime la branche de sa descendance.
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.chc.api;

import fr.univlorraine.pegase.chc.invoker.ApiCallback;
import fr.univlorraine.pegase.chc.invoker.ApiClient;
import fr.univlorraine.pegase.chc.invoker.ApiException;
import fr.univlorraine.pegase.chc.invoker.ApiResponse;
import fr.univlorraine.pegase.chc.invoker.Configuration;
import fr.univlorraine.pegase.chc.invoker.Pair;
import fr.univlorraine.pegase.chc.invoker.ProgressRequestBody;
import fr.univlorraine.pegase.chc.invoker.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import fr.univlorraine.pegase.chc.model.Composition;
import fr.univlorraine.pegase.chc.model.CompositionPourExtraction;
import fr.univlorraine.pegase.chc.model.CursusPourext;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtractionsApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public ExtractionsApi() {
        this(Configuration.getDefaultApiClient());
    }

    public ExtractionsApi(ApiClient apiClient) {
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
     * Build call for extraireCompositionsPourUnePeriode
     * @param codeStructureEtablissement Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code de la période (required)
     * @param codeFormation Paramètre optionnel qui limite la liste à certaines formations du code fourni (optional)
     * @param uniquementEmploiDuTemps Se limite aux compositions contenant des groupes planifiables pour les emplois du temps (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Si tous les codes de formation sont trouvés alors on retourne pour chaque code les compositions associées à la formation et ses objets de formation. Si aucun code n&#39;est spécifié alors on retourne toutes les compositions associées à des formations ou des objets de formation existantes sur la période. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Si l&#39;utilisateur n&#39;est pas authentifié. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Si l&#39;utilisateur n&#39;a pas les droits d&#39;extraction des compositions. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call extraireCompositionsPourUnePeriodeCall(@jakarta.annotation.Nonnull String codeStructureEtablissement, @jakarta.annotation.Nonnull String codePeriode, @jakarta.annotation.Nullable List<String> codeFormation, @jakarta.annotation.Nullable Boolean uniquementEmploiDuTemps, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/extractions/{codeStructureEtablissement}/periodes/{codePeriode}/compositions"
            .replace("{" + "codeStructureEtablissement" + "}", localVarApiClient.escapeString(codeStructureEtablissement.toString()))
            .replace("{" + "codePeriode" + "}", localVarApiClient.escapeString(codePeriode.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (codeFormation != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "codeFormation", codeFormation));
        }

        if (uniquementEmploiDuTemps != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("uniquementEmploiDuTemps", uniquementEmploiDuTemps));
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
    private okhttp3.Call extraireCompositionsPourUnePeriodeValidateBeforeCall(@jakarta.annotation.Nonnull String codeStructureEtablissement, @jakarta.annotation.Nonnull String codePeriode, @jakarta.annotation.Nullable List<String> codeFormation, @jakarta.annotation.Nullable Boolean uniquementEmploiDuTemps, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'codeStructureEtablissement' is set
        if (codeStructureEtablissement == null) {
            throw new ApiException("Missing the required parameter 'codeStructureEtablissement' when calling extraireCompositionsPourUnePeriode(Async)");
        }

        // verify the required parameter 'codePeriode' is set
        if (codePeriode == null) {
            throw new ApiException("Missing the required parameter 'codePeriode' when calling extraireCompositionsPourUnePeriode(Async)");
        }

        return extraireCompositionsPourUnePeriodeCall(codeStructureEtablissement, codePeriode, codeFormation, uniquementEmploiDuTemps, _callback);

    }

    /**
     * Extraire les compositions et les groupes associés à des formations mises en œuvre sur la période donnée pour un établissement
     * 
     * @param codeStructureEtablissement Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code de la période (required)
     * @param codeFormation Paramètre optionnel qui limite la liste à certaines formations du code fourni (optional)
     * @param uniquementEmploiDuTemps Se limite aux compositions contenant des groupes planifiables pour les emplois du temps (optional)
     * @return List&lt;CompositionPourExtraction&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Si tous les codes de formation sont trouvés alors on retourne pour chaque code les compositions associées à la formation et ses objets de formation. Si aucun code n&#39;est spécifié alors on retourne toutes les compositions associées à des formations ou des objets de formation existantes sur la période. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Si l&#39;utilisateur n&#39;est pas authentifié. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Si l&#39;utilisateur n&#39;a pas les droits d&#39;extraction des compositions. </td><td>  -  </td></tr>
     </table>
     */
    public List<CompositionPourExtraction> extraireCompositionsPourUnePeriode(@jakarta.annotation.Nonnull String codeStructureEtablissement, @jakarta.annotation.Nonnull String codePeriode, @jakarta.annotation.Nullable List<String> codeFormation, @jakarta.annotation.Nullable Boolean uniquementEmploiDuTemps) throws ApiException {
        ApiResponse<List<CompositionPourExtraction>> localVarResp = extraireCompositionsPourUnePeriodeWithHttpInfo(codeStructureEtablissement, codePeriode, codeFormation, uniquementEmploiDuTemps);
        return localVarResp.getData();
    }

    /**
     * Extraire les compositions et les groupes associés à des formations mises en œuvre sur la période donnée pour un établissement
     * 
     * @param codeStructureEtablissement Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code de la période (required)
     * @param codeFormation Paramètre optionnel qui limite la liste à certaines formations du code fourni (optional)
     * @param uniquementEmploiDuTemps Se limite aux compositions contenant des groupes planifiables pour les emplois du temps (optional)
     * @return ApiResponse&lt;List&lt;CompositionPourExtraction&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Si tous les codes de formation sont trouvés alors on retourne pour chaque code les compositions associées à la formation et ses objets de formation. Si aucun code n&#39;est spécifié alors on retourne toutes les compositions associées à des formations ou des objets de formation existantes sur la période. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Si l&#39;utilisateur n&#39;est pas authentifié. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Si l&#39;utilisateur n&#39;a pas les droits d&#39;extraction des compositions. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<CompositionPourExtraction>> extraireCompositionsPourUnePeriodeWithHttpInfo(@jakarta.annotation.Nonnull String codeStructureEtablissement, @jakarta.annotation.Nonnull String codePeriode, @jakarta.annotation.Nullable List<String> codeFormation, @jakarta.annotation.Nullable Boolean uniquementEmploiDuTemps) throws ApiException {
        okhttp3.Call localVarCall = extraireCompositionsPourUnePeriodeValidateBeforeCall(codeStructureEtablissement, codePeriode, codeFormation, uniquementEmploiDuTemps, null);
        Type localVarReturnType = new TypeToken<List<CompositionPourExtraction>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Extraire les compositions et les groupes associés à des formations mises en œuvre sur la période donnée pour un établissement (asynchronously)
     * 
     * @param codeStructureEtablissement Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code de la période (required)
     * @param codeFormation Paramètre optionnel qui limite la liste à certaines formations du code fourni (optional)
     * @param uniquementEmploiDuTemps Se limite aux compositions contenant des groupes planifiables pour les emplois du temps (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Si tous les codes de formation sont trouvés alors on retourne pour chaque code les compositions associées à la formation et ses objets de formation. Si aucun code n&#39;est spécifié alors on retourne toutes les compositions associées à des formations ou des objets de formation existantes sur la période. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Si l&#39;utilisateur n&#39;est pas authentifié. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Si l&#39;utilisateur n&#39;a pas les droits d&#39;extraction des compositions. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call extraireCompositionsPourUnePeriodeAsync(@jakarta.annotation.Nonnull String codeStructureEtablissement, @jakarta.annotation.Nonnull String codePeriode, @jakarta.annotation.Nullable List<String> codeFormation, @jakarta.annotation.Nullable Boolean uniquementEmploiDuTemps, final ApiCallback<List<CompositionPourExtraction>> _callback) throws ApiException {

        okhttp3.Call localVarCall = extraireCompositionsPourUnePeriodeValidateBeforeCall(codeStructureEtablissement, codePeriode, codeFormation, uniquementEmploiDuTemps, _callback);
        Type localVarReturnType = new TypeToken<List<CompositionPourExtraction>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for extraireCursusObjetFormationPeriode
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code de la période (required)
     * @param codeObjetFormation Le code de l&#39;objet de formation (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Liste des affectations au cursus pour une période donnée sur l&#39;objet de formation et pour la liste des apprenants rattachés, triés par nom de famille de l&#39;apprenant </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Si l&#39;utilisateur n&#39;est pas authentifié. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Si l&#39;utilisateur n&#39;a pas les droits d&#39;extraction des objets formation. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call extraireCursusObjetFormationPeriodeCall(@jakarta.annotation.Nonnull String codeStructure, @jakarta.annotation.Nonnull String codePeriode, @jakarta.annotation.Nonnull String codeObjetFormation, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/objet-formation/{codeStructure}/{codePeriode}/{codeObjetFormation}/cursus"
            .replace("{" + "codeStructure" + "}", localVarApiClient.escapeString(codeStructure.toString()))
            .replace("{" + "codePeriode" + "}", localVarApiClient.escapeString(codePeriode.toString()))
            .replace("{" + "codeObjetFormation" + "}", localVarApiClient.escapeString(codeObjetFormation.toString()));

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
    private okhttp3.Call extraireCursusObjetFormationPeriodeValidateBeforeCall(@jakarta.annotation.Nonnull String codeStructure, @jakarta.annotation.Nonnull String codePeriode, @jakarta.annotation.Nonnull String codeObjetFormation, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling extraireCursusObjetFormationPeriode(Async)");
        }

        // verify the required parameter 'codePeriode' is set
        if (codePeriode == null) {
            throw new ApiException("Missing the required parameter 'codePeriode' when calling extraireCursusObjetFormationPeriode(Async)");
        }

        // verify the required parameter 'codeObjetFormation' is set
        if (codeObjetFormation == null) {
            throw new ApiException("Missing the required parameter 'codeObjetFormation' when calling extraireCursusObjetFormationPeriode(Async)");
        }

        return extraireCursusObjetFormationPeriodeCall(codeStructure, codePeriode, codeObjetFormation, _callback);

    }

    /**
     * Extraire les affectations au cursus pour un objet de formation sur la période donnée pour un établissement
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code de la période (required)
     * @param codeObjetFormation Le code de l&#39;objet de formation (required)
     * @return List&lt;CursusPourext&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Liste des affectations au cursus pour une période donnée sur l&#39;objet de formation et pour la liste des apprenants rattachés, triés par nom de famille de l&#39;apprenant </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Si l&#39;utilisateur n&#39;est pas authentifié. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Si l&#39;utilisateur n&#39;a pas les droits d&#39;extraction des objets formation. </td><td>  -  </td></tr>
     </table>
     */
    public List<CursusPourext> extraireCursusObjetFormationPeriode(@jakarta.annotation.Nonnull String codeStructure, @jakarta.annotation.Nonnull String codePeriode, @jakarta.annotation.Nonnull String codeObjetFormation) throws ApiException {
        ApiResponse<List<CursusPourext>> localVarResp = extraireCursusObjetFormationPeriodeWithHttpInfo(codeStructure, codePeriode, codeObjetFormation);
        return localVarResp.getData();
    }

    /**
     * Extraire les affectations au cursus pour un objet de formation sur la période donnée pour un établissement
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code de la période (required)
     * @param codeObjetFormation Le code de l&#39;objet de formation (required)
     * @return ApiResponse&lt;List&lt;CursusPourext&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Liste des affectations au cursus pour une période donnée sur l&#39;objet de formation et pour la liste des apprenants rattachés, triés par nom de famille de l&#39;apprenant </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Si l&#39;utilisateur n&#39;est pas authentifié. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Si l&#39;utilisateur n&#39;a pas les droits d&#39;extraction des objets formation. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<CursusPourext>> extraireCursusObjetFormationPeriodeWithHttpInfo(@jakarta.annotation.Nonnull String codeStructure, @jakarta.annotation.Nonnull String codePeriode, @jakarta.annotation.Nonnull String codeObjetFormation) throws ApiException {
        okhttp3.Call localVarCall = extraireCursusObjetFormationPeriodeValidateBeforeCall(codeStructure, codePeriode, codeObjetFormation, null);
        Type localVarReturnType = new TypeToken<List<CursusPourext>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Extraire les affectations au cursus pour un objet de formation sur la période donnée pour un établissement (asynchronously)
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code de la période (required)
     * @param codeObjetFormation Le code de l&#39;objet de formation (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Liste des affectations au cursus pour une période donnée sur l&#39;objet de formation et pour la liste des apprenants rattachés, triés par nom de famille de l&#39;apprenant </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Si l&#39;utilisateur n&#39;est pas authentifié. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Si l&#39;utilisateur n&#39;a pas les droits d&#39;extraction des objets formation. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call extraireCursusObjetFormationPeriodeAsync(@jakarta.annotation.Nonnull String codeStructure, @jakarta.annotation.Nonnull String codePeriode, @jakarta.annotation.Nonnull String codeObjetFormation, final ApiCallback<List<CursusPourext>> _callback) throws ApiException {

        okhttp3.Call localVarCall = extraireCursusObjetFormationPeriodeValidateBeforeCall(codeStructure, codePeriode, codeObjetFormation, _callback);
        Type localVarReturnType = new TypeToken<List<CursusPourext>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for extraireCursusTypeObjetFormationPeriode
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code de la période (required)
     * @param typeObjet Le code du type de l&#39;objet de formation (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Liste des affectations au cursus pour une période donnée selon le type d&#39;objet de formation et pour la liste des apprenants rattachés, triés par nom de famille de l&#39;apprenant </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Si l&#39;utilisateur n&#39;est pas authentifié. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Si l&#39;utilisateur n&#39;a pas les droits d&#39;extraction des types d&#39;objet formation. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call extraireCursusTypeObjetFormationPeriodeCall(@jakarta.annotation.Nonnull String codeStructure, @jakarta.annotation.Nonnull String codePeriode, @jakarta.annotation.Nonnull String typeObjet, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/type-objet-formation/{codeStructure}/{codePeriode}/{typeObjet}/cursus"
            .replace("{" + "codeStructure" + "}", localVarApiClient.escapeString(codeStructure.toString()))
            .replace("{" + "codePeriode" + "}", localVarApiClient.escapeString(codePeriode.toString()))
            .replace("{" + "typeObjet" + "}", localVarApiClient.escapeString(typeObjet.toString()));

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
    private okhttp3.Call extraireCursusTypeObjetFormationPeriodeValidateBeforeCall(@jakarta.annotation.Nonnull String codeStructure, @jakarta.annotation.Nonnull String codePeriode, @jakarta.annotation.Nonnull String typeObjet, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling extraireCursusTypeObjetFormationPeriode(Async)");
        }

        // verify the required parameter 'codePeriode' is set
        if (codePeriode == null) {
            throw new ApiException("Missing the required parameter 'codePeriode' when calling extraireCursusTypeObjetFormationPeriode(Async)");
        }

        // verify the required parameter 'typeObjet' is set
        if (typeObjet == null) {
            throw new ApiException("Missing the required parameter 'typeObjet' when calling extraireCursusTypeObjetFormationPeriode(Async)");
        }

        return extraireCursusTypeObjetFormationPeriodeCall(codeStructure, codePeriode, typeObjet, _callback);

    }

    /**
     * Extraire les affectations au cursus pour un type d&#39;objet de formation sur la période donnée pour un établissement
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code de la période (required)
     * @param typeObjet Le code du type de l&#39;objet de formation (required)
     * @return List&lt;CursusPourext&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Liste des affectations au cursus pour une période donnée selon le type d&#39;objet de formation et pour la liste des apprenants rattachés, triés par nom de famille de l&#39;apprenant </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Si l&#39;utilisateur n&#39;est pas authentifié. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Si l&#39;utilisateur n&#39;a pas les droits d&#39;extraction des types d&#39;objet formation. </td><td>  -  </td></tr>
     </table>
     */
    public List<CursusPourext> extraireCursusTypeObjetFormationPeriode(@jakarta.annotation.Nonnull String codeStructure, @jakarta.annotation.Nonnull String codePeriode, @jakarta.annotation.Nonnull String typeObjet) throws ApiException {
        ApiResponse<List<CursusPourext>> localVarResp = extraireCursusTypeObjetFormationPeriodeWithHttpInfo(codeStructure, codePeriode, typeObjet);
        return localVarResp.getData();
    }

    /**
     * Extraire les affectations au cursus pour un type d&#39;objet de formation sur la période donnée pour un établissement
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code de la période (required)
     * @param typeObjet Le code du type de l&#39;objet de formation (required)
     * @return ApiResponse&lt;List&lt;CursusPourext&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Liste des affectations au cursus pour une période donnée selon le type d&#39;objet de formation et pour la liste des apprenants rattachés, triés par nom de famille de l&#39;apprenant </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Si l&#39;utilisateur n&#39;est pas authentifié. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Si l&#39;utilisateur n&#39;a pas les droits d&#39;extraction des types d&#39;objet formation. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<CursusPourext>> extraireCursusTypeObjetFormationPeriodeWithHttpInfo(@jakarta.annotation.Nonnull String codeStructure, @jakarta.annotation.Nonnull String codePeriode, @jakarta.annotation.Nonnull String typeObjet) throws ApiException {
        okhttp3.Call localVarCall = extraireCursusTypeObjetFormationPeriodeValidateBeforeCall(codeStructure, codePeriode, typeObjet, null);
        Type localVarReturnType = new TypeToken<List<CursusPourext>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Extraire les affectations au cursus pour un type d&#39;objet de formation sur la période donnée pour un établissement (asynchronously)
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code de la période (required)
     * @param typeObjet Le code du type de l&#39;objet de formation (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Liste des affectations au cursus pour une période donnée selon le type d&#39;objet de formation et pour la liste des apprenants rattachés, triés par nom de famille de l&#39;apprenant </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Si l&#39;utilisateur n&#39;est pas authentifié. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Si l&#39;utilisateur n&#39;a pas les droits d&#39;extraction des types d&#39;objet formation. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call extraireCursusTypeObjetFormationPeriodeAsync(@jakarta.annotation.Nonnull String codeStructure, @jakarta.annotation.Nonnull String codePeriode, @jakarta.annotation.Nonnull String typeObjet, final ApiCallback<List<CursusPourext>> _callback) throws ApiException {

        okhttp3.Call localVarCall = extraireCursusTypeObjetFormationPeriodeValidateBeforeCall(codeStructure, codePeriode, typeObjet, _callback);
        Type localVarReturnType = new TypeToken<List<CursusPourext>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for extraireGroupesDansCompositionsPourPeriode
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code de la période (required)
     * @param planification Est-ce que le groupe est planifiable? (optional)
     * @param requestBody La liste des codes de compositions à extraire ou vide si on extrait toutes les compositions (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Liste des affectations aux groupes dans les compositions pour une période donnée selon la formation et pour la liste des apprenants rattachés, triés par nom de famille de l&#39;apprenant </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Si l&#39;utilisateur n&#39;est pas authentifié. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Si l&#39;utilisateur n&#39;a pas les droits d&#39;extraction des groupes. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call extraireGroupesDansCompositionsPourPeriodeCall(@jakarta.annotation.Nonnull String codeStructure, @jakarta.annotation.Nonnull String codePeriode, @jakarta.annotation.Nullable Boolean planification, @jakarta.annotation.Nullable List<String> requestBody, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = requestBody;

        // create path and map variables
        String localVarPath = "/groupes/{codeStructure}/{codePeriode}"
            .replace("{" + "codeStructure" + "}", localVarApiClient.escapeString(codeStructure.toString()))
            .replace("{" + "codePeriode" + "}", localVarApiClient.escapeString(codePeriode.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (planification != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("planification", planification));
        }

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
    private okhttp3.Call extraireGroupesDansCompositionsPourPeriodeValidateBeforeCall(@jakarta.annotation.Nonnull String codeStructure, @jakarta.annotation.Nonnull String codePeriode, @jakarta.annotation.Nullable Boolean planification, @jakarta.annotation.Nullable List<String> requestBody, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling extraireGroupesDansCompositionsPourPeriode(Async)");
        }

        // verify the required parameter 'codePeriode' is set
        if (codePeriode == null) {
            throw new ApiException("Missing the required parameter 'codePeriode' when calling extraireGroupesDansCompositionsPourPeriode(Async)");
        }

        return extraireGroupesDansCompositionsPourPeriodeCall(codeStructure, codePeriode, planification, requestBody, _callback);

    }

    /**
     * Extraire les affectations aux groupes des compositions renseignées, de toutes les compositions sinon, pour la formation sur la période donnée pour un établissement
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code de la période (required)
     * @param planification Est-ce que le groupe est planifiable? (optional)
     * @param requestBody La liste des codes de compositions à extraire ou vide si on extrait toutes les compositions (optional)
     * @return List&lt;Composition&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Liste des affectations aux groupes dans les compositions pour une période donnée selon la formation et pour la liste des apprenants rattachés, triés par nom de famille de l&#39;apprenant </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Si l&#39;utilisateur n&#39;est pas authentifié. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Si l&#39;utilisateur n&#39;a pas les droits d&#39;extraction des groupes. </td><td>  -  </td></tr>
     </table>
     */
    public List<Composition> extraireGroupesDansCompositionsPourPeriode(@jakarta.annotation.Nonnull String codeStructure, @jakarta.annotation.Nonnull String codePeriode, @jakarta.annotation.Nullable Boolean planification, @jakarta.annotation.Nullable List<String> requestBody) throws ApiException {
        ApiResponse<List<Composition>> localVarResp = extraireGroupesDansCompositionsPourPeriodeWithHttpInfo(codeStructure, codePeriode, planification, requestBody);
        return localVarResp.getData();
    }

    /**
     * Extraire les affectations aux groupes des compositions renseignées, de toutes les compositions sinon, pour la formation sur la période donnée pour un établissement
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code de la période (required)
     * @param planification Est-ce que le groupe est planifiable? (optional)
     * @param requestBody La liste des codes de compositions à extraire ou vide si on extrait toutes les compositions (optional)
     * @return ApiResponse&lt;List&lt;Composition&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Liste des affectations aux groupes dans les compositions pour une période donnée selon la formation et pour la liste des apprenants rattachés, triés par nom de famille de l&#39;apprenant </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Si l&#39;utilisateur n&#39;est pas authentifié. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Si l&#39;utilisateur n&#39;a pas les droits d&#39;extraction des groupes. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<Composition>> extraireGroupesDansCompositionsPourPeriodeWithHttpInfo(@jakarta.annotation.Nonnull String codeStructure, @jakarta.annotation.Nonnull String codePeriode, @jakarta.annotation.Nullable Boolean planification, @jakarta.annotation.Nullable List<String> requestBody) throws ApiException {
        okhttp3.Call localVarCall = extraireGroupesDansCompositionsPourPeriodeValidateBeforeCall(codeStructure, codePeriode, planification, requestBody, null);
        Type localVarReturnType = new TypeToken<List<Composition>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Extraire les affectations aux groupes des compositions renseignées, de toutes les compositions sinon, pour la formation sur la période donnée pour un établissement (asynchronously)
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code de la période (required)
     * @param planification Est-ce que le groupe est planifiable? (optional)
     * @param requestBody La liste des codes de compositions à extraire ou vide si on extrait toutes les compositions (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Liste des affectations aux groupes dans les compositions pour une période donnée selon la formation et pour la liste des apprenants rattachés, triés par nom de famille de l&#39;apprenant </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Si l&#39;utilisateur n&#39;est pas authentifié. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Si l&#39;utilisateur n&#39;a pas les droits d&#39;extraction des groupes. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call extraireGroupesDansCompositionsPourPeriodeAsync(@jakarta.annotation.Nonnull String codeStructure, @jakarta.annotation.Nonnull String codePeriode, @jakarta.annotation.Nullable Boolean planification, @jakarta.annotation.Nullable List<String> requestBody, final ApiCallback<List<Composition>> _callback) throws ApiException {

        okhttp3.Call localVarCall = extraireGroupesDansCompositionsPourPeriodeValidateBeforeCall(codeStructure, codePeriode, planification, requestBody, _callback);
        Type localVarReturnType = new TypeToken<List<Composition>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
