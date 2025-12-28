package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.Authentication;

import java.security.Key;
import java.util.Date;

public class JwtTokenProvider {

private final Key key;
private final long validityInMs;

public JwtTokenProvider(String secret, long validityInMs) {
this.key = Keys.hmacShaKeyFor(secret.getBytes());
this.validityInMs = validityInMs;
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

public String getUsernameFromToken(String token) {
return getClaims(token).getSubject();
}

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
.setSigningKey(key)
.build()
.parseClaimsJws(token)
.getBody();
}
}
