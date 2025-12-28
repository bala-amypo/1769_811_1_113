package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

private final Key key;
private final long validityInMs;

/* 🔴 REQUIRED BY TESTS (NO-ARG CONSTRUCTOR) */
public JwtTokenProvider() {
this.key = Keys.hmacShaKeyFor(
"VerySecretKeyForJwtGeneration1234567890".getBytes()
);
this.validityInMs = 86400000; // 1 day
}

public String generateToken(
Authentication authentication,
Long userId,
String email,
String role
) {

Date now = new Date();
Date expiry = new Date(now.getTime() + validityInMs);

return Jwts.builder()
.setSubject(email)
.claim("userId", userId)
.claim("role", role)
.setIssuedAt(now)
.setExpiration(expiry)
.signWith(key, SignatureAlgorithm.HS256)
.compact();
}

public boolean validateToken(String token) {
try {
getClaims(token);
return true;
} catch (Exception e) {
return false;
}
}

public String getUsernameFromToken(String token) {
return getClaims(token).getSubject();
}

public String getRoleFromToken(String token) {
return getClaims(token).get("role", String.class);
}

private Claims getClaims(String token) {
return Jwts.parserBuilder()
.setSigningKey(key)
.build()
.parseClaimsJws(token)
.getBody();
}
}
