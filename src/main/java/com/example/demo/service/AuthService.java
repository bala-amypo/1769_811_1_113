package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.JwtResponse;

public interface AuthService {
JwtResponse authenticate(LoginRequest request);
}
