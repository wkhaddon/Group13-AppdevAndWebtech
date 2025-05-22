package edu.ntnu.iir.learniverse.controller;

import edu.ntnu.iir.learniverse.entity.Favorite;
import edu.ntnu.iir.learniverse.service.FavoriteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@Tag(name = "Favorites", description = "Endpoints for managing user favorites")
public class FavoriteController {
  private final FavoriteService favoriteService;

  public FavoriteController(FavoriteService favoriteService) {
    this.favoriteService = favoriteService;
  }

  @Operation(summary = "Get all favorites", description = "Retrieve a list of all user favorites")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved list of favorites")
  @GetMapping("/user/{userId}")
  public List<Favorite> getByUser(@PathVariable Long userId) {
    return favoriteService.getByUser(userId);
  }

  @Operation(summary = "Get favorite by user and course", description = "Retrieve a favorite by user ID and course ID")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved the favorite")
  @PostMapping
  public ResponseEntity<Favorite> add(@RequestBody Favorite favorite) {
    return ResponseEntity.status(HttpStatus.CREATED).body(favoriteService.save(favorite));
  }

  @Operation(summary = "Delete favorite by ID", description = "Delete a favorite by its ID")
  @ApiResponse(responseCode = "204", description = "Successfully deleted the favorite")
  @ApiResponse(responseCode = "404", description = "Favorite not found")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    favoriteService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "Delete favorite by user and course", description = "Delete a favorite by user ID and course ID")
  @ApiResponse(responseCode = "204", description = "Successfully deleted the favorite")
  @ApiResponse(responseCode = "404", description = "Favorite not found")
  @DeleteMapping("/user/{userId}/course/{courseId}")
  public ResponseEntity<Void> deleteByUserAndCourse(@PathVariable Long userId, @PathVariable Long courseId) {
    favoriteService.deleteByUserAndCourse(userId, courseId);
    return ResponseEntity.noContent().build();
  }
}
