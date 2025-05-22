package edu.ntnu.iir.learniverse.dto;

import edu.ntnu.iir.learniverse.entity.GlobalRole;
import edu.ntnu.iir.learniverse.entity.User;
import org.springframework.security.core.Authentication;

public record UserDto(
    Long id,
    String username,
    String email,
    GlobalRole role
) {
  public static UserDto fromUser(User user) {
    return new UserDto(
        user.getId(),
        user.getUsername(),
        user.getEmail(),
        user.getGlobalRole()
    );
  }

  public static UserDto fromAuthentication(Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    return fromUser(user);
  }
}
