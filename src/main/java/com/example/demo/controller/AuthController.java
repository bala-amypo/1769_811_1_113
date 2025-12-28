package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

private final AuthService service;

public AuthController(AuthService service) {
this.service = service;
}

@PostMapping("/register")
public String register(@RequestBody RegisterRequest req) {
return service.register(req);
}

@PostMapping("/login")
public JwtResponse login(@RequestBody LoginRequest req) {
return service.login(req);
}
}
