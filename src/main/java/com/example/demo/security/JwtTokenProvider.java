package com.example.demo.security;

import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import java.util.Date;

public class JwtTokenProvider {

private final String secret;
private final long expiration;

public JwtTokenProvider(String secret, long expiration) {
this.secret = secret;
this.expiration = expiration;
}

public String generateToken(Authentication auth, Long userId, String email, String role) {
return Jwts.builder()
.setSubject(email)
.claim("userId", userId)
.claim("role", role)
.setIssuedAt(new Date())
.setExpiration(new Date(System.currentTimeMillis() + expiration))
.signWith(SignatureAlgorithm.HS256, secret)
.compact();
}

public boolean validateToken(String token) {
try {
Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
return true;
} catch (Exception e) {
return false;
}
}

public String getUsernameFromToken(String token) {
return Jwts.parser()
.setSigningKey(secret)
.parseClaimsJws(token)
.getBody()
.getSubject();
}
}
