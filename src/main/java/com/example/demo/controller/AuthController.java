package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.*;
import com.example.demo.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

private final AuthService authService;

public AuthController(AuthService authService) {
this.authService = authService;
}

@PostMapping("/register")
public ApiResponse register(@RequestBody RegisterRequest request) {
authService.register(request);
return new ApiResponse(true, "User registered successfully");
}

@PostMapping("/login")
public JwtResponse login(@RequestBody LoginRequest request) {
return authService.login(request);
}
}
