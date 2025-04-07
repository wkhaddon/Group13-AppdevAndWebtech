package edu.ntnu.iir.learniverse.controller;

import edu.ntnu.iir.learniverse.entity.Course;
import edu.ntnu.iir.learniverse.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
  private final CourseService courseService;

  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @GetMapping
  public List<Course> getAllCourses() {
    return courseService.getAllCourses();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
    return courseService.getCourseById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/search")
  public List<Course> searchCourses(@RequestParam("q") String query) {
    return courseService.searchCourses(query);
  }

  @PostMapping
  public ResponseEntity<Course> createCourse(@RequestBody Course course) {
    Course createdCourse = courseService.createCourse(course);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
    courseService.deleteCourse(id);
    return ResponseEntity.noContent().build();
  }
}
