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
package fr.univlorraine.mondossierweb.test.integration;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.test.context.TestComponent;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.web.support.WebTestUtils;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.filter.GenericFilterBean;

import fr.univlorraine.mondossierweb.model.user.entity.Utilisateur;

/**
 * Permet de simuler l'authentification d'un utilisateur via l'url à des fins de tests.
 */
@TestComponent
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestParameterUserFilter extends GenericFilterBean {

	/** username parameter. */
	public static final String USERNAME_PARAMETER = "username";
	/** authority parameter. */
	public static final String AUTHORITY_PARAMETER = "authority";

	/**
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;

		final String username = servletRequest.getParameter(USERNAME_PARAMETER);
		if (username != null && !username.isEmpty()) {
			final SecurityContextRepository securityContextRepository = WebTestUtils.getSecurityContextRepository(servletRequest);
			final HttpRequestResponseHolder requestResponseHolder = new HttpRequestResponseHolder(servletRequest, servletResponse);
			securityContextRepository.loadContext(requestResponseHolder);
			servletRequest = requestResponseHolder.getRequest();
			servletResponse = requestResponseHolder.getResponse();

			final String[] authorities = servletRequest.getParameterValues(AUTHORITY_PARAMETER);
			final SecurityContext securityContext = createSecurityContext(username, authorities);
			SecurityContextHolder.setContext(securityContext);
			securityContextRepository.saveContext(securityContext, servletRequest, servletResponse);
		}

		chain.doFilter(request, response);
	}

	/**
	 * @param  username    username
	 * @param  authorities roles
	 * @return             security context
	 */
	private SecurityContext createSecurityContext(final String username, final String... authorities) {
		final Collection<GrantedAuthority> grantedAuthorities = authorities == null ? List.of() : AuthorityUtils.createAuthorityList(authorities);
		Utilisateur principal = new Utilisateur();
		principal.setUsername(username);
		principal.getAuthorities().addAll(grantedAuthorities);
		final Authentication authentication = new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities());

		final SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
		securityContext.setAuthentication(authentication);
		return securityContext;
	}

}
