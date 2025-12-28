package com.example.demo.service.impl;


import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.JwtResponse;
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

import java.util.Collections;
import java.util.HashMap; // <--- Added this import
import java.util.HashSet;
import java.util.Map;     // <--- Added this import

@Service
public class AuthServiceImpl implements AuthService {

    private final AppUserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(
            AppUserRepository userRepo,
            RoleRepository roleRepo,
            PasswordEncoder passwordEncoder,
            JwtTokenProvider jwtTokenProvider
    ) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

@Override
public JwtResponse login(LoginRequest request) {

    AppUser user = userRepo.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
        throw new RuntimeException("Invalid password");
    }

    String role = user.getRoles().iterator().next().getName();

    Map<String, Object> claims = new HashMap<>();
    claims.put("userId", user.getId());
    claims.put("role", role);

    String token = jwtTokenProvider.generateToken(
            claims,
            user.getEmail()
    );

    return new JwtResponse(
            token,
            user.getId(),
            user.getEmail(),
            role
    );
}



    @Override
    @Transactional
    public void register(RegisterRequest request) {
        try {
            // 1. Check if email exists
            if (userRepo.findByEmail(request.getEmail()).isPresent()) {
                throw new RuntimeException("Email already exists");
            }

            // 2. Create User
            AppUser user = new AppUser();
            user.setEmail(request.getEmail());
            user.setName(request.getName());
            user.setPassword(passwordEncoder.encode(request.getPassword()));

            // 3. Handle Role
            String roleName = request.getRole();
            if (roleName == null || roleName.isEmpty()) {
                roleName = "STUDENT";
            }
            String finalRoleName = roleName.toUpperCase();

            // Find or Create Role
            Role role = roleRepo.findByName(finalRoleName)
                    .orElseGet(() -> {
                        Role newRole = new Role();
                        newRole.setName(finalRoleName);
                        return roleRepo.save(newRole);
                    });

            // 4. Set Roles
            user.setRoles(new HashSet<>(Collections.singletonList(role)));

            // 5. Save User
            userRepo.save(user);
            System.out.println("User registered successfully: " + user.getEmail());

        } catch (Exception e) {
            System.err.println("ERROR IN REGISTER: " + e.getMessage());
            e.printStackTrace(); 
            throw e; 
        }
    }
}