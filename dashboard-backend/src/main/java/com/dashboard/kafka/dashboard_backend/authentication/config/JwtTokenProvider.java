package com.dashboard.kafka.dashboard_backend.authentication.config;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;

@Component
public class JwtTokenProvider {
	
	private String jwtSecret="";
	private long jwtExpirationDate=3600000;
	
	public String generateToken(Authentication authentication) {
		
		String username=authentication.getName();
		Date currentDate=new Date();
		
		Date expirationDate=new Date(currentDate.getTime()+jwtExpirationDate);
		
		String token=Jwts.builder().subject(username).issuedAt(new Date())
				.expiration(expirationDate).signWith(key(),SignatureAlgorithm.HS256).compact();
		
		return token;
	}
	
	
	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}
	
	
	public String getUserName(String token) {
		
		return Jwts.parser().verifyWith((SecretKey)key())
				.build().parseSignedClaims(token).getPayload().getSubject();
	}
	
	public boolean validateToken(String token) {
		 Jwts.parser().verifyWith((SecretKey) key()).build().parse(token);
		return true;
	}

}
