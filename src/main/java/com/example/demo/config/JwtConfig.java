package com.example.demo.config;

import com.example.demo.security.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

@Bean
public JwtTokenProvider jwtTokenProvider() {

String secret = "MyVerySecretKeyForJwt123456789012345";
long expiration = 60000L;

return new JwtTokenProvider(secret, expiration);
}
}
