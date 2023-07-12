package fr.univlorraine.mondossierweb.utils.logging;

import java.io.IOException;

import org.slf4j.MDC;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MDCAuthenticationFilter extends OncePerRequestFilter {

	private static final String USER = "user";

	@Override
	protected void doFilterInternal(
		final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain
	) throws ServletException, IOException {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails) {
			final UserDetails user = (UserDetails) authentication.getPrincipal();
			MDC.put(USER, user.getUsername());
			try {
				filterChain.doFilter(request, response);
			} finally {
				MDC.remove(USER);
			}
		} else {
			filterChain.doFilter(request, response);
		}
	}
}

