package com.example.demo.config;

import com.example.demo.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

private final JwtAuthenticationFilter filter;

public SecurityConfig(JwtAuthenticationFilter filter) {
this.filter = filter;
}

@Bean
PasswordEncoder passwordEncoder() {
return new BCryptPasswordEncoder();
}

@Bean
AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
return config.getAuthenticationManager();
}

@Bean
SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
http.csrf(csrf -> csrf.disable())
.authorizeHttpRequests(auth -> auth
.requestMatchers("/auth/**").permitAll()
.anyRequest().authenticated()
)
.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

return http.build();
}
}
