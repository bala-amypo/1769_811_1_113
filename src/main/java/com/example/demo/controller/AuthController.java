package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.JwtResponse;
import com.example.demo.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

private final AuthService service;

public AuthController(AuthService service) {
this.service = service;
}

@PostMapping("/register")
public JwtResponse register(@RequestBody RegisterRequest request) {
return service.register(request);
}
}
