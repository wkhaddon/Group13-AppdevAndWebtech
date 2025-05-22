package edu.ntnu.iir.learniverse.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for registration requests.
 */
public class RegisterRequest {

  @Pattern(regexp = "^[a-zA-Z0-9 ._-]{3,20}$",
          message = "Username must be 3-20 characters long and can only contain letters, "
                  + "numbers, spaces, dots, underscores, and hyphens.")
  @NotBlank
  public String username;

  @Email
  @NotBlank
  public String email;

  @NotBlank
  @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters long.")
  public String password;
}
