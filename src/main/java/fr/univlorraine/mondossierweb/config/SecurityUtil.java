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

import java.util.Arrays;
import java.util.Optional;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.HandlerHelper.RequestType;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.shared.ApplicationConstants;

import jakarta.servlet.http.HttpServletRequest;


public final class SecurityUtil {

	private SecurityUtil() {
	}

	/**
	 * Tests if the request is an internal framework request.
	 * @param  request {@link HttpServletRequest}
	 * @return         true if is an internal framework request. False otherwise.
	 */
	public static boolean isFrameworkInternalRequest(final HttpServletRequest request) {
		final String parameterValue = request.getParameter(ApplicationConstants.REQUEST_TYPE_PARAMETER);
		return parameterValue != null && Arrays.stream(RequestType.values())
			.map(RequestType::getIdentifier)
			.anyMatch(parameterValue::equals);
	}

	public static boolean isUserLoggedIn(final Authentication authentication) {
		return authentication != null
			&& authentication.isAuthenticated()
			&& !(authentication instanceof AnonymousAuthenticationToken);
	}

	public static Optional<SecurityContext> getSecurityContext(final UI ui) {
		return Optional.ofNullable(ui)
			.map(UI::getSession)
			.map(VaadinSession::getSession)
			.map(session -> session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY))
			.filter(SecurityContext.class::isInstance)
			.map(SecurityContext.class::cast);
	}

	public static Optional<Authentication> getAuthentication(final UI ui) {
		return getSecurityContext(ui).map(SecurityContext::getAuthentication);
	}

	public static Optional<String> getUsername(final UI ui) {
		return getAuthentication(ui).map(Authentication::getName);
	}

	public static <T extends UserDetails> Optional<T> getPrincipal(final UI ui, final Class<T> type) {
		return getAuthentication(ui).map(Authentication::getPrincipal)
			.filter(type::isInstance)
			.map(type::cast);
	}

	public static String getImpersonateUrl(final String username) {
		return SecurityConfig.SWITCH_USER_URL + '?' + SwitchUserFilter.SPRING_SECURITY_SWITCH_USERNAME_KEY + '=' + username;
	}

	private static boolean hasRole(final Authentication authentication, final String role) {
		return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch(e -> e.equals(role));
	}

	private static boolean hasRole(final UI ui, final String role) {
		return SecurityUtil.getAuthentication(ui).map(e -> SecurityUtil.hasRole(e, role)).orElse(false);
	}

	public static boolean isUserAdmin(final UI ui) {
		return hasRole(ui, Role.ADMIN);
	}

}

