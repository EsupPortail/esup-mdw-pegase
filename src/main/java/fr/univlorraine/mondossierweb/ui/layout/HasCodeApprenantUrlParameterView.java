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
package fr.univlorraine.mondossierweb.ui.layout;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import fr.univlorraine.mondossierweb.controllers.SessionController;
import fr.univlorraine.mondossierweb.services.SecurityService;
import fr.univlorraine.pegase.model.insext.ApprenantEtInscriptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("serial")
@Slf4j
public class HasCodeApprenantUrlParameterView extends VerticalLayout implements ApprenantView, LocaleChangeObserver, HasUrlParameter<String> {

	@Autowired
	private transient SecurityService securityService;

	@Autowired
	private transient SessionController sessionController;

	@Override
	public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String codeApprenant) {
		// Sécurisation de l'accès au dossier en paramètre
		if (securityService.secureAccess(codeApprenant)) {
			// Vérification que les informations nécessaires à la vue (dossier) ont été récupérées
			sessionController.checkDossier();
			// Mise à jour de l'affichage
			updateData(sessionController.getDossier() != null ? sessionController.getDossier() : null);
			//Force la maj des label
			localeChange(null);
		} else {
			updateData(null);
		}
	}

	@Override
	public void localeChange(LocaleChangeEvent event) {

	}

	@Override
	public void updateData(ApprenantEtInscriptions dossier) {

	}
}
