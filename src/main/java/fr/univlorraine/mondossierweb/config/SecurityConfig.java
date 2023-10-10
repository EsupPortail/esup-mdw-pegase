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

import org.apereo.cas.client.session.SingleSignOutFilter;
import org.apereo.cas.client.session.SingleSignOutHttpSessionListener;
import org.apereo.cas.client.validation.Cas30ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.util.UrlPathHelper;

import com.vaadin.flow.server.VaadinSession;

import fr.univlorraine.mondossierweb.services.AppUserDetailsService;
import fr.univlorraine.mondossierweb.utils.logging.MDCAuthenticationFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSessionEvent;

@Configuration
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

	/** URL permettant de prendre le rôle d'un autre utilisateur. */
	public static final String SWITCH_USER_URL = "/impersonate/login";
	/** URL permettant de quitter le rôle d'un autre utilisateur. */
	public static final String SWITCH_USER_EXIT_URL = "/impersonate/logout";
	/** URL permettant de se déconnecter. */
	public static final String LOGOUT_URL = "/logout";

	@Autowired
	private AppUserDetailsService appUserDetailsService;
	@Autowired
	private AuthenticationConfiguration configuration;


	@Value("${app.url}")
	private String appUrl;
	@Value("${cas.url}")
	private String casUrl;
	@Value("${cas.key}")
	private String casKey;
	


	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web.ignoring()
			/* Vaadin Flow */
			.requestMatchers("/VAADIN/static/**")
			/* Favicon */
			.requestMatchers("/favicon.ico")
			.requestMatchers("/favicon-*.png")
			/* Gestionnaire d'erreurs Spring */
			.requestMatchers("/error");

	}

	@Bean
	public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers(SecurityUtil::isFrameworkInternalRequest).permitAll()
				/* Autorise l'usurpation de compte pour les admins */
				.requestMatchers(new AntPathRequestMatcher(SWITCH_USER_URL)).hasAuthority(Role.SWITCH_USER)
				.requestMatchers(new AntPathRequestMatcher(SWITCH_USER_EXIT_URL)).hasAuthority(SwitchUserFilter.ROLE_PREVIOUS_ADMINISTRATOR)
				/* Les autres requêtes doivent être authentifiées */
				.anyRequest().authenticated())
			.httpBasic(Customizer.withDefaults());

		/* Configure les filtres */
		final AccessDeniedHandlerImpl accessDeniedHandler = new AccessDeniedHandlerImpl();
		accessDeniedHandler.setErrorPage("/unauthorized");
		http.exceptionHandling(exceptionHandling -> {
			exceptionHandling.authenticationEntryPoint(casAuthenticationEntryPoint())
				.accessDeniedHandler(accessDeniedHandler);
		}).addFilter(casAuthenticationFilter())
			.addFilterAfter(new MDCAuthenticationFilter(), CasAuthenticationFilter.class)
			.addFilterBefore(singleSignOutFilter(), CasAuthenticationFilter.class)
			.addFilterBefore(logoutFilter(), LogoutFilter.class);
			//.addFilterAfter(switchUserFilter(), AuthorizationFilter.class);

		/* La protection Spring Security contre le Cross Scripting Request Forgery est désactivée, Vaadin implémente sa propre protection */
		http.csrf(csrf -> csrf.disable());

		/* Autorise l'affichage en iFrame */
		http.headers((headers) -> headers.frameOptions(Customizer.withDefaults()));

		/* Renvoie vers la page d'accueil en cas de déconnexion */
		http.logout(logout -> logout.logoutSuccessUrl("/"));

		return http.build();
	}

	@Bean
	public ServiceProperties serviceProperties() {
		final ServiceProperties serviceProperties = new ServiceProperties();
		serviceProperties.setService(appUrl + "/login/cas");
		return serviceProperties;
	}

	@Bean
	public AuthenticationEntryPoint casAuthenticationEntryPoint() {
		final CasAuthenticationEntryPoint entryPoint = new CasAuthenticationEntryPoint();
		entryPoint.setLoginUrl(casUrl + "/login");
		entryPoint.setServiceProperties(serviceProperties());
		return entryPoint;
	}

	@Bean
	public AuthenticationProvider casAuthenticationProvider() {
		final CasAuthenticationProvider provider = new CasAuthenticationProvider();
		provider.setServiceProperties(serviceProperties());
		provider.setTicketValidator(new Cas30ServiceTicketValidator(casUrl));
		provider.setUserDetailsService(appUserDetailsService);
		provider.setKey(casKey);
		return provider;
	}

	@Bean
	AuthenticationManager authenticationManager() throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
		final CasAuthenticationFilter filter = new CasAuthenticationFilter();
		filter.setServiceProperties(serviceProperties());
		filter.setAuthenticationManager(authenticationManager());
		return filter;
	}

	/**
	 * N'enregistre pas les requêtes internes Vaadin.
	 */
	@Bean
	public RequestCache requestCache() {
		final HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
		requestCache.setRequestMatcher(request -> !SecurityUtil.isFrameworkInternalRequest(request));
		return requestCache;
	}

	/* Single Logout */

	@Bean
	public LogoutFilter logoutFilter() {
		final LogoutFilter logoutFilter = new LogoutFilter(
			casUrl + LOGOUT_URL,
			new SecurityContextLogoutHandler(),
			new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY),
			(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) -> VaadinSession.getAllSessions(request.getSession()).forEach(VaadinSession::close));
		logoutFilter.setFilterProcessesUrl(LOGOUT_URL);
		return logoutFilter;
	}

	@Bean
	public SingleSignOutFilter singleSignOutFilter() {
		final SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
		singleSignOutFilter.setIgnoreInitConfiguration(true);
		return singleSignOutFilter;
	}

	@EventListener
	public SingleSignOutHttpSessionListener singleSignOutHttpSessionListener(final HttpSessionEvent event) {
		return new SingleSignOutHttpSessionListener();
	}

	/* Filtre permettant de prendre le rôle d'un autre utilisateur */

	@Bean
	public SwitchUserFilter switchUserFilter() {
		final SwitchUserFilter filter = new SwitchUserFilter();
		filter.setUserDetailsService(appUserDetailsService);

		final RequestMatcher impersonateLoginMatcher = new AntPathRequestMatcher(SWITCH_USER_URL, null, true, new UrlPathHelper());
		filter.setSwitchUserMatcher(impersonateLoginMatcher);

		final RequestMatcher impersonateLogoutMatcher = new AntPathRequestMatcher(SWITCH_USER_EXIT_URL, null, true, new UrlPathHelper());
		filter.setExitUserMatcher(impersonateLogoutMatcher);

		/** TODO A modifier après résolution du bug --> https://github.com/spring-projects/spring-security/issues/12504 */
		filter.setSecurityContextRepository(new DelegatingSecurityContextRepository(new HttpSessionSecurityContextRepository(), new RequestAttributeSecurityContextRepository()));

		filter.setTargetUrl("/");
		return filter;
	}


}
