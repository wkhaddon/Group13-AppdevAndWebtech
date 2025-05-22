package edu.ntnu.iir.learniverse.service;

import edu.ntnu.iir.learniverse.dto.CategoryResponse;
import edu.ntnu.iir.learniverse.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Service class for managing categories.
 */
@Service
public class CategoryService {
  private final CategoryRepository categoryRepository;

  /**
   * Constructor for CategoryService.
   *
   * @param categoryRepository the repository for category data access
   */
  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  /**
   * Retrieves all categories.
   *
   * @return a list of all categories
   */
  public List<CategoryResponse> getAll() {
    return categoryRepository.findAll().stream().map(CategoryResponse::new).toList();
  }

  /**
   * Retrieves a category by its ID.
   *
   * @param id the ID of the category to retrieve
   * @return an Optional containing the category if found, or empty if not found
   */
  public Optional<CategoryResponse> getById(Long id) {
    return categoryRepository.findById(id)
        .map(CategoryResponse::new);
  }
}
