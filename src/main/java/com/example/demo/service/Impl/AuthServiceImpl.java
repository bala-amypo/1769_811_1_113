package com.example.demo.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

private final AppUserRepository userRepo;
private final RoleRepository roleRepo;
private final PasswordEncoder encoder;
private final JwtTokenProvider jwtProvider;

public AuthServiceImpl(
AppUserRepository userRepo,
RoleRepository roleRepo,
PasswordEncoder encoder,
JwtTokenProvider jwtProvider) {

this.userRepo = userRepo;
this.roleRepo = roleRepo;
this.encoder = encoder;
this.jwtProvider = jwtProvider;
}

@Override
public void register(RegisterRequest request) {

if(userRepo.existsByEmail(request.email)) {
throw new IllegalArgumentException("Email already exists");
}

Role role = roleRepo.findByName(request.role)
.orElseThrow(() -> new IllegalArgumentException("Invalid role"));

AppUser user = new AppUser(
request.fullName,
request.email,
encoder.encode(request.password)
);

userRepo.save(user);
}

@Override
public JwtResponse login(LoginRequest request) {

AppUser user = userRepo.findByEmail(request.email)
.orElseThrow(() -> new IllegalArgumentException("User not found"));

JwtResponse response = new JwtResponse();
response.email = user.getEmail();
response.userId = user.getId();
response.role = "USER";
response.token = jwtProvider.generateToken(user.getEmail(), "USER");

return response;
}
}
