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
package fr.univlorraine.mondossierweb.utils.security;

import java.util.stream.Stream;

import com.vaadin.flow.server.HandlerHelper.RequestType;
import com.vaadin.flow.shared.ApplicationConstants;

import jakarta.servlet.http.HttpServletRequest;

/**
 * SecurityUtils takes care of all such static operations that have to do with
 * security and querying rights from different beans of the UI.
 */
public final class SecurityUtils {
	
	public static final String ROLE_ETUDIANT = "ROLE_ETUDIANT";
	public static final String ROLE_GESTIONNAIRE = "ROLE_GESTIONNAIRE";
	public static final String ROLE_SUPERADMIN = "ROLE_SUPERADMIN";

	private SecurityUtils() {
		/* Util methods only */
	}

	/**
	 * Tests if the request is an internal framework request. The test consists of
	 * checking if the request parameter is present and if its value is consistent
	 * with any of the request types know.
	 * @param  request {@link HttpServletRequest}
	 * @return         true if is an internal framework request. False otherwise.
	 */
	public static boolean isFrameworkInternalRequest(final HttpServletRequest request) {
		final String parameterValue = request.getParameter(ApplicationConstants.REQUEST_TYPE_PARAMETER);
		return parameterValue != null
			&& Stream.of(RequestType.values()).anyMatch(r -> r.getIdentifier().equals(parameterValue));
	}

}
