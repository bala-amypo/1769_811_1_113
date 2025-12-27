package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

private final AuthService authService;

public AuthController(AuthService authService) {
this.authService = authService;
}
@PreAuthorize("hasRole('ADMIN)")
@PostMapping("/register")
public void register(@RequestBody RegisterRequest request) {
authService.register(request);
}

@PostMapping("/login")
public JwtResponse login(@RequestBody LoginRequest request) {
return authService.login(request);
}
}
