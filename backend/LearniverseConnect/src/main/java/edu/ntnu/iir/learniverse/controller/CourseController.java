package edu.ntnu.iir.learniverse.controller;

import edu.ntnu.iir.learniverse.dto.CourseResponse;
import edu.ntnu.iir.learniverse.entity.Course;
import edu.ntnu.iir.learniverse.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@Tag(name = "Courses", description = "Endpoints for managing courses")
public class CourseController {
  private final CourseService courseService;

  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @Operation(summary = "Get all courses", description = "Retrieve a list of all available courses")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved list of courses")
  @GetMapping
  public List<CourseResponse> getAllCourses() {
    return courseService.getAllCourses();
  }

  @Operation(summary = "Get course by ID", description = "Retrieve a course by its ID")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved the course")
  @ApiResponse(responseCode = "404", description = "Course not found")
  @GetMapping("/{id}")
  public ResponseEntity<CourseResponse> getCourseById(@PathVariable Long id) {
    return courseService.getCourseById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @Operation(
      summary = "Search courses",
      description = "Search for courses by query, category, and optional price range"
  )
  @ApiResponse(responseCode = "200", description = "Successfully retrieved search results")
  @ApiResponse(responseCode = "400", description = "Invalid query parameters")
  @GetMapping("/search")
  public List<CourseResponse> searchCourses(
      @RequestParam(name = "q", required = false) String query,
      @RequestParam(name = "category", required = false) Long categoryId,
      @RequestParam(name = "minPrice", required = false) Double minPrice,
      @RequestParam(name = "maxPrice", required = false) Double maxPrice
  ) {
    return courseService.searchCourses(query, categoryId, minPrice, maxPrice);
  }

  @Operation(summary = "Create a new course", description = "Add a new course to the system")
  @ApiResponse(responseCode = "201", description = "Successfully created the course")
  @PostMapping
  public ResponseEntity<Course> createCourse(@RequestBody Course course) {
    Course createdCourse = courseService.createCourse(course);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
  }

  @Operation(summary = "Get the maximum price of courses", description = "Retrieve the maximum price of courses")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved the maximum price")
  @GetMapping("/maxPrice")
  public ResponseEntity<Long> getMaxPrice() {
    return ResponseEntity.ok(courseService.getMaxPrice());
  }

  @Operation(summary = "Delete a course", description = "Delete a course by its ID")
  @ApiResponse(responseCode = "204", description = "Successfully deleted the course")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
    courseService.deleteCourse(id);
    return ResponseEntity.noContent().build();
  }
}
