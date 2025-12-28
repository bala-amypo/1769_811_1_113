package com.example.demo.service.Impl;

import com.example.demo.dto.LoginRequest;     // ✅ Changed
import com.example.demo.dto.JwtResponse;      // ✅ Changed
import com.example.demo.dto.RegisterRequest;  // ✅ Changed
import com.example.demo.entity.AppUser;
import com.example.demo.entity.Role;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    private final AppUserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(
            AppUserRepository userRepo,
            RoleRepository roleRepo,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider
    ) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public JwtResponse login(LoginRequest request) {
        // 1. Authenticate using email and password from LoginRequest
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // 2. Find User
        AppUser user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 3. Generate Token
        Map<String, Object> claims = new HashMap<>();
        String token = jwtTokenProvider.generateToken(claims, user.getEmail());

        // 4. Return your specific JwtResponse
        return new JwtResponse(token);
    }

    @Override
    public void register(RegisterRequest request) {
        if (userRepo.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        AppUser user = new AppUser();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // --- Handle Role from RegisterRequest ---
        String roleName = request.getRole();
        if (roleName == null || roleName.isEmpty()) {
            roleName = "STUDENT";
        }
        String finalRoleName = roleName.toUpperCase();

        Role role = roleRepo.findByName(finalRoleName)
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setName(finalRoleName);
                    return roleRepo.save(newRole);
                });

        user.setRoles(Collections.singleton(role));
        userRepo.save(user);
    }
}