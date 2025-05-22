package edu.ntnu.iir.learniverse.repository;

import edu.ntnu.iir.learniverse.entity.Course;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing courses.
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
  /**
   * Search for courses based on a query string, category ID, and price range.
   *
   * @param query the search query string
   * @param categoryId the category ID to filter by
   * @param minPrice the minimum price to filter by
   * @param maxPrice the maximum price to filter by
   * @return a list of courses matching the search criteria
   */
  @Query("""
      SELECT c FROM Course c
          WHERE (:query IS NULL OR LOWER(c.title)
          LIKE LOWER(CONCAT('%', CAST(:query AS string), '%')))
          AND (:categoryId IS NULL OR c.category.id = :categoryId)
          AND (:minPrice IS NULL OR c.price >= :minPrice)
          AND (:maxPrice IS NULL OR c.price <= :maxPrice)
      """)
  List<Course> searchCourses(
      @Param("query") String query,
      @Param("categoryId") Long categoryId,
      @Param("minPrice") Double minPrice,
      @Param("maxPrice") Double maxPrice
  );

  /**
   * Get the minimum price of all courses.
   *
   * @return the minimum price of all courses
   */
  @Query("SELECT MAX(c.price) FROM Course c")
  Long getMaxPrice();

}
