package edu.ntnu.iir.learniverse.service;

import edu.ntnu.iir.learniverse.entity.ProviderOrganization;
import edu.ntnu.iir.learniverse.repository.CourseProviderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseProviderService {
  private final CourseProviderRepository courseProviderRepository;

  public CourseProviderService(CourseProviderRepository courseProviderRepository) {
    this.courseProviderRepository = courseProviderRepository;
  }

  public List<ProviderOrganization> getAll() {
    return courseProviderRepository.findAll();
  }

  public ProviderOrganization save(ProviderOrganization cp) {
    return courseProviderRepository.save(cp);
  }
}
