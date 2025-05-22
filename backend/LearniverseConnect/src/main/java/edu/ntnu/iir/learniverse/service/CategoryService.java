package edu.ntnu.iir.learniverse.service;

import edu.ntnu.iir.learniverse.dto.CategoryDto;
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

  public List<CategoryDto> getAll() {
    return categoryRepository.findAll().stream().map(CategoryDto::fromCategory).toList();
  }

  public Optional<CategoryDto> getById(Long id) {
    return categoryRepository.findById(id)
        .map(CategoryDto::fromCategory);
  }
}
