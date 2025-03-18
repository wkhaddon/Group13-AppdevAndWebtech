package edu.ntnu.iir.learniverse.repository;

import edu.ntnu.iir.learniverse.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}