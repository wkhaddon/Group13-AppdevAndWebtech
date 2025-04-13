package edu.ntnu.iir.learniverse.service;

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

  public List<Course> getAllCourses() {
    return courseRepository.findAll();
  }

  public Optional<Course> getCourseById(Long id) {
    return courseRepository.findById(id);
  }

  public List<Course> searchCourses(String query) {
    return courseRepository.findByTitleContainingIgnoreCase(query);
  }

  public Course createCourse(Course course) {
    return courseRepository.save(course);
  }

  public void deleteCourse(Long id) {
    courseRepository.deleteById(id);
  }
}
