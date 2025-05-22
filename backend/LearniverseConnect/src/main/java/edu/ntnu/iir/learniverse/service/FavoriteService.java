package edu.ntnu.iir.learniverse.service;

import edu.ntnu.iir.learniverse.dto.FavoriteRequest;
import edu.ntnu.iir.learniverse.dto.FavoriteResponse;
import edu.ntnu.iir.learniverse.entity.Course;
import edu.ntnu.iir.learniverse.entity.Favorite;
import edu.ntnu.iir.learniverse.entity.User;
import edu.ntnu.iir.learniverse.exception.NotFoundException;
import edu.ntnu.iir.learniverse.repository.CourseRepository;
import edu.ntnu.iir.learniverse.repository.FavoriteRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Service class for managing user favorites.
 */
@Service
public class FavoriteService {
  private final FavoriteRepository favoriteRepository;
  private final CourseRepository courseRepository;

  /**
   * Constructor for FavoriteService.
   *
   * @param favoriteRepository the repository to manage favorites
   * @param courseRepository the repository to manage courses
   */
  public FavoriteService(FavoriteRepository favoriteRepository,
                         CourseRepository courseRepository) {
    this.favoriteRepository = favoriteRepository;
    this.courseRepository = courseRepository;
  }

  /**
   * Get all favorites for a user.
   *
   * @param userId the ID of the user
   * @return a list of favorites for the user
   */
  public List<FavoriteResponse> getByUser(Long userId) {
    return favoriteRepository.findByUserId(userId).stream().map(FavoriteResponse::new).toList();
  }

  /**
   * Add a new favorite for a user and course.
   *
   * @param courseId the ID of the course
   * @param user the User to associate with the favorite
   * @return the created favorite
   */
  public FavoriteResponse add(FavoriteRequest request, User user) {
    Course course = courseRepository.findById(request.courseId())
        .orElseThrow(() -> new NotFoundException("Course not found"));

    Favorite favorite = new Favorite();
    favorite.setCourse(course);
    favorite.setUser(user);
    return new FavoriteResponse(favoriteRepository.save(favorite));
  }

  /**
   * Delete a favorite by ID.
   *
   * @param id the ID of the favorite to delete
   */
  public void delete(Long id) {
    favoriteRepository.deleteById(id);
  }

  /**
   * Delete a favorite by user ID and course ID.
   *
   * @param userId the ID of the user
   * @param courseId the ID of the course
   */
  public void deleteByUserAndCourse(Long userId, Long courseId) {
    favoriteRepository.findByUserIdAndCourseId(userId, courseId)
        .ifPresent(favoriteRepository::delete);
  }
}
