package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.JwtResponse;

public interface AuthService {
JwtResponse login(LoginRequest request);
void register(RegisterRequest request);
}
