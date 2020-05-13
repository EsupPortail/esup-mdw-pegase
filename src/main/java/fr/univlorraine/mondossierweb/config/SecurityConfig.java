/**
 *
 *  ESUP-Portail MONDOSSIERWEB - Copyright (c) 2020 ESUP-Portail consortium
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

import javax.servlet.http.HttpSessionEvent;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.session.ChangeSessionIdAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;

import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.internal.RouteUtil;

import fr.univlorraine.mondossierweb.service.AppUserDetailsService;
import fr.univlorraine.mondossierweb.ui.view.error.AccessDeniedView;
import fr.univlorraine.mondossierweb.utils.security.AppRequestCache;
import fr.univlorraine.mondossierweb.utils.security.SecurityUtils;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/** URL permettant de prendre le rôle d'un autre utilisateur. */
	public static final String SWITCH_USER_URL = "/impersonate/login";
	/** URL permettant de quitter le rôle d'un autre utilisateur. */
	public static final String SWITCH_USER_EXIT_URL = "/impersonate/logout";
	/** URL lors d'un accès non autorisé. */
	private static final String ACCESS_DENIED_URL =
		'/' + RouteUtil.resolve(AccessDeniedView.class, AccessDeniedView.class.getAnnotationsByType(Route.class)[0]);
	/** URL permettant de se déconnecter. */
	public static final String LOGOUT_URL = "/logout";

	@Autowired
	private transient AppUserDetailsService userDetailsService;

	@Value("${app.url}")
	private transient String appUrl;
	@Value("${cas.url}")
	private transient String casUrl;
	@Value("${cas.key}")
	private transient String casKey;
	


	/**
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
	 */
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(casAuthenticationProvider());
	}

	/**
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.WebSecurity)
	 */
	@Override
	public void configure(final WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers(
				/* Vaadin Flow */
				"/VAADIN/**",

				/* Favicon */
				"/favicon.ico",
				"/favicon-*.png",

				/* Images */
				"/images/*");
	}

	/**
	 * Require login to access internal pages and configure login form.
	 */
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
			.exceptionHandling()
			.authenticationEntryPoint(casAuthenticationEntryPoint())
			.and()
			.sessionManagement()
			.sessionAuthenticationStrategy(sessionStrategy())
			.and()
			/* Enregistre la tentative d'accès pour redirection après connexion. */
			.requestCache()
			.requestCache(new AppRequestCache())
			.and()
			.authorizeRequests()
			/* Autorise les requêtes internes Vaadin Flow */
			.requestMatchers(SecurityUtils::isFrameworkInternalRequest)
			.permitAll()
			/* Autorise l'accès à la page indiquant un accès non autorisé */
			.antMatchers(ACCESS_DENIED_URL)
			.permitAll()
			/* Autorise l'usurpation de compte pour les admins */
			.antMatchers(SWITCH_USER_URL)
			.hasAuthority(SecurityUtils.ROLE_SUPERADMIN)
			.antMatchers(SWITCH_USER_EXIT_URL)
			.hasAuthority(SwitchUserFilter.ROLE_PREVIOUS_ADMINISTRATOR)
			.anyRequest()
			.authenticated()
			.and()
			.addFilter(casAuthenticationFilter())
			.addFilterBefore(singleSignOutFilter(), CasAuthenticationFilter.class)
			.addFilterBefore(logoutFilter(), LogoutFilter.class)
			.addFilterAfter(switchUserFilter(), FilterSecurityInterceptor.class)
			/* La protection Spring Security contre le Cross Scripting Request Forgery est désactivée, Vaadin implémente sa propre protection */
			.csrf()
			.disable()
			.headers()
			/* Autorise l'affichage en iFrame */
			.frameOptions()
			.disable()
			/* Supprime la gestion du cache du navigateur, pour corriger le bug IE de chargement des polices cf.
			 * http://stackoverflow.com/questions/7748140/font-face-eot-not-loading-over-https */
			.cacheControl()
			.disable()
			.and()
			.logout()
			.logoutSuccessUrl("/");
	}

	@Override
	public UserDetailsService userDetailsService() {
		return userDetailsService;
	}

	/**
	 * Spring Security Session Fixation Attack Protection.
	 * @see    https://docs.spring.io/spring-security/site/docs/5.2.2.RELEASE/reference/htmlsingle/#ns-session-fixation
	 * @return SessionAuthenticationStrategy
	 */
	@Bean
	public SessionAuthenticationStrategy sessionStrategy() {
		return new ChangeSessionIdAuthenticationStrategy();
	}

	/**
	 * @return Service CAS
	 */
	@Bean
	public ServiceProperties casServiceProperties() {
		final ServiceProperties serviceProperties = new ServiceProperties();
		serviceProperties.setService(appUrl + "/login/cas");
		serviceProperties.setSendRenew(false);
		return serviceProperties;
	}

	/**
	 * @return Serveur CAS
	 */
	@Bean
	public CasAuthenticationProvider casAuthenticationProvider() {
		final CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
		casAuthenticationProvider.setUserDetailsService(userDetailsService());
		casAuthenticationProvider.setServiceProperties(casServiceProperties());
		casAuthenticationProvider.setTicketValidator(new Cas20ServiceTicketValidator(casUrl));
		casAuthenticationProvider.setKey(casKey);
		return casAuthenticationProvider;
	}

	/**
	 * @return           Filtre CAS
	 * @throws Exception lors d'une erreur
	 */
	@Bean
	public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
		final CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
		casAuthenticationFilter.setAuthenticationManager(authenticationManager());
		casAuthenticationFilter.setSessionAuthenticationStrategy(sessionStrategy());
		casAuthenticationFilter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler(ACCESS_DENIED_URL));
		return casAuthenticationFilter;
	}

	/**
	 * @return Point d'entrée CAS
	 */
	@Bean
	public CasAuthenticationEntryPoint casAuthenticationEntryPoint() {
		final CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
		casAuthenticationEntryPoint.setLoginUrl(casUrl + "/login");
		casAuthenticationEntryPoint.setServiceProperties(casServiceProperties());
		return casAuthenticationEntryPoint;
	}

	/**
	 * @return Filtre de déconnexion CAS
	 */
	@Bean
	public SingleSignOutFilter singleSignOutFilter() {
		final SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
		singleSignOutFilter.setIgnoreInitConfiguration(true);
		singleSignOutFilter.setCasServerUrlPrefix(casUrl);
		return singleSignOutFilter;
	}

	/**
	 * @return Filtre requête de déconnexion CAS
	 */
	@Bean
	public LogoutFilter logoutFilter() {
		LogoutFilter logoutFilter = new LogoutFilter(
			casUrl + "/logout",
			new SecurityContextLogoutHandler(),
			new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY));
		logoutFilter.setFilterProcessesUrl(LOGOUT_URL);
		return logoutFilter;
	}

	/**
	 * @param  event sign out event
	 * @return
	 */
	@EventListener
	public SingleSignOutHttpSessionListener SingleSignOutHttpSessionListener(final HttpSessionEvent event) {
		return new SingleSignOutHttpSessionListener();
	}

	/**
	 * @return           Filtre permettant de prendre le rôle d'un autre utilisateur
	 * @throws Exception
	 *                       lors d'une erreur
	 */
	@Bean
	public SwitchUserFilter switchUserFilter() throws Exception {
		final SwitchUserFilter switchUserFilter = new SwitchUserFilter();
		switchUserFilter.setUserDetailsService(userDetailsService);
		switchUserFilter.setSwitchUserUrl(SWITCH_USER_URL);
		switchUserFilter.setExitUserUrl(SWITCH_USER_EXIT_URL);
		switchUserFilter.setTargetUrl("/");
		return switchUserFilter;
	}

	
	
	/*
	@Bean
	public LdapContextSource ldapServer() {
		LdapContextSource ldapContextSource = new LdapContextSource();
		String urls= ldapUrl;
		if(urls!=null && urls.contains(";")){
			ldapContextSource.setUrls(urls.split(";"));
		}else{
			ldapContextSource.setUrl(urls);
		}

		String userDn = ldapUserDn;
		if (userDn instanceof String && !userDn.isEmpty()) {
			ldapContextSource.setUserDn(userDn);
		}

		String password = ldapUserPwd;
		if (password instanceof String && !password.isEmpty()) {
			ldapContextSource.setPassword(password);
		}

		return ldapContextSource;
	}
	*/

	

}
