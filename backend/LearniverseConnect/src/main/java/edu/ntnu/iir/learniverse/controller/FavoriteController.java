package edu.ntnu.iir.learniverse.controller;

import edu.ntnu.iir.learniverse.annotation.CurrentUser;
import edu.ntnu.iir.learniverse.dto.FavoriteRequest;
import edu.ntnu.iir.learniverse.dto.FavoriteResponse;
import edu.ntnu.iir.learniverse.entity.User;
import edu.ntnu.iir.learniverse.service.FavoriteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for managing user favorites.
 */
@RestController
@RequestMapping("/api/favorites")
@Tag(name = "Favorites", description = "Endpoints for managing user favorites")
public class FavoriteController {
  private final FavoriteService favoriteService;

  /**
   * Constructor for FavoriteController.
   *
   * @param favoriteService the service to manage favorites
   */
  public FavoriteController(FavoriteService favoriteService) {
    this.favoriteService = favoriteService;
  }

  /**
   * Get all favorites for a user.
   *
   * @param userId the ID of the user
   * @return a list of favorites for the user
   */
  @Operation(summary = "Get all favorites", description = "Retrieve a list of all user favorites")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved list of favorites")
  @GetMapping("/user/{userId}")
  public List<FavoriteResponse> getByUser(@PathVariable Long userId) {
    return favoriteService.getByUser(userId);
  }

  /**
   * Add a new favorite for a user and course.
   *
   * @param request the request containing course ID
   * @return the created favorite
   */
  @Operation(
          summary = "Add favorite",
          description = "Add a new favorite for a user and course")
  @ApiResponse(responseCode = "200", description = "Successfully added favorite")
  @ApiResponse(responseCode = "400", description = "Invalid input")
  @ApiResponse(responseCode = "404", description = "User or course not found")
  @PostMapping("/add")
  public ResponseEntity<FavoriteResponse> add(
          @RequestBody FavoriteRequest request,
          @CurrentUser User user) {
    FavoriteResponse favorite = favoriteService.add(request, user);
    return ResponseEntity.status(HttpStatus.CREATED).body(favorite);
  }

  /**
   * Delete a favorite by ID.
   *
   * @param id the ID of the favorite to delete
   * @return a response entity with no content
   */
  @Operation(summary = "Delete favorite by ID", description = "Delete a favorite by its ID")
  @ApiResponse(responseCode = "204", description = "Successfully deleted the favorite")
  @ApiResponse(responseCode = "404", description = "Favorite not found")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    favoriteService.delete(id);
    return ResponseEntity.noContent().build();
  }

  /**
   * Delete a favorite by user ID and course ID.
   *
   * @param userId the ID of the user
   * @param courseId the ID of the course
   * @return a response entity with no content
   */
  @Operation(
          summary = "Delete favorite by user and course",
          description = "Delete a favorite by user ID and course ID")
  @ApiResponse(responseCode = "204", description = "Successfully deleted the favorite")
  @ApiResponse(responseCode = "404", description = "Favorite not found")
  @DeleteMapping("/user/{userId}/course/{courseId}")
  public ResponseEntity<Void> deleteByUserAndCourse(@PathVariable Long userId,
                                                    @PathVariable Long courseId) {
    favoriteService.deleteByUserAndCourse(userId, courseId);
    return ResponseEntity.noContent().build();
  }
}
