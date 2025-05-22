package edu.ntnu.iir.learniverse.dto;

import edu.ntnu.iir.learniverse.entity.Category;

/**
 * Data Transfer Object (DTO) for Category.
 *
 * @param id the ID of the category
 * @param name the name of the category
 */
public record CategoryResponse(
    Long id,
    String name
) {
  /**
   * Constructor for CategoryDto.
   *
   * @param category the category object containing category details
   */
  public CategoryResponse(Category category) {
    this(category.getId(), category.getName());
  }

  /**
   * Constructor for CategoryDto with courses.
   *
   * @param category the category object containing category details
   * @return a CategoryDto object
   */
  public static CategoryResponse fromCategoryWithCourses(Category category) {
    return new CategoryResponse(category.getId(), category.getName());
  }
}
