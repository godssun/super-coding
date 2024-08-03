package com.github.supercoding.config.security;

import com.github.supercoding.web.filters.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

	private final JwtTokenProvider jwtTokenProvider;

	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.headers(headers -> headers
						.frameOptions(frameOptions -> frameOptions.sameOrigin())
				)
				.formLogin(formLogin -> formLogin.disable())
				.csrf(csrf -> csrf.disable())
				.httpBasic(httpBasic -> httpBasic.disable())
				.rememberMe(rememberMe -> rememberMe.disable())
				.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}
