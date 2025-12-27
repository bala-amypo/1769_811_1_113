package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

private final Key key;
private final long expiration;

public JwtTokenProvider() {
this.key = Keys.hmacShaKeyFor(
"MyVerySecretKeyForJwt123456789012345".getBytes()
);
this.expiration = 60000L;
}

public String generateToken(
Authentication authentication,
Long userId,
String email,
String role
) {

return Jwts.builder()
.setSubject(email)
.claim("userId", userId)
.claim("role", role)
.setIssuedAt(new Date())
.setExpiration(new Date(System.currentTimeMillis() + expiration))
.signWith(key, SignatureAlgorithm.HS256)
.compact();
}

public String getUsernameFromToken(String token) {
return Jwts.parserBuilder()
.setSigningKey(key)
.build()
.parseClaimsJws(token)
.getBody()
.getSubject();
}

public Claims getClaims(String token) {
return Jwts.parserBuilder()
.setSigningKey(key)
.build()
.parseClaimsJws(token)
.getBody();
}

public boolean validateToken(String token) {
try {
getClaims(token);
return true;
} catch (Exception e) {
return false;
}
}
}
