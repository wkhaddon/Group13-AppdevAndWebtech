package edu.ntnu.iir.learniverse.repository;

import edu.ntnu.iir.learniverse.entity.CourseProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseProviderRepository extends JpaRepository<CourseProvider, Long> {
  List<CourseProvider> findByCourseId(Long courseId);
  List<CourseProvider> findByProviderId(Long providerId);
}
