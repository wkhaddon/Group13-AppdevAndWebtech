package edu.ntnu.iir.learniverse.dto;

import edu.ntnu.iir.learniverse.entity.User;

public class AuthResponse {
  public Long id;
  public String username;
  public String email;
  public String role;

  public AuthResponse(User user) {
    this.id = user.getId();
    this.username = user.getUsername();
    this.email = user.getEmail();
    this.role = user.getRole().name();
  }
}
