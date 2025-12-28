package com.example.demo.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.JwtResponse;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AuthService;

@Service
public class AuthServiceImpl
implements AuthService {

private final AuthenticationManager authenticationManager;
private final JwtTokenProvider jwtTokenProvider;

public AuthServiceImpl(
AuthenticationManager authenticationManager,
JwtTokenProvider jwtTokenProvider
) {
this.authenticationManager = authenticationManager;
this.jwtTokenProvider = jwtTokenProvider;
}

@Override
public JwtResponse authenticate(LoginRequest request) {

Authentication authentication =
authenticationManager.authenticate(
new UsernamePasswordAuthenticationToken(
request.getEmail(),
request.getPassword()
)
);

String token = jwtTokenProvider.generateToken(authentication);

return new JwtResponse(token);
}
}
