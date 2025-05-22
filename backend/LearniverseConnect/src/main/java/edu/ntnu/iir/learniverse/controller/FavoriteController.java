package edu.ntnu.iir.learniverse.controller;

import edu.ntnu.iir.learniverse.entity.Favorite;
import edu.ntnu.iir.learniverse.service.FavoriteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import edu.ntnu.iir.learniverse.dto.FavoriteRequest;
import edu.ntnu.iir.learniverse.entity.User;
import edu.ntnu.iir.learniverse.entity.Course;
import edu.ntnu.iir.learniverse.repository.UserRepository;
import edu.ntnu.iir.learniverse.repository.CourseRepository;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {
  private final FavoriteService favoriteService;
  private final UserRepository userRepository;
  private final CourseRepository courseRepository;

  public FavoriteController(FavoriteService favoriteService, UserRepository userRepository, CourseRepository courseRepository) {
    this.favoriteService = favoriteService;
    this.userRepository = userRepository;
    this.courseRepository = courseRepository;
  }

  @GetMapping("/user/{userId}")
  public List<Favorite> getByUser(@PathVariable Long userId) {
    return favoriteService.getByUser(userId);
  }

  @PostMapping
  public ResponseEntity<Favorite> add(@RequestBody FavoriteRequest request) {
    User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found"));
    Course course = courseRepository.findById(request.getCourseId())
            .orElseThrow(() -> new RuntimeException("Course not found"));

    Favorite favorite = new Favorite();
    favorite.setUser(user);
    favorite.setCourse(course);

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
