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
package fr.univlorraine.mondossierweb.ui.view.error;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.NotFoundException;
import com.vaadin.flow.router.RouteNotFoundError;

import jakarta.servlet.http.HttpServletResponse;

/**
 * En cas de route non trouvée, renvoie vers la page d'accueil.
 * @author Adrien Colson
 */
@SuppressWarnings("serial")
public class RouteNotFoundErrorView extends RouteNotFoundError {

	private static final String TARGET_ROUTE = "";

	/**
	 * @see com.vaadin.flow.router.RouteNotFoundError#setErrorParameter(com.vaadin.flow.router.BeforeEnterEvent, com.vaadin.flow.router.ErrorParameter)
	 */
	@Override
	public int setErrorParameter(final BeforeEnterEvent event, final ErrorParameter<NotFoundException> parameter) {
		/* Redirige */
		event.rerouteTo(TARGET_ROUTE);

		/* Met à jour l'url */
		event.getUI().getPage().getHistory().replaceState(null, TARGET_ROUTE);

		/* Affiche une notification */
		Notification.show(getTranslation("error.routenotfound"));

		/* Renvoie le statut ok */
		return HttpServletResponse.SC_ACCEPTED;
	}

}
