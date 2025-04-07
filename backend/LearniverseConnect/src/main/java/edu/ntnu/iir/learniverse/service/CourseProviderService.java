package edu.ntnu.iir.learniverse.service;

import edu.ntnu.iir.learniverse.entity.CourseProvider;
import edu.ntnu.iir.learniverse.repository.CourseProviderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseProviderService {
  private final CourseProviderRepository courseProviderRepository;

  public CourseProviderService(CourseProviderRepository courseProviderRepository) {
    this.courseProviderRepository = courseProviderRepository;
  }

  public List<CourseProvider> getAll() {
    return courseProviderRepository.findAll();
  }

  public CourseProvider save(CourseProvider cp) {
    return courseProviderRepository.save(cp);
  }
}
