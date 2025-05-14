package edu.ntnu.iir.learniverse.controller;

import edu.ntnu.iir.learniverse.dto.LoginRequest;
import edu.ntnu.iir.learniverse.dto.RegisterRequest;
import edu.ntnu.iir.learniverse.entity.User;
import edu.ntnu.iir.learniverse.security.JwtUtil;
import edu.ntnu.iir.learniverse.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private final AuthService authService;
  private final JwtUtil jwtUtil;

  public AuthController(AuthService authService, JwtUtil jwtUtil) {
    this.authService = authService;
    this.jwtUtil = jwtUtil;
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
    User user = authService.register(request);
    if (user == null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(Map.of("error", "User already exists"));
    }

    String jwt = jwtUtil.generateToken(user.getUsername(), user.getGlobalRole().name());
    ResponseCookie cookie = createJwtCookie(jwt);

    return ResponseEntity.ok()
        .header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body("User registered successfully");
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
    Optional<User> userOpt = authService.login(request);
    if (userOpt.isEmpty()) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    User user = userOpt.get();
    String jwt = jwtUtil.generateToken(user.getUsername(), user.getGlobalRole().name());
    ResponseCookie cookie = createJwtCookie(jwt);

    return ResponseEntity.ok()
        .header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body("Login successful");
  }

  @PostMapping("/logout")
  public ResponseEntity<?> logout() {
    ResponseCookie cookie = ResponseCookie.from("jwt", "")
        .httpOnly(true)
        .secure(true)
        .path("/")
        .maxAge(0)
        .sameSite("Strict")
        .build();

    return ResponseEntity.ok()
        .header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body("Logout successful");
  }

  @GetMapping("/validate")
  public ResponseEntity<?> validate(HttpServletRequest request) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
    }

    String username = auth.getName(); // from token subject
    String role = auth.getAuthorities().stream()
            .findFirst()
            .map(GrantedAuthority::getAuthority)
            .orElse("UNKNOWN");

    return ResponseEntity.ok(Map.of("username", username, "role", role));
  }

  private ResponseCookie createJwtCookie(String jwt) {
    return ResponseCookie.from("jwt", jwt)
        .httpOnly(true)
        .secure(false) // TODO: Set to true in production
        .path("/")
        .maxAge(Duration.ofDays(1))
        .sameSite("Strict")
        .build();
  }
}
