package com.example.demo.security;

import com.example.demo.entity.AppUser;
import com.example.demo.repository.AppUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service // ✅ This annotation is critical. It creates the Bean you are missing.
public class CustomUserDetailsService implements UserDetailsService {

    private final AppUserRepository userRepo;

    public CustomUserDetailsService(AppUserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        // 1. Find the user by email
        AppUser user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // 2. Convert Roles to Spring Security Authorities
        var authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())) 
                .collect(Collectors.toList());

        // 3. Return the UserDetails object
        return new User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }
}