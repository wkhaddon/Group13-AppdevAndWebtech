package edu.ntnu.iir.learniverse.service;

import edu.ntnu.iir.learniverse.entity.Category;
import edu.ntnu.iir.learniverse.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
  private final CategoryRepository categoryRepository;

  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public List<Category> getAll() {
    return categoryRepository.findAll();
  }

  public Optional<Category> getById(Long id) {
    return categoryRepository.findById(id);
  }
}
