package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AuthService;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

private final AuthenticationManager authManager;
private final AppUserRepository userRepo;
private final RoleRepository roleRepo;
private final PasswordEncoder encoder;
private final JwtTokenProvider provider;

public AuthServiceImpl(AuthenticationManager authManager, AppUserRepository userRepo,
RoleRepository roleRepo, PasswordEncoder encoder, JwtTokenProvider provider) {
this.authManager = authManager;
this.userRepo = userRepo;
this.roleRepo = roleRepo;
this.encoder = encoder;
this.provider = provider;
}

@Override
public String register(RegisterRequest req) {

if (userRepo.existsByEmail(req.getEmail())) {
throw new IllegalArgumentException("Email already exists");
}

AppUser user = new AppUser();
user.setEmail(req.getEmail());
user.setPassword(encoder.encode(req.getPassword()));
user.setFullName(req.getFullName());

Role role = roleRepo.findByName(req.getRole())
.orElseThrow(() -> new RuntimeException("Role not found"));

user.getRoles().add(role);
userRepo.save(user);

return "User registered successfully";
}

@Override
public JwtResponse login(LoginRequest req) {

Authentication auth = authManager.authenticate(
new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
);

AppUser user = userRepo.findByEmail(req.getEmail())
.orElseThrow(() -> new RuntimeException("User not found"));

String role = user.getRoles().iterator().next().getName();
String token = provider.generateToken(auth, user.getId(), user.getEmail(), role);

return new JwtResponse(token, user.getId(), user.getEmail(), role);
}
}
