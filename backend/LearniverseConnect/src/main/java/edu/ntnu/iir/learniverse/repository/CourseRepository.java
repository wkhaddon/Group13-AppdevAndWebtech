package edu.ntnu.iir.learniverse.repository;

import edu.ntnu.iir.learniverse.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
  List<Course> findByTitleContainingIgnoreCase(String query);
}
