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


import fr.univlorraine.pegase.model.chc.BrouillonMasseResponse;
import fr.univlorraine.pegase.model.chc.SupprimerBrouillonRequest;
import java.util.UUID;
import fr.univlorraine.pegase.model.chc.ValiderBrouillonRequest;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CursusApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

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
     * Build call for cursusCreerBrouillon
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param avecPropositions Si les propositions pédagogiques doivent être faites à la création du brouillon. C&#39;est à dire choix affectation ou choix capitalisé lorsque possible sur les OF obligatoires et hors plage de choix (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Le brouillon a été créé </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call cursusCreerBrouillonCall(String codeStructure, UUID cursusUuid, Boolean avecPropositions, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/structure/{codeStructure}/cursus/{cursusUuid}/creer-brouillon"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "cursusUuid" + "\\}", localVarApiClient.escapeString(cursusUuid.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (avecPropositions != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("avecPropositions", avecPropositions));
        }

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
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call cursusCreerBrouillonValidateBeforeCall(String codeStructure, UUID cursusUuid, Boolean avecPropositions, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling cursusCreerBrouillon(Async)");
        }
        
        // verify the required parameter 'cursusUuid' is set
        if (cursusUuid == null) {
            throw new ApiException("Missing the required parameter 'cursusUuid' when calling cursusCreerBrouillon(Async)");
        }
        
        // verify the required parameter 'avecPropositions' is set
        if (avecPropositions == null) {
            throw new ApiException("Missing the required parameter 'avecPropositions' when calling cursusCreerBrouillon(Async)");
        }
        

        okhttp3.Call localVarCall = cursusCreerBrouillonCall(codeStructure, cursusUuid, avecPropositions, _callback);
        return localVarCall;

    }

    /**
     * cursus [usage QA]
     * creation d&#39;un brouillon dans cursus
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param avecPropositions Si les propositions pédagogiques doivent être faites à la création du brouillon. C&#39;est à dire choix affectation ou choix capitalisé lorsque possible sur les OF obligatoires et hors plage de choix (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Le brouillon a été créé </td><td>  -  </td></tr>
     </table>
     */
    public void cursusCreerBrouillon(String codeStructure, UUID cursusUuid, Boolean avecPropositions) throws ApiException {
        cursusCreerBrouillonWithHttpInfo(codeStructure, cursusUuid, avecPropositions);
    }

    /**
     * cursus [usage QA]
     * creation d&#39;un brouillon dans cursus
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param avecPropositions Si les propositions pédagogiques doivent être faites à la création du brouillon. C&#39;est à dire choix affectation ou choix capitalisé lorsque possible sur les OF obligatoires et hors plage de choix (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Le brouillon a été créé </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> cursusCreerBrouillonWithHttpInfo(String codeStructure, UUID cursusUuid, Boolean avecPropositions) throws ApiException {
        okhttp3.Call localVarCall = cursusCreerBrouillonValidateBeforeCall(codeStructure, cursusUuid, avecPropositions, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * cursus [usage QA] (asynchronously)
     * creation d&#39;un brouillon dans cursus
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param avecPropositions Si les propositions pédagogiques doivent être faites à la création du brouillon. C&#39;est à dire choix affectation ou choix capitalisé lorsque possible sur les OF obligatoires et hors plage de choix (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Le brouillon a été créé </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call cursusCreerBrouillonAsync(String codeStructure, UUID cursusUuid, Boolean avecPropositions, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = cursusCreerBrouillonValidateBeforeCall(codeStructure, cursusUuid, avecPropositions, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for cursusSupprimerBrouillon
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Brouillon supprimé </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call cursusSupprimerBrouillonCall(String codeStructure, UUID cursusUuid, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/structure/{codeStructure}/cursus/{cursusUuid}/supprimer-brouillon"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "cursusUuid" + "\\}", localVarApiClient.escapeString(cursusUuid.toString()));

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
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call cursusSupprimerBrouillonValidateBeforeCall(String codeStructure, UUID cursusUuid, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling cursusSupprimerBrouillon(Async)");
        }
        
        // verify the required parameter 'cursusUuid' is set
        if (cursusUuid == null) {
            throw new ApiException("Missing the required parameter 'cursusUuid' when calling cursusSupprimerBrouillon(Async)");
        }
        

        okhttp3.Call localVarCall = cursusSupprimerBrouillonCall(codeStructure, cursusUuid, _callback);
        return localVarCall;

    }

    /**
     * cursus
     * suppression d&#39;un brouillon dans cursus
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Brouillon supprimé </td><td>  -  </td></tr>
     </table>
     */
    public void cursusSupprimerBrouillon(String codeStructure, UUID cursusUuid) throws ApiException {
        cursusSupprimerBrouillonWithHttpInfo(codeStructure, cursusUuid);
    }

    /**
     * cursus
     * suppression d&#39;un brouillon dans cursus
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Brouillon supprimé </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> cursusSupprimerBrouillonWithHttpInfo(String codeStructure, UUID cursusUuid) throws ApiException {
        okhttp3.Call localVarCall = cursusSupprimerBrouillonValidateBeforeCall(codeStructure, cursusUuid, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * cursus (asynchronously)
     * suppression d&#39;un brouillon dans cursus
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Brouillon supprimé </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call cursusSupprimerBrouillonAsync(String codeStructure, UUID cursusUuid, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = cursusSupprimerBrouillonValidateBeforeCall(codeStructure, cursusUuid, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for cursusSupprimerBrouillonEnMasse
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param supprimerBrouillonRequest liste des cursus uuid qui ont un brouillon à supprimer. (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Une liste composée des cursusId pour lesquels il y a eu une erreur + ne liste composée des cursusId pour lesquels l&#39;opération s&#39;est bien déroulée </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call cursusSupprimerBrouillonEnMasseCall(String codeStructure, SupprimerBrouillonRequest supprimerBrouillonRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = supprimerBrouillonRequest;

        // create path and map variables
        String localVarPath = "/structure/{codeStructure}/supprimer-brouillon-masse"
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
    private okhttp3.Call cursusSupprimerBrouillonEnMasseValidateBeforeCall(String codeStructure, SupprimerBrouillonRequest supprimerBrouillonRequest, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling cursusSupprimerBrouillonEnMasse(Async)");
        }
        
        // verify the required parameter 'supprimerBrouillonRequest' is set
        if (supprimerBrouillonRequest == null) {
            throw new ApiException("Missing the required parameter 'supprimerBrouillonRequest' when calling cursusSupprimerBrouillonEnMasse(Async)");
        }
        

        okhttp3.Call localVarCall = cursusSupprimerBrouillonEnMasseCall(codeStructure, supprimerBrouillonRequest, _callback);
        return localVarCall;

    }

    /**
     * cursus
     * suppression de plusieurs brouillons
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param supprimerBrouillonRequest liste des cursus uuid qui ont un brouillon à supprimer. (required)
     * @return BrouillonMasseResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Une liste composée des cursusId pour lesquels il y a eu une erreur + ne liste composée des cursusId pour lesquels l&#39;opération s&#39;est bien déroulée </td><td>  -  </td></tr>
     </table>
     */
    public BrouillonMasseResponse cursusSupprimerBrouillonEnMasse(String codeStructure, SupprimerBrouillonRequest supprimerBrouillonRequest) throws ApiException {
        ApiResponse<BrouillonMasseResponse> localVarResp = cursusSupprimerBrouillonEnMasseWithHttpInfo(codeStructure, supprimerBrouillonRequest);
        return localVarResp.getData();
    }

    /**
     * cursus
     * suppression de plusieurs brouillons
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param supprimerBrouillonRequest liste des cursus uuid qui ont un brouillon à supprimer. (required)
     * @return ApiResponse&lt;BrouillonMasseResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Une liste composée des cursusId pour lesquels il y a eu une erreur + ne liste composée des cursusId pour lesquels l&#39;opération s&#39;est bien déroulée </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<BrouillonMasseResponse> cursusSupprimerBrouillonEnMasseWithHttpInfo(String codeStructure, SupprimerBrouillonRequest supprimerBrouillonRequest) throws ApiException {
        okhttp3.Call localVarCall = cursusSupprimerBrouillonEnMasseValidateBeforeCall(codeStructure, supprimerBrouillonRequest, null);
        Type localVarReturnType = new TypeToken<BrouillonMasseResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * cursus (asynchronously)
     * suppression de plusieurs brouillons
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param supprimerBrouillonRequest liste des cursus uuid qui ont un brouillon à supprimer. (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Une liste composée des cursusId pour lesquels il y a eu une erreur + ne liste composée des cursusId pour lesquels l&#39;opération s&#39;est bien déroulée </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call cursusSupprimerBrouillonEnMasseAsync(String codeStructure, SupprimerBrouillonRequest supprimerBrouillonRequest, final ApiCallback<BrouillonMasseResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = cursusSupprimerBrouillonEnMasseValidateBeforeCall(codeStructure, supprimerBrouillonRequest, _callback);
        Type localVarReturnType = new TypeToken<BrouillonMasseResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for cursusValiderBrouillon
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Brouillon validé </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call cursusValiderBrouillonCall(String codeStructure, UUID cursusUuid, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/structure/{codeStructure}/cursus/{cursusUuid}/valider-brouillon"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "cursusUuid" + "\\}", localVarApiClient.escapeString(cursusUuid.toString()));

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
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call cursusValiderBrouillonValidateBeforeCall(String codeStructure, UUID cursusUuid, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling cursusValiderBrouillon(Async)");
        }
        
        // verify the required parameter 'cursusUuid' is set
        if (cursusUuid == null) {
            throw new ApiException("Missing the required parameter 'cursusUuid' when calling cursusValiderBrouillon(Async)");
        }
        

        okhttp3.Call localVarCall = cursusValiderBrouillonCall(codeStructure, cursusUuid, _callback);
        return localVarCall;

    }

    /**
     * cursus [usage QA]
     * validation d&#39;un brouillon dans cursus
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Brouillon validé </td><td>  -  </td></tr>
     </table>
     */
    public void cursusValiderBrouillon(String codeStructure, UUID cursusUuid) throws ApiException {
        cursusValiderBrouillonWithHttpInfo(codeStructure, cursusUuid);
    }

    /**
     * cursus [usage QA]
     * validation d&#39;un brouillon dans cursus
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Brouillon validé </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> cursusValiderBrouillonWithHttpInfo(String codeStructure, UUID cursusUuid) throws ApiException {
        okhttp3.Call localVarCall = cursusValiderBrouillonValidateBeforeCall(codeStructure, cursusUuid, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * cursus [usage QA] (asynchronously)
     * validation d&#39;un brouillon dans cursus
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param cursusUuid L&#39;identifiant technique du Cursus (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Brouillon validé </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call cursusValiderBrouillonAsync(String codeStructure, UUID cursusUuid, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = cursusValiderBrouillonValidateBeforeCall(codeStructure, cursusUuid, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for cursusValiderBrouillonEnMasse
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param validerBrouillonRequest liste des cursus uuid qui ont un brouillon à valider. (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Une liste composée des cursusId pour lesquels il y a eu une erreur + ne liste composée des cursusId pour lesquels l&#39;opération s&#39;est bien déroulée </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call cursusValiderBrouillonEnMasseCall(String codeStructure, ValiderBrouillonRequest validerBrouillonRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = validerBrouillonRequest;

        // create path and map variables
        String localVarPath = "/structure/{codeStructure}/valider-brouillon-masse"
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
    private okhttp3.Call cursusValiderBrouillonEnMasseValidateBeforeCall(String codeStructure, ValiderBrouillonRequest validerBrouillonRequest, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling cursusValiderBrouillonEnMasse(Async)");
        }
        
        // verify the required parameter 'validerBrouillonRequest' is set
        if (validerBrouillonRequest == null) {
            throw new ApiException("Missing the required parameter 'validerBrouillonRequest' when calling cursusValiderBrouillonEnMasse(Async)");
        }
        

        okhttp3.Call localVarCall = cursusValiderBrouillonEnMasseCall(codeStructure, validerBrouillonRequest, _callback);
        return localVarCall;

    }

    /**
     * cursus
     * validation de plusieurs brouillons
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param validerBrouillonRequest liste des cursus uuid qui ont un brouillon à valider. (required)
     * @return BrouillonMasseResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Une liste composée des cursusId pour lesquels il y a eu une erreur + ne liste composée des cursusId pour lesquels l&#39;opération s&#39;est bien déroulée </td><td>  -  </td></tr>
     </table>
     */
    public BrouillonMasseResponse cursusValiderBrouillonEnMasse(String codeStructure, ValiderBrouillonRequest validerBrouillonRequest) throws ApiException {
        ApiResponse<BrouillonMasseResponse> localVarResp = cursusValiderBrouillonEnMasseWithHttpInfo(codeStructure, validerBrouillonRequest);
        return localVarResp.getData();
    }

    /**
     * cursus
     * validation de plusieurs brouillons
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param validerBrouillonRequest liste des cursus uuid qui ont un brouillon à valider. (required)
     * @return ApiResponse&lt;BrouillonMasseResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Une liste composée des cursusId pour lesquels il y a eu une erreur + ne liste composée des cursusId pour lesquels l&#39;opération s&#39;est bien déroulée </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<BrouillonMasseResponse> cursusValiderBrouillonEnMasseWithHttpInfo(String codeStructure, ValiderBrouillonRequest validerBrouillonRequest) throws ApiException {
        okhttp3.Call localVarCall = cursusValiderBrouillonEnMasseValidateBeforeCall(codeStructure, validerBrouillonRequest, null);
        Type localVarReturnType = new TypeToken<BrouillonMasseResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * cursus (asynchronously)
     * validation de plusieurs brouillons
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param validerBrouillonRequest liste des cursus uuid qui ont un brouillon à valider. (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Une liste composée des cursusId pour lesquels il y a eu une erreur + ne liste composée des cursusId pour lesquels l&#39;opération s&#39;est bien déroulée </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call cursusValiderBrouillonEnMasseAsync(String codeStructure, ValiderBrouillonRequest validerBrouillonRequest, final ApiCallback<BrouillonMasseResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = cursusValiderBrouillonEnMasseValidateBeforeCall(codeStructure, validerBrouillonRequest, _callback);
        Type localVarReturnType = new TypeToken<BrouillonMasseResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
