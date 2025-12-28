package com.example.demo.controller;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class AuthController {

private final AuthService authService;

public AuthController(AuthService authService) {
this.authService = authService;
}

@PostMapping("/register")
public void register(@RequestBody RegisterRequest request) {
authService.register(request);
}

@PostMapping("/login")
public JwtResponse login(@RequestBody LoginRequest request) {
return authService.login(request);
}
}
