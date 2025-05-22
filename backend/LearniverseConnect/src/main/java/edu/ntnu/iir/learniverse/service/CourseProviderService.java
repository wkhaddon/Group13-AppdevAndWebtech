package edu.ntnu.iir.learniverse.service;

import edu.ntnu.iir.learniverse.dto.CourseProviderCreateRequest;
import edu.ntnu.iir.learniverse.dto.CourseProviderResponse;
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
  public List<CourseProviderResponse> getAll() {
    return courseProviderRepository.findAll()
            .stream().map(CourseProviderResponse::new).toList();
  }

  /**
   * Create a new course provider.
   *
   * @param cp the course provider to create
   * @return the created course provider
   */
  public CourseProviderResponse save(CourseProviderCreateRequest cp) {
    ProviderOrganization provider = new ProviderOrganization();
    provider.setName(cp.name());
    provider.setCurrency(cp.currency());
    return new CourseProviderResponse(courseProviderRepository.save(provider));
  }
}
