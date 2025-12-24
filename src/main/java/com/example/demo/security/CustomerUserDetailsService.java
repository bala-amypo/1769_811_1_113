package com.example.demo.security;

import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AppUser;
import com.example.demo.repository.AppUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

private final AppUserRepository repo;

public CustomUserDetailsService(AppUserRepository repo) {
this.repo = repo;
}

@Override
public UserDetails loadUserByUsername(String email)
throws UsernameNotFoundException {

AppUser user = repo.findByEmail(email)
.orElseThrow(() -> new UsernameNotFoundException("User not found"));

return org.springframework.security.core.userdetails.User
.withUsername(user.getEmail())
.password(user.getPassword())
.authorities(
user.getRoles()
.stream()
.map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName()))
.collect(Collectors.toList())
)
.build();
}
}
