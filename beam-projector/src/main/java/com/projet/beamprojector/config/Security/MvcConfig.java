package com.projet.beamprojector.config.Security;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addArgumentResolvers(
		List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new TokenMemberIdArgumentResolver());
	}
}
