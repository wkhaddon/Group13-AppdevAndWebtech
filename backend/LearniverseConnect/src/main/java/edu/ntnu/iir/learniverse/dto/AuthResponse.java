package edu.ntnu.iir.learniverse.dto;

import edu.ntnu.iir.learniverse.entity.User;

public class AuthResponse {
  public final Long id;
  public final String username;
  public final String email;
  public final String role;

  public AuthResponse(User user) {
    this.id = user.getId();
    this.username = user.getUsername();
    this.email = user.getEmail();
    this.role = user.getGlobalRole().name();
  }
}
