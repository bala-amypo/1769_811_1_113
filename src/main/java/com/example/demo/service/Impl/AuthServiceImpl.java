package com.example.demo.service.Impl; // Note: Ensure package name is lowercase 'impl' usually

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
import java.util.HashSet;

@Service
public class AuthServiceImpl implements AuthService {

    private final AppUserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    // REMOVED AuthenticationManager to prevent the 500 Crash / Loop
    
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
        // 1. Manual User Check (Safe & Prevents Circular Loop)
        AppUser user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // 2. Generate Token
        // NOTE: Ensure your JwtTokenProvider matches this signature!
        // If your provider takes (Map, String), keep your version. 
        // If it takes just (String), use: generateToken(user.getEmail())
        String token = jwtTokenProvider.generateToken(user.getEmail());

        // 3. Return Response (Assuming JwtResponse takes 4 args based on previous chats)
        String roleName = user.getRoles().isEmpty() ? "USER" : user.getRoles().iterator().next().getName();
        return new JwtResponse(token, user.getId(), user.getEmail(), roleName);
    }

    @Override
    @Transactional // <--- CRITICAL FIX: Keeps the Role "attached" so User can save it
    public void register(RegisterRequest request) {
        try {
            // 1. Check if email exists
            if (userRepo.findByEmail(request.getEmail()).isPresent()) {
                throw new RuntimeException("Email already exists");
            }

            // 2. Create User
            AppUser user = new AppUser();
            user.setEmail(request.getEmail());
            user.setName(request.getName()); // Ensure AppUser has .setName()!
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
            // We use new HashSet to ensure the collection is mutable (Hibernate prefers this)
            user.setRoles(new HashSet<>(Collections.singletonList(role)));

            // 5. Save User
            userRepo.save(user);
            System.out.println("User registered successfully: " + user.getEmail());

        } catch (Exception e) {
            // THIS WILL PRINT THE REAL ERROR IN YOUR CONSOLE
            System.err.println("ERROR IN REGISTER: " + e.getMessage());
            e.printStackTrace(); 
            throw e; // Re-throw so Controller knows it failed
        }
    }
}