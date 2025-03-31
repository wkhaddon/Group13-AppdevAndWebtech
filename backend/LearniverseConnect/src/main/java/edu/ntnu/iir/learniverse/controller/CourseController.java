package edu.ntnu.iir.learniverse.controller;

import edu.ntnu.iir.learniverse.entity.Course;
import edu.ntnu.iir.learniverse.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
  @Autowired
  private CourseRepository courseRepository;

  @GetMapping
  public List<Course> getAllCourses() {
    return courseRepository.findAll();
  }

  @GetMapping("/{id}")
  public Course getCourseById(@PathVariable Long id) {
    return courseRepository.findById(id).orElse(null);
  }

  @PostMapping
  public Course createCourse(@RequestBody Course course) {
    return courseRepository.save(course);
  }
}
