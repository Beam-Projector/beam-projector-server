package com.projet.beamprojector.util;

import com.projet.beamprojector.domain.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtUtil {

	public static String getUserName(String token, String secretKey) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
			.getBody().get("memberId", String.class);
	}

	public static boolean isExpired(String token, String secretKey) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
			.getBody()
			.getExpiration().before(new Date());
	}

	public static String createToken(Member member, String key,
		long expireTimeMs) {
		Claims claims = Jwts.claims();
		claims.put("memberId", member.getMemberId());
		claims.put("email", member.getMember());
		claims.put("name", member.getName());
		claims.put("nickName", member.getNickName());
		claims.put("profileUrl", member.getProfileImageUrl());

		log.info("JwtUtil userName : {}", member.getMemberId());

		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + expireTimeMs))
			.signWith(SignatureAlgorithm.HS256, key)
			.compact()
			;
	}

}
