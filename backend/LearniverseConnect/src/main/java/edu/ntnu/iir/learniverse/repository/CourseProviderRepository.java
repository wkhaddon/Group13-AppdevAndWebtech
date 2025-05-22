package edu.ntnu.iir.learniverse.repository;

import edu.ntnu.iir.learniverse.entity.ProviderOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing course providers.
 */
@Repository
public interface CourseProviderRepository extends JpaRepository<ProviderOrganization, Long> {
}
