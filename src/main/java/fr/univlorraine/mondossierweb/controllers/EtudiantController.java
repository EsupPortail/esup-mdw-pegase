package fr.univlorraine.mondossierweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vaadin.flow.server.VaadinSession;

import fr.univlorraine.mondossierweb.service.PegaseService;
import fr.univlorraine.pegase.model.insgestion.ApprenantEtInscriptions;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class EtudiantController {
	
	@Autowired
	private transient PegaseService pegaseService;
	
	
	public ApprenantEtInscriptions getDossier() {
		if(VaadinSession.getCurrent().getAttribute("dossierApprenant")!=null) {
			return (ApprenantEtInscriptions) VaadinSession.getCurrent().getAttribute("dossierApprenant");
		}
		return null;
	}
	
	public String getDossierConsulte() {
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
	}
	

}
