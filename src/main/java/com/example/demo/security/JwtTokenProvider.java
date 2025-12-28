package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenProvider {

private final Key key =
Keys.hmacShaKeyFor("test-secret-key-test-secret-key".getBytes());

private final long validityMs = 3600000;

public JwtTokenProvider() {}

public String generateToken(
Authentication authentication,
Long userId,
String email,
String role
) {

Date now = new Date();
Date expiry = new Date(now.getTime() + validityMs);

return Jwts.builder()
.setSubject(email)
.claim("userId", userId)
.claim("role", role)
.setIssuedAt(now)
.setExpiration(expiry)
.signWith(key, SignatureAlgorithm.HS256)
.compact();
}

public String generateToken(
Map<String,Object> claims,
String subject
) {

Date now = new Date();
Date expiry = new Date(now.getTime() + validityMs);

return Jwts.builder()
.setClaims(claims)
.setSubject(subject)
.setIssuedAt(now)
.setExpiration(expiry)
.signWith(key, SignatureAlgorithm.HS256)
.compact();
}

public boolean validateToken(String token) {
try {
Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
return true;
} catch (Exception e) {
return false;
}
}

public String getUsernameFromToken(String token) {
return Jwts.parserBuilder()
.setSigningKey(key)
.build()
.parseClaimsJws(token)
.getBody()
.getSubject();
}

/* REQUIRED BY FILTER */
public String getUsername(String token) {
return getUsernameFromToken(token);
}

public boolean isTokenValid(String token, String username) {
try {
return username.equals(getUsernameFromToken(token));
} catch (Exception e) {
return false;
}
}
}
