package edu.ntnu.iir.learniverse.controller;

import edu.ntnu.iir.learniverse.dto.LoginRequest;
import edu.ntnu.iir.learniverse.dto.RegisterRequest;
import edu.ntnu.iir.learniverse.dto.UserDto;
import edu.ntnu.iir.learniverse.security.JwtUtil;
import edu.ntnu.iir.learniverse.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;

@PermitAll
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Endpoints for user authentication and registration")
public class AuthController {
  private final AuthService authService;
  private final JwtUtil jwtUtil;

  public AuthController(AuthService authService, JwtUtil jwtUtil) {
    this.authService = authService;
    this.jwtUtil = jwtUtil;
  }

  @Operation(
      summary = "Register a new user",
      description = "Create a new user account with the provided registration details"
  )
  @ApiResponse(responseCode = "200", description = "User registered successfully")
  @ApiResponse(responseCode = "400", description = "User already exists")
  @PostMapping("/register")
  public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
    try {
      UserDto user = authService.register(request);
      if (user == null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "User already exists"));
      }

      String jwt = jwtUtil.generateToken(user.username(), user.role().name());
      ResponseCookie cookie = createJwtCookie(jwt);

      return ResponseEntity.ok()
          .header(HttpHeaders.SET_COOKIE, cookie.toString())
          .body(Map.of("message", "User registered successfully"));
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(Map.of("error", e.getMessage()));
    }
  }

  @Operation(summary = "Login", description = "Authenticate a user and return a JWT token")
  @ApiResponse(responseCode = "200", description = "Login successful")
  @ApiResponse(responseCode = "401", description = "Invalid credentials")
  @PostMapping("/login")
  public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
    Optional<UserDto> userDtoOpt = authService.login(request);
    if (userDtoOpt.isEmpty()) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body(Map.of("error", "Invalid credentials"));
    }

    UserDto userDto = userDtoOpt.get();
    String jwt = jwtUtil.generateToken(userDto.username(), userDto.role().name());
    ResponseCookie cookie = createJwtCookie(jwt);

    return ResponseEntity.ok()
        .header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body(Map.of("message", "Login successful"));
  }

  @Operation(summary = "Logout", description = "Invalidate the current user's session")
  @ApiResponse(responseCode = "200", description = "Logout successful")
  @PostMapping("/logout")
  public ResponseEntity<?> logout() {
    ResponseCookie cookie = ResponseCookie.from("jwt", "")
        .httpOnly(true)
        .secure(true)
        .path("/")
        .maxAge(0)
        .sameSite("None")
        .build();

    return ResponseEntity.ok()
        .header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body(Map.of("message", "Logout successful"));
  }

  @Operation(summary = "Validate JWT token", description = "Check if the provided JWT token is valid and return user details")
  @ApiResponse(responseCode = "200", description = "Token is valid")
  @ApiResponse(responseCode = "401", description = "Invalid token")
  @GetMapping("/validate")
  public ResponseEntity<?> validate(HttpServletRequest request) {
    Optional<UserDto> userDtoOpt = authService.validateToken();

    if (userDtoOpt.isEmpty()) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid token"));
    }

    return ResponseEntity.ok(userDtoOpt.get());
  }

  private ResponseCookie createJwtCookie(String jwt) {
    return ResponseCookie.from("jwt", jwt)
        .httpOnly(true)
        .secure(true)
        .path("/")
        .maxAge(Duration.ofDays(1))
        .sameSite("None")
        .build();
  }
}
