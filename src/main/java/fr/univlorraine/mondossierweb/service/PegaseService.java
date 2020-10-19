/**
 *
 *  ESUP-Portail MONDOSSIERWEB - Copyright (c) 2020 ESUP-Portail consortium
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
package fr.univlorraine.mondossierweb.service;

import java.io.File;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fr.univlorraine.pegase.api.ApiClient;
import fr.univlorraine.pegase.api.ApiException;
import fr.univlorraine.pegase.api.insgestion.ApprenantsApi;
import fr.univlorraine.pegase.api.insgestion.InscriptionsApi;
import fr.univlorraine.pegase.model.insgestion.Apprenant;
import fr.univlorraine.pegase.model.insgestion.ApprenantEtInscriptions;
import fr.univlorraine.pegase.model.insgestion.Inscriptions;
import fr.univlorraine.pegase.model.insgestion.StatutInscriptionVoeu;
import fr.univlorraine.pegase.model.insgestion.StatutPaiementVoeu;
import fr.univlorraine.pegase.model.insgestion.StatutPiecesVoeu;
import fr.univlorraine.pegase.model.insgestion.TriInscription;
import lombok.extern.slf4j.Slf4j;


@Service
@SuppressWarnings("serial")
@Slf4j
public class PegaseService implements Serializable {

	@Autowired
	private transient AccessTokenService accessTokenService;	

	@Value("${pegase.etablissement}")
	private transient String etablissement;	
	@Value("${pegase.api.ins.url}")
	private transient String apiInsUrl;	
	@Value("${pegase.photo.code}")
	private transient String codePhoto;	
	@Value("${pegase.demo.codeapprenant}")
	private transient String codeApprenantDemo;	
	//private transient String codeCibleDemo="F-ING-BIOSC→FING-BIOSC@PER-2019";	


	private ApiClient apiClientIns = new ApiClient();
	private ApprenantsApi appApiIns = new ApprenantsApi();
	private InscriptionsApi insApiIns = new InscriptionsApi();

	@PostConstruct
	public void init() {
		apiClientIns.setBasePath(apiInsUrl);
		insApiIns.setApiClient(apiClientIns);
		appApiIns.setApiClient(apiClientIns);
	}

	/**
	 * méthode de test qui liste les inscriptions validées dans Pégase
	 */
	public void listerInscriptionsValidees() {
		// Maj du token pour récupérer le dernier token valide
		insApiIns.getApiClient().setAccessToken(accessTokenService.getToken());

		// Préparation des paramètres
		List<StatutInscriptionVoeu> statutsInscription = new LinkedList<StatutInscriptionVoeu> ();
		statutsInscription.add(StatutInscriptionVoeu.VALIDE);
		//List<StatutInscriptionVoeu> statutsInscription = null;
		List<StatutPiecesVoeu> statutsPieces = null;
		List<StatutPaiementVoeu> statutsPaiement = null;
		List<TriInscription> tri = null;
		String recherche = null;

		try {
			// Appel de l'API Pégase
			Inscriptions response = insApiIns.listerInscriptionsValidees(etablissement, statutsInscription, statutsPieces, statutsPaiement, tri, recherche);
			if(response != null) {
				log.info("{} listerInscriptionsValidees", response.getNombre());
			} else {
				log.info("Anomalie lors de l'appel à la methode API : listerInscriptionsValidees");
			}
		} catch (ApiException e) {
			log.error("Erreur lors de l'appel à la methode API : listerInscriptionsValidees ",e);
		}

	}

	public ApprenantEtInscriptions recupererDossierApprenant(String codeApprenant) {

		// Si on a aucun codeApprenant en paramètre et qu'on a paramétré un code démo.
		if(!StringUtils.hasText(codeApprenant) && StringUtils.hasText(codeApprenantDemo)) {
			codeApprenant = codeApprenantDemo;
		}

		// Si les paramètres nécessaires sont valués
		if(StringUtils.hasText(etablissement) && StringUtils.hasText(codeApprenant)) {
			// Maj du token pour récupérer le dernier token valide
			insApiIns.getApiClient().setAccessToken(accessTokenService.getToken());
			try {
				// Appel de l'API Pégase
				ApprenantEtInscriptions dossier = insApiIns.lireInscriptions(etablissement, codeApprenant);
				if(dossier != null) {
					log.info("Dossier de {} {} recupere", dossier.getApprenant().getEtatCivil().getPrenom(), dossier.getApprenant().getEtatCivil().getNomUsuel());
				} else {
					log.info("Anomalie lors de l'appel à la methode API : lireInscriptions pour le code apprenant : {} et etablissement : {}", codeApprenant, etablissement);
				}
				return dossier;
			} catch (ApiException e) {
				log.error("Erreur lors de l'appel à la methode API : lireInscriptions pour le code apprenant : {} et etablissement : {}", codeApprenant, etablissement, e);
			}
		}
		return null;
	}
	
	public File recuperePhoto(String codeApprenant, String cible) {

		log.info("recuperePhoto codeApprenant : {} - cible : {}", codeApprenant, cible);
		
		// Si les paramètres nécessaires sont valués
		if(StringUtils.hasText(etablissement) && StringUtils.hasText(codeApprenant)
			&& StringUtils.hasText(cible)) {
			// Maj du token pour récupérer le dernier token valide
			insApiIns.getApiClient().setAccessToken(accessTokenService.getToken());
			try {
				// Appel de l'API Pégase
				File photo = insApiIns.contenuPiece(etablissement, codeApprenant, cible, codePhoto);
				if(photo != null) {
					log.info("Photo de {} recupere", codeApprenant);
				} else {
					log.info("Anomalie lors de l'appel à la methode API : contenuPiece pour le code apprenant : {} et etablissement : {} et cible {} et codePhoto {}", codeApprenant, etablissement, codePhoto,cible);
				}
				return photo;
			} catch (ApiException e) {
				log.error("Erreur lors de l'appel à la methode API : contenuPiece pour le code apprenant : {} et etablissement : {} et cible {} et codePhoto {}", codeApprenant, etablissement, cible, codePhoto, e);
			}
		}
		return null;
	}


	public File certificatDeScolarite(String codeApprenant, String cible) {

		log.info("certificatDeScolarite codeApprenant : {} - cible : {}", codeApprenant, cible);

		// Maj du token pour récupérer le dernier token valide
		insApiIns.getApiClient().setAccessToken(accessTokenService.getToken());

		try {
			// Appel de l'API Pégase
			File certificat = insApiIns.imprimerCertificatDeScolarite(etablissement, codeApprenant, cible);
			if(certificat != null) {
				log.info("{} certificatDeScolarite OK");
			} else {
				log.info("Anomalie lors de l'appel à la methode API : certificatDeScolarite");
			}
			return certificat;
		} catch (ApiException e) {
			log.error("Erreur lors de l'appel à la methode API : certificatDeScolarite ",e);
		}
		return null;
	}

	public File attestationDePaiement(String codeApprenant, String cible) {

		log.info("attestationDePaiement codeApprenant : {} - cible : {}", codeApprenant, cible);

		// Maj du token pour récupérer le dernier token valide
		insApiIns.getApiClient().setAccessToken(accessTokenService.getToken());

		try {
			// Appel de l'API Pégase
			File certificat = insApiIns.imprimerAttestationDePaiement(etablissement, codeApprenant, cible);
			if(certificat != null) {
				log.info("{} attestationDePaiement OK");
			} else {
				log.info("Anomalie lors de l'appel à la methode API : attestationDePaiement");
			}
			return certificat;
		} catch (ApiException e) {
			log.error("Erreur lors de l'appel à la methode API : attestationDePaiement ",e);
		}
		return null;
	}



	/**
	 * méthode de test qui lit un apprenant
	 */
	@Deprecated
	public void lireApprenant() {
		// Maj du token pour récupérer le dernier token valide
		appApiIns.getApiClient().setAccessToken(accessTokenService.getToken());

		try {
			// Appel de l'API Pégase
			Apprenant response = appApiIns.lireApprenant(etablissement, codeApprenantDemo);
			if(response != null) {
				log.info("{} lireApprenant", response.getEtatCivil().getNomUsuel());
			} else {
				log.info("Anomalie lors de l'appel à la methode API : lireApprenant");
			}
		} catch (ApiException e) {
			log.error("Erreur lors de l'appel à la methode API : lireApprenant ",e);
		}

	}

}
