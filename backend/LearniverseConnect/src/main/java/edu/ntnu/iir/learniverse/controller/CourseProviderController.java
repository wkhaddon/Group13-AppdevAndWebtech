package edu.ntnu.iir.learniverse.controller;

import edu.ntnu.iir.learniverse.entity.CourseProvider;
import edu.ntnu.iir.learniverse.repository.CourseProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course-providers")
public class CourseProviderController {
  @Autowired
  private CourseProviderRepository courseProviderRepository;

  @GetMapping
  public List<CourseProvider> getAllCourseProviders() {
    return courseProviderRepository.findAll();
  }

  @GetMapping("/course/{courseId}")
  public List<CourseProvider> getCourseProvidersByCourseId(@PathVariable Long courseId) {
    return courseProviderRepository.findByCourseId(courseId);
  }

  @GetMapping("/provider/{providerId}")
  public List<CourseProvider> getCourseProvidersByProviderId(@PathVariable Long providerId) {
    return courseProviderRepository.findByProviderId(providerId);
  }

  @PostMapping
  public CourseProvider createCourseProvider(@RequestBody CourseProvider courseProvider) {
    return courseProviderRepository.save(courseProvider);
  }
}
