/*
 * CHC v6 - Choix du cursus
 * <font color='red'>***Statut***: DRAFT (brouillon/preview)</font> Ne pas utiliser cette version d'API. Elle est au statut DRAFT, donc sujette à changements sans aucune garantie de compatibilité ascendante. Liste l'ensemble des services et des opérations disponibles dans le module choix des cursus v6 ### Introduction l’API liste l'ensemble des services et des opérations disponibles dans le module CHC (Choix du Cursus). Le module CHC permet d’affecter les apprenants aux Objets maquettes qu’ils doivent suivre pour une période de mise en œuvre donnée pendant leur cursus, de leur saisir des aménagements avec différentes prises en compte et de les affecter à des groupes. ### Authentification/autorisation obligatoire Pour tout appel à une opération vous devez être authentifié/authorisé à l’aide d’un token jwt. Pour cela, chaque requête HTTP doit contenir un token valide dans le header HTTP `Authorization`. Le format est `Authorization: Bearer <token-jwt>`. Par exemple `Authorization: Bearer xxxx.yyyy.zzzz` Vous pouvez recevoir un des ces codes retours si vous n’êtes pas authentifié ou autorisé : * 401 - Unauthorized - Vous n’êtes pas authentifié     * Il n’y a pas de token passé dans le header HTTP `Authorization`     * Le token passé n’est pas au bon format (Bearer <token-jwt>) * 403 - Forbidden - Vous êtes authentifié mais pas autorisé à exécuter cette opération     * La signature du token est incorrecte / n’a pas pû être vérifiée     * Le token est expiré     * Vérifier les droits de l’utilisateur * 500 - Internal Server Error     * Il n’est pas encore actif  ### Type de données Sauf indications spécifiques données au niveau de l'opération, les types de données utilisés dans cette API sont les suivants :  * string - Chaîne de caractères encodée en UTF8 (ex : `Une chaîne de caractère`)    * Dans le cas des descripteurs de type `codeXxx`, seuls les caractères de A à Z, de 0 à 9 et le tiret(-) sont autorisés  * string($date) - Une date sous la forme d'une chaîne de caractères (ex : `2020-02-25`, norme [ISO-8601](https://fr.wikipedia.org/wiki/ISO_8601))  * integer($int64) - Un entier sur 64 bits (de -9 223 372 036 854 775 808 à 9 223 372 036 854 775 807) (ex : `2542`)  * integer($int32) - Un entier sur 32 bits (de –2 147 483 648 à 2 147 483 647) (ex : `2542`)  * number($double) - un nombre à virgule flottante en double précision  * boolean - Un booléen représenté par `true` ou `false`  ### Code retour      | Code    | Description                                                                                                         |     |---------|---------------------------------------------------------------------------------------------------------------------|     | 200     | L'opération s'est déroulée avec succès                                                                              |     | 201     | L'opération a aboutie à la création d'une ressource                                                                 |     | 400     | Un ou des paramètres d'entrées sont erronées                                                                        |     |         | Une erreur fonctionnelle s'est produite                                                                             |     | 404     | La ressource demandée n'est pas trouvé                                                                              |     |         | Remarque : Dans le cas des opérations retournant une liste, on recevra un code 200 avec en résultat une liste vide  |     | 500     | Erreur technique rencontrée par le serveur                                                                          |   ## Notions métiers ### Objet Maquette (OM) Un Objet Maquette représente n'importe quel nœud d'un arbre de Formation: Formation, Objet de Formation ou Groupement. Un objet Maquette est identifié par le codeChemin (chemin pédagogique), le codePeriode et le codeStructure  ### Formation Une Formation est un arbre dont les nœuds sont des Objets de formation et dont la racine est la Formation elle-même. Pour apparaître dans le Module CHC, la formation doit être mise en œuvre, actualisée, ouverte à l’inscription et ouverte au CHC dans MOF. Il est également nécessaire qu’il y ait au moins une inscription valide sur un objet maquette de l’arbre de la formation. ### Objet formation Un objet de formation est l’un des nœuds de l’arbre de formation : année, semestre, UE, EC, enseignement, etc.(hors groupement). Pour apparaître dans le Module CHC, un objet de formation doit être ouvert au CHC dans MOF. ### Groupement Un groupement est une possibilité de structurer et d'organiser une formation.Il peut contenir des objets de formations de tous les types, être lié pour décomposer des objets pères de tous les types, être avec ou sans plage de choix. ### Plage de choix Une plage de choix permet de contraindre l’apprenant lorsque  qu’il effectue son CHC dans Pégase. Cette plage de choix est matérialisée par un nombre minimum et un nombre maximum d’objets de formation à sélectionner. La plage de choix est contrôlée au cours du CHC. ### Groupe Un Groupe est une entité permettant de diviser une population d’étudiants ou d’identifier une population spécifique d’étudiants inscrits administrativement ou pédagogiquement ### Composition Une composition est une entité permettant de rassembler des groupes. Une composition contient obligatoirement au moins un groupe.  ### Période Une période de mise en œuvre correspond à la période pendant laquelle se déroule la formation, du début des cours jusqu’à la délibération des jurys. Elle est le point d’entrée du module puisque le CHC se fait pour une période donnée. ### Apprenant Un apprenant est un usager qui suit un cursus et pour lequel le CHC va être saisi. ### Inscription L’inscription est l’ensemble des étapes de saisie des données concernant l’apprenant : état-civil, coordonnées, situation précédente, situation précédente, cursus, montant de l’inscription, mode de paiement. Cette saisie peut être faite par le gestionnaire ou l’apprenant. Elle doit être vérifiée et validée par le gestionnaire.Au 25/03/21, l’inscription doit être validée pour que l’apprenant puisse arriver dans le module CHC. ### Cursus Le cursus est l’ensemble des Objets Maquette auxquels l’apprenant va être affecté ou pour lesquels des aménagements vont être saisis, le tout pour une période donnée. Un cursus est défini par le code de l’apprenant et un objet maquette lui-même identifié par le codeChemin (chemin pédagogique), le codePeriode et le codeStructure. ### Acquis capitalisé Un acquis capitalisé est un objet de formation dont les modalités de contrôle des connaissances attendent un résultat capitalisable. Pour être identifié dans CHC comme acquis capitalisé, cet objet doit posséder un résultat positif obtenu sur une période passée, pour laquelle une délibération de jury a statué. ### Chemin pédagogique Un chemin pédagogique identifie le lien d'un Objet Maquette à un autre Objet maquette de sa descendance. **Exemple** ``` MASTER-RH>MASTER-1>SEMESTRE-1>UE-OPTIONS>ESPAGNOL ``` ### Affectation en masse (Dépréciée) L'affectation en masse permet, pour une période donnée,  d’affecter ou de désaffecter des apprenants sur un Objet Maquette ouvert au choix du cursus et éventuellement sur sa descendance obligatoire.Les affectations ne sont possibles que si le père de l'objet maquette a été affecté ou acquis => contrôle du chemin pédagogique. Les aménagements-acquis sont conservés lors de la désaffectation. ### Affectation individuelle (Dépréciée) L'affectation individuelle permet, pour une période et un apprenant donnés de saisir, modifier ou supprimer pour cet apprenant les affectations, les acquis et les aménagements aux Objets de formations souhaités. Un Objet de formation est soit affecté soit acquis : il ne peut pas être les deux en même temps. Des contrôles sont effectués pour la cohérence aménagement-acquis ou aménagement-affectation ou aménagement-aucun. Les affectations ou la saisie des aménagements ne sont possibles sur un OM que si le père a été affecté (contrôle du chemin pédagogique). ### Paramétrage Un paramétrage est une personnalisation de concepts spécifiques pour des processus métiers. Ils sont gérés dans le Référentiel ou dans chacun des modules. Ils peuvent être utilisés par les différents modules. Le Type d’aménagement est un paramétrage du module REF. ## Informations techniques Toutes les actions de cursus (affectation, désaffectation, acquis, non-acquis, type d’aménagement) de l’apprenant dans CHC seront envoyées au module COC. Toutes les actions de cursus sont en mode upsert (créer si ça n’existe pas ou modifier si ça existe). Seule la liste des types aménagements dans l’entrée sera remplacée par ses anciennes valeurs. ### Règles communes pour réaliser un choix de cursus * L’affectation peut seulement se faire s’il y a une inscription valide sur l’objet maquette ou un des objets maquette de son ascendance. Les statuts de l’inscription proviennent du module INS. * Un CHC sur un Objet Maquette peut se faire uniquement si cet objet Maquette a le témoin ouvertChoixCursus à true. * Lors de la désaffectation d’un apprenant  à un Objet Maquette, l’apprenant sera également désaffecté automatiquement à tous les Objets Maquette de sa descendance. * Un CHC sur un Objet Maquette dans un groupement à plage de choix peut être fait seulement si le nombre de CHC de l’apprenant dans ce groupement ne dépasse pas le maximum autorisé (la plageMax). On ne contrôle pas la valeur mininum de plage de choix. * L’affectation/acquis/type aménagement sur un Objet Maquette mutualisé présent plusieurs fois dans un arbre ne peut être réalisée qu’une fois, c’est-à-dire que l’Objet Maquette (avec un certain code chemin) ne peut être qu’une seule fois avec une affectation / un acquis ou un type aménagement sur le même cursus * La capacité d’accueil d’un Objet Maquette n’est pas contrôlée dans l’API car non bloquante. Cela peut donc entraîner des capacités d’accueil négatives. * Les aménagements avec prise en compte Acquis et Aucun ne sont pas décomptés de la capacité d’accueil d’un Objet Maquette. * La récupération d'un acquis capitalisé empêche son affectation et celle de sa descendance.
 *
 * The version of the OpenAPI document: 6.0.0
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


import fr.univlorraine.pegase.model.chc.ApprenantComposition;
import fr.univlorraine.pegase.model.chc.ClefChemin;
import fr.univlorraine.pegase.model.chc.Composition;
import fr.univlorraine.pegase.model.chc.OperationAffecterDesaffecter;
import fr.univlorraine.pegase.model.chc.StatsChemins;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompositionsApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public CompositionsApi() {
        this(Configuration.getDefaultApiClient());
    }

    public CompositionsApi(ApiClient apiClient) {
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
     * Build call for affecterDesaffecterApprenantsGroupes
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param codeComposition code de la composition (required)
     * @param operationAffecterDesaffecter  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> apprenants affectés/désaffectés </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> capacité dépassée </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> composition non trouvée </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call affecterDesaffecterApprenantsGroupesCall(String codeStructure, String codePeriode, String codeComposition, List<OperationAffecterDesaffecter> operationAffecterDesaffecter, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = operationAffecterDesaffecter;

        // create path and map variables
        String localVarPath = "/composition/{codeStructure}/{codeComposition}/apprenant"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "codeComposition" + "\\}", localVarApiClient.escapeString(codeComposition.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (codePeriode != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("codePeriode", codePeriode));
        }

        final String[] localVarAccepts = {
            
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
        return localVarApiClient.buildCall(basePath, localVarPath, "PATCH", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call affecterDesaffecterApprenantsGroupesValidateBeforeCall(String codeStructure, String codePeriode, String codeComposition, List<OperationAffecterDesaffecter> operationAffecterDesaffecter, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling affecterDesaffecterApprenantsGroupes(Async)");
        }
        
        // verify the required parameter 'codePeriode' is set
        if (codePeriode == null) {
            throw new ApiException("Missing the required parameter 'codePeriode' when calling affecterDesaffecterApprenantsGroupes(Async)");
        }
        
        // verify the required parameter 'codeComposition' is set
        if (codeComposition == null) {
            throw new ApiException("Missing the required parameter 'codeComposition' when calling affecterDesaffecterApprenantsGroupes(Async)");
        }
        
        // verify the required parameter 'operationAffecterDesaffecter' is set
        if (operationAffecterDesaffecter == null) {
            throw new ApiException("Missing the required parameter 'operationAffecterDesaffecter' when calling affecterDesaffecterApprenantsGroupes(Async)");
        }
        

        okhttp3.Call localVarCall = affecterDesaffecterApprenantsGroupesCall(codeStructure, codePeriode, codeComposition, operationAffecterDesaffecter, _callback);
        return localVarCall;

    }

    /**
     * affecter ou désaffecter des apprenants sur des groupes de cette composition
     * affecter ou désaffecter des apprenants sur des groupes de cette composition
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param codeComposition code de la composition (required)
     * @param operationAffecterDesaffecter  (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> apprenants affectés/désaffectés </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> capacité dépassée </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> composition non trouvée </td><td>  -  </td></tr>
     </table>
     */
    public void affecterDesaffecterApprenantsGroupes(String codeStructure, String codePeriode, String codeComposition, List<OperationAffecterDesaffecter> operationAffecterDesaffecter) throws ApiException {
        affecterDesaffecterApprenantsGroupesWithHttpInfo(codeStructure, codePeriode, codeComposition, operationAffecterDesaffecter);
    }

    /**
     * affecter ou désaffecter des apprenants sur des groupes de cette composition
     * affecter ou désaffecter des apprenants sur des groupes de cette composition
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param codeComposition code de la composition (required)
     * @param operationAffecterDesaffecter  (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> apprenants affectés/désaffectés </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> capacité dépassée </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> composition non trouvée </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> affecterDesaffecterApprenantsGroupesWithHttpInfo(String codeStructure, String codePeriode, String codeComposition, List<OperationAffecterDesaffecter> operationAffecterDesaffecter) throws ApiException {
        okhttp3.Call localVarCall = affecterDesaffecterApprenantsGroupesValidateBeforeCall(codeStructure, codePeriode, codeComposition, operationAffecterDesaffecter, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * affecter ou désaffecter des apprenants sur des groupes de cette composition (asynchronously)
     * affecter ou désaffecter des apprenants sur des groupes de cette composition
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param codeComposition code de la composition (required)
     * @param operationAffecterDesaffecter  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> apprenants affectés/désaffectés </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> capacité dépassée </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> composition non trouvée </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call affecterDesaffecterApprenantsGroupesAsync(String codeStructure, String codePeriode, String codeComposition, List<OperationAffecterDesaffecter> operationAffecterDesaffecter, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = affecterDesaffecterApprenantsGroupesValidateBeforeCall(codeStructure, codePeriode, codeComposition, operationAffecterDesaffecter, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for compteDesApprenantsDeComposition
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param codeComposition code de la composition (required)
     * @param clefChemin au titre de ces chemins uniquement (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> apprenants trouvés </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> composition non trouvée </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call compteDesApprenantsDeCompositionCall(String codeStructure, String codePeriode, String codeComposition, List<ClefChemin> clefChemin, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = clefChemin;

        // create path and map variables
        String localVarPath = "/composition/{codeStructure}/{codeComposition}/apprenant/stats"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "codeComposition" + "\\}", localVarApiClient.escapeString(codeComposition.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (codePeriode != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("codePeriode", codePeriode));
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
    private okhttp3.Call compteDesApprenantsDeCompositionValidateBeforeCall(String codeStructure, String codePeriode, String codeComposition, List<ClefChemin> clefChemin, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling compteDesApprenantsDeComposition(Async)");
        }
        
        // verify the required parameter 'codePeriode' is set
        if (codePeriode == null) {
            throw new ApiException("Missing the required parameter 'codePeriode' when calling compteDesApprenantsDeComposition(Async)");
        }
        
        // verify the required parameter 'codeComposition' is set
        if (codeComposition == null) {
            throw new ApiException("Missing the required parameter 'codeComposition' when calling compteDesApprenantsDeComposition(Async)");
        }
        

        okhttp3.Call localVarCall = compteDesApprenantsDeCompositionCall(codeStructure, codePeriode, codeComposition, clefChemin, _callback);
        return localVarCall;

    }

    /**
     * Comptes les apprenants sur cette composition
     * Comptes des apprenants sur cette composition (avec les chemins des cursus concernés)
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param codeComposition code de la composition (required)
     * @param clefChemin au titre de ces chemins uniquement (optional)
     * @return StatsChemins
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> apprenants trouvés </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> composition non trouvée </td><td>  -  </td></tr>
     </table>
     */
    public StatsChemins compteDesApprenantsDeComposition(String codeStructure, String codePeriode, String codeComposition, List<ClefChemin> clefChemin) throws ApiException {
        ApiResponse<StatsChemins> localVarResp = compteDesApprenantsDeCompositionWithHttpInfo(codeStructure, codePeriode, codeComposition, clefChemin);
        return localVarResp.getData();
    }

    /**
     * Comptes les apprenants sur cette composition
     * Comptes des apprenants sur cette composition (avec les chemins des cursus concernés)
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param codeComposition code de la composition (required)
     * @param clefChemin au titre de ces chemins uniquement (optional)
     * @return ApiResponse&lt;StatsChemins&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> apprenants trouvés </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> composition non trouvée </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<StatsChemins> compteDesApprenantsDeCompositionWithHttpInfo(String codeStructure, String codePeriode, String codeComposition, List<ClefChemin> clefChemin) throws ApiException {
        okhttp3.Call localVarCall = compteDesApprenantsDeCompositionValidateBeforeCall(codeStructure, codePeriode, codeComposition, clefChemin, null);
        Type localVarReturnType = new TypeToken<StatsChemins>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Comptes les apprenants sur cette composition (asynchronously)
     * Comptes des apprenants sur cette composition (avec les chemins des cursus concernés)
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param codeComposition code de la composition (required)
     * @param clefChemin au titre de ces chemins uniquement (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> apprenants trouvés </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> composition non trouvée </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call compteDesApprenantsDeCompositionAsync(String codeStructure, String codePeriode, String codeComposition, List<ClefChemin> clefChemin, final ApiCallback<StatsChemins> _callback) throws ApiException {

        okhttp3.Call localVarCall = compteDesApprenantsDeCompositionValidateBeforeCall(codeStructure, codePeriode, codeComposition, clefChemin, _callback);
        Type localVarReturnType = new TypeToken<StatsChemins>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for lireApprenantsAAffecterAComposition
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param codeComposition code de la composition (required)
     * @param clefChemin au titre de ces chemins uniquement (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> apprenants trouvés </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> composition non trouvée </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireApprenantsAAffecterACompositionCall(String codeStructure, String codePeriode, String codeComposition, List<ClefChemin> clefChemin, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = clefChemin;

        // create path and map variables
        String localVarPath = "/compositions/{codeStructure}/apprenant/a-affecter"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (codePeriode != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("codePeriode", codePeriode));
        }

        if (codeComposition != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("codeComposition", codeComposition));
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
    private okhttp3.Call lireApprenantsAAffecterACompositionValidateBeforeCall(String codeStructure, String codePeriode, String codeComposition, List<ClefChemin> clefChemin, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling lireApprenantsAAffecterAComposition(Async)");
        }
        
        // verify the required parameter 'codePeriode' is set
        if (codePeriode == null) {
            throw new ApiException("Missing the required parameter 'codePeriode' when calling lireApprenantsAAffecterAComposition(Async)");
        }
        
        // verify the required parameter 'codeComposition' is set
        if (codeComposition == null) {
            throw new ApiException("Missing the required parameter 'codeComposition' when calling lireApprenantsAAffecterAComposition(Async)");
        }
        

        okhttp3.Call localVarCall = lireApprenantsAAffecterACompositionCall(codeStructure, codePeriode, codeComposition, clefChemin, _callback);
        return localVarCall;

    }

    /**
     * les apprenants à affecter sur cette composition [usage QA]
     * liste des apprenants à affecter sur cette composition (avec les chemins des cursus concernés)
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param codeComposition code de la composition (required)
     * @param clefChemin au titre de ces chemins uniquement (optional)
     * @return List&lt;ApprenantComposition&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> apprenants trouvés </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> composition non trouvée </td><td>  -  </td></tr>
     </table>
     */
    public List<ApprenantComposition> lireApprenantsAAffecterAComposition(String codeStructure, String codePeriode, String codeComposition, List<ClefChemin> clefChemin) throws ApiException {
        ApiResponse<List<ApprenantComposition>> localVarResp = lireApprenantsAAffecterACompositionWithHttpInfo(codeStructure, codePeriode, codeComposition, clefChemin);
        return localVarResp.getData();
    }

    /**
     * les apprenants à affecter sur cette composition [usage QA]
     * liste des apprenants à affecter sur cette composition (avec les chemins des cursus concernés)
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param codeComposition code de la composition (required)
     * @param clefChemin au titre de ces chemins uniquement (optional)
     * @return ApiResponse&lt;List&lt;ApprenantComposition&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> apprenants trouvés </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> composition non trouvée </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<ApprenantComposition>> lireApprenantsAAffecterACompositionWithHttpInfo(String codeStructure, String codePeriode, String codeComposition, List<ClefChemin> clefChemin) throws ApiException {
        okhttp3.Call localVarCall = lireApprenantsAAffecterACompositionValidateBeforeCall(codeStructure, codePeriode, codeComposition, clefChemin, null);
        Type localVarReturnType = new TypeToken<List<ApprenantComposition>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * les apprenants à affecter sur cette composition [usage QA] (asynchronously)
     * liste des apprenants à affecter sur cette composition (avec les chemins des cursus concernés)
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param codeComposition code de la composition (required)
     * @param clefChemin au titre de ces chemins uniquement (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> apprenants trouvés </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> composition non trouvée </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireApprenantsAAffecterACompositionAsync(String codeStructure, String codePeriode, String codeComposition, List<ClefChemin> clefChemin, final ApiCallback<List<ApprenantComposition>> _callback) throws ApiException {

        okhttp3.Call localVarCall = lireApprenantsAAffecterACompositionValidateBeforeCall(codeStructure, codePeriode, codeComposition, clefChemin, _callback);
        Type localVarReturnType = new TypeToken<List<ApprenantComposition>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for lireApprenantsAffectesAGroupe
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param codeComposition code de la composition (required)
     * @param codeGroupe code du groupe de la composition (required)
     * @param clefChemin au titre de ces chemins uniquement (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> apprenants trouvés </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> groupe non trouvé </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireApprenantsAffectesAGroupeCall(String codeStructure, String codePeriode, String codeComposition, String codeGroupe, List<ClefChemin> clefChemin, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = clefChemin;

        // create path and map variables
        String localVarPath = "/composition/{codeStructure}/apprenant/affectes"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (codePeriode != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("codePeriode", codePeriode));
        }

        if (codeComposition != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("codeComposition", codeComposition));
        }

        if (codeGroupe != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("codeGroupe", codeGroupe));
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
    private okhttp3.Call lireApprenantsAffectesAGroupeValidateBeforeCall(String codeStructure, String codePeriode, String codeComposition, String codeGroupe, List<ClefChemin> clefChemin, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling lireApprenantsAffectesAGroupe(Async)");
        }
        
        // verify the required parameter 'codePeriode' is set
        if (codePeriode == null) {
            throw new ApiException("Missing the required parameter 'codePeriode' when calling lireApprenantsAffectesAGroupe(Async)");
        }
        
        // verify the required parameter 'codeComposition' is set
        if (codeComposition == null) {
            throw new ApiException("Missing the required parameter 'codeComposition' when calling lireApprenantsAffectesAGroupe(Async)");
        }
        
        // verify the required parameter 'codeGroupe' is set
        if (codeGroupe == null) {
            throw new ApiException("Missing the required parameter 'codeGroupe' when calling lireApprenantsAffectesAGroupe(Async)");
        }
        

        okhttp3.Call localVarCall = lireApprenantsAffectesAGroupeCall(codeStructure, codePeriode, codeComposition, codeGroupe, clefChemin, _callback);
        return localVarCall;

    }

    /**
     * les apprenants déjà affectés sur cette groupe
     * liste des apprenants déjà affectés sur ce groupe (avec les chemins des cursus concernés)
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param codeComposition code de la composition (required)
     * @param codeGroupe code du groupe de la composition (required)
     * @param clefChemin au titre de ces chemins uniquement (optional)
     * @return List&lt;ApprenantComposition&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> apprenants trouvés </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> groupe non trouvé </td><td>  -  </td></tr>
     </table>
     */
    public List<ApprenantComposition> lireApprenantsAffectesAGroupe(String codeStructure, String codePeriode, String codeComposition, String codeGroupe, List<ClefChemin> clefChemin) throws ApiException {
        ApiResponse<List<ApprenantComposition>> localVarResp = lireApprenantsAffectesAGroupeWithHttpInfo(codeStructure, codePeriode, codeComposition, codeGroupe, clefChemin);
        return localVarResp.getData();
    }

    /**
     * les apprenants déjà affectés sur cette groupe
     * liste des apprenants déjà affectés sur ce groupe (avec les chemins des cursus concernés)
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param codeComposition code de la composition (required)
     * @param codeGroupe code du groupe de la composition (required)
     * @param clefChemin au titre de ces chemins uniquement (optional)
     * @return ApiResponse&lt;List&lt;ApprenantComposition&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> apprenants trouvés </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> groupe non trouvé </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<ApprenantComposition>> lireApprenantsAffectesAGroupeWithHttpInfo(String codeStructure, String codePeriode, String codeComposition, String codeGroupe, List<ClefChemin> clefChemin) throws ApiException {
        okhttp3.Call localVarCall = lireApprenantsAffectesAGroupeValidateBeforeCall(codeStructure, codePeriode, codeComposition, codeGroupe, clefChemin, null);
        Type localVarReturnType = new TypeToken<List<ApprenantComposition>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * les apprenants déjà affectés sur cette groupe (asynchronously)
     * liste des apprenants déjà affectés sur ce groupe (avec les chemins des cursus concernés)
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param codeComposition code de la composition (required)
     * @param codeGroupe code du groupe de la composition (required)
     * @param clefChemin au titre de ces chemins uniquement (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> apprenants trouvés </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> groupe non trouvé </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireApprenantsAffectesAGroupeAsync(String codeStructure, String codePeriode, String codeComposition, String codeGroupe, List<ClefChemin> clefChemin, final ApiCallback<List<ApprenantComposition>> _callback) throws ApiException {

        okhttp3.Call localVarCall = lireApprenantsAffectesAGroupeValidateBeforeCall(codeStructure, codePeriode, codeComposition, codeGroupe, clefChemin, _callback);
        Type localVarReturnType = new TypeToken<List<ApprenantComposition>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for lireListeCompositions
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param composition code ou libelle (* pour recherche) de la(es) composition(s) (optional)
     * @param formation code ou libelle (* pour recherche) de la(es) formation(s) (optional)
     * @param typeFormation code du type de formation (optional)
     * @param objetFormation code ou libelle (* pour recherche) de la(es) objet(s) de formation(s) (optional)
     * @param typeObjetFormation code du type d&#39;objet de formation (optional)
     * @param tri tris (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> compositions trouvées </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireListeCompositionsCall(String codeStructure, String codePeriode, String composition, String formation, String typeFormation, String objetFormation, String typeObjetFormation, List<String> tri, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/composition/{codeStructure}"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (codePeriode != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("codePeriode", codePeriode));
        }

        if (composition != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("composition", composition));
        }

        if (formation != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("formation", formation));
        }

        if (typeFormation != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("type_formation", typeFormation));
        }

        if (objetFormation != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("objet_formation", objetFormation));
        }

        if (typeObjetFormation != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("type_objet_formation", typeObjetFormation));
        }

        if (tri != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "tri", tri));
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
    private okhttp3.Call lireListeCompositionsValidateBeforeCall(String codeStructure, String codePeriode, String composition, String formation, String typeFormation, String objetFormation, String typeObjetFormation, List<String> tri, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling lireListeCompositions(Async)");
        }
        
        // verify the required parameter 'codePeriode' is set
        if (codePeriode == null) {
            throw new ApiException("Missing the required parameter 'codePeriode' when calling lireListeCompositions(Async)");
        }
        

        okhttp3.Call localVarCall = lireListeCompositionsCall(codeStructure, codePeriode, composition, formation, typeFormation, objetFormation, typeObjetFormation, tri, _callback);
        return localVarCall;

    }

    /**
     * liste des compositions par période avec filtres et tris
     * liste des compositions par période avec filtres et tris
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param composition code ou libelle (* pour recherche) de la(es) composition(s) (optional)
     * @param formation code ou libelle (* pour recherche) de la(es) formation(s) (optional)
     * @param typeFormation code du type de formation (optional)
     * @param objetFormation code ou libelle (* pour recherche) de la(es) objet(s) de formation(s) (optional)
     * @param typeObjetFormation code du type d&#39;objet de formation (optional)
     * @param tri tris (optional)
     * @return List&lt;Composition&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> compositions trouvées </td><td>  -  </td></tr>
     </table>
     */
    public List<Composition> lireListeCompositions(String codeStructure, String codePeriode, String composition, String formation, String typeFormation, String objetFormation, String typeObjetFormation, List<String> tri) throws ApiException {
        ApiResponse<List<Composition>> localVarResp = lireListeCompositionsWithHttpInfo(codeStructure, codePeriode, composition, formation, typeFormation, objetFormation, typeObjetFormation, tri);
        return localVarResp.getData();
    }

    /**
     * liste des compositions par période avec filtres et tris
     * liste des compositions par période avec filtres et tris
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param composition code ou libelle (* pour recherche) de la(es) composition(s) (optional)
     * @param formation code ou libelle (* pour recherche) de la(es) formation(s) (optional)
     * @param typeFormation code du type de formation (optional)
     * @param objetFormation code ou libelle (* pour recherche) de la(es) objet(s) de formation(s) (optional)
     * @param typeObjetFormation code du type d&#39;objet de formation (optional)
     * @param tri tris (optional)
     * @return ApiResponse&lt;List&lt;Composition&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> compositions trouvées </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<Composition>> lireListeCompositionsWithHttpInfo(String codeStructure, String codePeriode, String composition, String formation, String typeFormation, String objetFormation, String typeObjetFormation, List<String> tri) throws ApiException {
        okhttp3.Call localVarCall = lireListeCompositionsValidateBeforeCall(codeStructure, codePeriode, composition, formation, typeFormation, objetFormation, typeObjetFormation, tri, null);
        Type localVarReturnType = new TypeToken<List<Composition>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * liste des compositions par période avec filtres et tris (asynchronously)
     * liste des compositions par période avec filtres et tris
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param composition code ou libelle (* pour recherche) de la(es) composition(s) (optional)
     * @param formation code ou libelle (* pour recherche) de la(es) formation(s) (optional)
     * @param typeFormation code du type de formation (optional)
     * @param objetFormation code ou libelle (* pour recherche) de la(es) objet(s) de formation(s) (optional)
     * @param typeObjetFormation code du type d&#39;objet de formation (optional)
     * @param tri tris (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> compositions trouvées </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireListeCompositionsAsync(String codeStructure, String codePeriode, String composition, String formation, String typeFormation, String objetFormation, String typeObjetFormation, List<String> tri, final ApiCallback<List<Composition>> _callback) throws ApiException {

        okhttp3.Call localVarCall = lireListeCompositionsValidateBeforeCall(codeStructure, codePeriode, composition, formation, typeFormation, objetFormation, typeObjetFormation, tri, _callback);
        Type localVarReturnType = new TypeToken<List<Composition>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
