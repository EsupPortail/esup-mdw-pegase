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

import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * HttpSessionRequestCache that avoids saving internal framework requests.
 */
public class AppRequestCache extends HttpSessionRequestCache {

	/**
	 * {@inheritDoc}
	 * If the method is considered an internal request from the framework, we skip
	 * saving it.
	 * @see SecurityUtils#isFrameworkInternalRequest(HttpServletRequest)
	 */
	@Override
	public void saveRequest(final HttpServletRequest request, final HttpServletResponse response) {
		if (!SecurityUtils.isFrameworkInternalRequest(request)) {
			super.saveRequest(request, response);
		}
	}

}
