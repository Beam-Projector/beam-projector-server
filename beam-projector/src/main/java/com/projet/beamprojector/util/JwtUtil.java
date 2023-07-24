package com.projet.beamprojector.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtUtil {

	public static String getUserName(String token, String secretKey) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
			.getBody().get("userName", String.class);
	}

	public static boolean isExpired(String token, String secretKey) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
			.getBody()
			.getExpiration().before(new Date());
	}

	public static String createToken(String userName, String key,
		long expireTimeMs) {
		Claims claims = Jwts.claims();
		claims.put("userName", userName);

		log.info("JwtUtil userName : {}", userName);

		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + expireTimeMs))
			.signWith(SignatureAlgorithm.HS256, key)
			.compact()
			;
	}

}