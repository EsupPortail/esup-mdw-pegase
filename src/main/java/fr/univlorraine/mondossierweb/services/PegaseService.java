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
package fr.univlorraine.mondossierweb.services;

import fr.univlorraine.mondossierweb.controllers.ConfigController;
import fr.univlorraine.pegase.api.ApiClient;
import fr.univlorraine.pegase.api.ApiException;
import fr.univlorraine.pegase.api.chc.CursusDcaApi;
import fr.univlorraine.pegase.api.coc.NotesEtResultatsPubliablesApi;
import fr.univlorraine.pegase.api.insext.InscriptionsApi;
import fr.univlorraine.pegase.api.insgestion.ApprenantsApi;
import fr.univlorraine.pegase.api.pai.PaiApi;
import fr.univlorraine.pegase.api.pieceext.PiecesApi;
import fr.univlorraine.pegase.model.chc.CursusDCA;
import fr.univlorraine.pegase.model.coc.Chemin;
import fr.univlorraine.pegase.model.insext.ApprenantEtInscriptions;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.Serializable;
import java.util.List;


@Service
@SuppressWarnings("serial")
@Slf4j
public class PegaseService implements Serializable {

	private static final String APPRENANT_NON_TROUVE = "apprenant non trouve";

	@Autowired
	private transient AccessTokenService accessTokenService;	

	private transient String etablissement;	
	private transient String apiInsUrl;	
	private transient String apiInsExtUrl;
	private transient String apiPieceExtUrl;
	private transient String apiChcUrl;	
	private transient String apiCocUrl;	
	private transient String apiPaiUrl;
	private transient String codeApprenantDemo;	
	private transient String codeApprenantTest;	
	private transient String codePeriodeTest;	
	private transient String codeCheminTest;

	@Autowired
	private transient ConfigController configController;

	// INS API
	private transient ApiClient apiClientIns = new ApiClient();
	private transient ApprenantsApi appApiIns = new ApprenantsApi();
	private transient fr.univlorraine.pegase.api.insgestion.InscriptionsApi insApiIns = new fr.univlorraine.pegase.api.insgestion.InscriptionsApi();
	// fluxInsApiIns en commentaire car non utilisé -> utilisation de insApiInsExt
	//private transient FluxInscriptionsApi fluxInsApiIns = new FluxInscriptionsApi();

	// INS-EXT API
	private transient ApiClient apiClientInsExt = new ApiClient();
	private transient InscriptionsApi insApiInsExt = new InscriptionsApi();

	// PIECE-EXT API
	private transient ApiClient apiClientPieceExt = new ApiClient();
	private transient PiecesApi insApiPieceExt = new PiecesApi();

	// PAI API
	private transient ApiClient apiClientPai = new ApiClient();
	private transient PaiApi insApiPai = new PaiApi();

	// CHC API
	private transient ApiClient apiClientChc = new ApiClient();
	private transient CursusDcaApi insApiChc = new CursusDcaApi();

	// COC API
	private transient ApiClient apiClientCoc = new ApiClient();
	private transient NotesEtResultatsPubliablesApi pubApiCoc = new NotesEtResultatsPubliablesApi();

	public String getCodeApprenantDemo() {
		return codeApprenantDemo;
	}
	public String getCodePeriodeTest() {
		return codePeriodeTest;
	}
	public String getCodeApprenantTest() {
		return codeApprenantTest;
	}
	public String getCodeCheminTest() {
		return codeCheminTest;
	}
	private void setEtablissement() {
		etablissement = configController.getEtablissement();
	}
	public String getEtablissement() {
		return etablissement;
	}
	private void setCodeApprenantDemo() {
		codeApprenantDemo = configController.getPegaseDemoApprenant();
	}
	private void setCodePeriodeTest() {
		codePeriodeTest = configController.getPegaseTestPeriode();
	}
	private void setCodeCheminTest() {
		codeCheminTest = configController.getPegaseTestChemin();
	}
	private void setCodeApprenantTest() {
		codeApprenantTest = configController.getPegaseTestApprenant();
	}

	private void setApiPaiUrl() {
		apiPaiUrl = configController.getApiPaiUrl();
	}

	private void setApiCocUrl() {
		apiCocUrl = configController.getApiCocUrl();
	}

	private void setApiChcUrl() {
		apiChcUrl = configController.getApiChcUrl();
	}

	private void setApiInsUrl() {
		apiInsUrl = configController.getApiInsUrl();	
	}

	private void setApiInsExtUrl() {
		apiInsExtUrl = configController.getApiInsExtUrl();	
	}
	private void setApiPieceExtUrl() { apiPieceExtUrl = configController.getApiPieceExtUrl();}

	public void refreshParameters() {
		refreshPegaseParameters();
		refreshApiParameters();
	}

	public void refreshPegaseParameters() {
		setEtablissement();
		setCodeApprenantDemo();
	}

	public void refreshApiParameters() {
		setApiInsUrl();
		setApiInsExtUrl();
		setApiPieceExtUrl();
		setApiChcUrl();
		setApiCocUrl();
		setApiPaiUrl();
		setCodePeriodeTest();
		setCodeApprenantTest();
		setCodeCheminTest();

		// Init INS
		apiClientIns.setBasePath(apiInsUrl);
		insApiIns.setApiClient(apiClientIns);
		//fluxInsApiIns.setApiClient(apiClientIns);
		appApiIns.setApiClient(apiClientIns);

		// Init INS-EXT
		apiClientInsExt.setBasePath(apiInsExtUrl);
		insApiInsExt.setApiClient(apiClientInsExt);

		// Init PIECE-EXT
		apiClientPieceExt.setBasePath(apiPieceExtUrl);
		insApiPieceExt.setApiClient(apiClientPieceExt);

		// Init PAI
		apiClientPai.setBasePath(apiPaiUrl);
		insApiPai.setApiClient(apiClientPai);

		// Init CHC
		apiClientChc.setBasePath(apiChcUrl);
		insApiChc.setApiClient(apiClientChc);

		// Init COC
		apiClientCoc.setBasePath(apiCocUrl);
		pubApiCoc.setApiClient(apiClientCoc);
	}

	@PostConstruct
	public void init() {
		refreshParameters();
	}

	/**
	 * Code démo (draft)
	 * @param codeApprenant
	 * @return
	 */
	/*public VueInscriptions getFluxDossierApprenant(String codeApprenant) {
		// Si les paramètres nécessaires sont valués
		if(StringUtils.hasText(etablissement) && StringUtils.hasText(codeApprenant)) {
			// Maj du token pour récupérer le dernier token valide
			fluxInsApiIns.getApiClient().setBearerToken(accessTokenService.getToken());
			try {
				Long depuis = null;
				Long jusqua = null;
				Boolean photo = Boolean.TRUE;
				String codePeriode = null;
				Pageable pageable = null;
				// Appel de l'API Pégase
				VueInscriptions vueIns = fluxInsApiIns.listerFluxInscriptionsPagine(depuis, jusqua, photo, codePeriode, pageable, codeApprenant);
				if(vueIns != null) {
					log.info("{}  recuperees", vueIns.getTotalElements());
				} else {
					log.info("Anomalie lors de l'appel à la methode API : listerFluxInscriptionsPagine pour le code apprenant : {} et etablissement : {} LE DOSSIER RECUPERE EST NULL", codeApprenant, etablissement);
				}
				return vueIns;
			} catch (ApiException e) {
				if(e.getCode() == 500 && e.getResponseBody()!=null && e.getResponseBody().contains(APPRENANT_NON_TROUVE)) {
					log.warn("Apprenant non trouvé lors de l'appel à la methode API : listerFluxInscriptionsPagine pour le code apprenant : {} et etablissement : {}", codeApprenant, etablissement);
				} else {
					log.error("Erreur lors de l'appel à la methode API : listerFluxInscriptionsPagine pour le code apprenant : {} et etablissement : {} => ({}) message: {} body : {}", codeApprenant, etablissement,e.getCode(), e.getMessage(),e.getResponseBody(), e);
				}
			}
		}
		return null;
	}*/

	public ApprenantEtInscriptions getDossierApprenant(String codeApprenant) {
		// Si les paramètres nécessaires sont valués
		if(StringUtils.hasText(etablissement) && StringUtils.hasText(codeApprenant)) {
			try {
				// Maj du token pour récupérer le dernier token valide
				insApiInsExt.getApiClient().setBearerToken(accessTokenService.getToken());
				// Appel de l'API Pégase
				ApprenantEtInscriptions dossier = insApiInsExt.lireInscriptions(etablissement, codeApprenant);
				if(dossier != null) {
					log.info("Dossier de {} {} {} recupere", dossier.getApprenant().getEtatCivil().getPrenom(),dossier.getApprenant().getEtatCivil().getNomDeNaissance(), dossier.getApprenant().getEtatCivil().getNomUsuel());
				} else {
					log.info("Anomalie lors de l'appel à la methode API : lireInscriptions pour le code apprenant : {} et etablissement : {} LE DOSSIER RECUPERE EST NULL", codeApprenant, etablissement);
				}
				return dossier;
			} catch (ApiException e) {
				if(e.getCode() == 500 && e.getResponseBody()!=null && e.getResponseBody().contains(APPRENANT_NON_TROUVE)) {
					log.warn("Apprenant non trouvé lors de l'appel à la methode API : lireInscriptions pour le code apprenant : {} et etablissement : {}", codeApprenant, etablissement);
				} else {
					log.error("Erreur lors de l'appel à la methode API : lireInscriptions pour le code apprenant : {} et etablissement : {} => ({}) message: {} body : {}", codeApprenant, etablissement,e.getCode(), e.getMessage(),e.getResponseBody(), e);
				}
			} catch (RuntimeException rex) {
				log.error("Erreur lors de l'appel à la methode API : lireInscriptions pour le code apprenant : {} et etablissement : {} => ",codeApprenant, etablissement,  rex);
			}
		}
		return null;
	}

	public List<CursusDCA> getCursus(String codeApprenant) {

		// Si les paramètres nécessaires sont valués
		if(StringUtils.hasText(etablissement) && StringUtils.hasText(codeApprenant)) {
			// Maj du token pour récupérer le dernier token valide
			insApiChc.getApiClient().setBearerToken(accessTokenService.getToken());
			try {
				//List<String> statutsInscription = List.of(Utils.STATUT_INSCRIPTION_VALIDE);
				// Appel de l'API Pégase
				//List<List<ObjetMaquetteExtension>> listObj = insApiChc.lireArbreCursusDesInscriptions(etablissement, codeApprenant, codePeriode, statutsInscription);
				List<CursusDCA> listObj = insApiChc.lireCusrsuApprenant(codeApprenant);
				if(listObj != null) {
					log.info("Cursus de {} recupéré: {} objets concernés", codeApprenant,listObj.size());
					log.debug("Cursus de : {}", listObj);
				} else {
					log.info("Anomalie lors de l'appel à la methode API : lireCusrsuApprenant pour le code apprenant : {} et etablissement : {}", codeApprenant, etablissement);
				}
				return listObj;
			} catch (ApiException e) {
				log.error("Erreur lors de l'appel à la methode API : lireCusrsuApprenant pour le code apprenant : {} et etablissement : {} => ({}) message: {} body : {}", codeApprenant, etablissement,e.getCode(), e.getMessage(),e.getResponseBody(),  e);
			} catch (RuntimeException rex) {
				log.error("Erreur lors de l'appel à la methode API : lireCusrsuApprenant pour le code apprenant : {} et etablissement : {} => ",codeApprenant, etablissement,  rex);
			}
		}
		return null;

	}

	public List<Chemin> getNotes(String codeApprenant, String codePeriode, String codeChemin) {
		// Si les paramètres nécessaires sont valués
		if(StringUtils.hasText(etablissement) && StringUtils.hasText(codePeriode) && StringUtils.hasText(codeApprenant)) {
			// Maj du token pour récupérer le dernier token valide
			pubApiCoc.getApiClient().setBearerToken(accessTokenService.getToken());
			try {
				// Appel de l'API Pégase
				List<Chemin> listObj = pubApiCoc.listerCursusPubliableApprenant(etablissement, codePeriode,codeApprenant, codeChemin);
				if(listObj != null) {
					log.info("Notes de {} recupéré: {} objets concernés", codeApprenant,listObj.size());
					log.debug("Notes de : {}", listObj);
				} else {
					log.info("Anomalie lors de l'appel à la methode API : listerCursusPubliableApprenant pour le code apprenant : {}, chemin {}, periode {} et etablissement : {}", codeApprenant, codeChemin, codePeriode, etablissement);
				}
				return listObj;
			} catch (ApiException e) {
				log.error("Erreur lors de l'appel à la methode API : listerCursusPubliableApprenant pour le code apprenant : {}, chemin {}, periode {} et etablissement : {} => ({}) message: {} body : {}", codeApprenant,codeChemin, codePeriode, etablissement, e.getCode(), e.getMessage(), e.getResponseBody(), e);
			} catch (RuntimeException rex) {
				log.error("Erreur lors de l'appel à la methode API : listerCursusPubliableApprenant pour le code apprenant : {}, chemin {}, periode {} et etablissement : {} => ",codeApprenant,codeChemin, codePeriode, etablissement,  rex);
			}
		}
		return null;

	}

	public File getPhoto(String codeApprenant, String cible, String codePeriode) {

		log.info("recuperePhoto codeApprenant : {} - cible : {} - periode : {}", codeApprenant, cible, codePeriode);

		// Si les paramètres nécessaires sont valués
		if(StringUtils.hasText(etablissement) && StringUtils.hasText(codeApprenant)
				&& StringUtils.hasText(cible)) {
			// Maj du token pour récupérer le dernier token valide
			insApiPieceExt.getApiClient().setBearerToken(accessTokenService.getToken());
			try {
				// Appel de l'API Pégase
				File photo = insApiPieceExt.visualiserPhoto(etablissement, codeApprenant, codePeriode, cible);

				if(photo != null) {
					log.info("Photo recuperee pour le code apprenant : {} et etablissement : {} et cible {} et codePeriode {}", codeApprenant, etablissement, cible, codePeriode);
				} else {
					log.info("Anomalie lors de l'appel à la methode API : recupererPiece pour le code apprenant : {} et etablissement : {} et cible {} et codePeriode {}", codeApprenant, etablissement, cible, codePeriode);
				}
				return photo;
			} catch (ApiException e) {
				// Erreur lors de la récupération de la photo. Un simple warning
				log.warn("Erreur (getPhoto) lors de l'appel à la methode API : recupererPiece pour le code apprenant : {} et etablissement : {} et cible {} et codePeriode {} => ({}) message: {} body : {}", codeApprenant, etablissement, cible, codePeriode, e.getCode(), e.getMessage(),e.getResponseBody());
			} catch (RuntimeException rex) {
				log.error("Erreur (getPhoto) lors de l'appel à la methode API : recupererPiece pour le code apprenant : {} et etablissement : {}  et cible {} et codePeriode {} => ",codeApprenant, etablissement, cible, codePeriode, rex);
			}
		}
		return null;
	}

	/*
	public File getPhoto(String codeApprenant, String cible) {

		log.info("recuperePhoto codeApprenant : {} - cible : {}", codeApprenant, cible);

		// Si les paramètres nécessaires sont valués
		if(StringUtils.hasText(etablissement) && StringUtils.hasText(codeApprenant)
			&& StringUtils.hasText(cible)) {
			// Maj du token pour récupérer le dernier token valide
			insApiInsExt.getApiClient().setBearerToken(accessTokenService.getToken());
			try {
				// Appel de l'API Pégase
				File photo = insApiInsExt.contenuPiece(etablissement, codeApprenant, cible, codePhoto);
				if(photo != null) {
					log.info("Photo de {} recupere", codeApprenant);
				} else {
					log.info("Anomalie lors de l'appel à la methode API : contenuPiece pour le code apprenant : {} et etablissement : {} et cible {} et codePhoto {}", codeApprenant, etablissement, codePhoto,cible);
				}
				return photo;
			} catch (ApiException e) {
				// Erreur lors de la récupération de la photo. Un simple warning
				log.warn("Erreur lors de l'appel à la methode API : contenuPiece pour le code apprenant : {} et etablissement : {} et cible {} et codePhoto {} => ({}) message: {} body : {}", codeApprenant, etablissement, cible, codePhoto,e.getCode(), e.getMessage(),e.getResponseBody());
			} catch (RuntimeException rex) {
				log.error("Erreur lors de l'appel à la methode API : contenuPiece pour le code apprenant : {} et etablissement : {}  et cible {} et codePhoto {} => ",codeApprenant, etablissement, cible, codePhoto, rex);
			}
		}
		return null;
	}*/


	public File getCertificatDeScolarite(String codeApprenant, String cible) {

		log.info("certificatDeScolarite codeApprenant : {} - cible : {}", codeApprenant, cible);

		// Maj du token pour récupérer le dernier token valide
		insApiIns.getApiClient().setBearerToken(accessTokenService.getToken());

		try {
			// Appel de l'API Pégase
			File certificat = insApiIns.imprimerCertificatDeScolarite(etablissement, codeApprenant, cible);
			if(certificat != null) {
				log.info("certificatDeScolarite OK");
			} else {
				log.info("Anomalie lors de l'appel à la methode API : certificatDeScolarite");
			}
			return certificat;
		} catch (ApiException e) {
			log.error("Erreur lors de l'appel à la methode API : certificatDeScolarite => ({}) message: {} body : {}",e.getCode(), e.getMessage(), e.getResponseBody(), e);
		} catch (RuntimeException rex) {
			log.error("Erreur lors de l'appel à la methode API : certificatDeScolarite pour le code apprenant : {} et etablissement : {}  et cible {} => ",codeApprenant, etablissement, cible, rex);
		}
		return null;
	}

	public File getAttestationDePaiement(String codeApprenant, String periode) {

		log.info("attestationDePaiement codeApprenant : {} - cible : {}", codeApprenant, periode);

		// Maj du token pour récupérer le dernier token valide
		insApiPai.getApiClient().setBearerToken(accessTokenService.getToken());

		try {
			// Appel de l'API Pégase
			File certificat = insApiPai.imprimerAttestationDePaiement(etablissement, codeApprenant, periode);
			if(certificat != null ) {
				log.info("attestationDePaiement OK :  {}", certificat.getName());
				return certificat;
			} else {
				log.info("Anomalie lors de l'appel à la methode API : attestationDePaiement");
			}
			return null;
		} catch (ApiException e) {
			log.error("Erreur lors de l'appel à la methode API : attestationDePaiement => ({}) message: {} body : {}",e.getCode(), e.getMessage(), e.getResponseBody(), e);
		} catch (RuntimeException rex) {
			log.error("Erreur lors de l'appel à la methode API : attestationDePaiement pour le code apprenant : {} et etablissement : {}  et periode {} => ",codeApprenant, etablissement, periode, rex);
		}
		return null;
	}

}
