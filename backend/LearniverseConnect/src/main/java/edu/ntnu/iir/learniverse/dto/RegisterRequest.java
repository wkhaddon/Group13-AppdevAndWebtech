package edu.ntnu.iir.learniverse.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {

  @NotBlank
  public String username;

  @Email
  @NotBlank
  public String email;

  @NotBlank
  @Size(min = 8)
  public String password;
}
