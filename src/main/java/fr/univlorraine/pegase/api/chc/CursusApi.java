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
 * CHC v5 - Choix du cursus
 * Liste l'ensemble des services et des opérations disponibles dans le module choix des cursus v5  ### Introduction  l’API liste l'ensemble des services et des opérations disponibles dans le module CHC (Choix du Cursus). Le module CHC permet d’affecter les apprenants aux Objets maquettes qu’ils doivent suivre pour une période de mise en œuvre donnée pendant leur cursus, de leur saisir des aménagements avec différentes prises en compte et de les affecter à des groupes.  ### Authentification/autorisation obligatoire  Pour tout appel à une opération vous devez être authentifié/authorisé à l’aide d’un token jwt. Pour cela, chaque requête HTTP doit contenir un token valide dans le header HTTP `Authorization`.  Le format est `Authorization: Bearer <token-jwt>`. Par exemple `Authorization: Bearer xxxx.yyyy.zzzz`  Vous pouvez recevoir un des ces codes retours si vous n’êtes pas authentifié ou autorisé :  * 401 - Unauthorized - Vous n’êtes pas authentifié     * Il n’y a pas de token passé dans le header HTTP `Authorization`     * Le token passé n’est pas au bon format (Bearer <token-jwt>) * 403 - Forbidden - Vous êtes authentifié mais pas autorisé à exécuter cette opération     * La signature du token est incorrecte / n’a pas pû être vérifiée     * Le token est expiré     * Vérifier les droits de l’utilisateur * 500 - Internal Server Error     * Il n’est pas encore actif  ### Type de données  Sauf indications spécifiques données au niveau de l'opération, les types de données utilisés dans cette API sont les suivants :  * string - Chaîne de caractères encodée en UTF8 (ex : `Une chaîne de caractère`)    * Dans le cas des descripteurs de type `codeXxx`, seuls les caractères de A à Z, de 0 à 9 et le tiret(-) sont autorisés  * string($date) - Une date sous la forme d'une chaîne de caractères (ex : `2020-02-25`, norme [ISO-8601](https://fr.wikipedia.org/wiki/ISO_8601))  * integer($int64) - Un entier sur 64 bits (de -9 223 372 036 854 775 808 à 9 223 372 036 854 775 807) (ex : `2542`)  * integer($int32) - Un entier sur 32 bits (de –2 147 483 648 à 2 147 483 647) (ex : `2542`)  * number($double) - un nombre à virgule flottante en double précision  * boolean - Un booléen représenté par `true` ou `false`  ### Code retour      | Code    | Description                                                                                                         |     |---------|---------------------------------------------------------------------------------------------------------------------|     | 200     | L'opération s'est déroulée avec succès                                                                              |     | 201     | L'opération a aboutie à la création d'une ressource                                                                 |     | 400     | Un ou des paramètres d'entrées sont erronées                                                                        |     |         | Une erreur fonctionnelle s'est produite                                                                             |     | 404     | La ressource demandée n'est pas trouvé                                                                              |     |         | Remarque : Dans le cas des opérations retournant une liste, on recevra un code 200 avec en résultat une liste vide  |     | 500     | Erreur technique rencontrée par le serveur                                                                          |   ## Notions métiers  ### Objet Maquette (OM)  Un Objet Maquette représente n'importe quel nœud d'un arbre de Formation: Formation, Objet de Formation ou Groupement.  Un objet Maquette est identifié par le codeChemin (chemin pédagogique), le codePeriode et le codeStructure   ### Formation  Une Formation est un arbre dont les nœuds sont des Objets de formation et dont la racine est la Formation elle-même. Pour apparaître dans le Module CHC, la formation doit être mise en œuvre, actualisée, ouverte à l’inscription et ouverte au CHC dans MOF. Il est également nécessaire qu’il y ait au moins une inscription valide sur un objet maquette de l’arbre de la formation.  ### Objet formation  Un objet de formation est l’un des nœuds de l’arbre de formation : année, semestre, UE, EC, enseignement, etc.(hors groupement). Pour apparaître dans le Module CHC, un objet de formation doit être ouvert au CHC dans MOF.  ### Groupement  Un groupement est une possibilité de structurer et d'organiser une formation.Il peut contenir des objets de formations de tous les types, être lié pour décomposer des objets pères de tous les types, être avec ou sans plage de choix.  ### Plage de choix  Une plage de choix permet de contraindre l’apprenant lorsque  qu’il effectue son CHC dans Pégase. Cette plage de choix est matérialisée par un nombre minimum et un nombre maximum d’objets de formation à sélectionner. La plage de choix est contrôlée au cours du CHC.  ### Groupe  Un Groupe est une entité permettant de diviser une population d’étudiants ou d’identifier une population spécifique d’étudiants inscrits administrativement ou pédagogiquement  ### Composition  Une composition est une entité permettant de rassembler des groupes. Une composition contient obligatoirement au moins un groupe.   ### Période  Une période de mise en œuvre correspond à la période pendant laquelle se déroule la formation, du début des cours jusqu’à la délibération des jurys. Elle est le point d’entrée du module puisque le CHC se fait pour une période donnée.  ### Apprenant  Un apprenant est un usager qui suit un cursus et pour lequel le CHC va être saisi.  ### Inscription  L’inscription est l’ensemble des étapes de saisie des données concernant l’apprenant : état-civil, coordonnées, situation précédente, situation précédente, cursus, montant de l’inscription, mode de paiement. Cette saisie peut être faite par le gestionnaire ou l’apprenant. Elle doit être vérifiée et validée par le gestionnaire.Au 25/03/21, l’inscription doit être validée pour que l’apprenant puisse arriver dans le module CHC.  ### Cursus  Le cursus est l’ensemble des Objets Maquette auxquels l’apprenant va être affecté ou pour lesquels des aménagements vont être saisis, le tout pour une période donnée.  Un cursus est défini par le code de l’apprenant et un objet maquette lui-même identifié par le codeChemin (chemin pédagogique), le codePeriode et le codeStructure.  ### Acquis capitalisé  Un acquis capitalisé est un objet de formation dont les modalités de contrôle des connaissances attendent un résultat capitalisable. Pour être identifié dans CHC comme acquis capitalisé, cet objet doit posséder un résultat positif obtenu sur une période passée, pour laquelle une délibération de jury a statué.  ### Chemin pédagogique  Un chemin pédagogique identifie le lien d'un Objet Maquette à un autre Objet maquette de sa descendance.  **Exemple**  ``` MASTER-RH>MASTER-1>SEMESTRE-1>UE-OPTIONS>ESPAGNOL ```  ### Affectation en masse (Dépréciée)  L'affectation en masse permet, pour une période donnée,  d’affecter ou de désaffecter des apprenants sur un Objet Maquette ouvert au choix du cursus et éventuellement sur sa descendance obligatoire.Les affectations ne sont possibles que si le père de l'objet maquette a été affecté ou acquis => contrôle du chemin pédagogique.  Les aménagements-acquis sont conservés lors de la désaffectation.  ### Affectation individuelle (Dépréciée)  L'affectation individuelle permet, pour une période et un apprenant donnés de saisir, modifier ou supprimer pour cet apprenant les affectations, les acquis et les aménagements aux Objets de formations souhaités. Un Objet de formation est soit affecté soit acquis : il ne peut pas être les deux en même temps.  Des contrôles sont effectués pour la cohérence aménagement-acquis ou aménagement-affectation ou aménagement-aucun.  Les affectations ou la saisie des aménagements ne sont possibles sur un OM que si le père a été affecté (contrôle du chemin pédagogique).  ### Paramétrage  Un paramétrage est une personnalisation de concepts spécifiques pour des processus métiers. Ils sont gérés dans le Référentiel ou dans chacun des modules. Ils peuvent être utilisés par les différents modules. Le Type d’aménagement est un paramétrage du module REF.  ## Informations techniques  Toutes les actions de cursus (affectation, désaffectation, acquis, non-acquis, type d’aménagement) de l’apprenant dans CHC seront envoyées au module COC.  Toutes les actions de cursus sont en mode upsert (créer si ça n’existe pas ou modifier si ça existe). Seule la liste des types aménagements dans l’entrée sera remplacée par ses anciennes valeurs.  ### Règles communes pour réaliser un choix de cursus  * L’affectation peut seulement se faire s’il y a une inscription valide sur l’objet maquette ou un des objets maquette de son ascendance. Les statuts de l’inscription proviennent du module INS. * Un CHC sur un Objet Maquette peut se faire uniquement si cet objet Maquette a le témoin ouvertChoixCursus à true. * Lors de la désaffectation d’un apprenant  à un Objet Maquette, l’apprenant sera également désaffecté automatiquement à tous les Objets Maquette de sa descendance. * Un CHC sur un Objet Maquette dans un groupement à plage de choix peut être fait seulement si le nombre de CHC de l’apprenant dans ce groupement ne dépasse pas le maximum autorisé (la plageMax). On ne contrôle pas la valeur mininum de plage de choix. * L’affectation/acquis/type aménagement sur un Objet Maquette mutualisé présent plusieurs fois dans un arbre ne peut être réalisée qu’une fois, c’est-à-dire que l’Objet Maquette (avec un certain code chemin) ne peut être qu’une seule fois avec une affectation / un acquis ou un type aménagement sur le même cursus * La capacité d’accueil d’un Objet Maquette n’est pas contrôlée dans l’API car non bloquante. Cela peut donc entraîner des capacités d’accueil négatives. * Les aménagements avec prise en compte Acquis et Aucun ne sont pas décomptés de la capacité d’accueil d’un Objet Maquette. * La récupération d'un acquis capitalisé empêche son affectation et celle de sa descendance. 
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


import fr.univlorraine.pegase.model.chc.AcquisCapitalisesDesApprenantsPourChaqueOFDescendantDeCodeRacine;
import fr.univlorraine.pegase.model.chc.ApprenantExtention;
import fr.univlorraine.pegase.model.chc.CursusModifies;
import fr.univlorraine.pegase.model.chc.ObjetMaquetteExtension;
import fr.univlorraine.pegase.model.chc.OperationCursus;
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
     * Build call for affecterApprennantLstObjectMaquette
     * @param paramsCursusApprenants Le choix de cursus des apprenants dans un objet maquette (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> L&#39;affectation de l&#39;apprenant à l&#39;objet maquette a été effectuée </td><td>  -  </td></tr>
     </table>
     * @deprecated
     */
    @Deprecated
    public okhttp3.Call affecterApprennantLstObjectMaquetteCall(ParamsCursusApprenants paramsCursusApprenants, final ApiCallback _callback) throws ApiException {
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
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "idTokenAuth" };
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @Deprecated
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
     * Affecter des apprenants à un objet maquette
     * Permet de faire des affectations/désaffectations en masse avec ou sans descendance  Règles de gestion: * Se reporter aux Règles de gestion communes * Le père de l’Objet Maquette doit avoir une prise en compte «Affectation» (contrôle du chemin pédagogique) * On ne peut pas faire une Affectation en masse si l’occurrence a un Aménagement &#x3D; Acquis ou Aucun dans la base de données. * Seuls les apprenants en erreur seront rejetés, les erreurs sont dans le champ “resultatErreur” de l’objet de retour * Lors de l’affectation avec descendance, seuls les Objets Maquette obligatoires seront affectés * Se reporter aux descriptions des attributs de l’affectation en masse  # Il est remplacé par [\&quot;Changer les choix de cursus d&#39;apprenants sur des objets maquette\&quot;](#/cursus/modifierChoixDeCursus) 
     * @param paramsCursusApprenants Le choix de cursus des apprenants dans un objet maquette (required)
     * @return ReponseAffectation
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> L&#39;affectation de l&#39;apprenant à l&#39;objet maquette a été effectuée </td><td>  -  </td></tr>
     </table>
     * @deprecated
     */
    @Deprecated
    public ReponseAffectation affecterApprennantLstObjectMaquette(ParamsCursusApprenants paramsCursusApprenants) throws ApiException {
        ApiResponse<ReponseAffectation> localVarResp = affecterApprennantLstObjectMaquetteWithHttpInfo(paramsCursusApprenants);
        return localVarResp.getData();
    }

    /**
     * Affecter des apprenants à un objet maquette
     * Permet de faire des affectations/désaffectations en masse avec ou sans descendance  Règles de gestion: * Se reporter aux Règles de gestion communes * Le père de l’Objet Maquette doit avoir une prise en compte «Affectation» (contrôle du chemin pédagogique) * On ne peut pas faire une Affectation en masse si l’occurrence a un Aménagement &#x3D; Acquis ou Aucun dans la base de données. * Seuls les apprenants en erreur seront rejetés, les erreurs sont dans le champ “resultatErreur” de l’objet de retour * Lors de l’affectation avec descendance, seuls les Objets Maquette obligatoires seront affectés * Se reporter aux descriptions des attributs de l’affectation en masse  # Il est remplacé par [\&quot;Changer les choix de cursus d&#39;apprenants sur des objets maquette\&quot;](#/cursus/modifierChoixDeCursus) 
     * @param paramsCursusApprenants Le choix de cursus des apprenants dans un objet maquette (required)
     * @return ApiResponse&lt;ReponseAffectation&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> L&#39;affectation de l&#39;apprenant à l&#39;objet maquette a été effectuée </td><td>  -  </td></tr>
     </table>
     * @deprecated
     */
    @Deprecated
    public ApiResponse<ReponseAffectation> affecterApprennantLstObjectMaquetteWithHttpInfo(ParamsCursusApprenants paramsCursusApprenants) throws ApiException {
        okhttp3.Call localVarCall = affecterApprennantLstObjectMaquetteValidateBeforeCall(paramsCursusApprenants, null);
        Type localVarReturnType = new TypeToken<ReponseAffectation>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Affecter des apprenants à un objet maquette (asynchronously)
     * Permet de faire des affectations/désaffectations en masse avec ou sans descendance  Règles de gestion: * Se reporter aux Règles de gestion communes * Le père de l’Objet Maquette doit avoir une prise en compte «Affectation» (contrôle du chemin pédagogique) * On ne peut pas faire une Affectation en masse si l’occurrence a un Aménagement &#x3D; Acquis ou Aucun dans la base de données. * Seuls les apprenants en erreur seront rejetés, les erreurs sont dans le champ “resultatErreur” de l’objet de retour * Lors de l’affectation avec descendance, seuls les Objets Maquette obligatoires seront affectés * Se reporter aux descriptions des attributs de l’affectation en masse  # Il est remplacé par [\&quot;Changer les choix de cursus d&#39;apprenants sur des objets maquette\&quot;](#/cursus/modifierChoixDeCursus) 
     * @param paramsCursusApprenants Le choix de cursus des apprenants dans un objet maquette (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> L&#39;affectation de l&#39;apprenant à l&#39;objet maquette a été effectuée </td><td>  -  </td></tr>
     </table>
     * @deprecated
     */
    @Deprecated
    public okhttp3.Call affecterApprennantLstObjectMaquetteAsync(ParamsCursusApprenants paramsCursusApprenants, final ApiCallback<ReponseAffectation> _callback) throws ApiException {

        okhttp3.Call localVarCall = affecterApprennantLstObjectMaquetteValidateBeforeCall(paramsCursusApprenants, _callback);
        Type localVarReturnType = new TypeToken<ReponseAffectation>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for affecterObjectMaquetteLstApprenant
     * @param paramsCursusObjetMaquettes Le choix de cursus de l&#39;apprenant dans les objets maquette (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> L&#39;affectation de l&#39;apprenant à l&#39;objet maquette a été effectuée </td><td>  -  </td></tr>
     </table>
     * @deprecated
     */
    @Deprecated
    public okhttp3.Call affecterObjectMaquetteLstApprenantCall(ParamsCursusObjetMaquettes paramsCursusObjetMaquettes, final ApiCallback _callback) throws ApiException {
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
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "idTokenAuth" };
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @Deprecated
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
     * Affecter un apprenant aux objets maquette
     * Permet de faire des affectations/désaffectations individuelles  Règles de gestion:  * Se reporter aux Règles de gestion communes * Par apprenant, tous les Objets Maquette de l’arbre doivent contenir un CHC avec la période, la structure, le chemin de chaque Objet Maquette et les témoins «Affectation» ou «Acquis» et la liste des types d’aménagement. * Il faut donner la liste des Objets Maquette depuis l’OM qui porte l’inscription jusqu’à l’Objet Maquette qu’on veut modifier.      **Exemple**     &#x60;&#x60;&#x60;     Si on veut modifier l’OM  LIC-STS-INFO-20&gt;L1-INFO-20&gt;L1INFORS100000 pour un apprenant, alors on précise aussi tous les OM ascendants dans les valeurs de l’objet \&quot;objetMaquetteLst\&quot;      LIC-STS-INFO-20, LIC-STS-INFO-20&gt;L1-INFO-20 et LIC-STS-INFO-20&gt;L1-INFO-20&gt;L1INFORS100000     &#x60;&#x60;&#x60; * Il est de plus préconiser d’envoyer la totalité des Objets Maquette du cursus d’un apprenant via l’API. Cet envoi global permet de s’assurer que les contrôles métier soient effectués sur la descendance de chaque Objet Maquette et ainsi éviter des cursus non complet ou des incohérences de CHC entre Objet Maquette ascendant et descendant. L’API suivante permet de récupérer pour un apprenant, une période, la totalité de ses Objets Maquettes: /inscriptions/objets-maquette/{codeApprenant}/{codePeriode}/{codeStructure}. * L’API supprime le Cursus de l’apprenant aux Objets Maquette et ses descendants de la même formation si ces OM n’existent plus dans l’objet \&quot;objetMaquetteLst\&quot;. * Il est possible d’affecter un apprenant (temoinAffecte&#x3D;true, temoinAcquis&#x3D;false) ou de désaffecter un apprenant (temoinAffecte&#x3D;false, temoinAcquis&#x3D;false) sur un Objet Maquette sans positionner d’aménagement. * La prise en compte peut être soit «Affectation», soit «Acquis», soit «Aucun». Les types d’aménagement doivent respecter la prise en compte paramétrée dans votre référentiel. * Un CHC avec aménagement peut avoir plusieurs prises en compte et les témoins “temoinAcquis” ou “temoinAffecte” doivent être cohérents avec les valeurs de prise en compte en suivant les règles suivantes :     * s’il existe au moins une prise en compte «Acquis», “temoinAcquis” doit être à true     * s’il n’existe pas de prise en compte «Acquis» et s’il existe au moins une prise en     compte « Affectation », “temoinAffecte” doit être à true     * sinon, avec une prise en compte “Aucun”, “temoinAcquis” et “temoinAffecte” doivent     être à false     * Si la priorité de la liste d’aménagements du père est à “Affectation“,     ses descendants peuvent être affectés via un aménagement ou non et désaffectés via un aménagement     ou non mais ils ne peuvent avoir le témoin acquis&#x3D;true seulement via un aménagement “Acquis“.     * Si le père a un aménagement \&quot;Acquis\&quot;, ses descendants obligatoires doivent avoir     le temoinAcquis&#x3D;true . Il n’est pas possible d’avoir un descendant avec temoinAffecte&#x3D;true.     L’aménagement \&quot;Acquis\&quot; n’est pas obligatoire si on a un héritage avec une prise en     compte Acquis     * Si la priorité de la liste d’aménagement du père est “Aucun“, les descendants peuvent     avoir temoinAcquis&#x3D;true avec au moins un aménagement (prise en compte acquis). Les descendants ne peuvent     pas avoir de temoinAffecte&#x3D;true, ni d’aménagement avec priorité de prise en compte Affectation.  * Le CHC de l’apprenant sera entièrement rejeté s’il existe des erreurs ou des incohérences * Se reporter aux descriptions des attributs de l’affectation individuelle  # Il est remplacé par [\&quot;Changer les choix de cursus d&#39;apprenants sur des objets maquette\&quot;](#/cursus/modifierChoixDeCursus) 
     * @param paramsCursusObjetMaquettes Le choix de cursus de l&#39;apprenant dans les objets maquette (required)
     * @return ReponseAffectation
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> L&#39;affectation de l&#39;apprenant à l&#39;objet maquette a été effectuée </td><td>  -  </td></tr>
     </table>
     * @deprecated
     */
    @Deprecated
    public ReponseAffectation affecterObjectMaquetteLstApprenant(ParamsCursusObjetMaquettes paramsCursusObjetMaquettes) throws ApiException {
        ApiResponse<ReponseAffectation> localVarResp = affecterObjectMaquetteLstApprenantWithHttpInfo(paramsCursusObjetMaquettes);
        return localVarResp.getData();
    }

    /**
     * Affecter un apprenant aux objets maquette
     * Permet de faire des affectations/désaffectations individuelles  Règles de gestion:  * Se reporter aux Règles de gestion communes * Par apprenant, tous les Objets Maquette de l’arbre doivent contenir un CHC avec la période, la structure, le chemin de chaque Objet Maquette et les témoins «Affectation» ou «Acquis» et la liste des types d’aménagement. * Il faut donner la liste des Objets Maquette depuis l’OM qui porte l’inscription jusqu’à l’Objet Maquette qu’on veut modifier.      **Exemple**     &#x60;&#x60;&#x60;     Si on veut modifier l’OM  LIC-STS-INFO-20&gt;L1-INFO-20&gt;L1INFORS100000 pour un apprenant, alors on précise aussi tous les OM ascendants dans les valeurs de l’objet \&quot;objetMaquetteLst\&quot;      LIC-STS-INFO-20, LIC-STS-INFO-20&gt;L1-INFO-20 et LIC-STS-INFO-20&gt;L1-INFO-20&gt;L1INFORS100000     &#x60;&#x60;&#x60; * Il est de plus préconiser d’envoyer la totalité des Objets Maquette du cursus d’un apprenant via l’API. Cet envoi global permet de s’assurer que les contrôles métier soient effectués sur la descendance de chaque Objet Maquette et ainsi éviter des cursus non complet ou des incohérences de CHC entre Objet Maquette ascendant et descendant. L’API suivante permet de récupérer pour un apprenant, une période, la totalité de ses Objets Maquettes: /inscriptions/objets-maquette/{codeApprenant}/{codePeriode}/{codeStructure}. * L’API supprime le Cursus de l’apprenant aux Objets Maquette et ses descendants de la même formation si ces OM n’existent plus dans l’objet \&quot;objetMaquetteLst\&quot;. * Il est possible d’affecter un apprenant (temoinAffecte&#x3D;true, temoinAcquis&#x3D;false) ou de désaffecter un apprenant (temoinAffecte&#x3D;false, temoinAcquis&#x3D;false) sur un Objet Maquette sans positionner d’aménagement. * La prise en compte peut être soit «Affectation», soit «Acquis», soit «Aucun». Les types d’aménagement doivent respecter la prise en compte paramétrée dans votre référentiel. * Un CHC avec aménagement peut avoir plusieurs prises en compte et les témoins “temoinAcquis” ou “temoinAffecte” doivent être cohérents avec les valeurs de prise en compte en suivant les règles suivantes :     * s’il existe au moins une prise en compte «Acquis», “temoinAcquis” doit être à true     * s’il n’existe pas de prise en compte «Acquis» et s’il existe au moins une prise en     compte « Affectation », “temoinAffecte” doit être à true     * sinon, avec une prise en compte “Aucun”, “temoinAcquis” et “temoinAffecte” doivent     être à false     * Si la priorité de la liste d’aménagements du père est à “Affectation“,     ses descendants peuvent être affectés via un aménagement ou non et désaffectés via un aménagement     ou non mais ils ne peuvent avoir le témoin acquis&#x3D;true seulement via un aménagement “Acquis“.     * Si le père a un aménagement \&quot;Acquis\&quot;, ses descendants obligatoires doivent avoir     le temoinAcquis&#x3D;true . Il n’est pas possible d’avoir un descendant avec temoinAffecte&#x3D;true.     L’aménagement \&quot;Acquis\&quot; n’est pas obligatoire si on a un héritage avec une prise en     compte Acquis     * Si la priorité de la liste d’aménagement du père est “Aucun“, les descendants peuvent     avoir temoinAcquis&#x3D;true avec au moins un aménagement (prise en compte acquis). Les descendants ne peuvent     pas avoir de temoinAffecte&#x3D;true, ni d’aménagement avec priorité de prise en compte Affectation.  * Le CHC de l’apprenant sera entièrement rejeté s’il existe des erreurs ou des incohérences * Se reporter aux descriptions des attributs de l’affectation individuelle  # Il est remplacé par [\&quot;Changer les choix de cursus d&#39;apprenants sur des objets maquette\&quot;](#/cursus/modifierChoixDeCursus) 
     * @param paramsCursusObjetMaquettes Le choix de cursus de l&#39;apprenant dans les objets maquette (required)
     * @return ApiResponse&lt;ReponseAffectation&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> L&#39;affectation de l&#39;apprenant à l&#39;objet maquette a été effectuée </td><td>  -  </td></tr>
     </table>
     * @deprecated
     */
    @Deprecated
    public ApiResponse<ReponseAffectation> affecterObjectMaquetteLstApprenantWithHttpInfo(ParamsCursusObjetMaquettes paramsCursusObjetMaquettes) throws ApiException {
        okhttp3.Call localVarCall = affecterObjectMaquetteLstApprenantValidateBeforeCall(paramsCursusObjetMaquettes, null);
        Type localVarReturnType = new TypeToken<ReponseAffectation>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Affecter un apprenant aux objets maquette (asynchronously)
     * Permet de faire des affectations/désaffectations individuelles  Règles de gestion:  * Se reporter aux Règles de gestion communes * Par apprenant, tous les Objets Maquette de l’arbre doivent contenir un CHC avec la période, la structure, le chemin de chaque Objet Maquette et les témoins «Affectation» ou «Acquis» et la liste des types d’aménagement. * Il faut donner la liste des Objets Maquette depuis l’OM qui porte l’inscription jusqu’à l’Objet Maquette qu’on veut modifier.      **Exemple**     &#x60;&#x60;&#x60;     Si on veut modifier l’OM  LIC-STS-INFO-20&gt;L1-INFO-20&gt;L1INFORS100000 pour un apprenant, alors on précise aussi tous les OM ascendants dans les valeurs de l’objet \&quot;objetMaquetteLst\&quot;      LIC-STS-INFO-20, LIC-STS-INFO-20&gt;L1-INFO-20 et LIC-STS-INFO-20&gt;L1-INFO-20&gt;L1INFORS100000     &#x60;&#x60;&#x60; * Il est de plus préconiser d’envoyer la totalité des Objets Maquette du cursus d’un apprenant via l’API. Cet envoi global permet de s’assurer que les contrôles métier soient effectués sur la descendance de chaque Objet Maquette et ainsi éviter des cursus non complet ou des incohérences de CHC entre Objet Maquette ascendant et descendant. L’API suivante permet de récupérer pour un apprenant, une période, la totalité de ses Objets Maquettes: /inscriptions/objets-maquette/{codeApprenant}/{codePeriode}/{codeStructure}. * L’API supprime le Cursus de l’apprenant aux Objets Maquette et ses descendants de la même formation si ces OM n’existent plus dans l’objet \&quot;objetMaquetteLst\&quot;. * Il est possible d’affecter un apprenant (temoinAffecte&#x3D;true, temoinAcquis&#x3D;false) ou de désaffecter un apprenant (temoinAffecte&#x3D;false, temoinAcquis&#x3D;false) sur un Objet Maquette sans positionner d’aménagement. * La prise en compte peut être soit «Affectation», soit «Acquis», soit «Aucun». Les types d’aménagement doivent respecter la prise en compte paramétrée dans votre référentiel. * Un CHC avec aménagement peut avoir plusieurs prises en compte et les témoins “temoinAcquis” ou “temoinAffecte” doivent être cohérents avec les valeurs de prise en compte en suivant les règles suivantes :     * s’il existe au moins une prise en compte «Acquis», “temoinAcquis” doit être à true     * s’il n’existe pas de prise en compte «Acquis» et s’il existe au moins une prise en     compte « Affectation », “temoinAffecte” doit être à true     * sinon, avec une prise en compte “Aucun”, “temoinAcquis” et “temoinAffecte” doivent     être à false     * Si la priorité de la liste d’aménagements du père est à “Affectation“,     ses descendants peuvent être affectés via un aménagement ou non et désaffectés via un aménagement     ou non mais ils ne peuvent avoir le témoin acquis&#x3D;true seulement via un aménagement “Acquis“.     * Si le père a un aménagement \&quot;Acquis\&quot;, ses descendants obligatoires doivent avoir     le temoinAcquis&#x3D;true . Il n’est pas possible d’avoir un descendant avec temoinAffecte&#x3D;true.     L’aménagement \&quot;Acquis\&quot; n’est pas obligatoire si on a un héritage avec une prise en     compte Acquis     * Si la priorité de la liste d’aménagement du père est “Aucun“, les descendants peuvent     avoir temoinAcquis&#x3D;true avec au moins un aménagement (prise en compte acquis). Les descendants ne peuvent     pas avoir de temoinAffecte&#x3D;true, ni d’aménagement avec priorité de prise en compte Affectation.  * Le CHC de l’apprenant sera entièrement rejeté s’il existe des erreurs ou des incohérences * Se reporter aux descriptions des attributs de l’affectation individuelle  # Il est remplacé par [\&quot;Changer les choix de cursus d&#39;apprenants sur des objets maquette\&quot;](#/cursus/modifierChoixDeCursus) 
     * @param paramsCursusObjetMaquettes Le choix de cursus de l&#39;apprenant dans les objets maquette (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> L&#39;affectation de l&#39;apprenant à l&#39;objet maquette a été effectuée </td><td>  -  </td></tr>
     </table>
     * @deprecated
     */
    @Deprecated
    public okhttp3.Call affecterObjectMaquetteLstApprenantAsync(ParamsCursusObjetMaquettes paramsCursusObjetMaquettes, final ApiCallback<ReponseAffectation> _callback) throws ApiException {

        okhttp3.Call localVarCall = affecterObjectMaquetteLstApprenantValidateBeforeCall(paramsCursusObjetMaquettes, _callback);
        Type localVarReturnType = new TypeToken<ReponseAffectation>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for lireArbreCursusDesInscriptions
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codeApprenant Le code de l&#39;apprenant (required)
     * @param codePeriode Le code periode (required)
     * @param statutsInscription statut d&#39;inscription de l&#39;apprenant (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Les objets maquette de l&#39;apprenant avec un inscription administrative valide ont été récupérés </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireArbreCursusDesInscriptionsCall(String codeStructure, String codeApprenant, String codePeriode, List<String> statutsInscription, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/cursus/{codeStructure}/apprenants/{codeApprenant}/periodes/{codePeriode}"
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()))
            .replaceAll("\\{" + "codeApprenant" + "\\}", localVarApiClient.escapeString(codeApprenant.toString()))
            .replaceAll("\\{" + "codePeriode" + "\\}", localVarApiClient.escapeString(codePeriode.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (statutsInscription != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "statutsInscription", statutsInscription));
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
    private okhttp3.Call lireArbreCursusDesInscriptionsValidateBeforeCall(String codeStructure, String codeApprenant, String codePeriode, List<String> statutsInscription, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling lireArbreCursusDesInscriptions(Async)");
        }
        
        // verify the required parameter 'codeApprenant' is set
        if (codeApprenant == null) {
            throw new ApiException("Missing the required parameter 'codeApprenant' when calling lireArbreCursusDesInscriptions(Async)");
        }
        
        // verify the required parameter 'codePeriode' is set
        if (codePeriode == null) {
            throw new ApiException("Missing the required parameter 'codePeriode' when calling lireArbreCursusDesInscriptions(Async)");
        }
        

        okhttp3.Call localVarCall = lireArbreCursusDesInscriptionsCall(codeStructure, codeApprenant, codePeriode, statutsInscription, _callback);
        return localVarCall;

    }

    /**
     * Liste des objets maquette de l&#39;apprenant avec une(des) inscription(s) administrative validée ou annulée en fonction d&#39;une période, d&#39;une structure 
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codeApprenant Le code de l&#39;apprenant (required)
     * @param codePeriode Le code periode (required)
     * @param statutsInscription statut d&#39;inscription de l&#39;apprenant (optional)
     * @return List&lt;List&lt;ObjetMaquetteExtension&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Les objets maquette de l&#39;apprenant avec un inscription administrative valide ont été récupérés </td><td>  -  </td></tr>
     </table>
     */
    public List<List<ObjetMaquetteExtension>> lireArbreCursusDesInscriptions(String codeStructure, String codeApprenant, String codePeriode, List<String> statutsInscription) throws ApiException {
        ApiResponse<List<List<ObjetMaquetteExtension>>> localVarResp = lireArbreCursusDesInscriptionsWithHttpInfo(codeStructure, codeApprenant, codePeriode, statutsInscription);
        return localVarResp.getData();
    }

    /**
     * Liste des objets maquette de l&#39;apprenant avec une(des) inscription(s) administrative validée ou annulée en fonction d&#39;une période, d&#39;une structure 
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codeApprenant Le code de l&#39;apprenant (required)
     * @param codePeriode Le code periode (required)
     * @param statutsInscription statut d&#39;inscription de l&#39;apprenant (optional)
     * @return ApiResponse&lt;List&lt;List&lt;ObjetMaquetteExtension&gt;&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Les objets maquette de l&#39;apprenant avec un inscription administrative valide ont été récupérés </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<List<ObjetMaquetteExtension>>> lireArbreCursusDesInscriptionsWithHttpInfo(String codeStructure, String codeApprenant, String codePeriode, List<String> statutsInscription) throws ApiException {
        okhttp3.Call localVarCall = lireArbreCursusDesInscriptionsValidateBeforeCall(codeStructure, codeApprenant, codePeriode, statutsInscription, null);
        Type localVarReturnType = new TypeToken<List<List<ObjetMaquetteExtension>>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Liste des objets maquette de l&#39;apprenant avec une(des) inscription(s) administrative validée ou annulée en fonction d&#39;une période, d&#39;une structure  (asynchronously)
     * 
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param codeApprenant Le code de l&#39;apprenant (required)
     * @param codePeriode Le code periode (required)
     * @param statutsInscription statut d&#39;inscription de l&#39;apprenant (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Les objets maquette de l&#39;apprenant avec un inscription administrative valide ont été récupérés </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call lireArbreCursusDesInscriptionsAsync(String codeStructure, String codeApprenant, String codePeriode, List<String> statutsInscription, final ApiCallback<List<List<ObjetMaquetteExtension>>> _callback) throws ApiException {

        okhttp3.Call localVarCall = lireArbreCursusDesInscriptionsValidateBeforeCall(codeStructure, codeApprenant, codePeriode, statutsInscription, _callback);
        Type localVarReturnType = new TypeToken<List<List<ObjetMaquetteExtension>>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for lireListeApprenantsObjetMaquette
     * @param codeChemin Le code chemin de l&#39;objet maquette (required)
     * @param codePeriode Le code periode (required)
     * @param codeStructure Le code structure de l&#39;établissement (required)
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
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "idTokenAuth" };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
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
     * Liste des apprenants qui ont un cursus dans un objet maquette en fonction du statut d&#39;inscription des apprenants
     * 
     * @param codeChemin Le code chemin de l&#39;objet maquette (required)
     * @param codePeriode Le code periode (required)
     * @param codeStructure Le code structure de l&#39;établissement (required)
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
     * Liste des apprenants qui ont un cursus dans un objet maquette en fonction du statut d&#39;inscription des apprenants
     * 
     * @param codeChemin Le code chemin de l&#39;objet maquette (required)
     * @param codePeriode Le code periode (required)
     * @param codeStructure Le code structure de l&#39;établissement (required)
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
     * Liste des apprenants qui ont un cursus dans un objet maquette en fonction du statut d&#39;inscription des apprenants (asynchronously)
     * 
     * @param codeChemin Le code chemin de l&#39;objet maquette (required)
     * @param codePeriode Le code periode (required)
     * @param codeStructure Le code structure de l&#39;établissement (required)
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
    /**
     * Build call for miseAJourCapitalisations
     * @param codePeriode Le code periode (required)
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param acquisCapitalisesDesApprenantsPourChaqueOFDescendantDeCodeRacine Le nouvel état des capitalisations de chaque objet de formation de chaque arbre (désigné par codeCheminRacine) pour chaque apprenant (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> capitalisations mises à jour </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call miseAJourCapitalisationsCall(String codePeriode, String codeStructure, List<AcquisCapitalisesDesApprenantsPourChaqueOFDescendantDeCodeRacine> acquisCapitalisesDesApprenantsPourChaqueOFDescendantDeCodeRacine, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = acquisCapitalisesDesApprenantsPourChaqueOFDescendantDeCodeRacine;

        // create path and map variables
        String localVarPath = "/cursus/{codeStructure}/periode/{codePeriode}/capitalises"
            .replaceAll("\\{" + "codePeriode" + "\\}", localVarApiClient.escapeString(codePeriode.toString()))
            .replaceAll("\\{" + "codeStructure" + "\\}", localVarApiClient.escapeString(codeStructure.toString()));

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
    private okhttp3.Call miseAJourCapitalisationsValidateBeforeCall(String codePeriode, String codeStructure, List<AcquisCapitalisesDesApprenantsPourChaqueOFDescendantDeCodeRacine> acquisCapitalisesDesApprenantsPourChaqueOFDescendantDeCodeRacine, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codePeriode' is set
        if (codePeriode == null) {
            throw new ApiException("Missing the required parameter 'codePeriode' when calling miseAJourCapitalisations(Async)");
        }
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling miseAJourCapitalisations(Async)");
        }
        
        // verify the required parameter 'acquisCapitalisesDesApprenantsPourChaqueOFDescendantDeCodeRacine' is set
        if (acquisCapitalisesDesApprenantsPourChaqueOFDescendantDeCodeRacine == null) {
            throw new ApiException("Missing the required parameter 'acquisCapitalisesDesApprenantsPourChaqueOFDescendantDeCodeRacine' when calling miseAJourCapitalisations(Async)");
        }
        

        okhttp3.Call localVarCall = miseAJourCapitalisationsCall(codePeriode, codeStructure, acquisCapitalisesDesApprenantsPourChaqueOFDescendantDeCodeRacine, _callback);
        return localVarCall;

    }

    /**
     * Met à jour l&#39;état de capitalisation des objets maquettes de chaque arbre de chaque étudiant passé en paramètre.
     * Met à jour l&#39;état de capitalisation des objets maquettes de chaque arbre dont les racines sont passées en paramètre et pour chaque étudiant passé en paramètre et inscrits sur cette racine 
     * @param codePeriode Le code periode (required)
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param acquisCapitalisesDesApprenantsPourChaqueOFDescendantDeCodeRacine Le nouvel état des capitalisations de chaque objet de formation de chaque arbre (désigné par codeCheminRacine) pour chaque apprenant (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> capitalisations mises à jour </td><td>  -  </td></tr>
     </table>
     */
    public void miseAJourCapitalisations(String codePeriode, String codeStructure, List<AcquisCapitalisesDesApprenantsPourChaqueOFDescendantDeCodeRacine> acquisCapitalisesDesApprenantsPourChaqueOFDescendantDeCodeRacine) throws ApiException {
        miseAJourCapitalisationsWithHttpInfo(codePeriode, codeStructure, acquisCapitalisesDesApprenantsPourChaqueOFDescendantDeCodeRacine);
    }

    /**
     * Met à jour l&#39;état de capitalisation des objets maquettes de chaque arbre de chaque étudiant passé en paramètre.
     * Met à jour l&#39;état de capitalisation des objets maquettes de chaque arbre dont les racines sont passées en paramètre et pour chaque étudiant passé en paramètre et inscrits sur cette racine 
     * @param codePeriode Le code periode (required)
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param acquisCapitalisesDesApprenantsPourChaqueOFDescendantDeCodeRacine Le nouvel état des capitalisations de chaque objet de formation de chaque arbre (désigné par codeCheminRacine) pour chaque apprenant (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> capitalisations mises à jour </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> miseAJourCapitalisationsWithHttpInfo(String codePeriode, String codeStructure, List<AcquisCapitalisesDesApprenantsPourChaqueOFDescendantDeCodeRacine> acquisCapitalisesDesApprenantsPourChaqueOFDescendantDeCodeRacine) throws ApiException {
        okhttp3.Call localVarCall = miseAJourCapitalisationsValidateBeforeCall(codePeriode, codeStructure, acquisCapitalisesDesApprenantsPourChaqueOFDescendantDeCodeRacine, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * Met à jour l&#39;état de capitalisation des objets maquettes de chaque arbre de chaque étudiant passé en paramètre. (asynchronously)
     * Met à jour l&#39;état de capitalisation des objets maquettes de chaque arbre dont les racines sont passées en paramètre et pour chaque étudiant passé en paramètre et inscrits sur cette racine 
     * @param codePeriode Le code periode (required)
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param acquisCapitalisesDesApprenantsPourChaqueOFDescendantDeCodeRacine Le nouvel état des capitalisations de chaque objet de formation de chaque arbre (désigné par codeCheminRacine) pour chaque apprenant (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> capitalisations mises à jour </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call miseAJourCapitalisationsAsync(String codePeriode, String codeStructure, List<AcquisCapitalisesDesApprenantsPourChaqueOFDescendantDeCodeRacine> acquisCapitalisesDesApprenantsPourChaqueOFDescendantDeCodeRacine, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = miseAJourCapitalisationsValidateBeforeCall(codePeriode, codeStructure, acquisCapitalisesDesApprenantsPourChaqueOFDescendantDeCodeRacine, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for modifierChoixDeCursus
     * @param codePeriode Le code periode (required)
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param operationCursus Les modifications de choix de cursus (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> liste des cursus effectivement modifiés (ou impactés) par la(les) demande(s) adjoint d&#39;erreur(s) potentielle(s) </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call modifierChoixDeCursusCall(String codePeriode, String codeStructure, List<OperationCursus> operationCursus, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = operationCursus;

        // create path and map variables
        String localVarPath = "/cursus/{codeStructure}/periode/{codePeriode}"
            .replaceAll("\\{" + "codePeriode" + "\\}", localVarApiClient.escapeString(codePeriode.toString()))
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
        return localVarApiClient.buildCall(basePath, localVarPath, "PATCH", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call modifierChoixDeCursusValidateBeforeCall(String codePeriode, String codeStructure, List<OperationCursus> operationCursus, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'codePeriode' is set
        if (codePeriode == null) {
            throw new ApiException("Missing the required parameter 'codePeriode' when calling modifierChoixDeCursus(Async)");
        }
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new ApiException("Missing the required parameter 'codeStructure' when calling modifierChoixDeCursus(Async)");
        }
        
        // verify the required parameter 'operationCursus' is set
        if (operationCursus == null) {
            throw new ApiException("Missing the required parameter 'operationCursus' when calling modifierChoixDeCursus(Async)");
        }
        

        okhttp3.Call localVarCall = modifierChoixDeCursusCall(codePeriode, codeStructure, operationCursus, _callback);
        return localVarCall;

    }

    /**
     * Changer les choix de cursus d&#39;apprenants sur des objets maquette
     * Permet de faire des affectations, désaffectations, ajouts ou suppressions d&#39;aménagements, ajouts ou suppressions des acquis capitalisés 
     * @param codePeriode Le code periode (required)
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param operationCursus Les modifications de choix de cursus (required)
     * @return CursusModifies
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> liste des cursus effectivement modifiés (ou impactés) par la(les) demande(s) adjoint d&#39;erreur(s) potentielle(s) </td><td>  -  </td></tr>
     </table>
     */
    public CursusModifies modifierChoixDeCursus(String codePeriode, String codeStructure, List<OperationCursus> operationCursus) throws ApiException {
        ApiResponse<CursusModifies> localVarResp = modifierChoixDeCursusWithHttpInfo(codePeriode, codeStructure, operationCursus);
        return localVarResp.getData();
    }

    /**
     * Changer les choix de cursus d&#39;apprenants sur des objets maquette
     * Permet de faire des affectations, désaffectations, ajouts ou suppressions d&#39;aménagements, ajouts ou suppressions des acquis capitalisés 
     * @param codePeriode Le code periode (required)
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param operationCursus Les modifications de choix de cursus (required)
     * @return ApiResponse&lt;CursusModifies&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> liste des cursus effectivement modifiés (ou impactés) par la(les) demande(s) adjoint d&#39;erreur(s) potentielle(s) </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<CursusModifies> modifierChoixDeCursusWithHttpInfo(String codePeriode, String codeStructure, List<OperationCursus> operationCursus) throws ApiException {
        okhttp3.Call localVarCall = modifierChoixDeCursusValidateBeforeCall(codePeriode, codeStructure, operationCursus, null);
        Type localVarReturnType = new TypeToken<CursusModifies>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Changer les choix de cursus d&#39;apprenants sur des objets maquette (asynchronously)
     * Permet de faire des affectations, désaffectations, ajouts ou suppressions d&#39;aménagements, ajouts ou suppressions des acquis capitalisés 
     * @param codePeriode Le code periode (required)
     * @param codeStructure Le code structure de l&#39;établissement (required)
     * @param operationCursus Les modifications de choix de cursus (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> liste des cursus effectivement modifiés (ou impactés) par la(les) demande(s) adjoint d&#39;erreur(s) potentielle(s) </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call modifierChoixDeCursusAsync(String codePeriode, String codeStructure, List<OperationCursus> operationCursus, final ApiCallback<CursusModifies> _callback) throws ApiException {

        okhttp3.Call localVarCall = modifierChoixDeCursusValidateBeforeCall(codePeriode, codeStructure, operationCursus, _callback);
        Type localVarReturnType = new TypeToken<CursusModifies>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
