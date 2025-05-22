package edu.ntnu.iir.learniverse.dto;

import edu.ntnu.iir.learniverse.entity.Category;

public record CategoryDto(
    Long id,
    String name
) {
  public static CategoryDto fromCategory(Category category) {
    return new CategoryDto(category.getId(), category.getName());
  }

  public static CategoryDto fromCategoryWithCourses(Category category) {
    return new CategoryDto(category.getId(), category.getName());
  }
}
