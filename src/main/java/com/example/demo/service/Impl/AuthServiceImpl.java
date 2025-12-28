package com.example.demo.service.impl;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.AppUser;
import com.example.demo.entity.Role;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AuthService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service   // 🔴 REQUIRED FOR SPRING BOOT
public class AuthServiceImpl implements AuthService {

private final AppUserRepository appUserRepository;
private final RoleRepository roleRepository;
private final PasswordEncoder passwordEncoder;
private final AuthenticationManager authenticationManager;
private final JwtTokenProvider jwtTokenProvider;

/* 🔴 CONSTRUCTOR ORDER MUST BE EXACT (TESTED) */
public AuthServiceImpl(
AppUserRepository appUserRepository,
RoleRepository roleRepository,
PasswordEncoder passwordEncoder,
AuthenticationManager authenticationManager,
JwtTokenProvider jwtTokenProvider
) {
this.appUserRepository = appUserRepository;
this.roleRepository = roleRepository;
this.passwordEncoder = passwordEncoder;
this.authenticationManager = authenticationManager;
this.jwtTokenProvider = jwtTokenProvider;
}

@Override
public void register(RegisterRequest request) {

if (appUserRepository.existsByEmail(request.getEmail())) {
throw new IllegalArgumentException("Email already in use");
}

Role role = roleRepository.findByName(request.getRole())
.orElseThrow(() -> new IllegalArgumentException("Invalid role"));

AppUser user = new AppUser();
user.setEmail(request.getEmail());
user.setPassword(passwordEncoder.encode(request.getPassword()));
user.setFullName(request.getFullName());
user.getRoles().add(role);

appUserRepository.save(user);
}

@Override
public JwtResponse login(LoginRequest request) {

Authentication authentication =
authenticationManager.authenticate(
new UsernamePasswordAuthenticationToken(
request.getEmail(),
request.getPassword()
)
);

AppUser user = appUserRepository.findByEmail(request.getEmail())
.orElseThrow(() -> new IllegalArgumentException("User not found"));

String role = user.getRoles().iterator().next().getName();

String token = jwtTokenProvider.generateToken(
authentication,
user.getId(),
user.getEmail(),
role
);

return new JwtResponse(
token,
user.getId(),
user.getEmail(),
role
);
}
}
