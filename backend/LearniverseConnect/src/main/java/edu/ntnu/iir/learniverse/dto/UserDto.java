package edu.ntnu.iir.learniverse.dto;

import edu.ntnu.iir.learniverse.entity.GlobalRole;

public record UserDto(
    Long id,
    String username,
    String email,
    GlobalRole role
) {}
