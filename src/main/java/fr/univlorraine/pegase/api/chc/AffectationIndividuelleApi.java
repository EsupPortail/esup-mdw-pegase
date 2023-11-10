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


import fr.univlorraine.pegase.model.chc.AcquisUtilisableSurChoixPedagogiqueRequest;
import fr.univlorraine.pegase.model.chc.AffectationIndividuelleAcquisUtilisable;
import fr.univlorraine.pegase.model.chc.AffectationIndividuelleRechercherCursusApprenantResultatListeCursus;
import fr.univlorraine.pegase.model.chc.ChoisirAmenagementRequest;
import fr.univlorraine.pegase.model.chc.CustomPageable;
import java.util.Date;
import fr.univlorraine.pegase.model.chc.FaireChoixPedagogiqueAvecPropositionRequest;
import fr.univlorraine.pegase.model.chc.FaireChoixPedagogiqueRequest;
import fr.univlorraine.pegase.model.chc.LectureAffectationIndividuelle;
import fr.univlorraine.pegase.model.chc.PagedApprenants;
import java.util.UUID;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AffectationIndividuelleApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public AffectationIndividuelleApi() {
        this(Configuration.getDefaultApiClient());
    }

    public AffectationIndividuelleApi(ApiClient apiClient) {
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
     * Build call for affectationIndividuelleAjouterAmenagement
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param choisirAmenagementRequest Le choix pedagogique sur lequel on veut ajouter un amenagement + avec proposition (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call affectationIndividuelleAjouterAmenagementCall(String codeStructure, UUID cursusUuid, ChoisirAmenagementRequest choisirAmenagementRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = choisirAmenagementRequest;

        // create path and map variables
        String localVarPath = "/structure/{codeStructure}/affectation-individuelle/cursus/{cursusUuid}/brouillon/ajouter-amenagement"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "cursusUuid" + "\\}", localVarApiClient.escapeString(cursusUuid.toString()));

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
    private okhttp3.Call affectationIndividuelleAjouterAmenagementValidateBeforeCall(String codeStructure, UUID cursusUuid, ChoisirAmenagementRequest choisirAmenagementRequest, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling affectationIndividuelleAjouterAmenagement(Async)");
        }
        
        // verify the required parameter 'cursusUuid' is set
        if (cursusUuid == null) {
            throw new ApiException("Missing the required parameter 'cursusUuid' when calling affectationIndividuelleAjouterAmenagement(Async)");
        }
        
        // verify the required parameter 'choisirAmenagementRequest' is set
        if (choisirAmenagementRequest == null) {
            throw new ApiException("Missing the required parameter 'choisirAmenagementRequest' when calling affectationIndividuelleAjouterAmenagement(Async)");
        }
        

        okhttp3.Call localVarCall = affectationIndividuelleAjouterAmenagementCall(codeStructure, cursusUuid, choisirAmenagementRequest, _callback);
        return localVarCall;

    }

    /**
     * Choisir un amenagement sur ce choix pedagogique. [usage QA]
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param choisirAmenagementRequest Le choix pedagogique sur lequel on veut ajouter un amenagement + avec proposition (required)
     * @return LectureAffectationIndividuelle
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public LectureAffectationIndividuelle affectationIndividuelleAjouterAmenagement(String codeStructure, UUID cursusUuid, ChoisirAmenagementRequest choisirAmenagementRequest) throws ApiException {
        ApiResponse<LectureAffectationIndividuelle> localVarResp = affectationIndividuelleAjouterAmenagementWithHttpInfo(codeStructure, cursusUuid, choisirAmenagementRequest);
        return localVarResp.getData();
    }

    /**
     * Choisir un amenagement sur ce choix pedagogique. [usage QA]
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param choisirAmenagementRequest Le choix pedagogique sur lequel on veut ajouter un amenagement + avec proposition (required)
     * @return ApiResponse&lt;LectureAffectationIndividuelle&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<LectureAffectationIndividuelle> affectationIndividuelleAjouterAmenagementWithHttpInfo(String codeStructure, UUID cursusUuid, ChoisirAmenagementRequest choisirAmenagementRequest) throws ApiException {
        okhttp3.Call localVarCall = affectationIndividuelleAjouterAmenagementValidateBeforeCall(codeStructure, cursusUuid, choisirAmenagementRequest, null);
        Type localVarReturnType = new TypeToken<LectureAffectationIndividuelle>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Choisir un amenagement sur ce choix pedagogique. [usage QA] (asynchronously)
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param choisirAmenagementRequest Le choix pedagogique sur lequel on veut ajouter un amenagement + avec proposition (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call affectationIndividuelleAjouterAmenagementAsync(String codeStructure, UUID cursusUuid, ChoisirAmenagementRequest choisirAmenagementRequest, final ApiCallback<LectureAffectationIndividuelle> _callback) throws ApiException {

        okhttp3.Call localVarCall = affectationIndividuelleAjouterAmenagementValidateBeforeCall(codeStructure, cursusUuid, choisirAmenagementRequest, _callback);
        Type localVarReturnType = new TypeToken<LectureAffectationIndividuelle>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for affectationIndividuelleAnnulerChoixAffectation
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param faireChoixPedagogiqueRequest Le choix pedagogique sur lequel on veut annuler l&#39;affectation de cet apprenant + avec proposition (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call affectationIndividuelleAnnulerChoixAffectationCall(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueRequest faireChoixPedagogiqueRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = faireChoixPedagogiqueRequest;

        // create path and map variables
        String localVarPath = "/structure/{codeStructure}/affectation-individuelle/cursus/{cursusUuid}/brouillon/annuler-choix-affectation"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "cursusUuid" + "\\}", localVarApiClient.escapeString(cursusUuid.toString()));

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
    private okhttp3.Call affectationIndividuelleAnnulerChoixAffectationValidateBeforeCall(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueRequest faireChoixPedagogiqueRequest, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling affectationIndividuelleAnnulerChoixAffectation(Async)");
        }
        
        // verify the required parameter 'cursusUuid' is set
        if (cursusUuid == null) {
            throw new ApiException("Missing the required parameter 'cursusUuid' when calling affectationIndividuelleAnnulerChoixAffectation(Async)");
        }
        
        // verify the required parameter 'faireChoixPedagogiqueRequest' is set
        if (faireChoixPedagogiqueRequest == null) {
            throw new ApiException("Missing the required parameter 'faireChoixPedagogiqueRequest' when calling affectationIndividuelleAnnulerChoixAffectation(Async)");
        }
        

        okhttp3.Call localVarCall = affectationIndividuelleAnnulerChoixAffectationCall(codeStructure, cursusUuid, faireChoixPedagogiqueRequest, _callback);
        return localVarCall;

    }

    /**
     * Annuler Choix une affectation pour cet apprenant pour ce choix pedagogique
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param faireChoixPedagogiqueRequest Le choix pedagogique sur lequel on veut annuler l&#39;affectation de cet apprenant + avec proposition (required)
     * @return LectureAffectationIndividuelle
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public LectureAffectationIndividuelle affectationIndividuelleAnnulerChoixAffectation(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueRequest faireChoixPedagogiqueRequest) throws ApiException {
        ApiResponse<LectureAffectationIndividuelle> localVarResp = affectationIndividuelleAnnulerChoixAffectationWithHttpInfo(codeStructure, cursusUuid, faireChoixPedagogiqueRequest);
        return localVarResp.getData();
    }

    /**
     * Annuler Choix une affectation pour cet apprenant pour ce choix pedagogique
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param faireChoixPedagogiqueRequest Le choix pedagogique sur lequel on veut annuler l&#39;affectation de cet apprenant + avec proposition (required)
     * @return ApiResponse&lt;LectureAffectationIndividuelle&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<LectureAffectationIndividuelle> affectationIndividuelleAnnulerChoixAffectationWithHttpInfo(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueRequest faireChoixPedagogiqueRequest) throws ApiException {
        okhttp3.Call localVarCall = affectationIndividuelleAnnulerChoixAffectationValidateBeforeCall(codeStructure, cursusUuid, faireChoixPedagogiqueRequest, null);
        Type localVarReturnType = new TypeToken<LectureAffectationIndividuelle>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Annuler Choix une affectation pour cet apprenant pour ce choix pedagogique (asynchronously)
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param faireChoixPedagogiqueRequest Le choix pedagogique sur lequel on veut annuler l&#39;affectation de cet apprenant + avec proposition (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call affectationIndividuelleAnnulerChoixAffectationAsync(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueRequest faireChoixPedagogiqueRequest, final ApiCallback<LectureAffectationIndividuelle> _callback) throws ApiException {

        okhttp3.Call localVarCall = affectationIndividuelleAnnulerChoixAffectationValidateBeforeCall(codeStructure, cursusUuid, faireChoixPedagogiqueRequest, _callback);
        Type localVarReturnType = new TypeToken<LectureAffectationIndividuelle>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for affectationIndividuelleAnnulerChoixCapitalisation
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param faireChoixPedagogiqueRequest Le choix pedagogique sur lequel on veut annuler un acquis + avec proposition (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call affectationIndividuelleAnnulerChoixCapitalisationCall(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueRequest faireChoixPedagogiqueRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = faireChoixPedagogiqueRequest;

        // create path and map variables
        String localVarPath = "/structure/{codeStructure}/affectation-individuelle/cursus/{cursusUuid}/brouillon/annuler-choix-capitalisation"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "cursusUuid" + "\\}", localVarApiClient.escapeString(cursusUuid.toString()));

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
    private okhttp3.Call affectationIndividuelleAnnulerChoixCapitalisationValidateBeforeCall(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueRequest faireChoixPedagogiqueRequest, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling affectationIndividuelleAnnulerChoixCapitalisation(Async)");
        }
        
        // verify the required parameter 'cursusUuid' is set
        if (cursusUuid == null) {
            throw new ApiException("Missing the required parameter 'cursusUuid' when calling affectationIndividuelleAnnulerChoixCapitalisation(Async)");
        }
        
        // verify the required parameter 'faireChoixPedagogiqueRequest' is set
        if (faireChoixPedagogiqueRequest == null) {
            throw new ApiException("Missing the required parameter 'faireChoixPedagogiqueRequest' when calling affectationIndividuelleAnnulerChoixCapitalisation(Async)");
        }
        

        okhttp3.Call localVarCall = affectationIndividuelleAnnulerChoixCapitalisationCall(codeStructure, cursusUuid, faireChoixPedagogiqueRequest, _callback);
        return localVarCall;

    }

    /**
     * Choisir un Acquis capitalise pour cet apprenant pour ce choix pedagogique
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param faireChoixPedagogiqueRequest Le choix pedagogique sur lequel on veut annuler un acquis + avec proposition (required)
     * @return LectureAffectationIndividuelle
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public LectureAffectationIndividuelle affectationIndividuelleAnnulerChoixCapitalisation(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueRequest faireChoixPedagogiqueRequest) throws ApiException {
        ApiResponse<LectureAffectationIndividuelle> localVarResp = affectationIndividuelleAnnulerChoixCapitalisationWithHttpInfo(codeStructure, cursusUuid, faireChoixPedagogiqueRequest);
        return localVarResp.getData();
    }

    /**
     * Choisir un Acquis capitalise pour cet apprenant pour ce choix pedagogique
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param faireChoixPedagogiqueRequest Le choix pedagogique sur lequel on veut annuler un acquis + avec proposition (required)
     * @return ApiResponse&lt;LectureAffectationIndividuelle&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<LectureAffectationIndividuelle> affectationIndividuelleAnnulerChoixCapitalisationWithHttpInfo(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueRequest faireChoixPedagogiqueRequest) throws ApiException {
        okhttp3.Call localVarCall = affectationIndividuelleAnnulerChoixCapitalisationValidateBeforeCall(codeStructure, cursusUuid, faireChoixPedagogiqueRequest, null);
        Type localVarReturnType = new TypeToken<LectureAffectationIndividuelle>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Choisir un Acquis capitalise pour cet apprenant pour ce choix pedagogique (asynchronously)
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param faireChoixPedagogiqueRequest Le choix pedagogique sur lequel on veut annuler un acquis + avec proposition (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call affectationIndividuelleAnnulerChoixCapitalisationAsync(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueRequest faireChoixPedagogiqueRequest, final ApiCallback<LectureAffectationIndividuelle> _callback) throws ApiException {

        okhttp3.Call localVarCall = affectationIndividuelleAnnulerChoixCapitalisationValidateBeforeCall(codeStructure, cursusUuid, faireChoixPedagogiqueRequest, _callback);
        Type localVarReturnType = new TypeToken<LectureAffectationIndividuelle>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for affectationIndividuelleAnnulerChoixrRenonciation
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param faireChoixPedagogiqueRequest Le choix pedagogique sur lequel on veut annuler la renonciation de l&#39;apprenant + avec proposition (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call affectationIndividuelleAnnulerChoixrRenonciationCall(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueRequest faireChoixPedagogiqueRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = faireChoixPedagogiqueRequest;

        // create path and map variables
        String localVarPath = "/structure/{codeStructure}/affectation-individuelle/cursus/{cursusUuid}/brouillon/annuler-choix-renonciation"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "cursusUuid" + "\\}", localVarApiClient.escapeString(cursusUuid.toString()));

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
    private okhttp3.Call affectationIndividuelleAnnulerChoixrRenonciationValidateBeforeCall(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueRequest faireChoixPedagogiqueRequest, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling affectationIndividuelleAnnulerChoixrRenonciation(Async)");
        }
        
        // verify the required parameter 'cursusUuid' is set
        if (cursusUuid == null) {
            throw new ApiException("Missing the required parameter 'cursusUuid' when calling affectationIndividuelleAnnulerChoixrRenonciation(Async)");
        }
        
        // verify the required parameter 'faireChoixPedagogiqueRequest' is set
        if (faireChoixPedagogiqueRequest == null) {
            throw new ApiException("Missing the required parameter 'faireChoixPedagogiqueRequest' when calling affectationIndividuelleAnnulerChoixrRenonciation(Async)");
        }
        

        okhttp3.Call localVarCall = affectationIndividuelleAnnulerChoixrRenonciationCall(codeStructure, cursusUuid, faireChoixPedagogiqueRequest, _callback);
        return localVarCall;

    }

    /**
     * Annuler choix Renonciation pour cet apprenant pour ce choix pedagogique
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param faireChoixPedagogiqueRequest Le choix pedagogique sur lequel on veut annuler la renonciation de l&#39;apprenant + avec proposition (required)
     * @return LectureAffectationIndividuelle
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public LectureAffectationIndividuelle affectationIndividuelleAnnulerChoixrRenonciation(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueRequest faireChoixPedagogiqueRequest) throws ApiException {
        ApiResponse<LectureAffectationIndividuelle> localVarResp = affectationIndividuelleAnnulerChoixrRenonciationWithHttpInfo(codeStructure, cursusUuid, faireChoixPedagogiqueRequest);
        return localVarResp.getData();
    }

    /**
     * Annuler choix Renonciation pour cet apprenant pour ce choix pedagogique
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param faireChoixPedagogiqueRequest Le choix pedagogique sur lequel on veut annuler la renonciation de l&#39;apprenant + avec proposition (required)
     * @return ApiResponse&lt;LectureAffectationIndividuelle&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<LectureAffectationIndividuelle> affectationIndividuelleAnnulerChoixrRenonciationWithHttpInfo(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueRequest faireChoixPedagogiqueRequest) throws ApiException {
        okhttp3.Call localVarCall = affectationIndividuelleAnnulerChoixrRenonciationValidateBeforeCall(codeStructure, cursusUuid, faireChoixPedagogiqueRequest, null);
        Type localVarReturnType = new TypeToken<LectureAffectationIndividuelle>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Annuler choix Renonciation pour cet apprenant pour ce choix pedagogique (asynchronously)
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param faireChoixPedagogiqueRequest Le choix pedagogique sur lequel on veut annuler la renonciation de l&#39;apprenant + avec proposition (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call affectationIndividuelleAnnulerChoixrRenonciationAsync(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueRequest faireChoixPedagogiqueRequest, final ApiCallback<LectureAffectationIndividuelle> _callback) throws ApiException {

        okhttp3.Call localVarCall = affectationIndividuelleAnnulerChoixrRenonciationValidateBeforeCall(codeStructure, cursusUuid, faireChoixPedagogiqueRequest, _callback);
        Type localVarReturnType = new TypeToken<LectureAffectationIndividuelle>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for affectationIndividuelleChoisirAffectation
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param faireChoixPedagogiqueAvecPropositionRequest Le choix pedagogique sur lequel on veut affecter cet apprenant + avec proposition (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call affectationIndividuelleChoisirAffectationCall(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueAvecPropositionRequest faireChoixPedagogiqueAvecPropositionRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = faireChoixPedagogiqueAvecPropositionRequest;

        // create path and map variables
        String localVarPath = "/structure/{codeStructure}/affectation-individuelle/cursus/{cursusUuid}/brouillon/choisir-affectation"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "cursusUuid" + "\\}", localVarApiClient.escapeString(cursusUuid.toString()));

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
    private okhttp3.Call affectationIndividuelleChoisirAffectationValidateBeforeCall(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueAvecPropositionRequest faireChoixPedagogiqueAvecPropositionRequest, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling affectationIndividuelleChoisirAffectation(Async)");
        }
        
        // verify the required parameter 'cursusUuid' is set
        if (cursusUuid == null) {
            throw new ApiException("Missing the required parameter 'cursusUuid' when calling affectationIndividuelleChoisirAffectation(Async)");
        }
        
        // verify the required parameter 'faireChoixPedagogiqueAvecPropositionRequest' is set
        if (faireChoixPedagogiqueAvecPropositionRequest == null) {
            throw new ApiException("Missing the required parameter 'faireChoixPedagogiqueAvecPropositionRequest' when calling affectationIndividuelleChoisirAffectation(Async)");
        }
        

        okhttp3.Call localVarCall = affectationIndividuelleChoisirAffectationCall(codeStructure, cursusUuid, faireChoixPedagogiqueAvecPropositionRequest, _callback);
        return localVarCall;

    }

    /**
     * Choisir une affectation pour cet apprenant pour ce choix pedagogique [usage QA]
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param faireChoixPedagogiqueAvecPropositionRequest Le choix pedagogique sur lequel on veut affecter cet apprenant + avec proposition (required)
     * @return LectureAffectationIndividuelle
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public LectureAffectationIndividuelle affectationIndividuelleChoisirAffectation(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueAvecPropositionRequest faireChoixPedagogiqueAvecPropositionRequest) throws ApiException {
        ApiResponse<LectureAffectationIndividuelle> localVarResp = affectationIndividuelleChoisirAffectationWithHttpInfo(codeStructure, cursusUuid, faireChoixPedagogiqueAvecPropositionRequest);
        return localVarResp.getData();
    }

    /**
     * Choisir une affectation pour cet apprenant pour ce choix pedagogique [usage QA]
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param faireChoixPedagogiqueAvecPropositionRequest Le choix pedagogique sur lequel on veut affecter cet apprenant + avec proposition (required)
     * @return ApiResponse&lt;LectureAffectationIndividuelle&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<LectureAffectationIndividuelle> affectationIndividuelleChoisirAffectationWithHttpInfo(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueAvecPropositionRequest faireChoixPedagogiqueAvecPropositionRequest) throws ApiException {
        okhttp3.Call localVarCall = affectationIndividuelleChoisirAffectationValidateBeforeCall(codeStructure, cursusUuid, faireChoixPedagogiqueAvecPropositionRequest, null);
        Type localVarReturnType = new TypeToken<LectureAffectationIndividuelle>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Choisir une affectation pour cet apprenant pour ce choix pedagogique [usage QA] (asynchronously)
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param faireChoixPedagogiqueAvecPropositionRequest Le choix pedagogique sur lequel on veut affecter cet apprenant + avec proposition (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call affectationIndividuelleChoisirAffectationAsync(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueAvecPropositionRequest faireChoixPedagogiqueAvecPropositionRequest, final ApiCallback<LectureAffectationIndividuelle> _callback) throws ApiException {

        okhttp3.Call localVarCall = affectationIndividuelleChoisirAffectationValidateBeforeCall(codeStructure, cursusUuid, faireChoixPedagogiqueAvecPropositionRequest, _callback);
        Type localVarReturnType = new TypeToken<LectureAffectationIndividuelle>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for affectationIndividuelleChoisirCapitalisation
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param faireChoixPedagogiqueRequest Le choix pedagogique sur lequel on veut capitaliser un acquis + avec proposition (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call affectationIndividuelleChoisirCapitalisationCall(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueRequest faireChoixPedagogiqueRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = faireChoixPedagogiqueRequest;

        // create path and map variables
        String localVarPath = "/structure/{codeStructure}/affectation-individuelle/cursus/{cursusUuid}/brouillon/choisir-capitalisation"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "cursusUuid" + "\\}", localVarApiClient.escapeString(cursusUuid.toString()));

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
    private okhttp3.Call affectationIndividuelleChoisirCapitalisationValidateBeforeCall(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueRequest faireChoixPedagogiqueRequest, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling affectationIndividuelleChoisirCapitalisation(Async)");
        }
        
        // verify the required parameter 'cursusUuid' is set
        if (cursusUuid == null) {
            throw new ApiException("Missing the required parameter 'cursusUuid' when calling affectationIndividuelleChoisirCapitalisation(Async)");
        }
        
        // verify the required parameter 'faireChoixPedagogiqueRequest' is set
        if (faireChoixPedagogiqueRequest == null) {
            throw new ApiException("Missing the required parameter 'faireChoixPedagogiqueRequest' when calling affectationIndividuelleChoisirCapitalisation(Async)");
        }
        

        okhttp3.Call localVarCall = affectationIndividuelleChoisirCapitalisationCall(codeStructure, cursusUuid, faireChoixPedagogiqueRequest, _callback);
        return localVarCall;

    }

    /**
     * Choisir un Acquis capitalise pour cet apprenant pour ce choix pedagogique
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param faireChoixPedagogiqueRequest Le choix pedagogique sur lequel on veut capitaliser un acquis + avec proposition (required)
     * @return LectureAffectationIndividuelle
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public LectureAffectationIndividuelle affectationIndividuelleChoisirCapitalisation(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueRequest faireChoixPedagogiqueRequest) throws ApiException {
        ApiResponse<LectureAffectationIndividuelle> localVarResp = affectationIndividuelleChoisirCapitalisationWithHttpInfo(codeStructure, cursusUuid, faireChoixPedagogiqueRequest);
        return localVarResp.getData();
    }

    /**
     * Choisir un Acquis capitalise pour cet apprenant pour ce choix pedagogique
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param faireChoixPedagogiqueRequest Le choix pedagogique sur lequel on veut capitaliser un acquis + avec proposition (required)
     * @return ApiResponse&lt;LectureAffectationIndividuelle&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<LectureAffectationIndividuelle> affectationIndividuelleChoisirCapitalisationWithHttpInfo(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueRequest faireChoixPedagogiqueRequest) throws ApiException {
        okhttp3.Call localVarCall = affectationIndividuelleChoisirCapitalisationValidateBeforeCall(codeStructure, cursusUuid, faireChoixPedagogiqueRequest, null);
        Type localVarReturnType = new TypeToken<LectureAffectationIndividuelle>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Choisir un Acquis capitalise pour cet apprenant pour ce choix pedagogique (asynchronously)
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param faireChoixPedagogiqueRequest Le choix pedagogique sur lequel on veut capitaliser un acquis + avec proposition (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call affectationIndividuelleChoisirCapitalisationAsync(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueRequest faireChoixPedagogiqueRequest, final ApiCallback<LectureAffectationIndividuelle> _callback) throws ApiException {

        okhttp3.Call localVarCall = affectationIndividuelleChoisirCapitalisationValidateBeforeCall(codeStructure, cursusUuid, faireChoixPedagogiqueRequest, _callback);
        Type localVarReturnType = new TypeToken<LectureAffectationIndividuelle>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for affectationIndividuelleChoisirRenonciation
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param faireChoixPedagogiqueRequest Le choix pedagogique que l&#39;apprenant souhaite renoncer + avec proposition (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call affectationIndividuelleChoisirRenonciationCall(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueRequest faireChoixPedagogiqueRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = faireChoixPedagogiqueRequest;

        // create path and map variables
        String localVarPath = "/structure/{codeStructure}/affectation-individuelle/cursus/{cursusUuid}/brouillon/choisir-renonciation"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "cursusUuid" + "\\}", localVarApiClient.escapeString(cursusUuid.toString()));

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
    private okhttp3.Call affectationIndividuelleChoisirRenonciationValidateBeforeCall(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueRequest faireChoixPedagogiqueRequest, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling affectationIndividuelleChoisirRenonciation(Async)");
        }
        
        // verify the required parameter 'cursusUuid' is set
        if (cursusUuid == null) {
            throw new ApiException("Missing the required parameter 'cursusUuid' when calling affectationIndividuelleChoisirRenonciation(Async)");
        }
        
        // verify the required parameter 'faireChoixPedagogiqueRequest' is set
        if (faireChoixPedagogiqueRequest == null) {
            throw new ApiException("Missing the required parameter 'faireChoixPedagogiqueRequest' when calling affectationIndividuelleChoisirRenonciation(Async)");
        }
        

        okhttp3.Call localVarCall = affectationIndividuelleChoisirRenonciationCall(codeStructure, cursusUuid, faireChoixPedagogiqueRequest, _callback);
        return localVarCall;

    }

    /**
     * Choisir un Renonciation pour cet apprenant pour ce choix pedagogique
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param faireChoixPedagogiqueRequest Le choix pedagogique que l&#39;apprenant souhaite renoncer + avec proposition (required)
     * @return LectureAffectationIndividuelle
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public LectureAffectationIndividuelle affectationIndividuelleChoisirRenonciation(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueRequest faireChoixPedagogiqueRequest) throws ApiException {
        ApiResponse<LectureAffectationIndividuelle> localVarResp = affectationIndividuelleChoisirRenonciationWithHttpInfo(codeStructure, cursusUuid, faireChoixPedagogiqueRequest);
        return localVarResp.getData();
    }

    /**
     * Choisir un Renonciation pour cet apprenant pour ce choix pedagogique
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param faireChoixPedagogiqueRequest Le choix pedagogique que l&#39;apprenant souhaite renoncer + avec proposition (required)
     * @return ApiResponse&lt;LectureAffectationIndividuelle&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<LectureAffectationIndividuelle> affectationIndividuelleChoisirRenonciationWithHttpInfo(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueRequest faireChoixPedagogiqueRequest) throws ApiException {
        okhttp3.Call localVarCall = affectationIndividuelleChoisirRenonciationValidateBeforeCall(codeStructure, cursusUuid, faireChoixPedagogiqueRequest, null);
        Type localVarReturnType = new TypeToken<LectureAffectationIndividuelle>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Choisir un Renonciation pour cet apprenant pour ce choix pedagogique (asynchronously)
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param faireChoixPedagogiqueRequest Le choix pedagogique que l&#39;apprenant souhaite renoncer + avec proposition (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call affectationIndividuelleChoisirRenonciationAsync(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueRequest faireChoixPedagogiqueRequest, final ApiCallback<LectureAffectationIndividuelle> _callback) throws ApiException {

        okhttp3.Call localVarCall = affectationIndividuelleChoisirRenonciationValidateBeforeCall(codeStructure, cursusUuid, faireChoixPedagogiqueRequest, _callback);
        Type localVarReturnType = new TypeToken<LectureAffectationIndividuelle>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for affectationIndividuelleCorrigerAnomalie
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param faireChoixPedagogiqueRequest Le choix pedagogique sur lequel on veut effectuer une correction d&#39;anomalie + avec proposition (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call affectationIndividuelleCorrigerAnomalieCall(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueRequest faireChoixPedagogiqueRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = faireChoixPedagogiqueRequest;

        // create path and map variables
        String localVarPath = "/structure/{codeStructure}/affectation-individuelle/cursus/{cursusUuid}/brouillon/corriger-anomalie"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "cursusUuid" + "\\}", localVarApiClient.escapeString(cursusUuid.toString()));

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
    private okhttp3.Call affectationIndividuelleCorrigerAnomalieValidateBeforeCall(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueRequest faireChoixPedagogiqueRequest, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling affectationIndividuelleCorrigerAnomalie(Async)");
        }
        
        // verify the required parameter 'cursusUuid' is set
        if (cursusUuid == null) {
            throw new ApiException("Missing the required parameter 'cursusUuid' when calling affectationIndividuelleCorrigerAnomalie(Async)");
        }
        
        // verify the required parameter 'faireChoixPedagogiqueRequest' is set
        if (faireChoixPedagogiqueRequest == null) {
            throw new ApiException("Missing the required parameter 'faireChoixPedagogiqueRequest' when calling affectationIndividuelleCorrigerAnomalie(Async)");
        }
        

        okhttp3.Call localVarCall = affectationIndividuelleCorrigerAnomalieCall(codeStructure, cursusUuid, faireChoixPedagogiqueRequest, _callback);
        return localVarCall;

    }

    /**
     * Corriger une anomalie pour cet apprenant pour ce choix pedagogique
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param faireChoixPedagogiqueRequest Le choix pedagogique sur lequel on veut effectuer une correction d&#39;anomalie + avec proposition (required)
     * @return LectureAffectationIndividuelle
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public LectureAffectationIndividuelle affectationIndividuelleCorrigerAnomalie(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueRequest faireChoixPedagogiqueRequest) throws ApiException {
        ApiResponse<LectureAffectationIndividuelle> localVarResp = affectationIndividuelleCorrigerAnomalieWithHttpInfo(codeStructure, cursusUuid, faireChoixPedagogiqueRequest);
        return localVarResp.getData();
    }

    /**
     * Corriger une anomalie pour cet apprenant pour ce choix pedagogique
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param faireChoixPedagogiqueRequest Le choix pedagogique sur lequel on veut effectuer une correction d&#39;anomalie + avec proposition (required)
     * @return ApiResponse&lt;LectureAffectationIndividuelle&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<LectureAffectationIndividuelle> affectationIndividuelleCorrigerAnomalieWithHttpInfo(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueRequest faireChoixPedagogiqueRequest) throws ApiException {
        okhttp3.Call localVarCall = affectationIndividuelleCorrigerAnomalieValidateBeforeCall(codeStructure, cursusUuid, faireChoixPedagogiqueRequest, null);
        Type localVarReturnType = new TypeToken<LectureAffectationIndividuelle>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Corriger une anomalie pour cet apprenant pour ce choix pedagogique (asynchronously)
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param faireChoixPedagogiqueRequest Le choix pedagogique sur lequel on veut effectuer une correction d&#39;anomalie + avec proposition (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call affectationIndividuelleCorrigerAnomalieAsync(String codeStructure, UUID cursusUuid, FaireChoixPedagogiqueRequest faireChoixPedagogiqueRequest, final ApiCallback<LectureAffectationIndividuelle> _callback) throws ApiException {

        okhttp3.Call localVarCall = affectationIndividuelleCorrigerAnomalieValidateBeforeCall(codeStructure, cursusUuid, faireChoixPedagogiqueRequest, _callback);
        Type localVarReturnType = new TypeToken<LectureAffectationIndividuelle>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for affectationIndividuelleLectureCursus
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call affectationIndividuelleLectureCursusCall(String codeStructure, UUID cursusUuid, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/structure/{codeStructure}/affectation-individuelle/cursus/{cursusUuid}/brouillon"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "cursusUuid" + "\\}", localVarApiClient.escapeString(cursusUuid.toString()));

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
    private okhttp3.Call affectationIndividuelleLectureCursusValidateBeforeCall(String codeStructure, UUID cursusUuid, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling affectationIndividuelleLectureCursus(Async)");
        }
        
        // verify the required parameter 'cursusUuid' is set
        if (cursusUuid == null) {
            throw new ApiException("Missing the required parameter 'cursusUuid' when calling affectationIndividuelleLectureCursus(Async)");
        }
        

        okhttp3.Call localVarCall = affectationIndividuelleLectureCursusCall(codeStructure, cursusUuid, _callback);
        return localVarCall;

    }

    /**
     * Lecture d&#39;un Cursus pour l&#39;affectation individuelle [usage QA]
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @return LectureAffectationIndividuelle
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public LectureAffectationIndividuelle affectationIndividuelleLectureCursus(String codeStructure, UUID cursusUuid) throws ApiException {
        ApiResponse<LectureAffectationIndividuelle> localVarResp = affectationIndividuelleLectureCursusWithHttpInfo(codeStructure, cursusUuid);
        return localVarResp.getData();
    }

    /**
     * Lecture d&#39;un Cursus pour l&#39;affectation individuelle [usage QA]
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @return ApiResponse&lt;LectureAffectationIndividuelle&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<LectureAffectationIndividuelle> affectationIndividuelleLectureCursusWithHttpInfo(String codeStructure, UUID cursusUuid) throws ApiException {
        okhttp3.Call localVarCall = affectationIndividuelleLectureCursusValidateBeforeCall(codeStructure, cursusUuid, null);
        Type localVarReturnType = new TypeToken<LectureAffectationIndividuelle>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Lecture d&#39;un Cursus pour l&#39;affectation individuelle [usage QA] (asynchronously)
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call affectationIndividuelleLectureCursusAsync(String codeStructure, UUID cursusUuid, final ApiCallback<LectureAffectationIndividuelle> _callback) throws ApiException {

        okhttp3.Call localVarCall = affectationIndividuelleLectureCursusValidateBeforeCall(codeStructure, cursusUuid, _callback);
        Type localVarReturnType = new TypeToken<LectureAffectationIndividuelle>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for affectationIndividuelleLectureCursusVisualisation
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call affectationIndividuelleLectureCursusVisualisationCall(String codeStructure, UUID cursusUuid, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/structure/{codeStructure}/affectation-individuelle/cursus/{cursusUuid}"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "cursusUuid" + "\\}", localVarApiClient.escapeString(cursusUuid.toString()));

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
    private okhttp3.Call affectationIndividuelleLectureCursusVisualisationValidateBeforeCall(String codeStructure, UUID cursusUuid, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling affectationIndividuelleLectureCursusVisualisation(Async)");
        }
        
        // verify the required parameter 'cursusUuid' is set
        if (cursusUuid == null) {
            throw new ApiException("Missing the required parameter 'cursusUuid' when calling affectationIndividuelleLectureCursusVisualisation(Async)");
        }
        

        okhttp3.Call localVarCall = affectationIndividuelleLectureCursusVisualisationCall(codeStructure, cursusUuid, _callback);
        return localVarCall;

    }

    /**
     * Lecture d&#39;un Cursus pour l&#39;affectation individuelle en mode visualisation
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @return LectureAffectationIndividuelle
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public LectureAffectationIndividuelle affectationIndividuelleLectureCursusVisualisation(String codeStructure, UUID cursusUuid) throws ApiException {
        ApiResponse<LectureAffectationIndividuelle> localVarResp = affectationIndividuelleLectureCursusVisualisationWithHttpInfo(codeStructure, cursusUuid);
        return localVarResp.getData();
    }

    /**
     * Lecture d&#39;un Cursus pour l&#39;affectation individuelle en mode visualisation
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @return ApiResponse&lt;LectureAffectationIndividuelle&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<LectureAffectationIndividuelle> affectationIndividuelleLectureCursusVisualisationWithHttpInfo(String codeStructure, UUID cursusUuid) throws ApiException {
        okhttp3.Call localVarCall = affectationIndividuelleLectureCursusVisualisationValidateBeforeCall(codeStructure, cursusUuid, null);
        Type localVarReturnType = new TypeToken<LectureAffectationIndividuelle>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Lecture d&#39;un Cursus pour l&#39;affectation individuelle en mode visualisation (asynchronously)
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call affectationIndividuelleLectureCursusVisualisationAsync(String codeStructure, UUID cursusUuid, final ApiCallback<LectureAffectationIndividuelle> _callback) throws ApiException {

        okhttp3.Call localVarCall = affectationIndividuelleLectureCursusVisualisationValidateBeforeCall(codeStructure, cursusUuid, _callback);
        Type localVarReturnType = new TypeToken<LectureAffectationIndividuelle>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for affectationIndividuelleRechercherApprenant
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param pageable  (optional)
     * @param codeApprenant Le code de l&#39;apprenant (optional)
     * @param statutInscription le statut de l&#39;inscription (optional)
     * @param nomApprenant Le nom de l&#39;apprenant (optional)
     * @param prenomApprenant Le prenom de l&#39;apprenant (optional)
     * @param dateNaissanceApprenant La date de naissance de l&#39;apprenant (optional)
     * @param codeObjetMaquetteIa Le code de l&#39;objet maquette de l&#39;IA (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La liste des apprenants ayant au moins un cursus ouvert aux choix de cursus </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call affectationIndividuelleRechercherApprenantCall(String codeStructure, String codePeriode, CustomPageable pageable, String codeApprenant, String statutInscription, String nomApprenant, String prenomApprenant, Date dateNaissanceApprenant, String codeObjetMaquetteIa, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/structure/{codeStructure}/affectation-individuelle/recherche-apprenant"
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

        if (codeApprenant != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("codeApprenant", codeApprenant));
        }

        if (statutInscription != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("statutInscription", statutInscription));
        }

        if (nomApprenant != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("nomApprenant", nomApprenant));
        }

        if (prenomApprenant != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("prenomApprenant", prenomApprenant));
        }

        if (dateNaissanceApprenant != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("dateNaissanceApprenant", dateNaissanceApprenant));
        }

        if (codeObjetMaquetteIa != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("codeObjetMaquetteIa", codeObjetMaquetteIa));
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
    private okhttp3.Call affectationIndividuelleRechercherApprenantValidateBeforeCall(String codeStructure, String codePeriode, CustomPageable pageable, String codeApprenant, String statutInscription, String nomApprenant, String prenomApprenant, Date dateNaissanceApprenant, String codeObjetMaquetteIa, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling affectationIndividuelleRechercherApprenant(Async)");
        }
        
        // verify the required parameter 'codePeriode' is set
        if (codePeriode == null) {
            throw new ApiException("Missing the required parameter 'codePeriode' when calling affectationIndividuelleRechercherApprenant(Async)");
        }
        

        okhttp3.Call localVarCall = affectationIndividuelleRechercherApprenantCall(codeStructure, codePeriode, pageable, codeApprenant, statutInscription, nomApprenant, prenomApprenant, dateNaissanceApprenant, codeObjetMaquetteIa, _callback);
        return localVarCall;

    }

    /**
     * Rechercher les apprenants sur la période [usage QA]
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param pageable  (optional)
     * @param codeApprenant Le code de l&#39;apprenant (optional)
     * @param statutInscription le statut de l&#39;inscription (optional)
     * @param nomApprenant Le nom de l&#39;apprenant (optional)
     * @param prenomApprenant Le prenom de l&#39;apprenant (optional)
     * @param dateNaissanceApprenant La date de naissance de l&#39;apprenant (optional)
     * @param codeObjetMaquetteIa Le code de l&#39;objet maquette de l&#39;IA (optional)
     * @return PagedApprenants
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La liste des apprenants ayant au moins un cursus ouvert aux choix de cursus </td><td>  -  </td></tr>
     </table>
     */
    public PagedApprenants affectationIndividuelleRechercherApprenant(String codeStructure, String codePeriode, CustomPageable pageable, String codeApprenant, String statutInscription, String nomApprenant, String prenomApprenant, Date dateNaissanceApprenant, String codeObjetMaquetteIa) throws ApiException {
        ApiResponse<PagedApprenants> localVarResp = affectationIndividuelleRechercherApprenantWithHttpInfo(codeStructure, codePeriode, pageable, codeApprenant, statutInscription, nomApprenant, prenomApprenant, dateNaissanceApprenant, codeObjetMaquetteIa);
        return localVarResp.getData();
    }

    /**
     * Rechercher les apprenants sur la période [usage QA]
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param pageable  (optional)
     * @param codeApprenant Le code de l&#39;apprenant (optional)
     * @param statutInscription le statut de l&#39;inscription (optional)
     * @param nomApprenant Le nom de l&#39;apprenant (optional)
     * @param prenomApprenant Le prenom de l&#39;apprenant (optional)
     * @param dateNaissanceApprenant La date de naissance de l&#39;apprenant (optional)
     * @param codeObjetMaquetteIa Le code de l&#39;objet maquette de l&#39;IA (optional)
     * @return ApiResponse&lt;PagedApprenants&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La liste des apprenants ayant au moins un cursus ouvert aux choix de cursus </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<PagedApprenants> affectationIndividuelleRechercherApprenantWithHttpInfo(String codeStructure, String codePeriode, CustomPageable pageable, String codeApprenant, String statutInscription, String nomApprenant, String prenomApprenant, Date dateNaissanceApprenant, String codeObjetMaquetteIa) throws ApiException {
        okhttp3.Call localVarCall = affectationIndividuelleRechercherApprenantValidateBeforeCall(codeStructure, codePeriode, pageable, codeApprenant, statutInscription, nomApprenant, prenomApprenant, dateNaissanceApprenant, codeObjetMaquetteIa, null);
        Type localVarReturnType = new TypeToken<PagedApprenants>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Rechercher les apprenants sur la période [usage QA] (asynchronously)
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param pageable  (optional)
     * @param codeApprenant Le code de l&#39;apprenant (optional)
     * @param statutInscription le statut de l&#39;inscription (optional)
     * @param nomApprenant Le nom de l&#39;apprenant (optional)
     * @param prenomApprenant Le prenom de l&#39;apprenant (optional)
     * @param dateNaissanceApprenant La date de naissance de l&#39;apprenant (optional)
     * @param codeObjetMaquetteIa Le code de l&#39;objet maquette de l&#39;IA (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La liste des apprenants ayant au moins un cursus ouvert aux choix de cursus </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call affectationIndividuelleRechercherApprenantAsync(String codeStructure, String codePeriode, CustomPageable pageable, String codeApprenant, String statutInscription, String nomApprenant, String prenomApprenant, Date dateNaissanceApprenant, String codeObjetMaquetteIa, final ApiCallback<PagedApprenants> _callback) throws ApiException {

        okhttp3.Call localVarCall = affectationIndividuelleRechercherApprenantValidateBeforeCall(codeStructure, codePeriode, pageable, codeApprenant, statutInscription, nomApprenant, prenomApprenant, dateNaissanceApprenant, codeObjetMaquetteIa, _callback);
        Type localVarReturnType = new TypeToken<PagedApprenants>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for affectationIndividuelleRechercherLesCursusDeApprenant
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param apprenantUuid L&#39;UUID de l&#39;apprenant (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La liste des cursus de l&#39;apprenant sur la période </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call affectationIndividuelleRechercherLesCursusDeApprenantCall(String codeStructure, String codePeriode, UUID apprenantUuid, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/structure/{codeStructure}/affectation-individuelle/recherche-cursus/apprenant/{apprenantUuid}"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "apprenantUuid" + "\\}", localVarApiClient.escapeString(apprenantUuid.toString()));

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
    private okhttp3.Call affectationIndividuelleRechercherLesCursusDeApprenantValidateBeforeCall(String codeStructure, String codePeriode, UUID apprenantUuid, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling affectationIndividuelleRechercherLesCursusDeApprenant(Async)");
        }
        
        // verify the required parameter 'codePeriode' is set
        if (codePeriode == null) {
            throw new ApiException("Missing the required parameter 'codePeriode' when calling affectationIndividuelleRechercherLesCursusDeApprenant(Async)");
        }
        
        // verify the required parameter 'apprenantUuid' is set
        if (apprenantUuid == null) {
            throw new ApiException("Missing the required parameter 'apprenantUuid' when calling affectationIndividuelleRechercherLesCursusDeApprenant(Async)");
        }
        

        okhttp3.Call localVarCall = affectationIndividuelleRechercherLesCursusDeApprenantCall(codeStructure, codePeriode, apprenantUuid, _callback);
        return localVarCall;

    }

    /**
     * Rechercher les cursus de l&#39;apprenant sur la période [usage QA]
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param apprenantUuid L&#39;UUID de l&#39;apprenant (required)
     * @return AffectationIndividuelleRechercherCursusApprenantResultatListeCursus
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La liste des cursus de l&#39;apprenant sur la période </td><td>  -  </td></tr>
     </table>
     */
    public AffectationIndividuelleRechercherCursusApprenantResultatListeCursus affectationIndividuelleRechercherLesCursusDeApprenant(String codeStructure, String codePeriode, UUID apprenantUuid) throws ApiException {
        ApiResponse<AffectationIndividuelleRechercherCursusApprenantResultatListeCursus> localVarResp = affectationIndividuelleRechercherLesCursusDeApprenantWithHttpInfo(codeStructure, codePeriode, apprenantUuid);
        return localVarResp.getData();
    }

    /**
     * Rechercher les cursus de l&#39;apprenant sur la période [usage QA]
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param apprenantUuid L&#39;UUID de l&#39;apprenant (required)
     * @return ApiResponse&lt;AffectationIndividuelleRechercherCursusApprenantResultatListeCursus&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La liste des cursus de l&#39;apprenant sur la période </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<AffectationIndividuelleRechercherCursusApprenantResultatListeCursus> affectationIndividuelleRechercherLesCursusDeApprenantWithHttpInfo(String codeStructure, String codePeriode, UUID apprenantUuid) throws ApiException {
        okhttp3.Call localVarCall = affectationIndividuelleRechercherLesCursusDeApprenantValidateBeforeCall(codeStructure, codePeriode, apprenantUuid, null);
        Type localVarReturnType = new TypeToken<AffectationIndividuelleRechercherCursusApprenantResultatListeCursus>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Rechercher les cursus de l&#39;apprenant sur la période [usage QA] (asynchronously)
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param apprenantUuid L&#39;UUID de l&#39;apprenant (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La liste des cursus de l&#39;apprenant sur la période </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call affectationIndividuelleRechercherLesCursusDeApprenantAsync(String codeStructure, String codePeriode, UUID apprenantUuid, final ApiCallback<AffectationIndividuelleRechercherCursusApprenantResultatListeCursus> _callback) throws ApiException {

        okhttp3.Call localVarCall = affectationIndividuelleRechercherLesCursusDeApprenantValidateBeforeCall(codeStructure, codePeriode, apprenantUuid, _callback);
        Type localVarReturnType = new TypeToken<AffectationIndividuelleRechercherCursusApprenantResultatListeCursus>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for affectationIndividuelleRetirerAmenagement
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param choisirAmenagementRequest Le choix pedagogique sur lequel on veut retirer un amenagement + avec proposition (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call affectationIndividuelleRetirerAmenagementCall(String codeStructure, UUID cursusUuid, ChoisirAmenagementRequest choisirAmenagementRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = choisirAmenagementRequest;

        // create path and map variables
        String localVarPath = "/structure/{codeStructure}/affectation-individuelle/cursus/{cursusUuid}/brouillon/retirer-amenagement"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "cursusUuid" + "\\}", localVarApiClient.escapeString(cursusUuid.toString()));

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
    private okhttp3.Call affectationIndividuelleRetirerAmenagementValidateBeforeCall(String codeStructure, UUID cursusUuid, ChoisirAmenagementRequest choisirAmenagementRequest, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling affectationIndividuelleRetirerAmenagement(Async)");
        }
        
        // verify the required parameter 'cursusUuid' is set
        if (cursusUuid == null) {
            throw new ApiException("Missing the required parameter 'cursusUuid' when calling affectationIndividuelleRetirerAmenagement(Async)");
        }
        
        // verify the required parameter 'choisirAmenagementRequest' is set
        if (choisirAmenagementRequest == null) {
            throw new ApiException("Missing the required parameter 'choisirAmenagementRequest' when calling affectationIndividuelleRetirerAmenagement(Async)");
        }
        

        okhttp3.Call localVarCall = affectationIndividuelleRetirerAmenagementCall(codeStructure, cursusUuid, choisirAmenagementRequest, _callback);
        return localVarCall;

    }

    /**
     * Choisir un amenagement sur ce choix pedagogique.
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param choisirAmenagementRequest Le choix pedagogique sur lequel on veut retirer un amenagement + avec proposition (required)
     * @return LectureAffectationIndividuelle
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public LectureAffectationIndividuelle affectationIndividuelleRetirerAmenagement(String codeStructure, UUID cursusUuid, ChoisirAmenagementRequest choisirAmenagementRequest) throws ApiException {
        ApiResponse<LectureAffectationIndividuelle> localVarResp = affectationIndividuelleRetirerAmenagementWithHttpInfo(codeStructure, cursusUuid, choisirAmenagementRequest);
        return localVarResp.getData();
    }

    /**
     * Choisir un amenagement sur ce choix pedagogique.
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param choisirAmenagementRequest Le choix pedagogique sur lequel on veut retirer un amenagement + avec proposition (required)
     * @return ApiResponse&lt;LectureAffectationIndividuelle&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<LectureAffectationIndividuelle> affectationIndividuelleRetirerAmenagementWithHttpInfo(String codeStructure, UUID cursusUuid, ChoisirAmenagementRequest choisirAmenagementRequest) throws ApiException {
        okhttp3.Call localVarCall = affectationIndividuelleRetirerAmenagementValidateBeforeCall(codeStructure, cursusUuid, choisirAmenagementRequest, null);
        Type localVarReturnType = new TypeToken<LectureAffectationIndividuelle>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Choisir un amenagement sur ce choix pedagogique. (asynchronously)
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param choisirAmenagementRequest Le choix pedagogique sur lequel on veut retirer un amenagement + avec proposition (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La maquette, les choix pedagogiques actuels, les choix pédagogiques possibles, les info de capacité d&#39;accueil et d&#39;acquis capitalisés </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call affectationIndividuelleRetirerAmenagementAsync(String codeStructure, UUID cursusUuid, ChoisirAmenagementRequest choisirAmenagementRequest, final ApiCallback<LectureAffectationIndividuelle> _callback) throws ApiException {

        okhttp3.Call localVarCall = affectationIndividuelleRetirerAmenagementValidateBeforeCall(codeStructure, cursusUuid, choisirAmenagementRequest, _callback);
        Type localVarReturnType = new TypeToken<LectureAffectationIndividuelle>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for detailAcquisUtilisableSurCp
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param acquisUtilisableSurChoixPedagogiqueRequest Le choix pedagogique pour lequel on veut le detail de l&#39;acquis utilisable. (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Response detail de l&#39;acquis utilisable du cp </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call detailAcquisUtilisableSurCpCall(String codeStructure, UUID cursusUuid, AcquisUtilisableSurChoixPedagogiqueRequest acquisUtilisableSurChoixPedagogiqueRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = acquisUtilisableSurChoixPedagogiqueRequest;

        // create path and map variables
        String localVarPath = "/structure/{codeStructure}/affectation-individuelle/cursus/{cursusUuid}/acquis-utilisable-sur-cp"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "cursusUuid" + "\\}", localVarApiClient.escapeString(cursusUuid.toString()));

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
    private okhttp3.Call detailAcquisUtilisableSurCpValidateBeforeCall(String codeStructure, UUID cursusUuid, AcquisUtilisableSurChoixPedagogiqueRequest acquisUtilisableSurChoixPedagogiqueRequest, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling detailAcquisUtilisableSurCp(Async)");
        }
        
        // verify the required parameter 'cursusUuid' is set
        if (cursusUuid == null) {
            throw new ApiException("Missing the required parameter 'cursusUuid' when calling detailAcquisUtilisableSurCp(Async)");
        }
        
        // verify the required parameter 'acquisUtilisableSurChoixPedagogiqueRequest' is set
        if (acquisUtilisableSurChoixPedagogiqueRequest == null) {
            throw new ApiException("Missing the required parameter 'acquisUtilisableSurChoixPedagogiqueRequest' when calling detailAcquisUtilisableSurCp(Async)");
        }
        

        okhttp3.Call localVarCall = detailAcquisUtilisableSurCpCall(codeStructure, cursusUuid, acquisUtilisableSurChoixPedagogiqueRequest, _callback);
        return localVarCall;

    }

    /**
     * Detail d&#39;un acquis utilisable sur un cp
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param acquisUtilisableSurChoixPedagogiqueRequest Le choix pedagogique pour lequel on veut le detail de l&#39;acquis utilisable. (required)
     * @return AffectationIndividuelleAcquisUtilisable
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Response detail de l&#39;acquis utilisable du cp </td><td>  -  </td></tr>
     </table>
     */
    public AffectationIndividuelleAcquisUtilisable detailAcquisUtilisableSurCp(String codeStructure, UUID cursusUuid, AcquisUtilisableSurChoixPedagogiqueRequest acquisUtilisableSurChoixPedagogiqueRequest) throws ApiException {
        ApiResponse<AffectationIndividuelleAcquisUtilisable> localVarResp = detailAcquisUtilisableSurCpWithHttpInfo(codeStructure, cursusUuid, acquisUtilisableSurChoixPedagogiqueRequest);
        return localVarResp.getData();
    }

    /**
     * Detail d&#39;un acquis utilisable sur un cp
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param acquisUtilisableSurChoixPedagogiqueRequest Le choix pedagogique pour lequel on veut le detail de l&#39;acquis utilisable. (required)
     * @return ApiResponse&lt;AffectationIndividuelleAcquisUtilisable&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Response detail de l&#39;acquis utilisable du cp </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<AffectationIndividuelleAcquisUtilisable> detailAcquisUtilisableSurCpWithHttpInfo(String codeStructure, UUID cursusUuid, AcquisUtilisableSurChoixPedagogiqueRequest acquisUtilisableSurChoixPedagogiqueRequest) throws ApiException {
        okhttp3.Call localVarCall = detailAcquisUtilisableSurCpValidateBeforeCall(codeStructure, cursusUuid, acquisUtilisableSurChoixPedagogiqueRequest, null);
        Type localVarReturnType = new TypeToken<AffectationIndividuelleAcquisUtilisable>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Detail d&#39;un acquis utilisable sur un cp (asynchronously)
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param acquisUtilisableSurChoixPedagogiqueRequest Le choix pedagogique pour lequel on veut le detail de l&#39;acquis utilisable. (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Response detail de l&#39;acquis utilisable du cp </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call detailAcquisUtilisableSurCpAsync(String codeStructure, UUID cursusUuid, AcquisUtilisableSurChoixPedagogiqueRequest acquisUtilisableSurChoixPedagogiqueRequest, final ApiCallback<AffectationIndividuelleAcquisUtilisable> _callback) throws ApiException {

        okhttp3.Call localVarCall = detailAcquisUtilisableSurCpValidateBeforeCall(codeStructure, cursusUuid, acquisUtilisableSurChoixPedagogiqueRequest, _callback);
        Type localVarReturnType = new TypeToken<AffectationIndividuelleAcquisUtilisable>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for detailAcquisUtilisableSurCpBrouillon
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param acquisUtilisableSurChoixPedagogiqueRequest Le choix pedagogique pour lequel on veut le detail de l&#39;acquis utilisable. (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Response detail de l&#39;acquis utilisable sur le cp </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call detailAcquisUtilisableSurCpBrouillonCall(String codeStructure, UUID cursusUuid, AcquisUtilisableSurChoixPedagogiqueRequest acquisUtilisableSurChoixPedagogiqueRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = acquisUtilisableSurChoixPedagogiqueRequest;

        // create path and map variables
        String localVarPath = "/structure/{codeStructure}/affectation-individuelle/cursus/{cursusUuid}/brouillon/acquis-utilisable-sur-cp"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "cursusUuid" + "\\}", localVarApiClient.escapeString(cursusUuid.toString()));

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
    private okhttp3.Call detailAcquisUtilisableSurCpBrouillonValidateBeforeCall(String codeStructure, UUID cursusUuid, AcquisUtilisableSurChoixPedagogiqueRequest acquisUtilisableSurChoixPedagogiqueRequest, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling detailAcquisUtilisableSurCpBrouillon(Async)");
        }
        
        // verify the required parameter 'cursusUuid' is set
        if (cursusUuid == null) {
            throw new ApiException("Missing the required parameter 'cursusUuid' when calling detailAcquisUtilisableSurCpBrouillon(Async)");
        }
        
        // verify the required parameter 'acquisUtilisableSurChoixPedagogiqueRequest' is set
        if (acquisUtilisableSurChoixPedagogiqueRequest == null) {
            throw new ApiException("Missing the required parameter 'acquisUtilisableSurChoixPedagogiqueRequest' when calling detailAcquisUtilisableSurCpBrouillon(Async)");
        }
        

        okhttp3.Call localVarCall = detailAcquisUtilisableSurCpBrouillonCall(codeStructure, cursusUuid, acquisUtilisableSurChoixPedagogiqueRequest, _callback);
        return localVarCall;

    }

    /**
     * Detail de l&#39;acquis utilisable sur un cp sur le brouillon du cursus
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param acquisUtilisableSurChoixPedagogiqueRequest Le choix pedagogique pour lequel on veut le detail de l&#39;acquis utilisable. (required)
     * @return AffectationIndividuelleAcquisUtilisable
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Response detail de l&#39;acquis utilisable sur le cp </td><td>  -  </td></tr>
     </table>
     */
    public AffectationIndividuelleAcquisUtilisable detailAcquisUtilisableSurCpBrouillon(String codeStructure, UUID cursusUuid, AcquisUtilisableSurChoixPedagogiqueRequest acquisUtilisableSurChoixPedagogiqueRequest) throws ApiException {
        ApiResponse<AffectationIndividuelleAcquisUtilisable> localVarResp = detailAcquisUtilisableSurCpBrouillonWithHttpInfo(codeStructure, cursusUuid, acquisUtilisableSurChoixPedagogiqueRequest);
        return localVarResp.getData();
    }

    /**
     * Detail de l&#39;acquis utilisable sur un cp sur le brouillon du cursus
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param acquisUtilisableSurChoixPedagogiqueRequest Le choix pedagogique pour lequel on veut le detail de l&#39;acquis utilisable. (required)
     * @return ApiResponse&lt;AffectationIndividuelleAcquisUtilisable&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Response detail de l&#39;acquis utilisable sur le cp </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<AffectationIndividuelleAcquisUtilisable> detailAcquisUtilisableSurCpBrouillonWithHttpInfo(String codeStructure, UUID cursusUuid, AcquisUtilisableSurChoixPedagogiqueRequest acquisUtilisableSurChoixPedagogiqueRequest) throws ApiException {
        okhttp3.Call localVarCall = detailAcquisUtilisableSurCpBrouillonValidateBeforeCall(codeStructure, cursusUuid, acquisUtilisableSurChoixPedagogiqueRequest, null);
        Type localVarReturnType = new TypeToken<AffectationIndividuelleAcquisUtilisable>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Detail de l&#39;acquis utilisable sur un cp sur le brouillon du cursus (asynchronously)
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param acquisUtilisableSurChoixPedagogiqueRequest Le choix pedagogique pour lequel on veut le detail de l&#39;acquis utilisable. (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Response detail de l&#39;acquis utilisable sur le cp </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call detailAcquisUtilisableSurCpBrouillonAsync(String codeStructure, UUID cursusUuid, AcquisUtilisableSurChoixPedagogiqueRequest acquisUtilisableSurChoixPedagogiqueRequest, final ApiCallback<AffectationIndividuelleAcquisUtilisable> _callback) throws ApiException {

        okhttp3.Call localVarCall = detailAcquisUtilisableSurCpBrouillonValidateBeforeCall(codeStructure, cursusUuid, acquisUtilisableSurChoixPedagogiqueRequest, _callback);
        Type localVarReturnType = new TypeToken<AffectationIndividuelleAcquisUtilisable>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
