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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;

import fr.univlorraine.mondossierweb.services.SecurityService;
import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("serial")
@Slf4j
public class HasCodeEtuUrlParameterView extends Div implements HasUrlParameter<String> {

	@Autowired
	private transient SecurityService securityService;
	
	@Override
	public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String codetu) {
		if(StringUtils.hasText(codetu)) {
			// Si l'utilisateur est autorisé à accéder au dossier en paramètre
			if(securityService.isAccessGrantedForStudent(codetu)) {
				securityService.setDossierConsulte(codetu);
				log.info(getTranslation("action.acces.dossier", codetu));
			} else {
				Notification.show(getTranslation("error.accesdossierrefuse"));
			}
		}
	}
}
