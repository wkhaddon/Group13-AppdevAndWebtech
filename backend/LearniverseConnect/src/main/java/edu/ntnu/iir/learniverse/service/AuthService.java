package edu.ntnu.iir.learniverse.service;

import edu.ntnu.iir.learniverse.dto.LoginRequest;
import edu.ntnu.iir.learniverse.dto.RegisterRequest;
import edu.ntnu.iir.learniverse.dto.UserDto;
import edu.ntnu.iir.learniverse.entity.GlobalRole;
import edu.ntnu.iir.learniverse.entity.User;
import edu.ntnu.iir.learniverse.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class for handling authentication-related operations.
 */
@Service
public class AuthService {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  /**
   * Constructor for AuthService.
   *
   * @param userRepository the user repository for database operations
   * @param passwordEncoder the password encoder for hashing passwords
   */
  public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  /**
   * Registers a new user.
   *
   * @param request the registration request containing user details
   * @return the registered user as a UserDto
   */
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

  /**
   * Logs in a user.
   *
   * @param request the login request containing username/email and password
   * @return the logged-in user as a UserDto, or an empty Optional if login fails
   */
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

  /**
   * Validates the current user's token.
   *
   * @return an Optional containing the user details if the token is valid, or an empty Optional
   */
  public Optional<UserDto> validateToken() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
      return Optional.empty();
    }

    String username = auth.getName();

    return userRepository.findByUsernameIgnoreCase(username)
        .map(UserDto::fromUser);
  }

  /**
   * Normalizes the email address by removing dots and aliases.
   *
   * @param email the email address to normalize
   * @return the normalized email address
   */
  private String normalizeEmail(String email) {
    String[] parts = email.split("@");
    if (parts.length != 2) {
      return email; // Invalid email format, return as is
    }

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
