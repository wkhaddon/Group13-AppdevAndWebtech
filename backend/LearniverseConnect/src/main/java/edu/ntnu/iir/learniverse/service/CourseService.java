package edu.ntnu.iir.learniverse.service;

import edu.ntnu.iir.learniverse.dto.CourseResponse;
import edu.ntnu.iir.learniverse.entity.Course;
import edu.ntnu.iir.learniverse.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
  private final CourseRepository courseRepository;

  public CourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public List<CourseResponse> getAllCourses() {
    List<Course> courses = courseRepository.findAll();

    return courses.stream()
        .map(CourseResponse::new)
        .toList();
  }

  public Optional<CourseResponse> getCourseById(Long id) {
    Optional<Course> course = courseRepository.findById(id);
    return course.map(CourseResponse::new);
  }

  public List<CourseResponse> searchCourses(String query, Long categoryId, Double minPrice, Double maxPrice) {
    if (query == null && categoryId == null && minPrice == null && maxPrice == null) {
      return getAllCourses();
    }

    List<Course> courses = courseRepository.searchCourses(query, categoryId, minPrice, maxPrice);
    return courses.stream()
        .map(CourseResponse::new)
        .toList();
  }

  public Course createCourse(Course course) {
    return courseRepository.save(course);
  }

  public void deleteCourse(Long id) {
    courseRepository.deleteById(id);
  }
}
