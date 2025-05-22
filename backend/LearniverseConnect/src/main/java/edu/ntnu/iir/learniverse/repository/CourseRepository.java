package edu.ntnu.iir.learniverse.repository;

import edu.ntnu.iir.learniverse.entity.Course;
import java.util.List;
import java.util.Optional;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing courses.
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
  @Query("SELECT c FROM Course c WHERE c.isHidden = FALSE")
  @NonNull
  @Override
  List<Course> findAll();

  /**
   * Find all courses, optionally including hidden ones.
   *
   * @param showHidden whether to include hidden courses
   * @return a list of courses
   */
  @Query("SELECT c FROM Course c WHERE (:showHidden = TRUE OR c.isHidden = FALSE)")
  @NonNull
  List<Course> findAll(@Param("showHidden") boolean showHidden);

  @Query("SELECT c FROM Course c WHERE c.id = :id AND (:showHidden = TRUE OR c.isHidden = FALSE)")
  @NonNull
  Optional<Course> findById(@NonNull Long id, boolean showHidden);

  /**
   * Search for courses based on a query string, category ID, and price range.
   *
   * @param query the search query string
   * @param categoryId the category ID to filter by
   * @param minPrice the minimum price to filter by
   * @param maxPrice the maximum price to filter by
   * @param showHidden whether to include hidden courses
   * @return a list of courses matching the search criteria
   */
  @Query("""
    SELECT c FROM Course c
        WHERE (:query IS NULL OR LOWER(c.title)
            LIKE LOWER(CONCAT('%', CAST(:query AS string), '%')))
        AND (:categoryId IS NULL OR c.category.id = :categoryId)
        AND (:minPrice IS NULL OR c.price >= :minPrice)
        AND (:maxPrice IS NULL OR c.price <= :maxPrice)
        AND (:showHidden = TRUE OR c.isHidden = FALSE)
    """)
  List<Course> searchCourses(
          @Param("query") String query,
          @Param("categoryId") Long categoryId,
          @Param("minPrice") Double minPrice,
          @Param("maxPrice") Double maxPrice,
          @Param("showHidden") boolean showHidden
  );

  /**
   * Get the minimum price of all courses.
   *
   * @return the minimum price of all courses
   */
  @Query("SELECT MAX(c.price) FROM Course c WHERE (:showHidden = TRUE OR c.isHidden = FALSE)")
  Long getMaxPrice(boolean showHidden);

}
