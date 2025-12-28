package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
private final long validityMs = 3600000;

public String generateToken(Authentication auth, Long id, String email, String role) {

Date now = new Date();
Date expiry = new Date(now.getTime() + validityMs);

return Jwts.builder()
.setSubject(email)
.claim("userId", id)
.claim("role", role)
.setIssuedAt(now)
.setExpiration(expiry)
.signWith(key)
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
}
