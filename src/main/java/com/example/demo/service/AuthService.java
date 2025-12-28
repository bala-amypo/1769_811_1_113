package com.example.demo.service;

import com.example.demo.dto.;
import com.example.demo.dto.AuthResponseDto;
import com.example.demo.dto.RegisterRequestDto;

public interface AuthService {
     login(AuthRequestDto request);
    void register(RegisterRequestDto request);
}