package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.example.demo.service.AuthService;
import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.security.JwtTokenProvider;

import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

private final AppUserRepository userRepo;
private final RoleRepository roleRepo;
private final PasswordEncoder encoder;
private final AuthenticationManager authManager;
private final JwtTokenProvider jwtProvider;

public AuthServiceImpl(
AppUserRepository userRepo,
RoleRepository roleRepo,
PasswordEncoder encoder,
AuthenticationManager authManager,
JwtTokenProvider jwtProvider){
this.userRepo=userRepo;
this.roleRepo=roleRepo;
this.encoder=encoder;
this.authManager=authManager;
this.jwtProvider=jwtProvider;
}

public void register(RegisterRequest req){
if(userRepo.existsByEmail(req.email))
throw new IllegalArgumentException("Email already exists");

Role role=roleRepo.findByName(req.role)
.orElseThrow(() -> new IllegalArgumentException("Role not found"));

AppUser user=new AppUser(req.fullName,req.email,encoder.encode(req.password));
user.setRoles(Set.of(role));
userRepo.save(user);
}

public JwtResponse login(LoginRequest req){
authManager.authenticate(
new UsernamePasswordAuthenticationToken(req.email,req.password));

AppUser user=userRepo.findByEmail(req.email).orElseThrow();

String role=user.getRoles().iterator().next().getName();
String token=jwtProvider.generateToken(null,user.getId(),user.getEmail(),role);

JwtResponse res=new JwtResponse();
res.token=token;
res.userId=user.getId();
res.email=user.getEmail();
res.role=role;
return res;
}
}
