@RestController
@RequestMapping("/auth")
public class AuthController {

private final AuthService authService;

public AuthController(AuthService authService) {
this.authService = authService;
}

@PostMapping("/register")
public void register(@RequestBody RegisterRequest request) {
authService.register(request);
}

@PostMapping("/login")
public JwtResponse login(@RequestBody LoginRequest request) {
return authService.login(request);
}
}
