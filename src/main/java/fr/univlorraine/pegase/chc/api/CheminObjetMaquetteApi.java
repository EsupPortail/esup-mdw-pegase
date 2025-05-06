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


import fr.univlorraine.pegase.chc.model.CustomPageable;
import fr.univlorraine.pegase.chc.model.PagedCheminObjetMaquette;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheminObjetMaquetteApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public CheminObjetMaquetteApi() {
        this(Configuration.getDefaultApiClient());
    }

    public CheminObjetMaquetteApi(ApiClient apiClient) {
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
     * Build call for rechercherFormations
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param pageable Les tris possibles sont :          * codeType          * formation.code          * formation.libelleCourt (optional)
     * @param codeOuLibelleFormation Le code ou le libellé de la formation (optional)
     * @param codeTypeFormation Le code type de la formation (optional)
     * @param etatOuvertureAuChc L&#39;état d&#39;ouverture au chc (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La liste des formations </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call rechercherFormationsCall(String codeStructure, String codePeriode, CustomPageable pageable, String codeOuLibelleFormation, String codeTypeFormation, List<String> etatOuvertureAuChc, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/structure/{codeStructure}/chemins-formation"
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

        if (codeOuLibelleFormation != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("codeOuLibelleFormation", codeOuLibelleFormation));
        }

        if (codeTypeFormation != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("codeTypeFormation", codeTypeFormation));
        }

        if (etatOuvertureAuChc != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "etatOuvertureAuChc", etatOuvertureAuChc));
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
    private okhttp3.Call rechercherFormationsValidateBeforeCall(String codeStructure, String codePeriode, CustomPageable pageable, String codeOuLibelleFormation, String codeTypeFormation, List<String> etatOuvertureAuChc, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling rechercherFormations(Async)");
        }
        
        // verify the required parameter 'codePeriode' is set
        if (codePeriode == null) {
            throw new ApiException("Missing the required parameter 'codePeriode' when calling rechercherFormations(Async)");
        }
        

        okhttp3.Call localVarCall = rechercherFormationsCall(codeStructure, codePeriode, pageable, codeOuLibelleFormation, codeTypeFormation, etatOuvertureAuChc, _callback);
        return localVarCall;

    }

    /**
     * Rechercher les chemins des formations correspondants aux critères
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param pageable Les tris possibles sont :          * codeType          * formation.code          * formation.libelleCourt (optional)
     * @param codeOuLibelleFormation Le code ou le libellé de la formation (optional)
     * @param codeTypeFormation Le code type de la formation (optional)
     * @param etatOuvertureAuChc L&#39;état d&#39;ouverture au chc (optional)
     * @return PagedCheminObjetMaquette
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La liste des formations </td><td>  -  </td></tr>
     </table>
     */
    public PagedCheminObjetMaquette rechercherFormations(String codeStructure, String codePeriode, CustomPageable pageable, String codeOuLibelleFormation, String codeTypeFormation, List<String> etatOuvertureAuChc) throws ApiException {
        ApiResponse<PagedCheminObjetMaquette> localVarResp = rechercherFormationsWithHttpInfo(codeStructure, codePeriode, pageable, codeOuLibelleFormation, codeTypeFormation, etatOuvertureAuChc);
        return localVarResp.getData();
    }

    /**
     * Rechercher les chemins des formations correspondants aux critères
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param pageable Les tris possibles sont :          * codeType          * formation.code          * formation.libelleCourt (optional)
     * @param codeOuLibelleFormation Le code ou le libellé de la formation (optional)
     * @param codeTypeFormation Le code type de la formation (optional)
     * @param etatOuvertureAuChc L&#39;état d&#39;ouverture au chc (optional)
     * @return ApiResponse&lt;PagedCheminObjetMaquette&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La liste des formations </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<PagedCheminObjetMaquette> rechercherFormationsWithHttpInfo(String codeStructure, String codePeriode, CustomPageable pageable, String codeOuLibelleFormation, String codeTypeFormation, List<String> etatOuvertureAuChc) throws ApiException {
        okhttp3.Call localVarCall = rechercherFormationsValidateBeforeCall(codeStructure, codePeriode, pageable, codeOuLibelleFormation, codeTypeFormation, etatOuvertureAuChc, null);
        Type localVarReturnType = new TypeToken<PagedCheminObjetMaquette>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Rechercher les chemins des formations correspondants aux critères (asynchronously)
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param pageable Les tris possibles sont :          * codeType          * formation.code          * formation.libelleCourt (optional)
     * @param codeOuLibelleFormation Le code ou le libellé de la formation (optional)
     * @param codeTypeFormation Le code type de la formation (optional)
     * @param etatOuvertureAuChc L&#39;état d&#39;ouverture au chc (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La liste des formations </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call rechercherFormationsAsync(String codeStructure, String codePeriode, CustomPageable pageable, String codeOuLibelleFormation, String codeTypeFormation, List<String> etatOuvertureAuChc, final ApiCallback<PagedCheminObjetMaquette> _callback) throws ApiException {

        okhttp3.Call localVarCall = rechercherFormationsValidateBeforeCall(codeStructure, codePeriode, pageable, codeOuLibelleFormation, codeTypeFormation, etatOuvertureAuChc, _callback);
        Type localVarReturnType = new TypeToken<PagedCheminObjetMaquette>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for rechercherObjetsFormation
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param pageable Les tris possibles sont :          * objetFormation.code          * objetFormation.libelleCourt          * estObligatoire          * codeType          * formation.code          * formation.libelleCourt          * codeChemin (optional)
     * @param etatsOuvertureAuChc L&#39;état d&#39;ouverture au chc (optional)
     * @param codeOuLiblelleFormation Le code ou le libellé de la formation (optional)
     * @param codeTypeFormation Le code type de la formation (optional)
     * @param codeOuLibelleObjetFormation Le code ou le libellé de l&#39;objet formation (optional)
     * @param codeTypeObjetFormation Le code du type de l&#39;objet formation (optional)
     * @param codeOuLibelleGroupement Le code ou le libellé du groupement (optional)
     * @param codeNatureGroupement Le code de La nature du groupement (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La liste des objets de formation </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call rechercherObjetsFormationCall(String codeStructure, String codePeriode, CustomPageable pageable, List<String> etatsOuvertureAuChc, String codeOuLiblelleFormation, String codeTypeFormation, String codeOuLibelleObjetFormation, String codeTypeObjetFormation, String codeOuLibelleGroupement, String codeNatureGroupement, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/structure/{codeStructure}/chemins-objet-formation"
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

        if (etatsOuvertureAuChc != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "etatsOuvertureAuChc", etatsOuvertureAuChc));
        }

        if (codeOuLiblelleFormation != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("codeOuLiblelleFormation", codeOuLiblelleFormation));
        }

        if (codeTypeFormation != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("codeTypeFormation", codeTypeFormation));
        }

        if (codeOuLibelleObjetFormation != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("codeOuLibelleObjetFormation", codeOuLibelleObjetFormation));
        }

        if (codeTypeObjetFormation != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("codeTypeObjetFormation", codeTypeObjetFormation));
        }

        if (codeOuLibelleGroupement != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("codeOuLibelleGroupement", codeOuLibelleGroupement));
        }

        if (codeNatureGroupement != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("codeNatureGroupement", codeNatureGroupement));
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
    private okhttp3.Call rechercherObjetsFormationValidateBeforeCall(String codeStructure, String codePeriode, CustomPageable pageable, List<String> etatsOuvertureAuChc, String codeOuLiblelleFormation, String codeTypeFormation, String codeOuLibelleObjetFormation, String codeTypeObjetFormation, String codeOuLibelleGroupement, String codeNatureGroupement, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling rechercherObjetsFormation(Async)");
        }
        
        // verify the required parameter 'codePeriode' is set
        if (codePeriode == null) {
            throw new ApiException("Missing the required parameter 'codePeriode' when calling rechercherObjetsFormation(Async)");
        }
        

        okhttp3.Call localVarCall = rechercherObjetsFormationCall(codeStructure, codePeriode, pageable, etatsOuvertureAuChc, codeOuLiblelleFormation, codeTypeFormation, codeOuLibelleObjetFormation, codeTypeObjetFormation, codeOuLibelleGroupement, codeNatureGroupement, _callback);
        return localVarCall;

    }

    /**
     * Rechercher les chemins des objets formations correspondants aux critères
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param pageable Les tris possibles sont :          * objetFormation.code          * objetFormation.libelleCourt          * estObligatoire          * codeType          * formation.code          * formation.libelleCourt          * codeChemin (optional)
     * @param etatsOuvertureAuChc L&#39;état d&#39;ouverture au chc (optional)
     * @param codeOuLiblelleFormation Le code ou le libellé de la formation (optional)
     * @param codeTypeFormation Le code type de la formation (optional)
     * @param codeOuLibelleObjetFormation Le code ou le libellé de l&#39;objet formation (optional)
     * @param codeTypeObjetFormation Le code du type de l&#39;objet formation (optional)
     * @param codeOuLibelleGroupement Le code ou le libellé du groupement (optional)
     * @param codeNatureGroupement Le code de La nature du groupement (optional)
     * @return PagedCheminObjetMaquette
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La liste des objets de formation </td><td>  -  </td></tr>
     </table>
     */
    public PagedCheminObjetMaquette rechercherObjetsFormation(String codeStructure, String codePeriode, CustomPageable pageable, List<String> etatsOuvertureAuChc, String codeOuLiblelleFormation, String codeTypeFormation, String codeOuLibelleObjetFormation, String codeTypeObjetFormation, String codeOuLibelleGroupement, String codeNatureGroupement) throws ApiException {
        ApiResponse<PagedCheminObjetMaquette> localVarResp = rechercherObjetsFormationWithHttpInfo(codeStructure, codePeriode, pageable, etatsOuvertureAuChc, codeOuLiblelleFormation, codeTypeFormation, codeOuLibelleObjetFormation, codeTypeObjetFormation, codeOuLibelleGroupement, codeNatureGroupement);
        return localVarResp.getData();
    }

    /**
     * Rechercher les chemins des objets formations correspondants aux critères
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param pageable Les tris possibles sont :          * objetFormation.code          * objetFormation.libelleCourt          * estObligatoire          * codeType          * formation.code          * formation.libelleCourt          * codeChemin (optional)
     * @param etatsOuvertureAuChc L&#39;état d&#39;ouverture au chc (optional)
     * @param codeOuLiblelleFormation Le code ou le libellé de la formation (optional)
     * @param codeTypeFormation Le code type de la formation (optional)
     * @param codeOuLibelleObjetFormation Le code ou le libellé de l&#39;objet formation (optional)
     * @param codeTypeObjetFormation Le code du type de l&#39;objet formation (optional)
     * @param codeOuLibelleGroupement Le code ou le libellé du groupement (optional)
     * @param codeNatureGroupement Le code de La nature du groupement (optional)
     * @return ApiResponse&lt;PagedCheminObjetMaquette&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La liste des objets de formation </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<PagedCheminObjetMaquette> rechercherObjetsFormationWithHttpInfo(String codeStructure, String codePeriode, CustomPageable pageable, List<String> etatsOuvertureAuChc, String codeOuLiblelleFormation, String codeTypeFormation, String codeOuLibelleObjetFormation, String codeTypeObjetFormation, String codeOuLibelleGroupement, String codeNatureGroupement) throws ApiException {
        okhttp3.Call localVarCall = rechercherObjetsFormationValidateBeforeCall(codeStructure, codePeriode, pageable, etatsOuvertureAuChc, codeOuLiblelleFormation, codeTypeFormation, codeOuLibelleObjetFormation, codeTypeObjetFormation, codeOuLibelleGroupement, codeNatureGroupement, null);
        Type localVarReturnType = new TypeToken<PagedCheminObjetMaquette>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Rechercher les chemins des objets formations correspondants aux critères (asynchronously)
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codePeriode Le code periode (required)
     * @param pageable Les tris possibles sont :          * objetFormation.code          * objetFormation.libelleCourt          * estObligatoire          * codeType          * formation.code          * formation.libelleCourt          * codeChemin (optional)
     * @param etatsOuvertureAuChc L&#39;état d&#39;ouverture au chc (optional)
     * @param codeOuLiblelleFormation Le code ou le libellé de la formation (optional)
     * @param codeTypeFormation Le code type de la formation (optional)
     * @param codeOuLibelleObjetFormation Le code ou le libellé de l&#39;objet formation (optional)
     * @param codeTypeObjetFormation Le code du type de l&#39;objet formation (optional)
     * @param codeOuLibelleGroupement Le code ou le libellé du groupement (optional)
     * @param codeNatureGroupement Le code de La nature du groupement (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> La liste des objets de formation </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call rechercherObjetsFormationAsync(String codeStructure, String codePeriode, CustomPageable pageable, List<String> etatsOuvertureAuChc, String codeOuLiblelleFormation, String codeTypeFormation, String codeOuLibelleObjetFormation, String codeTypeObjetFormation, String codeOuLibelleGroupement, String codeNatureGroupement, final ApiCallback<PagedCheminObjetMaquette> _callback) throws ApiException {

        okhttp3.Call localVarCall = rechercherObjetsFormationValidateBeforeCall(codeStructure, codePeriode, pageable, etatsOuvertureAuChc, codeOuLiblelleFormation, codeTypeFormation, codeOuLibelleObjetFormation, codeTypeObjetFormation, codeOuLibelleGroupement, codeNatureGroupement, _callback);
        Type localVarReturnType = new TypeToken<PagedCheminObjetMaquette>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
