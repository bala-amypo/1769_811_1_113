package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

private final SecretKey key =
Keys.hmacShaKeyFor(
"THIS_IS_A_256_BIT_SECRET_KEY_FOR_TESTS_1234".getBytes()
);

private final long validityMs = 3600000;

public JwtTokenProvider() {}

/* REQUIRED BY TESTS */
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
.setExpiration(new Date(System.currentTimeMillis() + validityMs))
.signWith(key, SignatureAlgorithm.HS256)
.compact();
}

/* REQUIRED BY TESTS */
public boolean validateToken(String token) {
try {
Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
return true;
} catch (Exception e) {
return false;
}
}

/* REQUIRED BY TESTS */
public String getUsernameFromToken(String token) {
return Jwts.parserBuilder()
.setSigningKey(key)
.build()
.parseClaimsJws(token)
.getBody()
.getSubject();
}
}
