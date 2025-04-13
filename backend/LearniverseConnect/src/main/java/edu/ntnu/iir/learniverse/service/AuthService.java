package edu.ntnu.iir.learniverse.service;

import edu.ntnu.iir.learniverse.dto.LoginRequest;
import edu.ntnu.iir.learniverse.dto.RegisterRequest;
import edu.ntnu.iir.learniverse.entity.User;
import edu.ntnu.iir.learniverse.entity.UserRole;
import edu.ntnu.iir.learniverse.repository.UserRepository;
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

  public User register(RegisterRequest request) {
    String normalizedEmail = normalizeEmail(request.email);

    // Check for existing username/email
    if (userRepository.findByUsername(request.username).isPresent()) {
      throw new IllegalArgumentException("Username already exists");
    }

    if (userRepository.findByEmail(normalizedEmail).isPresent()) {
      throw new IllegalArgumentException("Email already exists");
    }

    User user = new User();
    user.setUsername(request.username);
    user.setEmail(normalizedEmail);
    user.setPasswordHash(passwordEncoder.encode(request.password));
    user.setRole(UserRole.CUSTOMER);
    user.setCreatedAt(LocalDateTime.now());
    return userRepository.save(user);
  }

  public Optional<User> login(LoginRequest request) {
    Optional<User> userOptional = userRepository.findByUsername(request.username);
    if (userOptional.isPresent()) {
      User user = userOptional.get();
      if (passwordEncoder.matches(request.password, user.getPasswordHash())) {
        return Optional.of(user);
      }
    }
    return Optional.empty();
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
