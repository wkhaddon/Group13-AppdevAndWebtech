package edu.ntnu.iir.learniverse.dto;

import edu.ntnu.iir.learniverse.entity.GlobalRole;
import edu.ntnu.iir.learniverse.entity.User;
import org.springframework.security.core.Authentication;

/**
 * Data Transfer Object (DTO) for User.
 *
 * @param id the ID of the user
 * @param username the username of the user
 * @param email the email of the user
 * @param role the global role of the user
 */
public record UserDto(
    Long id,
    String username,
    String email,
    GlobalRole role
) {
  /**
   * Creates a UserDto from a User entity.
   *
   * @param user the User entity
   * @return a UserDto object
   */
  public static UserDto fromUser(User user) {
    return new UserDto(
        user.getId(),
        user.getUsername(),
        user.getEmail(),
        user.getGlobalRole()
    );
  }

  /**
   * Creates a UserDto from an Authentication object.
   *
   * @param authentication the Authentication object
   * @return a UserDto object
   */
  public static UserDto fromAuthentication(Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    return fromUser(user);
  }
}
