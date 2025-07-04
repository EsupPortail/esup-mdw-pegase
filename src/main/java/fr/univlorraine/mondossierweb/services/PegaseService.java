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
import fr.univlorraine.pegase.chc.api.CursusDcaApi;
import fr.univlorraine.pegase.chc.model.CursusDCA;
import fr.univlorraine.pegase.coc.api.NotesEtResultatsPubliablesApi;
import fr.univlorraine.pegase.coc.api.RelevesDeNotesPubliablesApi;
import fr.univlorraine.pegase.coc.model.Chemin;
import fr.univlorraine.pegase.coc.model.ReleveDeNotePublie;
import fr.univlorraine.pegase.idt.api.ApprenantApi;
import fr.univlorraine.pegase.idt.model.IdentiteApprenantSummary;
import fr.univlorraine.pegase.idt.model.PagedIdentiteApprenantSummaries;
import fr.univlorraine.pegase.ins.api.InscriptionApi;
import fr.univlorraine.pegase.insext.api.InscriptionsApi;
import fr.univlorraine.pegase.insext.model.ApprenantEtInscriptions;
import fr.univlorraine.pegase.pai.api.PaiApi;
import fr.univlorraine.pegase.pieceext.api.PiecesApi;
import fr.univlorraine.pegase.pieceext.model.StatutGlobalPieceParInscription;
import fr.univlorraine.pegase.pieceext.model.StatutGlobalPieceParInscriptionCommand;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


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
	private transient String apiIdtUrl;
	private transient String codeApprenantDemo;	
	private transient String codeApprenantTest;	
	private transient String codePeriodeTest;	
	private transient String codeCheminTest;

	@Autowired
	private transient ConfigController configController;

	// INS API
	private transient fr.univlorraine.pegase.ins.invoker.ApiClient apiClientIns = new fr.univlorraine.pegase.ins.invoker.ApiClient();
	private transient InscriptionApi apiIns = new InscriptionApi();
	// fluxInsApiIns en commentaire car non utilisé -> utilisation de insApiInsExt
	//private transient FluxInscriptionsApi fluxInsApiIns = new FluxInscriptionsApi();

	// INS-EXT API
	private transient fr.univlorraine.pegase.insext.invoker.ApiClient apiClientInsExt = new fr.univlorraine.pegase.insext.invoker.ApiClient();
	private transient InscriptionsApi apiInsExt = new InscriptionsApi();

	// PIECE-EXT API
	private transient fr.univlorraine.pegase.pieceext.invoker.ApiClient apiClientPieceExt = new fr.univlorraine.pegase.pieceext.invoker.ApiClient();
	private transient PiecesApi apiPieceExt = new PiecesApi();

	// PAI API
	private transient fr.univlorraine.pegase.pai.invoker.ApiClient apiClientPai = new fr.univlorraine.pegase.pai.invoker.ApiClient();
	private transient PaiApi apiPai = new PaiApi();

	// CHC API
	private transient fr.univlorraine.pegase.chc.invoker.ApiClient apiClientChc = new fr.univlorraine.pegase.chc.invoker.ApiClient();
	private transient CursusDcaApi apiChc = new CursusDcaApi();

	// COC API
	private transient fr.univlorraine.pegase.coc.invoker.ApiClient apiClientCoc = new fr.univlorraine.pegase.coc.invoker.ApiClient();
	private transient NotesEtResultatsPubliablesApi apiPubNotesCoc = new NotesEtResultatsPubliablesApi();
	private transient RelevesDeNotesPubliablesApi apiPubRelevesCoc = new RelevesDeNotesPubliablesApi();

	// IDT API
	private transient fr.univlorraine.pegase.idt.invoker.ApiClient apiClientIdt = new fr.univlorraine.pegase.idt.invoker.ApiClient();
	private transient ApprenantApi apiIdt = new ApprenantApi();

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
	private void setApiIdtUrl() {
		apiIdtUrl = configController.getApiIdtUrl();
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
		setApiIdtUrl();
		setCodePeriodeTest();
		setCodeApprenantTest();
		setCodeCheminTest();

		// Init INS
		apiClientIns.setBasePath(apiInsUrl);
		apiIns.setApiClient(apiClientIns);
		//fluxInsApiIns.setApiClient(apiClientIns);

		// Init INS-EXT
		apiClientInsExt.setBasePath(apiInsExtUrl);
		apiInsExt.setApiClient(apiClientInsExt);

		// Init PIECE-EXT
		apiClientPieceExt.setBasePath(apiPieceExtUrl);
		apiPieceExt.setApiClient(apiClientPieceExt);

		// Init PAI
		apiClientPai.setBasePath(apiPaiUrl);
		apiPai.setApiClient(apiClientPai);

		// Init CHC
		apiClientChc.setBasePath(apiChcUrl);
		apiChc.setApiClient(apiClientChc);

		// Init COC
		apiClientCoc.setBasePath(apiCocUrl);
		apiPubNotesCoc.setApiClient(apiClientCoc);
		apiPubRelevesCoc.setApiClient(apiClientCoc);

		// Init IDT
		apiClientIdt.setBasePath(apiIdtUrl);
		apiIdt.setApiClient(apiClientIdt);
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
				apiInsExt.getApiClient().setBearerToken(accessTokenService.getToken());
				// Appel de l'API Pégase
				ApprenantEtInscriptions dossier = apiInsExt.lireInscriptions(etablissement, codeApprenant);
				if(dossier != null) {
					log.info("Dossier de {} {} {} recupere", dossier.getApprenant().getEtatCivil().getPrenom(),dossier.getApprenant().getEtatCivil().getNomDeNaissance(), dossier.getApprenant().getEtatCivil().getNomUsuel());
				} else {
					log.info("Anomalie lors de l'appel à la methode API : lireInscriptions pour le code apprenant : {} et etablissement : {} LE DOSSIER RECUPERE EST NULL", codeApprenant, etablissement);
				}
				return dossier;
			} catch (fr.univlorraine.pegase.insext.invoker.ApiException e) {
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
			apiChc.getApiClient().setBearerToken(accessTokenService.getToken());
			try {
				//List<String> statutsInscription = List.of(Utils.STATUT_INSCRIPTION_VALIDE);
				// Appel de l'API Pégase
				//List<List<ObjetMaquetteExtension>> listObj = insApiChc.lireArbreCursusDesInscriptions(etablissement, codeApprenant, codePeriode, statutsInscription);
				List<CursusDCA> listObj = apiChc.lireCusrsuApprenant(codeApprenant);
				if(listObj != null) {
					log.info("Cursus de {} recupéré: {} objets concernés", codeApprenant,listObj.size());
					log.debug("Cursus de : {}", listObj);
				} else {
					log.info("Anomalie lors de l'appel à la methode API : lireCusrsuApprenant pour le code apprenant : {} et etablissement : {}", codeApprenant, etablissement);
				}
				return listObj;
			} catch (fr.univlorraine.pegase.chc.invoker.ApiException e) {
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
			apiPubNotesCoc.getApiClient().setBearerToken(accessTokenService.getToken());
			try {
				// Appel de l'API Pégase
				List<Chemin> listObj = apiPubNotesCoc.listerCursusPubliableApprenant(etablissement, codePeriode,codeApprenant, codeChemin);
				if(listObj != null) {
					log.info("Notes de {} recupéré: {} objets concernés", codeApprenant,listObj.size());
					log.debug("Notes de : {}", listObj);
				} else {
					log.info("Anomalie lors de l'appel à la methode API : listerCursusPubliableApprenant pour le code apprenant : {}, chemin {}, periode {} et etablissement : {}", codeApprenant, codeChemin, codePeriode, etablissement);
				}
				return listObj;
			} catch (fr.univlorraine.pegase.coc.invoker.ApiException e) {
				log.error("Erreur lors de l'appel à la methode API : listerCursusPubliableApprenant pour le code apprenant : {}, chemin {}, periode {} et etablissement : {} => ({}) message: {} body : {}", codeApprenant,codeChemin, codePeriode, etablissement, e.getCode(), e.getMessage(), e.getResponseBody(), e);
			} catch (RuntimeException rex) {
				log.error("Erreur lors de l'appel à la methode API : listerCursusPubliableApprenant pour le code apprenant : {}, chemin {}, periode {} et etablissement : {} => ",codeApprenant,codeChemin, codePeriode, etablissement,  rex);
			}
		}
		return null;

	}


	public List<ReleveDeNotePublie> getListeReleves(String codeApprenant, String codePeriode, String codeChemin) {
		// Si les paramètres nécessaires sont valués
		if(StringUtils.hasText(etablissement) && StringUtils.hasText(codePeriode) && StringUtils.hasText(codeApprenant)) {
			// Maj du token pour récupérer le dernier token valide
			apiPubRelevesCoc.getApiClient().setBearerToken(accessTokenService.getToken());
			try {
				// Appel de l'API Pégase
				List<ReleveDeNotePublie> listObj = apiPubRelevesCoc.listerReleveDeNotePubliableApprenant(etablissement, codePeriode,codeApprenant, codeChemin);
				if(listObj != null) {
					log.info("Relevés de notes {} recupérés: {} objets concernés", codeApprenant,listObj.size());
					log.debug("Relevés de notes de : {}", listObj);
				} else {
					log.info("Anomalie lors de l'appel à la methode API : listerReleveDeNotePubliableApprenant pour le code apprenant : {}, chemin {}, periode {} et etablissement : {}", codeApprenant, codeChemin, codePeriode, etablissement);
				}
				return listObj;
			} catch (fr.univlorraine.pegase.coc.invoker.ApiException e) {
				log.error("Erreur lors de l'appel à la methode API : listerReleveDeNotePubliableApprenant pour le code apprenant : {}, chemin {}, periode {} et etablissement : {} => ({}) message: {} body : {}", codeApprenant,codeChemin, codePeriode, etablissement, e.getCode(), e.getMessage(), e.getResponseBody(), e);
			} catch (RuntimeException rex) {
				log.error("Erreur lors de l'appel à la methode API : listerReleveDeNotePubliableApprenant pour le code apprenant : {}, chemin {}, periode {} et etablissement : {} => ",codeApprenant,codeChemin, codePeriode, etablissement,  rex);
			}
		}
		return null;

	}

	public File getReleveDeNote(String codeApprenant, String codeChemin, UUID uuidReleve) {
		// Si les paramètres nécessaires sont valués
		if(StringUtils.hasText(etablissement) && uuidReleve != null && StringUtils.hasText(codeApprenant)) {
			// Maj du token pour récupérer le dernier token valide
			apiPubRelevesCoc.getApiClient().setBearerToken(accessTokenService.getToken());
			try {
				// Appel de l'API Pégase
				File releve = apiPubRelevesCoc.genererRelevesDeNotesEtResultatsPubliableApprenant(uuidReleve,codeApprenant, codeChemin);
				if(releve != null) {
					log.info("Relevé de notes {} récupéré pour {} et cible {} ", uuidReleve, codeApprenant, codeChemin);
				} else {
					log.info("Anomalie lors de l'appel à la methode API : genererRelevesDeNotesEtResultatsPubliableApprenant pour le code apprenant : {}, chemin {}, et uuidReleve : {}", codeApprenant, codeChemin, uuidReleve);
				}
				return releve;
			} catch (fr.univlorraine.pegase.coc.invoker.ApiException e) {
				log.error("Erreur lors de l'appel à la methode API : genererRelevesDeNotesEtResultatsPubliableApprenant pour le code apprenant : {}, chemin {} et uuidReleve : {} => ({}) message: {} body : {}", codeApprenant,codeChemin, uuidReleve, e.getCode(), e.getMessage(), e.getResponseBody(), e);
			} catch (RuntimeException rex) {
				log.error("Erreur lors de l'appel à la methode API : genererRelevesDeNotesEtResultatsPubliableApprenant pour le code apprenant : {}, chemin {} et uuidReleve : {} => ",codeApprenant,codeChemin, uuidReleve,  rex);
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
			apiPieceExt.getApiClient().setBearerToken(accessTokenService.getToken());
			try {
				// Appel de l'API Pégase
				File photo = apiPieceExt.visualiserPhoto(etablissement, codeApprenant, codePeriode, cible);

				if(photo != null) {
					log.info("Photo recuperee pour le code apprenant : {} et etablissement : {} et cible {} et codePeriode {}", codeApprenant, etablissement, cible, codePeriode);
				} else {
					log.info("Anomalie lors de l'appel à la methode API : recupererPiece pour le code apprenant : {} et etablissement : {} et cible {} et codePeriode {}", codeApprenant, etablissement, cible, codePeriode);
				}
				return photo;
			} catch (fr.univlorraine.pegase.pieceext.invoker.ApiException e) {
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

	public UUID getUidApprenant(String codeApprenant) {
		log.info("GET UidApprenant codeApprenant : {} ", codeApprenant);

		// Maj du token pour récupérer le dernier token valide
		apiIdt.getApiClient().setBearerToken(accessTokenService.getToken());

		try {
			// Appel de l'API Pégase
			PagedIdentiteApprenantSummaries ids = apiIdt.rechercherIdentiteApprenant(etablissement, null, null, null,codeApprenant, null, null, null, null, null);
			if(ids != null && ids.getItems() != null && ids.getItems().size() == 1) {
				IdentiteApprenantSummary id = ids.getItems().get(0);
				if (id != null) {
					UUID uuid = UUID.fromString(id.getId());
					log.info("rechercherIdentiteApprenant OK {} => {}", codeApprenant, uuid);
					return uuid;
				}
				return null;
			} else {
				log.info("Anomalie lors de l'appel à la methode API : rechercherIdentiteApprenant");
			}
			return null;
		} catch (fr.univlorraine.pegase.idt.invoker.ApiException e) {
			log.error("Erreur lors de l'appel à la methode API : rechercherIdentiteApprenant => ({}) message: {} body : {}",e.getCode(), e.getMessage(), e.getResponseBody(), e);
		} catch (RuntimeException rex) {
			log.error("Erreur lors de l'appel à la methode API : rechercherIdentiteApprenant pour le code apprenant : {} et etablissement : {}   => ", codeApprenant, etablissement, rex);
		}
        return null;
	}

	public File getCertificatDeScolarite(UUID uidApprenant, String cible) {

		log.info("certificatDeScolarite codeApprenant : {} - cible : {}", uidApprenant, cible);

		// Maj du token pour récupérer le dernier token valide
		apiIns.getApiClient().setBearerToken(accessTokenService.getToken());

		try {
			// Appel de l'API Pégase
			File certificat = apiIns.genererCertificatDeScolarite(etablissement, uidApprenant, cible);
			if(certificat != null) {
				log.info("certificatDeScolarite OK");
			} else {
				log.info("Anomalie lors de l'appel à la methode API : certificatDeScolarite");
			}
			return certificat;
		} catch (fr.univlorraine.pegase.ins.invoker.ApiException e) {
			log.error("Erreur lors de l'appel à la methode API : certificatDeScolarite => ({}) message: {} body : {}",e.getCode(), e.getMessage(), e.getResponseBody(), e);
		} catch (RuntimeException rex) {
			log.error("Erreur lors de l'appel à la methode API : certificatDeScolarite pour le code apprenant : {} et etablissement : {}  et cible {} => ", uidApprenant, etablissement, cible, rex);
		}
		return null;
	}

	public File getAttestationDePaiement(String codeApprenant, String periode) {

		log.info("attestationDePaiement codeApprenant : {} - cible : {}", codeApprenant, periode);

		// Maj du token pour récupérer le dernier token valide
		apiPai.getApiClient().setBearerToken(accessTokenService.getToken());

		try {
			// Appel de l'API Pégase
			File attestationDePaiement = apiPai.imprimerAttestationDePaiement(etablissement, codeApprenant, periode);
			if(attestationDePaiement != null ) {
				log.info("attestationDePaiement OK :  {}", attestationDePaiement.getName());
				return attestationDePaiement;
			} else {
				log.info("Anomalie lors de l'appel à la methode API : attestationDePaiement");
			}
			return null;
		} catch (fr.univlorraine.pegase.pai.invoker.ApiException e) {
			log.error("Erreur lors de l'appel à la methode API : attestationDePaiement => ({}) message: {} body : {}",e.getCode(), e.getMessage(), e.getResponseBody(), e);
		} catch (RuntimeException rex) {
			log.error("Erreur lors de l'appel à la methode API : attestationDePaiement pour le code apprenant : {} et etablissement : {}  et periode {} => ",codeApprenant, etablissement, periode, rex);
		}
		return null;
	}

	public List<StatutGlobalPieceParInscription> getStatutPiece(String codeApprenant, String cible, String codePeriode) {

		log.info("recuperePhoto codeApprenant : {} - cible : {} - periode : {}", codeApprenant, cible, codePeriode);

		// Si les paramètres nécessaires sont valués
		if(StringUtils.hasText(etablissement) && StringUtils.hasText(codeApprenant)
				&& StringUtils.hasText(cible)) {
			// Maj du token pour récupérer le dernier token valide
			apiPieceExt.getApiClient().setBearerToken(accessTokenService.getToken());
			try {
				List<StatutGlobalPieceParInscriptionCommand> listparam = new ArrayList<>();
				StatutGlobalPieceParInscriptionCommand param = new StatutGlobalPieceParInscriptionCommand();
				param.setCodeApprenant(codeApprenant);
				param.setCodeChemin(cible);
				param.setCodePeriode(codePeriode);
				param.setSourceInscription(StatutGlobalPieceParInscriptionCommand.SourceInscriptionEnum.GESTION);
				listparam.add(param);
				// Appel de l'API Pégase
				List<StatutGlobalPieceParInscription> statuts = apiPieceExt.listerStatutGlobalPieceParInscription(etablissement, listparam);

				if(statuts != null) {
					log.info("Statuts des pieces recupere pour le code apprenant : {} et etablissement : {} et cible {} et codePeriode {}", codeApprenant, etablissement, cible, codePeriode);
				} else {
					log.info("Anomalie lors de l'appel à la methode API : listerStatutGlobalPieceParInscription pour le code apprenant : {} et etablissement : {} et cible {} et codePeriode {}", codeApprenant, etablissement, cible, codePeriode);
				}
				return statuts;
			} catch (fr.univlorraine.pegase.pieceext.invoker.ApiException e) {
				// Erreur lors de la récupération de la photo. Un simple warning
				log.warn("Erreur (getStatutPiece) lors de l'appel à la methode API : listerStatutGlobalPieceParInscription pour le code apprenant : {} et etablissement : {} et cible {} et codePeriode {} => ({}) message: {} body : {}", codeApprenant, etablissement, cible, codePeriode, e.getCode(), e.getMessage(),e.getResponseBody());
			} catch (RuntimeException rex) {
				log.error("Erreur (getStatutPiece) lors de l'appel à la methode API : listerStatutGlobalPieceParInscription pour le code apprenant : {} et etablissement : {}  et cible {} et codePeriode {} => ",codeApprenant, etablissement, cible, codePeriode, rex);
			}
		}
		return null;
	}
}
