package com.example.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

private final JwtTokenProvider jwtTokenProvider;

public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
this.jwtTokenProvider = jwtTokenProvider;
}

@Override
protected void doFilterInternal(
HttpServletRequest request,
HttpServletResponse response,
FilterChain filterChain
) throws ServletException, IOException {

String header = request.getHeader("Authorization");

if (header != null && header.startsWith("Bearer ")) {

String token = header.substring(7);

if (jwtTokenProvider.validateToken(token)) {

Claims claims = jwtTokenProvider.getClaims(token);
String email = claims.getSubject();
String role = claims.get("role", String.class);

SimpleGrantedAuthority authority =
new SimpleGrantedAuthority("ROLE_" + role);

UsernamePasswordAuthenticationToken auth =
new UsernamePasswordAuthenticationToken(
email,
null,
List.of(authority)
);

SecurityContextHolder
.getContext()
.setAuthentication(auth);
}
}

filterChain.doFilter(request, response);
}
}
