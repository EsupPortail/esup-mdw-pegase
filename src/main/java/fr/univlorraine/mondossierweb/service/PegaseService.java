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

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fr.univlorraine.pegase.api.ApiClient;
import fr.univlorraine.pegase.api.ApiException;
import fr.univlorraine.pegase.api.insgestion.ApprenantsApi;
import fr.univlorraine.pegase.api.insgestion.InscriptionsApi;
import fr.univlorraine.pegase.model.insgestion.Apprenant;
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
	 * méthode de test qui liste les inscription validée dans Pégase
	 */
	public void listerInscriptionsValidees() {
		
		insApiIns.getApiClient().setAccessToken(accessTokenService.getToken());
		
		List<StatutInscriptionVoeu> statutsInscription = new LinkedList<StatutInscriptionVoeu> ();
		statutsInscription.add(StatutInscriptionVoeu.VALIDE);
		
		List<StatutPiecesVoeu> statutsPieces = null;
		List<StatutPaiementVoeu> statutsPaiement = null;
		List<TriInscription> tri = null;
		String recherche = null;
		
		try {
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
	
	/**
	 * méthode de test qui lit un apprenant
	 */
	@Deprecated
	public void lireApprenant() {
		
		appApiIns.getApiClient().setAccessToken(accessTokenService.getToken());
		
		try {
			Apprenant response = appApiIns.lireApprenant(etablissement, "000000001");
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
