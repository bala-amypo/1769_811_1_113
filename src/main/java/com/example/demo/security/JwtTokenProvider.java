package com.example.demo.security;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

@Component
public class JwtTokenProvider {

private final SecretKey secretKey;
private final long expirationMillis;

public JwtTokenProvider() {
/*
 Default constructor values
 You can override these via application.properties if needed
*/
this.secretKey = Keys.hmacShaKeyFor(
"my-secret-key-my-secret-key-my-secret-key".getBytes()
);
this.expirationMillis = 86400000;
}

public JwtTokenProvider(String secretKey,long expirationMillis) {
this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes());
this.expirationMillis = expirationMillis;
}

public String generateToken(
Authentication authentication,
Long userId,
String email,
String role
) {

Date now = new Date();
Date expiryDate = new Date(now.getTime() + expirationMillis);

return Jwts.builder()
.setSubject(email)
.claim("userId",userId)
.claim("role",role)
.setIssuedAt(now)
.setExpiration(expiryDate)
.signWith(secretKey,SignatureAlgorithm.HS256)
.compact();
}

public String getUsernameFromToken(String token) {

Claims claims = Jwts.parserBuilder()
.setSigningKey(secretKey)
.build()
.parseClaimsJws(token)
.getBody();

return claims.getSubject();
}

public boolean validateToken(String token) {
try {
Jwts.parserBuilder()
.setSigningKey(secretKey)
.build()
.parseClaimsJws(token);
return true;
}
catch(Exception ex) {
return false;
}
}
}
