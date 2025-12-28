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

    private final AuthenticationManager authManager;
    private final AppUserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;
    private final JwtTokenProvider tokenProvider;

    public AuthServiceImpl(AuthenticationManager authManager, AppUserRepository userRepo,
                           RoleRepository roleRepo, PasswordEncoder encoder,
                           JwtTokenProvider tokenProvider) {
        this.authManager = authManager;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.encoder = encoder;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public String register(RegisterRequest req) {
        if (userRepo.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        AppUser user = new AppUser();
        user.setEmail(req.getEmail());
        user.setFullName(req.getFullName());
        user.setPassword(encoder.encode(req.getPassword()));

        // Default to a specific role or fetch from request. 
        // Ensure role exists in DB (handled by InitRoles in main app).
        Role role = roleRepo.findByName(req.getRole())
                .orElseThrow(() -> new RuntimeException("Role not found: " + req.getRole()));
        
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
        
        String role = user.getRoles().isEmpty() ? "" : user.getRoles().iterator().next().getName();

        String token = tokenProvider.generateToken(auth, user.getId(), user.getEmail(), role);
        return new JwtResponse(token, user.getEmail(), role);
    }
}