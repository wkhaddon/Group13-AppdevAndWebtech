package edu.ntnu.iir.learniverse.service;

import edu.ntnu.iir.learniverse.dto.LoginRequest;
import edu.ntnu.iir.learniverse.dto.RegisterRequest;
import edu.ntnu.iir.learniverse.dto.UserDto;
import edu.ntnu.iir.learniverse.entity.GlobalRole;
import edu.ntnu.iir.learniverse.entity.User;
import edu.ntnu.iir.learniverse.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public UserDto register(RegisterRequest request) {
    String normalizedEmail = normalizeEmail(request.email);

    // Check for existing username/email
    if (userRepository.findByUsernameIgnoreCase(request.username).isPresent()) {
      throw new IllegalArgumentException("Username already exists");
    }

    if (userRepository.findByEmailIgnoreCase(normalizedEmail).isPresent()) {
      throw new IllegalArgumentException("Email already exists");
    }

    User user = new User();
    user.setUsername(request.username);
    user.setEmail(normalizedEmail);
    user.setPasswordHash(passwordEncoder.encode(request.password));
    user.setGlobalRole(GlobalRole.USER);
    user.setCreatedAt(LocalDateTime.now());

    return UserDto.fromUser(userRepository.save(user));
  }

  public Optional<UserDto> login(LoginRequest request) {
    Optional<User> userOpt;

    // Determine if it's an email (contains @) or username
    if (request.identifier.contains("@")) {
      userOpt = userRepository.findByEmailIgnoreCase(normalizeEmail(request.identifier));
    } else {
      userOpt = userRepository.findByUsernameIgnoreCase(request.identifier);
    }

    return userOpt
        .filter(user -> passwordEncoder.matches(request.password, user.getPasswordHash()))
        .map(UserDto::fromUser);
  }

  public Optional<UserDto> validateToken() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
      return Optional.empty();
    }

    String username = auth.getName();

    return userRepository.findByUsernameIgnoreCase(username)
        .map(UserDto::fromUser);
  }

  private String normalizeEmail(String email) {
    String[] parts = email.split("@");
    if (parts.length != 2) return email; // Invalid email format, return as is

    String local = parts[0];
    String domain = parts[1].toLowerCase();

    // Remove everything after + in the local part
    if (local.contains("+")) {
      local = local.substring(0, local.indexOf("+"));
    }

    // Remove dots in the local part - Gmail specific
    if (domain.equals("gmail.com")) {
      local = local.replace(".", "");
    }

    return (local + "@" + domain).toLowerCase();
  }
}
