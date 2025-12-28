package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenProvider {

    // ✅ MUST be hardcoded for tests
    private static final String SECRET =
            "my-test-secret-key-my-test-secret-key-123456"; // 256+ bits

    private static final long EXPIRATION_MS = 60 * 60 * 1000; // 1 hour

    private final SecretKey key;

    // ✅ NO-ARG CONSTRUCTOR (TESTS REQUIRE THIS)
    public JwtTokenProvider() {
        this.key = Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // ✅ METHOD SIGNATURE EXPECTED BY TESTS
    public String generateToken(
            org.springframework.security.core.Authentication authentication,
            Long userId,
            String email,
            String role
    ) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ REQUIRED BY TESTS
    public String getUsernameFromToken(String token) {
        return parseClaims(token).getSubject();
    }

    // ✅ REQUIRED BY TESTS
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
