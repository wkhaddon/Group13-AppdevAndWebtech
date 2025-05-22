package edu.ntnu.iir.learniverse.repository;

import edu.ntnu.iir.learniverse.entity.Favorite;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing user favorites.
 */
@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
  /**
   * Find all favorites for a user.
   *
   * @param userId the ID of the user
   * @return a list of favorites for the user
   */
  List<Favorite> findByUserId(Long userId);

  /**
   * Find the favorite instance by user ID and course ID.
   *
   * @param userId the ID of the user
   * @param courseId the ID of the course
   * @return an optional favorite instance
   */
  Optional<Favorite> findByUserIdAndCourseId(Long userId, Long courseId);
}
