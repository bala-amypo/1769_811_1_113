package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.JwtResponse;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

private final JwtTokenProvider jwt;

public AuthServiceImpl(JwtTokenProvider jwt) {
this.jwt = jwt;
}

@Override
public JwtResponse register(RegisterRequest request) {
return new JwtResponse(jwt.generateToken(request.getEmail()), request.getRole());
}
}
