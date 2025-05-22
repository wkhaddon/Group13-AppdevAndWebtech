package edu.ntnu.iir.learniverse.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object (DTO) for login requests.
 */
public class LoginRequest {

  @NotBlank
  public String identifier;

  @NotBlank
  public String password;
}
