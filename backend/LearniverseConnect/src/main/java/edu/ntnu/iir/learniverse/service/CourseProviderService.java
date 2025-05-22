package edu.ntnu.iir.learniverse.service;

import edu.ntnu.iir.learniverse.entity.ProviderOrganization;
import edu.ntnu.iir.learniverse.repository.CourseProviderRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Service for managing course providers.
 */
@Service
public class CourseProviderService {
  private final CourseProviderRepository courseProviderRepository;

  /**
   * Constructor for CourseProviderService.
   *
   * @param courseProviderRepository the repository for managing course providers
   */
  public CourseProviderService(CourseProviderRepository courseProviderRepository) {
    this.courseProviderRepository = courseProviderRepository;
  }

  /**
   * Get all course providers.
   *
   * @return a list of all course providers
   */
  public List<ProviderOrganization> getAll() {
    return courseProviderRepository.findAll();
  }

  /**
   * Create a new course provider.
   *
   * @param cp the course provider to create
   * @return the created course provider
   */
  public ProviderOrganization save(ProviderOrganization cp) {
    return courseProviderRepository.save(cp);
  }
}
