package com.example.demo.security;

import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AppUser;
import com.example.demo.repository.AppUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

private final AppUserRepository appUserRepository;

public CustomUserDetailsService(AppUserRepository appUserRepository) {
this.appUserRepository = appUserRepository;
}

@Override
public UserDetails loadUserByUsername(String email)
throws UsernameNotFoundException {

AppUser user = appUserRepository
.findByEmail(email)
.orElseThrow(() ->
new UsernameNotFoundException("User not found")
);

return new User(
user.getEmail(),
user.getPassword(),
user.getEnabled(),
true,
true,
true,
user.getRoles()
.stream()
.map(role ->
new SimpleGrantedAuthority("ROLE_" + role.getName())
)
.collect(Collectors.toSet())
);
}
}
