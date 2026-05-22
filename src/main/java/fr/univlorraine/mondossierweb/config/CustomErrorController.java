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
package fr.univlorraine.mondossierweb.config;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@SuppressWarnings("serial")
public class CustomErrorController implements ErrorController {

	private static final String ERROR_PATH = "/error";

	/**
	 * Intercepte toutes les erreurs HTTP (erreurs non gérées par Vaadin)
	 * et redirige vers la vue d'erreur générique de l'application.
	 *
	 * @param request la requête HTTP
	 * @return redirection vers la route Vaadin "erreur"
	 */
	@RequestMapping(ERROR_PATH)
	public String handleError(final HttpServletRequest request) {
		final Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		final Object message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
		final Object exception = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

		final int statusCode = status != null ? Integer.parseInt(status.toString()) : HttpStatus.INTERNAL_SERVER_ERROR.value();

		log.warn("Erreur HTTP {} interceptée par CustomErrorController - message={}, exception={}",
			statusCode, message, exception);

		return "redirect:/" + fr.univlorraine.mondossierweb.ui.view.error.ErrorView.ROUTE;
	}

}
