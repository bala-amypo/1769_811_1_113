@Service
public class AuthServiceImpl implements AuthService {

private final AuthenticationManager authenticationManager;
private final JwtTokenProvider jwtTokenProvider;

public AuthServiceImpl(
AuthenticationManager authenticationManager,
JwtTokenProvider jwtTokenProvider
) {
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

String token = jwtTokenProvider.generateToken(authentication);
return new JwtResponse(token);
}
}
