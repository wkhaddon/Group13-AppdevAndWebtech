package edu.ntnu.iir.learniverse.repository;

import edu.ntnu.iir.learniverse.entity.CourseProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseProviderRepository extends JpaRepository<CourseProvider, Long> {
}
