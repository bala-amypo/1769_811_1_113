@Configuration
@EnableMethodSecurity
public class SecurityConfig {

@Bean
public PasswordEncoder passwordEncoder() {
return new BCryptPasswordEncoder();
}

@Bean
public AuthenticationManager authenticationManager(
AuthenticationConfiguration config
) throws Exception {
return config.getAuthenticationManager();
}

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

http
.csrf(csrf -> csrf.disable())
.authorizeHttpRequests(auth -> auth
.requestMatchers("/auth/**").permitAll()
.anyRequest().authenticated()
);

return http.build();
}
}
