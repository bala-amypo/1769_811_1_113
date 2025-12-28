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

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    // NOTE: AuthenticationManager removed to prevent StackOverflowError (Exit Code 137)

    public AuthServiceImpl(
            AppUserRepository appUserRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            JwtTokenProvider jwtTokenProvider
    ) {
        this.appUserRepository = appUserRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
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
        // 1. Check if user exists (Fixed variable name: appUserRepository)
        AppUser user = appUserRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found with email: " + request.getEmail()));

        // 2. Check Password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // 3. Generate Token (Fixed variable name: jwtTokenProvider)
        String token = jwtTokenProvider.generateToken(user.getEmail());

        // 4. Get Role
        String roleName = user.getRoles().isEmpty() ? "USER" : user.getRoles().iterator().next().getName();

        // 5. Return JwtResponse
        return new JwtResponse(token, user.getId(), user.getEmail(), roleName);
    }
}