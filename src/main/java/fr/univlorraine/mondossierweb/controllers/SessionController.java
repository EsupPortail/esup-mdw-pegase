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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.UIScope;

import fr.univlorraine.mondossierweb.service.PegaseService;
import fr.univlorraine.mondossierweb.service.SecurityService;
import fr.univlorraine.mondossierweb.ui.layout.MainLayout;
import fr.univlorraine.pegase.model.insgestion.ApprenantEtInscriptions;
import lombok.extern.slf4j.Slf4j;

@Component
@UIScope
@Slf4j
public class SessionController {

	
	@Autowired
	private transient PegaseService pegaseService;
	
	@Autowired
	private transient SecurityService securityService;

	// Layout permettant la maj du menu latéral lors de la récupération dossier (cf méthode checkDossier)
	private MainLayout mainLayout;
	
	public void setMainLayout(MainLayout mainLayout) {
		this.mainLayout = mainLayout;
	}
	
	/**
	 * 
	 * @return Dossier en session si renseigné
	 */
	public ApprenantEtInscriptions getDossier() {
		if(VaadinSession.getCurrent().getAttribute("dossierApprenant")!=null) {
			return (ApprenantEtInscriptions) VaadinSession.getCurrent().getAttribute("dossierApprenant");
		}
		return null;
	}
	
	/**
	 * 
	 * @return Code apprenant du dossier en cours de consultation
	 */
	public String getDossierConsulte() {
		// On a rien dans la Session Vaadin mais on a l'info dans le SecurityService (cas d'un étudiant qui vient de se connecter à l'application)
		if(VaadinSession.getCurrent().getAttribute("codeApprenant")==null && securityService.getCodeEtudiant().isPresent()) {
			setDossierConsulte(securityService.getCodeEtudiant().get());
		}
		if(VaadinSession.getCurrent().getAttribute("codeApprenant")!=null) {
			return (String) VaadinSession.getCurrent().getAttribute("codeApprenant");
		} 
		
		return null;
	}
	
	public void setDossierConsulte(String codeApprenant) {
		VaadinSession.getCurrent().setAttribute("codeApprenant", codeApprenant);
	}

	public void setDossier(ApprenantEtInscriptions dossier) {
		VaadinSession.getCurrent().setAttribute("dossierApprenant", dossier);
	}
	
	/**
	 * Met à jour les informations sur le dossier si nécessaire
	 */
	public void checkDossier() {
		// Si on n'a pas les informations sur l'étudiant consulté
		if(getDossier() == null || !getDossier().getApprenant().getCode().equals(getDossierConsulte())) {
			log.info("Mise à jour des données du dossier en session pour : {}", getDossierConsulte());
			// Met à jour les données du dossier en session
			setDossier(pegaseService.recupererDossierApprenant(getDossierConsulte()));
		}
		// Maj du nom/prenom dans le menu latéral
		if(mainLayout!=null) {
			mainLayout.updateData(getDossier()!=null ? getDossier().getApprenant() : null);
		}
	}


	

	

}