package edu.ntnu.iir.learniverse.controller;

import edu.ntnu.iir.learniverse.entity.Category;
import edu.ntnu.iir.learniverse.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping
  public List<Category> getAllCategories() {
    return categoryService.getAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
    return categoryService.getById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }
}
