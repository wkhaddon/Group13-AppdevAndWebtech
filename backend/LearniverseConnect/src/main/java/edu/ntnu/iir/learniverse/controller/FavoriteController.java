package edu.ntnu.iir.learniverse.controller;

import edu.ntnu.iir.learniverse.entity.Favorite;
import edu.ntnu.iir.learniverse.service.FavoriteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {
  private final FavoriteService favoriteService;

  public FavoriteController(FavoriteService favoriteService) {
    this.favoriteService = favoriteService;
  }

  @GetMapping("/user/{userId}")
  public List<Favorite> getByUser(Long userId) {
    return favoriteService.getByUser(userId);
  }

  @PostMapping
  public ResponseEntity<Favorite> add(@RequestBody Favorite favorite) {
    return ResponseEntity.status(HttpStatus.CREATED).body(favoriteService.save(favorite));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    favoriteService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/user/{userId}/course/{courseId}")
  public ResponseEntity<Void> deleteByUserAndCourse(@PathVariable Long userId, @PathVariable Long courseId) {
    favoriteService.deleteByUserAndCourse(userId, courseId);
    return ResponseEntity.noContent().build();
  }
}
