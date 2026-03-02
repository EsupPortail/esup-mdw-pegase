package fr.univlorraine.pegase.insext.api;

import fr.univlorraine.pegase.insext.invoker.ApiClient;
import fr.univlorraine.pegase.insext.invoker.BaseApi;

import fr.univlorraine.pegase.insext.model.ApprenantEtInscriptions;
import fr.univlorraine.pegase.insext.model.Pageable;
import fr.univlorraine.pegase.insext.model.VueInscriptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2026-03-02T14:43:56.235007300+01:00[Europe/Paris]", comments = "Generator version: 7.20.0")
public class InscriptionsApi extends BaseApi {

    public InscriptionsApi() {
        super(new ApiClient());
    }

    public InscriptionsApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Récupérer le dossier complet d&#39;un apprenant
     * Récupérer le dossier complet d&#39;un apprenant 
     * <p><b>200</b> - Données de l&#39;apprenant et ses inscriptions
     * <p><b>404</b> - Apprenant introuvable
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param codeApprenant Le code de l&#39;apprenant (required)
     * @return ApprenantEtInscriptions
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApprenantEtInscriptions lireInscriptions(String codeStructure, String codeApprenant) throws RestClientException {
        return lireInscriptionsWithHttpInfo(codeStructure, codeApprenant).getBody();
    }

    /**
     * Récupérer le dossier complet d&#39;un apprenant
     * Récupérer le dossier complet d&#39;un apprenant 
     * <p><b>200</b> - Données de l&#39;apprenant et ses inscriptions
     * <p><b>404</b> - Apprenant introuvable
     * @param codeStructure Le code de l&#39;établissement (required)
     * @param codeApprenant Le code de l&#39;apprenant (required)
     * @return ResponseEntity&lt;ApprenantEtInscriptions&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApprenantEtInscriptions> lireInscriptionsWithHttpInfo(String codeStructure, String codeApprenant) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'codeStructure' is set
        if (codeStructure == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'codeStructure' when calling lireInscriptions");
        }
        
        // verify the required parameter 'codeApprenant' is set
        if (codeApprenant == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'codeApprenant' when calling lireInscriptions");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("codeStructure", codeStructure);
        uriVariables.put("codeApprenant", codeApprenant);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "idTokenAuth" };

        ParameterizedTypeReference<ApprenantEtInscriptions> localReturnType = new ParameterizedTypeReference<ApprenantEtInscriptions>() {};
        return apiClient.invokeAPI("/gestion/inscription/{codeStructure}/{codeApprenant}/", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Chercher les données de la carte multi-services permettant également de répondre au besoin du flux des comptes
     * Les données renvoyées correspondent aux inscriptions validées, annulées ou ayant fait l&#39;objet d&#39;une actualisation
     * <p><b>200</b> - Données de la carte multi-services récupérées
     * <p><b>400</b> - La pagination est invalide. Une pagination est obligatoire, taille limitée à 500 sans photo, 50 avec photo.
     * <p><b>404</b> - Flux introuvable
     * @param depuis L&#39;horodatage de la dernière extraction (exprimé en nombre de secondes depuis le 01/01/1970). Les entrées modifiées ou créées depuis seront remontées. (optional)
     * @param jusqua L&#39;horodatage à partir duquel les entrées sont exclues (exprimé en nombre de secondes depuis le 01/01/1970). Si absent ou 0, toutes les entrées seront remontées depuis l&#39;horodatage depuis. (optional, default to 0)
     * @param photo Inclure la photo d&#39;identité binaire dans les réponses. (optional, default to false)
     * @param codePeriode Code de la période (optional)
     * @param pageable L&#39;objet de pagination (page : le numéro de la page (défaut 0), taille : le nombre d&#39;élément par page, strictement positif, défaut 50, limité à 500 sans photo, 50 avec photo (optional)
     * @param codeApprenant Code apprenant (optional)
     * @return VueInscriptions
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public VueInscriptions listerFluxInscriptionsPagine(Long depuis, Long jusqua, Boolean photo, String codePeriode, Pageable pageable, String codeApprenant) throws RestClientException {
        return listerFluxInscriptionsPagineWithHttpInfo(depuis, jusqua, photo, codePeriode, pageable, codeApprenant).getBody();
    }

    /**
     * Chercher les données de la carte multi-services permettant également de répondre au besoin du flux des comptes
     * Les données renvoyées correspondent aux inscriptions validées, annulées ou ayant fait l&#39;objet d&#39;une actualisation
     * <p><b>200</b> - Données de la carte multi-services récupérées
     * <p><b>400</b> - La pagination est invalide. Une pagination est obligatoire, taille limitée à 500 sans photo, 50 avec photo.
     * <p><b>404</b> - Flux introuvable
     * @param depuis L&#39;horodatage de la dernière extraction (exprimé en nombre de secondes depuis le 01/01/1970). Les entrées modifiées ou créées depuis seront remontées. (optional)
     * @param jusqua L&#39;horodatage à partir duquel les entrées sont exclues (exprimé en nombre de secondes depuis le 01/01/1970). Si absent ou 0, toutes les entrées seront remontées depuis l&#39;horodatage depuis. (optional, default to 0)
     * @param photo Inclure la photo d&#39;identité binaire dans les réponses. (optional, default to false)
     * @param codePeriode Code de la période (optional)
     * @param pageable L&#39;objet de pagination (page : le numéro de la page (défaut 0), taille : le nombre d&#39;élément par page, strictement positif, défaut 50, limité à 500 sans photo, 50 avec photo (optional)
     * @param codeApprenant Code apprenant (optional)
     * @return ResponseEntity&lt;VueInscriptions&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<VueInscriptions> listerFluxInscriptionsPagineWithHttpInfo(Long depuis, Long jusqua, Boolean photo, String codePeriode, Pageable pageable, String codeApprenant) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "depuis", depuis));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "jusqua", jusqua));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "photo", photo));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "codePeriode", codePeriode));
        
        if (pageable != null) {
            localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "page", pageable.getPage()));
            localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "taille", pageable.getTaille()));
        }localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "codeApprenant", codeApprenant));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "idTokenAuth" };

        ParameterizedTypeReference<VueInscriptions> localReturnType = new ParameterizedTypeReference<VueInscriptions>() {};
        return apiClient.invokeAPI("/gestion/flux-inscriptions/pagine", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }

    @Override
    public <T> ResponseEntity<T> invokeAPI(String url, HttpMethod method, Object request, ParameterizedTypeReference<T> returnType) throws RestClientException {
        String localVarPath = url.replace(apiClient.getBasePath(), "");
        Object localVarPostBody = request;

        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "idTokenAuth" };

        return apiClient.invokeAPI(localVarPath, method, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, returnType);
    }
}
