package com.example.demo.controller;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.AppUser;
import com.example.demo.entity.Role;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final AppUserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;
    private final JwtTokenProvider tokenProvider;

    public AuthController(AuthenticationManager authManager, AppUserRepository userRepo, 
                          RoleRepository roleRepo, PasswordEncoder encoder, 
                          JwtTokenProvider tokenProvider) {
        this.authManager = authManager;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.encoder = encoder;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        if (userRepo.existsByEmail(req.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        AppUser user = new AppUser();
        user.setEmail(req.getEmail());
        user.setFullName(req.getFullName());
        user.setPassword(encoder.encode(req.getPassword()));

        Role role = roleRepo.findByName(req.getRole())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.getRoles().add(role);

        userRepo.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest req) {
        Authentication auth = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
        );

        AppUser user = userRepo.findByEmail(req.getEmail()).orElseThrow();
        String role = user.getRoles().isEmpty() ? "" : user.getRoles().iterator().next().getName();

        String token = tokenProvider.generateToken(auth, user.getId(), user.getEmail(), role);
        return ResponseEntity.ok(new JwtResponse(token, user.getEmail(), role));
    }
}