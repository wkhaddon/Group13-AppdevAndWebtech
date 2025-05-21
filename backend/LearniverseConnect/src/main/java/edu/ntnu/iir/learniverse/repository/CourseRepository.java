package edu.ntnu.iir.learniverse.repository;

import edu.ntnu.iir.learniverse.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
  @Query("""
    SELECT c FROM Course c
    WHERE (:query IS NULL OR LOWER(c.title) LIKE LOWER(CONCAT('%', CAST(:query AS string), '%')))
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

  @Query("SELECT MAX(c.price) FROM Course c")
  Long getMaxPrice();

}
