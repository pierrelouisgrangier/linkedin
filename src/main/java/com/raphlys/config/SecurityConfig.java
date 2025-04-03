package com.raphlys.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

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
				.ignoringRequestMatchers(new AntPathRequestMatcher("/login"),
						new AntPathRequestMatcher("/logout")))
		.cors(cors -> cors.configurationSource(request -> {
			CorsConfiguration config = new CorsConfiguration();
			config.setAllowedOrigins(List.of("http://localhost:4200"));
			config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
			config.setAllowedHeaders(List.of("Authorization", "Content-Type", "X-XSRF-TOKEN", "X-Requested-With"));
			config.setAllowCredentials(true);
			return config;
		})).formLogin(form -> form.successHandler((request, response, authentication) -> {
					((CsrfToken) request.getAttribute(CsrfToken.class.getName())).getToken();
					response.setStatus(HttpServletResponse.SC_OK);
				})
				.failureHandler((request, response, exception) -> response
						.setStatus(HttpServletResponse.SC_UNAUTHORIZED)))
				.authorizeHttpRequests(authorizedHttpRequest -> authorizedHttpRequest
						.requestMatchers("/raph/all", "GET").permitAll().requestMatchers("/error/**").permitAll().requestMatchers("/raph/**").authenticated())
				.userDetailsService(customUserDetailService).logout(logout -> logout.logoutUrl("/logout").logoutSuccessHandler(
						(request, response, authentication) -> response.setStatus(HttpServletResponse.SC_OK)));

		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
