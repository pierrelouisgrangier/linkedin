package com.raphlys.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		CsrfTokenRequestAttributeHandler handler = new CsrfTokenRequestAttributeHandler();
		http.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.csrfTokenRequestHandler(handler)
				.ignoringRequestMatchers(new AntPathRequestMatcher("/api/login"),
						new AntPathRequestMatcher("/api/logout")))
				.formLogin(form -> form.loginProcessingUrl("/api/login").permitAll()
						.successHandler((request, response, authentication) -> {
							response.setStatus(HttpServletResponse.SC_OK);
						})
						.failureHandler((request, response, exception) -> response
								.setStatus(HttpServletResponse.SC_UNAUTHORIZED)))
				.authorizeHttpRequests(
						authorizedHttpRequest -> authorizedHttpRequest.requestMatchers("/raph/all", "GET").permitAll()
								.requestMatchers("/error/**").permitAll().requestMatchers("/raph/**").authenticated())
				.userDetailsService(customUserDetailService)
				.logout(logout -> logout.logoutUrl("/api/logout").logoutSuccessHandler(
						(request, response, authentication) -> response.setStatus(HttpServletResponse.SC_OK)));

		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
