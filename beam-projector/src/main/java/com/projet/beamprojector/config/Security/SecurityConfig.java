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

	private final String[] notAuthPaths = {
		"/**", //TODO 인증 들어온 이후 제거 해야 합니다. 임시로 모든 요청 들어가게 선언
		"/members/signin", "/members/signup", "/members/signup/confirm",
		"/members/check/id", "/members/check/email", "/members/reissue",
		"/v2/api-docs",
		"/swagger-resources",
		"/swagger-resources/**",
		"/configuration/ui",
		"/configuration/security",
		"/swagger-ui.html",
		"/webjars/**",
		"/v3/api-docs/**",
		"/swagger-ui/**",
		"/api-doc.html"
	};
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)
		throws Exception {
		http
			.httpBasic().disable()// 기본설정 비인증시 로그인폼으로 Redirect 옵션 끄기
			.csrf().disable()// csrf 보안 옵션끄기
			.authorizeRequests()
			.antMatchers(notAuthPaths).permitAll()
			.anyRequest().authenticated();
//			.formLogin().loginProcessingUrl("/api/login")
//			.logout().logoutUrl("/api/logout");

		// 커스텀 인증 제공자를 등록합니다.
//		http.authenticationProvider(customAuthenticationProvider);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
