package edu.ntnu.iir.learniverse.controller;

import edu.ntnu.iir.learniverse.dto.CategoryResponse;
import edu.ntnu.iir.learniverse.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling category-related requests.
 */
@RestController
@RequestMapping("/api/categories")
@Tag(name = "Category", description = "Category API")
public class CategoryController {
  private final CategoryService categoryService;

  /**
   * Constructor for CategoryController.
   *
   * @param categoryService the category service for handling category-related operations
   */
  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  /**
   * Handles the request to get all categories.
   *
   * @return a list of all categories
   */
  @Operation(summary = "Get all categories", description = "Retrieve a list of all categories.")
  @ApiResponse(responseCode = "200", description = "List of categories retrieved successfully.")
  @ApiResponse(responseCode = "500", description = "Internal server error.")
  @PermitAll
  @GetMapping
  public List<CategoryResponse> getAllCategories() {
    return categoryService.getAll();
  }

  /**
   * Handles the request to get a category by its ID.
   *
   * @param id the ID of the category to retrieve
   * @return the category with the specified ID, or a 404 response if not found
   */
  @Operation(summary = "Get category by ID", description = "Retrieve a category by its ID.")
  @ApiResponse(responseCode = "200", description = "Category retrieved successfully.")
  @ApiResponse(responseCode = "404", description = "Category not found.")
  @ApiResponse(responseCode = "500", description = "Internal server error.")
  @PermitAll
  @GetMapping("/{id}")
  public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
    return categoryService.getById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }
}
