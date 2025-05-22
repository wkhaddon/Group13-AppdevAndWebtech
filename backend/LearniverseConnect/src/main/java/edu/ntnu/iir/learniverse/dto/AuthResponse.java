package edu.ntnu.iir.learniverse.dto;

import edu.ntnu.iir.learniverse.entity.User;

/**
 * Data Transfer Object (DTO) for authentication response.
 */
public record AuthResponse(
    Long id,
    String username,
    String email,
    String role
) {
  /**
   * Constructor for AuthResponse.
   *
   * @param user the user object containing user details
   */
  public AuthResponse(User user) {
    this(
        user.getId(),
        user.getUsername(),
        user.getEmail(),
        user.getGlobalRole().name()
    );
  }
}
