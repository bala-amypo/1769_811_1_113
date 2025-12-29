package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final String DEFAULT_SECRET =
            "my-test-secret-key-my-test-secret-key-123456"; // >= 256 bits

    private SecretKey key;
    private long expirationMs;

    
    public JwtTokenProvider(String secret, long expirationMs) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationMs = expirationMs;
    }

    
    public JwtTokenProvider() {
        this.key = Keys.hmacShaKeyFor(DEFAULT_SECRET.getBytes());
        this.expirationMs = 60 * 60 * 1000; // 1 hour
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
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    
    public String getUsernameFromToken(String token) {
        return parseClaims(token).getSubject();
    }

   
    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
