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
package fr.univlorraine.mondossierweb.controllers;

import com.vaadin.flow.server.VaadinSession;
import fr.univlorraine.mondossierweb.services.PegaseService;
import fr.univlorraine.mondossierweb.services.SecurityService;
import fr.univlorraine.mondossierweb.utils.Utils;
import fr.univlorraine.pegase.model.insext.ApprenantEtInscriptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Component
@Slf4j
public class SessionController {

	@Autowired
	private PegaseService pegaseService;

	@Autowired
	private SecurityService securityService;

	/**
	 * 
	 * @return Dossier en session si renseigné
	 */
	public ApprenantEtInscriptions getDossier() {
		if(VaadinSession.getCurrent().getAttribute(Utils.DOSSIER_APPRENANT)!=null) {
			return (ApprenantEtInscriptions) VaadinSession.getCurrent().getAttribute(Utils.DOSSIER_APPRENANT);
		}
		return null;
	}

	/**
	 * 
	 * @return Code apprenant du dossier en cours de consultation
	 */
	public String getCodeApprenant() {
		if(securityService != null) {
			Optional<String> codeEtudiant = securityService.getCodeEtudiant();
			// On a rien dans la Session Vaadin mais on a l'info dans le SecurityService (cas d'un étudiant qui vient de se connecter à l'application)
			if(getCodeApprenantEnSession() == null 
				&& codeEtudiant.isPresent()) {
				setCodeApprenantEnSession(codeEtudiant.get());
			}
		}
		// récupération du code apprenant de démo (le dossier par défaut)
		String codeApprenantDemo = pegaseService.getCodeApprenantDemo();
		// Si on a aucun codeApprenant en session
		if(getCodeApprenantEnSession() == null && StringUtils.hasText(codeApprenantDemo)
			&& securityService.isAccessGrantedForStudent(codeApprenantDemo)) {
				setCodeApprenantEnSession(codeApprenantDemo);
		}

		return getCodeApprenantEnSession();
	}

	private String getCodeApprenantEnSession() {
		if(VaadinSession.getCurrent().getAttribute(Utils.DOSSIER_CONSULTE_APPRENANT) != null) {
			return (String) VaadinSession.getCurrent().getAttribute(Utils.DOSSIER_CONSULTE_APPRENANT);
		}
		return null;
	}

	public void setCodeApprenantEnSession(String codeApprenant) {
		VaadinSession.getCurrent().setAttribute(Utils.DOSSIER_CONSULTE_APPRENANT, codeApprenant);
	}

	public void setDossierEnSession(ApprenantEtInscriptions dossier) {
		VaadinSession.getCurrent().setAttribute(Utils.DOSSIER_APPRENANT, dossier);
	}

	/**
	 * Met à jour les informations sur le dossier si nécessaire
	 */
	public void checkDossier() {
		// Si on n'a pas les informations sur l'étudiant consulté
		if(getDossier() == null || !getDossier().getApprenant().getCode().equals(getCodeApprenant())) {
			log.info("Mise à jour des données du dossier en session pour : {}", getCodeApprenant());
			// Met à jour les données du dossier en session
			setDossierEnSession(pegaseService.recupererDossierApprenant(getCodeApprenant()));
		}
	}

}
