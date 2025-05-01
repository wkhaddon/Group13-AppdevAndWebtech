package edu.ntnu.iir.learniverse.dto;

import edu.ntnu.iir.learniverse.entity.UserRole;

public record UserDto(
    Long id,
    String username,
    String email,
    UserRole role
) {}
