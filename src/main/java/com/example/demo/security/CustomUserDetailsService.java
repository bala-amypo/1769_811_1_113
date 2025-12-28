@Service
public class CustomUserDetailsService implements UserDetailsService {

private final AppUserRepository userRepository;

public CustomUserDetailsService(AppUserRepository userRepository) {
this.userRepository = userRepository;
}

@Override
public UserDetails loadUserByUsername(String email)
throws UsernameNotFoundException {

AppUser user = userRepository.findByEmail(email)
.orElseThrow(() -> new UsernameNotFoundException("User not found"));

return User.builder()
.username(user.getEmail())
.password(user.getPassword())
.authorities(
user.getRoles()
.stream()
.map(r -> "ROLE_" + r.getName())
.toArray(String[]::new)
)
.disabled(!user.isEnabled())
.build();
}
}
