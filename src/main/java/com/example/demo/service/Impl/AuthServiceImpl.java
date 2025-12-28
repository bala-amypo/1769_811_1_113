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

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class AuthServiceImpl implements AuthService {

private final AppUserRepository userRepo;
private final RoleRepository roleRepo;
private final PasswordEncoder passwordEncoder;
private final AuthenticationManager authenticationManager;
private final JwtTokenProvider jwtTokenProvider;

/* 🔴 REQUIRED constructor (exact order for tests) */
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

Authentication authentication =
authenticationManager.authenticate(
new UsernamePasswordAuthenticationToken(
request.getEmail(),
request.getPassword()
)
);

AppUser user =
userRepo.findByEmail(request.getEmail())
.orElseThrow(() -> new RuntimeException("User not found"));

String role =
user.getRoles().iterator().next().getName();

String token =
jwtTokenProvider.generateToken(
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

@Override
@Transactional
public void register(RegisterRequest request) {

    // 1️⃣ Required by test: duplicate email throws
    if (userRepo.existsByEmail(request.getEmail())) {
        throw new RuntimeException("Email already exists");
    }

    // 2️⃣ Create user
    AppUser user = new AppUser();
    user.setEmail(request.getEmail());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setName(request.getName());

    // 3️⃣ Role handling (TEST + RUNTIME SAFE)
    String roleName = request.getRole();
    if (roleName == null || roleName.isBlank()) {
        roleName = "STUDENT";
    }

    String finalRole = roleName.toUpperCase();

    Role role = roleRepo.findByName(finalRole)
            .orElseGet(() -> {
                Role r = new Role();
                r.setName(finalRole);
                return roleRepo.save(r);
            });

    user.getRoles().add(role);

    // 4️⃣ Save
    userRepo.save(user);
}

}
