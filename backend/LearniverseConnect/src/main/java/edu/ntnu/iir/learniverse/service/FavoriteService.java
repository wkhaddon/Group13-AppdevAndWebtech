package edu.ntnu.iir.learniverse.service;

import edu.ntnu.iir.learniverse.entity.Favorite;
import edu.ntnu.iir.learniverse.repository.FavoriteRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Service class for managing user favorites.
 */
@Service
public class FavoriteService {
  private final FavoriteRepository favoriteRepository;

  /**
   * Constructor for FavoriteService.
   *
   * @param favoriteRepository the repository to manage favorites
   */
  public FavoriteService(FavoriteRepository favoriteRepository) {
    this.favoriteRepository = favoriteRepository;
  }

  /**
   * Get all favorites for a user.
   *
   * @param userId the ID of the user
   * @return a list of favorites for the user
   */
  public List<Favorite> getByUser(Long userId) {
    return favoriteRepository.findByUserId(userId);
  }

  /**
   * Get all favorites for a course.
   *
   * @param favorite the favorite object containing user and course information
   * @return the created favorite
   */
  public Favorite save(Favorite favorite) {
    return favoriteRepository.save(favorite);
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
