package com.example.demo.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
    // For this project scope, we use a simple mock token generation
    // In production, use JJWT library with a strong secret key
    public String generateToken(Authentication auth, Long id, String email, String role) {
        return "mock-jwt-token-" + email + "-" + role;
    }

    public boolean validateToken(String token) {
        return token != null && token.startsWith("mock-jwt-token-");
    }
}