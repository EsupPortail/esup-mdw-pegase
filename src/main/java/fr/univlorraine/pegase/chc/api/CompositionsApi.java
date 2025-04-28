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


import fr.univlorraine.pegase.chc.model.ApprenantComposition;
import fr.univlorraine.pegase.chc.model.AssociationObjetMaquetteRequest;
import fr.univlorraine.pegase.chc.model.ClefChemin;
import fr.univlorraine.pegase.chc.model.Composition;
import fr.univlorraine.pegase.chc.model.CompositionCreation;
import fr.univlorraine.pegase.chc.model.CompositionDetails;
import fr.univlorraine.pegase.chc.model.CompositionModification;
import fr.univlorraine.pegase.chc.model.CompositionPourListe;
import fr.univlorraine.pegase.chc.model.CompositionRecopie;
import fr.univlorraine.pegase.chc.model.CustomPageable;
import java.io.File;
import fr.univlorraine.pegase.chc.model.OperationAffecterDesaffecter;
import fr.univlorraine.pegase.chc.model.PagedComposition;
import fr.univlorraine.pegase.chc.model.StatsChemins;
import java.util.UUID;

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
     * @param compositionUuid identifiant unique de la composition (required)
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
    public okhttp3.Call affecterDesaffecterApprenantsGroupesCall(String codeStructure, UUID compositionUuid, List<OperationAffecterDesaffecter> operationAffecterDesaffecter, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/composition/{codeStructure}/apprenant/{compositionUuid}"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "compositionUuid" + "\\}", localVarApiClient.escapeString(compositionUuid.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

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
    private okhttp3.Call affecterDesaffecterApprenantsGroupesValidateBeforeCall(String codeStructure, UUID compositionUuid, List<OperationAffecterDesaffecter> operationAffecterDesaffecter, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling affecterDesaffecterApprenantsGroupes(Async)");
        }
        
        // verify the required parameter 'compositionUuid' is set
        if (compositionUuid == null) {
            throw new ApiException("Missing the required parameter 'compositionUuid' when calling affecterDesaffecterApprenantsGroupes(Async)");
        }
        
        // verify the required parameter 'operationAffecterDesaffecter' is set
        if (operationAffecterDesaffecter == null) {
            throw new ApiException("Missing the required parameter 'operationAffecterDesaffecter' when calling affecterDesaffecterApprenantsGroupes(Async)");
        }
        

        okhttp3.Call localVarCall = affecterDesaffecterApprenantsGroupesCall(codeStructure, compositionUuid, operationAffecterDesaffecter, _callback);
        return localVarCall;

    }

    /**
     * affecter ou désaffecter des apprenants sur des groupes de cette composition
     * affecter ou désaffecter des apprenants sur des groupes de cette composition
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param compositionUuid identifiant unique de la composition (required)
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
    public void affecterDesaffecterApprenantsGroupes(String codeStructure, UUID compositionUuid, List<OperationAffecterDesaffecter> operationAffecterDesaffecter) throws ApiException {
        affecterDesaffecterApprenantsGroupesWithHttpInfo(codeStructure, compositionUuid, operationAffecterDesaffecter);
    }

    /**
     * affecter ou désaffecter des apprenants sur des groupes de cette composition
     * affecter ou désaffecter des apprenants sur des groupes de cette composition
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param compositionUuid identifiant unique de la composition (required)
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
    public ApiResponse<Void> affecterDesaffecterApprenantsGroupesWithHttpInfo(String codeStructure, UUID compositionUuid, List<OperationAffecterDesaffecter> operationAffecterDesaffecter) throws ApiException {
        okhttp3.Call localVarCall = affecterDesaffecterApprenantsGroupesValidateBeforeCall(codeStructure, compositionUuid, operationAffecterDesaffecter, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * affecter ou désaffecter des apprenants sur des groupes de cette composition (asynchronously)
     * affecter ou désaffecter des apprenants sur des groupes de cette composition
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param compositionUuid identifiant unique de la composition (required)
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
    public okhttp3.Call affecterDesaffecterApprenantsGroupesAsync(String codeStructure, UUID compositionUuid, List<OperationAffecterDesaffecter> operationAffecterDesaffecter, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = affecterDesaffecterApprenantsGroupesValidateBeforeCall(codeStructure, compositionUuid, operationAffecterDesaffecter, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for associationFormationComposition
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param uuidCompo L&#39;uuid de la composition sur laquelle il faut associer les formations (required)
     * @param associationObjetMaquetteRequest La liste des uuids des objets formation à associer (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La composition et les formations sont associées </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Periode, Composition ou Formation non trouvé </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call associationFormationCompositionCall(String codeStructure, UUID uuidCompo, AssociationObjetMaquetteRequest associationObjetMaquetteRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = associationObjetMaquetteRequest;

        // create path and map variables
        String localVarPath = "/composition/{codeStructure}/association-formations/{uuidCompo}"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "uuidCompo" + "\\}", localVarApiClient.escapeString(uuidCompo.toString()));

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
    private okhttp3.Call associationFormationCompositionValidateBeforeCall(String codeStructure, UUID uuidCompo, AssociationObjetMaquetteRequest associationObjetMaquetteRequest, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling associationFormationComposition(Async)");
        }
        
        // verify the required parameter 'uuidCompo' is set
        if (uuidCompo == null) {
            throw new ApiException("Missing the required parameter 'uuidCompo' when calling associationFormationComposition(Async)");
        }
        
        // verify the required parameter 'associationObjetMaquetteRequest' is set
        if (associationObjetMaquetteRequest == null) {
            throw new ApiException("Missing the required parameter 'associationObjetMaquetteRequest' when calling associationFormationComposition(Async)");
        }
        

        okhttp3.Call localVarCall = associationFormationCompositionCall(codeStructure, uuidCompo, associationObjetMaquetteRequest, _callback);
        return localVarCall;

    }

    /**
     * Associer une ou plusieurs formations sur une composition
     * Association de formation sur une composition pour une periode
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param uuidCompo L&#39;uuid de la composition sur laquelle il faut associer les formations (required)
     * @param associationObjetMaquetteRequest La liste des uuids des objets formation à associer (required)
     * @return CompositionDetails
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La composition et les formations sont associées </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Periode, Composition ou Formation non trouvé </td><td>  -  </td></tr>
     </table>
     */
    public CompositionDetails associationFormationComposition(String codeStructure, UUID uuidCompo, AssociationObjetMaquetteRequest associationObjetMaquetteRequest) throws ApiException {
        ApiResponse<CompositionDetails> localVarResp = associationFormationCompositionWithHttpInfo(codeStructure, uuidCompo, associationObjetMaquetteRequest);
        return localVarResp.getData();
    }

    /**
     * Associer une ou plusieurs formations sur une composition
     * Association de formation sur une composition pour une periode
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param uuidCompo L&#39;uuid de la composition sur laquelle il faut associer les formations (required)
     * @param associationObjetMaquetteRequest La liste des uuids des objets formation à associer (required)
     * @return ApiResponse&lt;CompositionDetails&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La composition et les formations sont associées </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Periode, Composition ou Formation non trouvé </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<CompositionDetails> associationFormationCompositionWithHttpInfo(String codeStructure, UUID uuidCompo, AssociationObjetMaquetteRequest associationObjetMaquetteRequest) throws ApiException {
        okhttp3.Call localVarCall = associationFormationCompositionValidateBeforeCall(codeStructure, uuidCompo, associationObjetMaquetteRequest, null);
        Type localVarReturnType = new TypeToken<CompositionDetails>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Associer une ou plusieurs formations sur une composition (asynchronously)
     * Association de formation sur une composition pour une periode
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param uuidCompo L&#39;uuid de la composition sur laquelle il faut associer les formations (required)
     * @param associationObjetMaquetteRequest La liste des uuids des objets formation à associer (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La composition et les formations sont associées </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Periode, Composition ou Formation non trouvé </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call associationFormationCompositionAsync(String codeStructure, UUID uuidCompo, AssociationObjetMaquetteRequest associationObjetMaquetteRequest, final ApiCallback<CompositionDetails> _callback) throws ApiException {

        okhttp3.Call localVarCall = associationFormationCompositionValidateBeforeCall(codeStructure, uuidCompo, associationObjetMaquetteRequest, _callback);
        Type localVarReturnType = new TypeToken<CompositionDetails>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for associationObjetFormationComposition
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param uuidCompo L&#39;uuid de la composition sur laquelle il faut associer les objets formation (required)
     * @param associationObjetMaquetteRequest La liste des uuids des objets formation à associer (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La composition et les objets formation sont associés </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Periode, Composition ou Objet formation non trouvé </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call associationObjetFormationCompositionCall(String codeStructure, UUID uuidCompo, AssociationObjetMaquetteRequest associationObjetMaquetteRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = associationObjetMaquetteRequest;

        // create path and map variables
        String localVarPath = "/composition/{codeStructure}/association-objets-formation/{uuidCompo}"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "uuidCompo" + "\\}", localVarApiClient.escapeString(uuidCompo.toString()));

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
    private okhttp3.Call associationObjetFormationCompositionValidateBeforeCall(String codeStructure, UUID uuidCompo, AssociationObjetMaquetteRequest associationObjetMaquetteRequest, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling associationObjetFormationComposition(Async)");
        }
        
        // verify the required parameter 'uuidCompo' is set
        if (uuidCompo == null) {
            throw new ApiException("Missing the required parameter 'uuidCompo' when calling associationObjetFormationComposition(Async)");
        }
        
        // verify the required parameter 'associationObjetMaquetteRequest' is set
        if (associationObjetMaquetteRequest == null) {
            throw new ApiException("Missing the required parameter 'associationObjetMaquetteRequest' when calling associationObjetFormationComposition(Async)");
        }
        

        okhttp3.Call localVarCall = associationObjetFormationCompositionCall(codeStructure, uuidCompo, associationObjetMaquetteRequest, _callback);
        return localVarCall;

    }

    /**
     * Associer un ou plusieurs objets formation sur une composition
     * Association d&#39;objets formation sur une composition pour une periode
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param uuidCompo L&#39;uuid de la composition sur laquelle il faut associer les objets formation (required)
     * @param associationObjetMaquetteRequest La liste des uuids des objets formation à associer (required)
     * @return CompositionDetails
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La composition et les objets formation sont associés </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Periode, Composition ou Objet formation non trouvé </td><td>  -  </td></tr>
     </table>
     */
    public CompositionDetails associationObjetFormationComposition(String codeStructure, UUID uuidCompo, AssociationObjetMaquetteRequest associationObjetMaquetteRequest) throws ApiException {
        ApiResponse<CompositionDetails> localVarResp = associationObjetFormationCompositionWithHttpInfo(codeStructure, uuidCompo, associationObjetMaquetteRequest);
        return localVarResp.getData();
    }

    /**
     * Associer un ou plusieurs objets formation sur une composition
     * Association d&#39;objets formation sur une composition pour une periode
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param uuidCompo L&#39;uuid de la composition sur laquelle il faut associer les objets formation (required)
     * @param associationObjetMaquetteRequest La liste des uuids des objets formation à associer (required)
     * @return ApiResponse&lt;CompositionDetails&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La composition et les objets formation sont associés </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Periode, Composition ou Objet formation non trouvé </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<CompositionDetails> associationObjetFormationCompositionWithHttpInfo(String codeStructure, UUID uuidCompo, AssociationObjetMaquetteRequest associationObjetMaquetteRequest) throws ApiException {
        okhttp3.Call localVarCall = associationObjetFormationCompositionValidateBeforeCall(codeStructure, uuidCompo, associationObjetMaquetteRequest, null);
        Type localVarReturnType = new TypeToken<CompositionDetails>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Associer un ou plusieurs objets formation sur une composition (asynchronously)
     * Association d&#39;objets formation sur une composition pour une periode
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param uuidCompo L&#39;uuid de la composition sur laquelle il faut associer les objets formation (required)
     * @param associationObjetMaquetteRequest La liste des uuids des objets formation à associer (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La composition et les objets formation sont associés </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Periode, Composition ou Objet formation non trouvé </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call associationObjetFormationCompositionAsync(String codeStructure, UUID uuidCompo, AssociationObjetMaquetteRequest associationObjetMaquetteRequest, final ApiCallback<CompositionDetails> _callback) throws ApiException {

        okhttp3.Call localVarCall = associationObjetFormationCompositionValidateBeforeCall(codeStructure, uuidCompo, associationObjetMaquetteRequest, _callback);
        Type localVarReturnType = new TypeToken<CompositionDetails>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
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
     * Build call for creerComposition
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param compositionCreation  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> La composition et les groupes sont créés </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Periode non trouvée </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call creerCompositionCall(String codeStructure, String codePeriode, CompositionCreation compositionCreation, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = compositionCreation;

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
    private okhttp3.Call creerCompositionValidateBeforeCall(String codeStructure, String codePeriode, CompositionCreation compositionCreation, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling creerComposition(Async)");
        }
        
        // verify the required parameter 'codePeriode' is set
        if (codePeriode == null) {
            throw new ApiException("Missing the required parameter 'codePeriode' when calling creerComposition(Async)");
        }
        
        // verify the required parameter 'compositionCreation' is set
        if (compositionCreation == null) {
            throw new ApiException("Missing the required parameter 'compositionCreation' when calling creerComposition(Async)");
        }
        

        okhttp3.Call localVarCall = creerCompositionCall(codeStructure, codePeriode, compositionCreation, _callback);
        return localVarCall;

    }

    /**
     * Créer une composition avec les groupes
     * Création d&#39;une composition avec ses groupes
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param compositionCreation  (required)
     * @return CompositionDetails
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> La composition et les groupes sont créés </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Periode non trouvée </td><td>  -  </td></tr>
     </table>
     */
    public CompositionDetails creerComposition(String codeStructure, String codePeriode, CompositionCreation compositionCreation) throws ApiException {
        ApiResponse<CompositionDetails> localVarResp = creerCompositionWithHttpInfo(codeStructure, codePeriode, compositionCreation);
        return localVarResp.getData();
    }

    /**
     * Créer une composition avec les groupes
     * Création d&#39;une composition avec ses groupes
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param compositionCreation  (required)
     * @return ApiResponse&lt;CompositionDetails&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> La composition et les groupes sont créés </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Periode non trouvée </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<CompositionDetails> creerCompositionWithHttpInfo(String codeStructure, String codePeriode, CompositionCreation compositionCreation) throws ApiException {
        okhttp3.Call localVarCall = creerCompositionValidateBeforeCall(codeStructure, codePeriode, compositionCreation, null);
        Type localVarReturnType = new TypeToken<CompositionDetails>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Créer une composition avec les groupes (asynchronously)
     * Création d&#39;une composition avec ses groupes
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param compositionCreation  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> La composition et les groupes sont créés </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Periode non trouvée </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call creerCompositionAsync(String codeStructure, String codePeriode, CompositionCreation compositionCreation, final ApiCallback<CompositionDetails> _callback) throws ApiException {

        okhttp3.Call localVarCall = creerCompositionValidateBeforeCall(codeStructure, codePeriode, compositionCreation, _callback);
        Type localVarReturnType = new TypeToken<CompositionDetails>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for dissociationFormationComposition
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param uuidCompo L&#39;uuid de la composition sur laquelle il faut dissocier les formations (required)
     * @param associationObjetMaquetteRequest La liste des uuids des objets formation à associer (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La composition et les formations sont dissociées </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Periode, Composition ou Formation non trouvé </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call dissociationFormationCompositionCall(String codeStructure, UUID uuidCompo, AssociationObjetMaquetteRequest associationObjetMaquetteRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = associationObjetMaquetteRequest;

        // create path and map variables
        String localVarPath = "/composition/{codeStructure}/dissociation-formations/{uuidCompo}"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "uuidCompo" + "\\}", localVarApiClient.escapeString(uuidCompo.toString()));

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
    private okhttp3.Call dissociationFormationCompositionValidateBeforeCall(String codeStructure, UUID uuidCompo, AssociationObjetMaquetteRequest associationObjetMaquetteRequest, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling dissociationFormationComposition(Async)");
        }
        
        // verify the required parameter 'uuidCompo' is set
        if (uuidCompo == null) {
            throw new ApiException("Missing the required parameter 'uuidCompo' when calling dissociationFormationComposition(Async)");
        }
        
        // verify the required parameter 'associationObjetMaquetteRequest' is set
        if (associationObjetMaquetteRequest == null) {
            throw new ApiException("Missing the required parameter 'associationObjetMaquetteRequest' when calling dissociationFormationComposition(Async)");
        }
        

        okhttp3.Call localVarCall = dissociationFormationCompositionCall(codeStructure, uuidCompo, associationObjetMaquetteRequest, _callback);
        return localVarCall;

    }

    /**
     * Dissociation d&#39;une ou plusieurs formations d&#39;une composition
     * Dissociation de formation sur une composition pour une periode
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param uuidCompo L&#39;uuid de la composition sur laquelle il faut dissocier les formations (required)
     * @param associationObjetMaquetteRequest La liste des uuids des objets formation à associer (required)
     * @return CompositionDetails
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La composition et les formations sont dissociées </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Periode, Composition ou Formation non trouvé </td><td>  -  </td></tr>
     </table>
     */
    public CompositionDetails dissociationFormationComposition(String codeStructure, UUID uuidCompo, AssociationObjetMaquetteRequest associationObjetMaquetteRequest) throws ApiException {
        ApiResponse<CompositionDetails> localVarResp = dissociationFormationCompositionWithHttpInfo(codeStructure, uuidCompo, associationObjetMaquetteRequest);
        return localVarResp.getData();
    }

    /**
     * Dissociation d&#39;une ou plusieurs formations d&#39;une composition
     * Dissociation de formation sur une composition pour une periode
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param uuidCompo L&#39;uuid de la composition sur laquelle il faut dissocier les formations (required)
     * @param associationObjetMaquetteRequest La liste des uuids des objets formation à associer (required)
     * @return ApiResponse&lt;CompositionDetails&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La composition et les formations sont dissociées </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Periode, Composition ou Formation non trouvé </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<CompositionDetails> dissociationFormationCompositionWithHttpInfo(String codeStructure, UUID uuidCompo, AssociationObjetMaquetteRequest associationObjetMaquetteRequest) throws ApiException {
        okhttp3.Call localVarCall = dissociationFormationCompositionValidateBeforeCall(codeStructure, uuidCompo, associationObjetMaquetteRequest, null);
        Type localVarReturnType = new TypeToken<CompositionDetails>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Dissociation d&#39;une ou plusieurs formations d&#39;une composition (asynchronously)
     * Dissociation de formation sur une composition pour une periode
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param uuidCompo L&#39;uuid de la composition sur laquelle il faut dissocier les formations (required)
     * @param associationObjetMaquetteRequest La liste des uuids des objets formation à associer (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La composition et les formations sont dissociées </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Periode, Composition ou Formation non trouvé </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call dissociationFormationCompositionAsync(String codeStructure, UUID uuidCompo, AssociationObjetMaquetteRequest associationObjetMaquetteRequest, final ApiCallback<CompositionDetails> _callback) throws ApiException {

        okhttp3.Call localVarCall = dissociationFormationCompositionValidateBeforeCall(codeStructure, uuidCompo, associationObjetMaquetteRequest, _callback);
        Type localVarReturnType = new TypeToken<CompositionDetails>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for dissociationObjetFormationComposition
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param uuidCompo L&#39;uuid de la composition sur laquelle il faut dissocier les objets formation (required)
     * @param associationObjetMaquetteRequest La liste des uuids des objets formation à associer (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La composition et les objets formation sont dissociés </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Periode, Composition ou Objet formation non trouvé </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call dissociationObjetFormationCompositionCall(String codeStructure, UUID uuidCompo, AssociationObjetMaquetteRequest associationObjetMaquetteRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = associationObjetMaquetteRequest;

        // create path and map variables
        String localVarPath = "/composition/{codeStructure}/dissociation-objets-formation/{uuidCompo}"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "uuidCompo" + "\\}", localVarApiClient.escapeString(uuidCompo.toString()));

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
    private okhttp3.Call dissociationObjetFormationCompositionValidateBeforeCall(String codeStructure, UUID uuidCompo, AssociationObjetMaquetteRequest associationObjetMaquetteRequest, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling dissociationObjetFormationComposition(Async)");
        }
        
        // verify the required parameter 'uuidCompo' is set
        if (uuidCompo == null) {
            throw new ApiException("Missing the required parameter 'uuidCompo' when calling dissociationObjetFormationComposition(Async)");
        }
        
        // verify the required parameter 'associationObjetMaquetteRequest' is set
        if (associationObjetMaquetteRequest == null) {
            throw new ApiException("Missing the required parameter 'associationObjetMaquetteRequest' when calling dissociationObjetFormationComposition(Async)");
        }
        

        okhttp3.Call localVarCall = dissociationObjetFormationCompositionCall(codeStructure, uuidCompo, associationObjetMaquetteRequest, _callback);
        return localVarCall;

    }

    /**
     * Dissocier un ou plusieurs objets formation d&#39;une composition
     * Dissociation d&#39;objets de formation d&#39;une composition pour une periode
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param uuidCompo L&#39;uuid de la composition sur laquelle il faut dissocier les objets formation (required)
     * @param associationObjetMaquetteRequest La liste des uuids des objets formation à associer (required)
     * @return CompositionDetails
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La composition et les objets formation sont dissociés </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Periode, Composition ou Objet formation non trouvé </td><td>  -  </td></tr>
     </table>
     */
    public CompositionDetails dissociationObjetFormationComposition(String codeStructure, UUID uuidCompo, AssociationObjetMaquetteRequest associationObjetMaquetteRequest) throws ApiException {
        ApiResponse<CompositionDetails> localVarResp = dissociationObjetFormationCompositionWithHttpInfo(codeStructure, uuidCompo, associationObjetMaquetteRequest);
        return localVarResp.getData();
    }

    /**
     * Dissocier un ou plusieurs objets formation d&#39;une composition
     * Dissociation d&#39;objets de formation d&#39;une composition pour une periode
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param uuidCompo L&#39;uuid de la composition sur laquelle il faut dissocier les objets formation (required)
     * @param associationObjetMaquetteRequest La liste des uuids des objets formation à associer (required)
     * @return ApiResponse&lt;CompositionDetails&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La composition et les objets formation sont dissociés </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Periode, Composition ou Objet formation non trouvé </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<CompositionDetails> dissociationObjetFormationCompositionWithHttpInfo(String codeStructure, UUID uuidCompo, AssociationObjetMaquetteRequest associationObjetMaquetteRequest) throws ApiException {
        okhttp3.Call localVarCall = dissociationObjetFormationCompositionValidateBeforeCall(codeStructure, uuidCompo, associationObjetMaquetteRequest, null);
        Type localVarReturnType = new TypeToken<CompositionDetails>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Dissocier un ou plusieurs objets formation d&#39;une composition (asynchronously)
     * Dissociation d&#39;objets de formation d&#39;une composition pour une periode
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param uuidCompo L&#39;uuid de la composition sur laquelle il faut dissocier les objets formation (required)
     * @param associationObjetMaquetteRequest La liste des uuids des objets formation à associer (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La composition et les objets formation sont dissociés </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Periode, Composition ou Objet formation non trouvé </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call dissociationObjetFormationCompositionAsync(String codeStructure, UUID uuidCompo, AssociationObjetMaquetteRequest associationObjetMaquetteRequest, final ApiCallback<CompositionDetails> _callback) throws ApiException {

        okhttp3.Call localVarCall = dissociationObjetFormationCompositionValidateBeforeCall(codeStructure, uuidCompo, associationObjetMaquetteRequest, _callback);
        Type localVarReturnType = new TypeToken<CompositionDetails>(){}.getType();
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
     * les apprenants à affecter sur cette composition
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
     * les apprenants à affecter sur cette composition
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
     * les apprenants à affecter sur cette composition (asynchronously)
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
     * Build call for lireComposition
     * @param compositionUuid L&#39;UUID de la composition (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La composition </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> composition non trouvée </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireCompositionCall(UUID compositionUuid, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/compositions/{compositionUuid}/details"
            .replaceAll("\\{" + "compositionUuid" + "\\}", localVarApiClient.escapeString(compositionUuid.toString()));

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
    private okhttp3.Call lireCompositionValidateBeforeCall(UUID compositionUuid, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'compositionUuid' is set
        if (compositionUuid == null) {
            throw new ApiException("Missing the required parameter 'compositionUuid' when calling lireComposition(Async)");
        }
        

        okhttp3.Call localVarCall = lireCompositionCall(compositionUuid, _callback);
        return localVarCall;

    }

    /**
     * Lire details d&#39;une composition
     * Récuperer les details d&#39;une composition
     * @param compositionUuid L&#39;UUID de la composition (required)
     * @return CompositionDetails
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La composition </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> composition non trouvée </td><td>  -  </td></tr>
     </table>
     */
    public CompositionDetails lireComposition(UUID compositionUuid) throws ApiException {
        ApiResponse<CompositionDetails> localVarResp = lireCompositionWithHttpInfo(compositionUuid);
        return localVarResp.getData();
    }

    /**
     * Lire details d&#39;une composition
     * Récuperer les details d&#39;une composition
     * @param compositionUuid L&#39;UUID de la composition (required)
     * @return ApiResponse&lt;CompositionDetails&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La composition </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> composition non trouvée </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<CompositionDetails> lireCompositionWithHttpInfo(UUID compositionUuid) throws ApiException {
        okhttp3.Call localVarCall = lireCompositionValidateBeforeCall(compositionUuid, null);
        Type localVarReturnType = new TypeToken<CompositionDetails>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Lire details d&#39;une composition (asynchronously)
     * Récuperer les details d&#39;une composition
     * @param compositionUuid L&#39;UUID de la composition (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La composition </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> composition non trouvée </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireCompositionAsync(UUID compositionUuid, final ApiCallback<CompositionDetails> _callback) throws ApiException {

        okhttp3.Call localVarCall = lireCompositionValidateBeforeCall(compositionUuid, _callback);
        Type localVarReturnType = new TypeToken<CompositionDetails>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for lireListe
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> compositions trouvées </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireListeCall(String codeStructure, String codePeriode, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/compositions/{codeStructure}"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()));

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
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "idTokenAuth" };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call lireListeValidateBeforeCall(String codeStructure, String codePeriode, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling lireListe(Async)");
        }
        
        // verify the required parameter 'codePeriode' is set
        if (codePeriode == null) {
            throw new ApiException("Missing the required parameter 'codePeriode' when calling lireListe(Async)");
        }
        

        okhttp3.Call localVarCall = lireListeCall(codeStructure, codePeriode, _callback);
        return localVarCall;

    }

    /**
     * Liste des composition, pour un établissement
     * Liste des composition, pour un établissement
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @return List&lt;CompositionPourListe&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> compositions trouvées </td><td>  -  </td></tr>
     </table>
     */
    public List<CompositionPourListe> lireListe(String codeStructure, String codePeriode) throws ApiException {
        ApiResponse<List<CompositionPourListe>> localVarResp = lireListeWithHttpInfo(codeStructure, codePeriode);
        return localVarResp.getData();
    }

    /**
     * Liste des composition, pour un établissement
     * Liste des composition, pour un établissement
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @return ApiResponse&lt;List&lt;CompositionPourListe&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> compositions trouvées </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<CompositionPourListe>> lireListeWithHttpInfo(String codeStructure, String codePeriode) throws ApiException {
        okhttp3.Call localVarCall = lireListeValidateBeforeCall(codeStructure, codePeriode, null);
        Type localVarReturnType = new TypeToken<List<CompositionPourListe>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Liste des composition, pour un établissement (asynchronously)
     * Liste des composition, pour un établissement
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> compositions trouvées </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireListeAsync(String codeStructure, String codePeriode, final ApiCallback<List<CompositionPourListe>> _callback) throws ApiException {

        okhttp3.Call localVarCall = lireListeValidateBeforeCall(codeStructure, codePeriode, _callback);
        Type localVarReturnType = new TypeToken<List<CompositionPourListe>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for lireListeCompositions
     * @param uuidPeriode L&#39;uuid de la periode (required)
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
    public okhttp3.Call lireListeCompositionsCall(UUID uuidPeriode, String composition, String formation, String typeFormation, String objetFormation, String typeObjetFormation, List<String> tri, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/composition";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (uuidPeriode != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("uuidPeriode", uuidPeriode));
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
    private okhttp3.Call lireListeCompositionsValidateBeforeCall(UUID uuidPeriode, String composition, String formation, String typeFormation, String objetFormation, String typeObjetFormation, List<String> tri, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'uuidPeriode' is set
        if (uuidPeriode == null) {
            throw new ApiException("Missing the required parameter 'uuidPeriode' when calling lireListeCompositions(Async)");
        }
        

        okhttp3.Call localVarCall = lireListeCompositionsCall(uuidPeriode, composition, formation, typeFormation, objetFormation, typeObjetFormation, tri, _callback);
        return localVarCall;

    }

    /**
     * liste des compositions par période avec filtres et tris
     * Recherche des compositions par période avec filtres et tris
     * @param uuidPeriode L&#39;uuid de la periode (required)
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
    public List<Composition> lireListeCompositions(UUID uuidPeriode, String composition, String formation, String typeFormation, String objetFormation, String typeObjetFormation, List<String> tri) throws ApiException {
        ApiResponse<List<Composition>> localVarResp = lireListeCompositionsWithHttpInfo(uuidPeriode, composition, formation, typeFormation, objetFormation, typeObjetFormation, tri);
        return localVarResp.getData();
    }

    /**
     * liste des compositions par période avec filtres et tris
     * Recherche des compositions par période avec filtres et tris
     * @param uuidPeriode L&#39;uuid de la periode (required)
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
    public ApiResponse<List<Composition>> lireListeCompositionsWithHttpInfo(UUID uuidPeriode, String composition, String formation, String typeFormation, String objetFormation, String typeObjetFormation, List<String> tri) throws ApiException {
        okhttp3.Call localVarCall = lireListeCompositionsValidateBeforeCall(uuidPeriode, composition, formation, typeFormation, objetFormation, typeObjetFormation, tri, null);
        Type localVarReturnType = new TypeToken<List<Composition>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * liste des compositions par période avec filtres et tris (asynchronously)
     * Recherche des compositions par période avec filtres et tris
     * @param uuidPeriode L&#39;uuid de la periode (required)
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
    public okhttp3.Call lireListeCompositionsAsync(UUID uuidPeriode, String composition, String formation, String typeFormation, String objetFormation, String typeObjetFormation, List<String> tri, final ApiCallback<List<Composition>> _callback) throws ApiException {

        okhttp3.Call localVarCall = lireListeCompositionsValidateBeforeCall(uuidPeriode, composition, formation, typeFormation, objetFormation, typeObjetFormation, tri, _callback);
        Type localVarReturnType = new TypeToken<List<Composition>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for modifierComposition
     * @param compositionUuid L&#39;UUID de la composition (required)
     * @param compositionModification  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La composition </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Modification impossible </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> composition non trouvée </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call modifierCompositionCall(UUID compositionUuid, CompositionModification compositionModification, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = compositionModification;

        // create path and map variables
        String localVarPath = "/compositions/{compositionUuid}"
            .replaceAll("\\{" + "compositionUuid" + "\\}", localVarApiClient.escapeString(compositionUuid.toString()));

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
        return localVarApiClient.buildCall(basePath, localVarPath, "PATCH", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call modifierCompositionValidateBeforeCall(UUID compositionUuid, CompositionModification compositionModification, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'compositionUuid' is set
        if (compositionUuid == null) {
            throw new ApiException("Missing the required parameter 'compositionUuid' when calling modifierComposition(Async)");
        }
        
        // verify the required parameter 'compositionModification' is set
        if (compositionModification == null) {
            throw new ApiException("Missing the required parameter 'compositionModification' when calling modifierComposition(Async)");
        }
        

        okhttp3.Call localVarCall = modifierCompositionCall(compositionUuid, compositionModification, _callback);
        return localVarCall;

    }

    /**
     * mettre une composition
     * mettre à jour une composition
     * @param compositionUuid L&#39;UUID de la composition (required)
     * @param compositionModification  (required)
     * @return CompositionDetails
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La composition </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Modification impossible </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> composition non trouvée </td><td>  -  </td></tr>
     </table>
     */
    public CompositionDetails modifierComposition(UUID compositionUuid, CompositionModification compositionModification) throws ApiException {
        ApiResponse<CompositionDetails> localVarResp = modifierCompositionWithHttpInfo(compositionUuid, compositionModification);
        return localVarResp.getData();
    }

    /**
     * mettre une composition
     * mettre à jour une composition
     * @param compositionUuid L&#39;UUID de la composition (required)
     * @param compositionModification  (required)
     * @return ApiResponse&lt;CompositionDetails&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La composition </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Modification impossible </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> composition non trouvée </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<CompositionDetails> modifierCompositionWithHttpInfo(UUID compositionUuid, CompositionModification compositionModification) throws ApiException {
        okhttp3.Call localVarCall = modifierCompositionValidateBeforeCall(compositionUuid, compositionModification, null);
        Type localVarReturnType = new TypeToken<CompositionDetails>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * mettre une composition (asynchronously)
     * mettre à jour une composition
     * @param compositionUuid L&#39;UUID de la composition (required)
     * @param compositionModification  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La composition </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Modification impossible </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> composition non trouvée </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call modifierCompositionAsync(UUID compositionUuid, CompositionModification compositionModification, final ApiCallback<CompositionDetails> _callback) throws ApiException {

        okhttp3.Call localVarCall = modifierCompositionValidateBeforeCall(compositionUuid, compositionModification, _callback);
        Type localVarReturnType = new TypeToken<CompositionDetails>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for rechercherCompositions
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param pageable Les tris possibles sont :         * code         * libelleCourt         * active (optional)
     * @param codeOuLibelle Le code ou le libellé de la composition (optional)
     * @param active L&#39;état de la composition (active/inactive) (optional)
     * @param associe Si la composition est associé à des objets formations / formations (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La liste des compositions </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call rechercherCompositionsCall(String codeStructure, String codePeriode, CustomPageable pageable, String codeOuLibelle, Boolean active, Boolean associe, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/structure/{codeStructure}/compositions"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (codePeriode != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("codePeriode", codePeriode));
        }

        if (pageable != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("pageable", pageable));
        }

        if (codeOuLibelle != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("codeOuLibelle", codeOuLibelle));
        }

        if (active != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("active", active));
        }

        if (associe != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("associe", associe));
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
    private okhttp3.Call rechercherCompositionsValidateBeforeCall(String codeStructure, String codePeriode, CustomPageable pageable, String codeOuLibelle, Boolean active, Boolean associe, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling rechercherCompositions(Async)");
        }
        
        // verify the required parameter 'codePeriode' is set
        if (codePeriode == null) {
            throw new ApiException("Missing the required parameter 'codePeriode' when calling rechercherCompositions(Async)");
        }
        

        okhttp3.Call localVarCall = rechercherCompositionsCall(codeStructure, codePeriode, pageable, codeOuLibelle, active, associe, _callback);
        return localVarCall;

    }

    /**
     * Rechercher les compositions correspondants aux critères
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param pageable Les tris possibles sont :         * code         * libelleCourt         * active (optional)
     * @param codeOuLibelle Le code ou le libellé de la composition (optional)
     * @param active L&#39;état de la composition (active/inactive) (optional)
     * @param associe Si la composition est associé à des objets formations / formations (optional)
     * @return PagedComposition
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La liste des compositions </td><td>  -  </td></tr>
     </table>
     */
    public PagedComposition rechercherCompositions(String codeStructure, String codePeriode, CustomPageable pageable, String codeOuLibelle, Boolean active, Boolean associe) throws ApiException {
        ApiResponse<PagedComposition> localVarResp = rechercherCompositionsWithHttpInfo(codeStructure, codePeriode, pageable, codeOuLibelle, active, associe);
        return localVarResp.getData();
    }

    /**
     * Rechercher les compositions correspondants aux critères
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param pageable Les tris possibles sont :         * code         * libelleCourt         * active (optional)
     * @param codeOuLibelle Le code ou le libellé de la composition (optional)
     * @param active L&#39;état de la composition (active/inactive) (optional)
     * @param associe Si la composition est associé à des objets formations / formations (optional)
     * @return ApiResponse&lt;PagedComposition&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La liste des compositions </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<PagedComposition> rechercherCompositionsWithHttpInfo(String codeStructure, String codePeriode, CustomPageable pageable, String codeOuLibelle, Boolean active, Boolean associe) throws ApiException {
        okhttp3.Call localVarCall = rechercherCompositionsValidateBeforeCall(codeStructure, codePeriode, pageable, codeOuLibelle, active, associe, null);
        Type localVarReturnType = new TypeToken<PagedComposition>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Rechercher les compositions correspondants aux critères (asynchronously)
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param pageable Les tris possibles sont :         * code         * libelleCourt         * active (optional)
     * @param codeOuLibelle Le code ou le libellé de la composition (optional)
     * @param active L&#39;état de la composition (active/inactive) (optional)
     * @param associe Si la composition est associé à des objets formations / formations (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La liste des compositions </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call rechercherCompositionsAsync(String codeStructure, String codePeriode, CustomPageable pageable, String codeOuLibelle, Boolean active, Boolean associe, final ApiCallback<PagedComposition> _callback) throws ApiException {

        okhttp3.Call localVarCall = rechercherCompositionsValidateBeforeCall(codeStructure, codePeriode, pageable, codeOuLibelle, active, associe, _callback);
        Type localVarReturnType = new TypeToken<PagedComposition>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for recopierComposition
     * @param compositionRecopie  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> La composition et les groupes sont recopiés </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> La recopie des compositions n&#39;a pas pu être effectuée Un fichier CSV est retourné listant toutes les erreurs rencontrées </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Vous n&#39;avez pas accès au chemin demandé </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Ressource non trouvée </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call recopierCompositionCall(CompositionRecopie compositionRecopie, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = compositionRecopie;

        // create path and map variables
        String localVarPath = "/compositions/recopie";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "text/csv", "application/json"
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
    private okhttp3.Call recopierCompositionValidateBeforeCall(CompositionRecopie compositionRecopie, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'compositionRecopie' is set
        if (compositionRecopie == null) {
            throw new ApiException("Missing the required parameter 'compositionRecopie' when calling recopierComposition(Async)");
        }
        

        okhttp3.Call localVarCall = recopierCompositionCall(compositionRecopie, _callback);
        return localVarCall;

    }

    /**
     * Créer une composition avec les groupes
     * Recopie des compositions et de leurs paramétrages sur une période
     * @param compositionRecopie  (required)
     * @return File
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> La composition et les groupes sont recopiés </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> La recopie des compositions n&#39;a pas pu être effectuée Un fichier CSV est retourné listant toutes les erreurs rencontrées </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Vous n&#39;avez pas accès au chemin demandé </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Ressource non trouvée </td><td>  -  </td></tr>
     </table>
     */
    public File recopierComposition(CompositionRecopie compositionRecopie) throws ApiException {
        ApiResponse<File> localVarResp = recopierCompositionWithHttpInfo(compositionRecopie);
        return localVarResp.getData();
    }

    /**
     * Créer une composition avec les groupes
     * Recopie des compositions et de leurs paramétrages sur une période
     * @param compositionRecopie  (required)
     * @return ApiResponse&lt;File&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> La composition et les groupes sont recopiés </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> La recopie des compositions n&#39;a pas pu être effectuée Un fichier CSV est retourné listant toutes les erreurs rencontrées </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Vous n&#39;avez pas accès au chemin demandé </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Ressource non trouvée </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<File> recopierCompositionWithHttpInfo(CompositionRecopie compositionRecopie) throws ApiException {
        okhttp3.Call localVarCall = recopierCompositionValidateBeforeCall(compositionRecopie, null);
        Type localVarReturnType = new TypeToken<File>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Créer une composition avec les groupes (asynchronously)
     * Recopie des compositions et de leurs paramétrages sur une période
     * @param compositionRecopie  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> La composition et les groupes sont recopiés </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> La recopie des compositions n&#39;a pas pu être effectuée Un fichier CSV est retourné listant toutes les erreurs rencontrées </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Vous n&#39;avez pas accès au chemin demandé </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Ressource non trouvée </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call recopierCompositionAsync(CompositionRecopie compositionRecopie, final ApiCallback<File> _callback) throws ApiException {

        okhttp3.Call localVarCall = recopierCompositionValidateBeforeCall(compositionRecopie, _callback);
        Type localVarReturnType = new TypeToken<File>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for supprimerComposition
     * @param compositionUuid L&#39;UUID de la composition (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Suppression réalisé </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Suppression impossible, supprimer les associations préalablement </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> composition non trouvée </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call supprimerCompositionCall(UUID compositionUuid, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/compositions/{compositionUuid}"
            .replaceAll("\\{" + "compositionUuid" + "\\}", localVarApiClient.escapeString(compositionUuid.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            
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
        return localVarApiClient.buildCall(basePath, localVarPath, "DELETE", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call supprimerCompositionValidateBeforeCall(UUID compositionUuid, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'compositionUuid' is set
        if (compositionUuid == null) {
            throw new ApiException("Missing the required parameter 'compositionUuid' when calling supprimerComposition(Async)");
        }
        

        okhttp3.Call localVarCall = supprimerCompositionCall(compositionUuid, _callback);
        return localVarCall;

    }

    /**
     * Supprimer une composition
     * Supprimer une composition
     * @param compositionUuid L&#39;UUID de la composition (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Suppression réalisé </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Suppression impossible, supprimer les associations préalablement </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> composition non trouvée </td><td>  -  </td></tr>
     </table>
     */
    public void supprimerComposition(UUID compositionUuid) throws ApiException {
        supprimerCompositionWithHttpInfo(compositionUuid);
    }

    /**
     * Supprimer une composition
     * Supprimer une composition
     * @param compositionUuid L&#39;UUID de la composition (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Suppression réalisé </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Suppression impossible, supprimer les associations préalablement </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> composition non trouvée </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> supprimerCompositionWithHttpInfo(UUID compositionUuid) throws ApiException {
        okhttp3.Call localVarCall = supprimerCompositionValidateBeforeCall(compositionUuid, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * Supprimer une composition (asynchronously)
     * Supprimer une composition
     * @param compositionUuid L&#39;UUID de la composition (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Suppression réalisé </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Suppression impossible, supprimer les associations préalablement </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> composition non trouvée </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call supprimerCompositionAsync(UUID compositionUuid, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = supprimerCompositionValidateBeforeCall(compositionUuid, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
}
