package com.projet.beamprojector.config.Security;

import com.projet.beamprojector.member.service.MemberService;
import com.projet.beamprojector.util.JwtUtil;
import java.io.IOException;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

	private final MemberService memberService;
	private final String key;

	@Override
	protected void doFilterInternal(HttpServletRequest request,
		HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {

		final String authorization = request.getHeader(
			HttpHeaders.AUTHORIZATION);
		log.info("authorization : {}", authorization);

		// token을 보냈는지 확인
		if (authorization == null || !authorization.startsWith("Bearer ")) {

			log.info("authorization을 잘못 보냈습니다.");
			filterChain.doFilter(request, response);
			return;
		}

		// token 꺼내기
		String token = authorization.split(" ")[1];

		// Token Expired 되었는지 확인
		if (JwtUtil.isExpired(token, key)) {
			log.info("Token이 만료 되었습니다.");
			filterChain.doFilter(request, response);
			return;
		}

		// UserName에서 Token 꺼내기
		String userName = JwtUtil.getUserName(token, key);
		log.info("userName:{}", userName);

		UsernamePasswordAuthenticationToken authenticationToken =
			new UsernamePasswordAuthenticationToken(userName, token,
				List.of(new SimpleGrantedAuthority("USER")));

		authenticationToken.setDetails(
			new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext()
			.setAuthentication(authenticationToken);
		filterChain.doFilter(request, response);

	}
}

