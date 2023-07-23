package com.projet.beamprojector.config.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final CustomAuthenticationProvider customAuthenticationProvider;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)
		throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/api/login").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin().loginProcessingUrl("/api/login")
			.and()
			.logout().logoutUrl("/api/logout");

		// 커스텀 인증 제공자를 등록합니다.
		http.authenticationProvider(customAuthenticationProvider);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
