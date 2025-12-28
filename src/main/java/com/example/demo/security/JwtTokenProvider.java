package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

/* 🔴 MUST be >= 256 bits */
private static final SecretKey KEY =
Keys.secretKeyFor(SignatureAlgorithm.HS256);

private static final long EXPIRATION =
1000 * 60 * 60; // 1 hour

/* 🔴 REQUIRED by tests */
public String generateToken(
Authentication authentication,
Long userId,
String email,
String role
) {

Date now = new Date();
Date expiry = new Date(now.getTime() + EXPIRATION);

return Jwts.builder()
.setSubject(email)
.claim("userId", userId)
.claim("role", role)
.setIssuedAt(now)
.setExpiration(expiry)
.signWith(KEY)
.compact();
}

/* 🔴 REQUIRED by tests */
public String getUsernameFromToken(String token) {
return getClaims(token).getSubject();
}

/* 🔴 REQUIRED by tests */
public boolean validateToken(String token) {
try {
getClaims(token);
return true;
} catch (Exception e) {
return false;
}
}

private Claims getClaims(String token) {
return Jwts.parserBuilder()
.setSigningKey(KEY)
.build()
.parseClaimsJws(token)
.getBody();
}
}
