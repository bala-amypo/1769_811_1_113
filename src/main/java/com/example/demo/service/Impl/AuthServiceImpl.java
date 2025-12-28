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

@Service
public class AuthServiceImpl implements AuthService {

private final AuthenticationManager authenticationManager;
private final JwtTokenProvider jwtTokenProvider;
private final AppUserRepository userRepository;
private final RoleRepository roleRepository;
private final PasswordEncoder passwordEncoder;

public AuthServiceImpl(
AuthenticationManager authenticationManager,
JwtTokenProvider jwtTokenProvider,
AppUserRepository userRepository,
RoleRepository roleRepository,
PasswordEncoder passwordEncoder
) {
this.authenticationManager = authenticationManager;
this.jwtTokenProvider = jwtTokenProvider;
this.userRepository = userRepository;
this.roleRepository = roleRepository;
this.passwordEncoder = passwordEncoder;
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

AppUser user =
userRepository.findByEmail(request.getEmail()).orElseThrow();

String token = jwtTokenProvider.generateToken(
authentication,
user.getId(),
user.getEmail(),
user.getRole()
);

return new JwtResponse(
token,
user.getId(),
user.getEmail(),
user.getRole()
);
}

@Override
public void register(RegisterRequest request) {

Role role = roleRepository.findByName(request.getRole())
.orElseThrow(() ->
new RuntimeException("Invalid role")
);

AppUser user = new AppUser();
user.setEmail(request.getEmail());
user.setPassword(passwordEncoder.encode(request.getPassword()));
user.setFullName(request.getFullName());
user.setRole(role.getName());

userRepository.save(user);
}
}
