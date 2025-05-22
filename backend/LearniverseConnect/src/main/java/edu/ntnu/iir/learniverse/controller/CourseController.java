package edu.ntnu.iir.learniverse.controller;

import edu.ntnu.iir.learniverse.annotation.CurrentUser;
import edu.ntnu.iir.learniverse.dto.CourseCreateRequest;
import edu.ntnu.iir.learniverse.dto.CourseResponse;
import edu.ntnu.iir.learniverse.entity.User;
import edu.ntnu.iir.learniverse.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling course-related requests.
 */
@RestController
@RequestMapping("/api/courses")
@Tag(name = "Courses", description = "Endpoints for managing courses")
public class CourseController {
  private final CourseService courseService;

  /**
   * Constructor for CourseController.
   *
   * @param courseService the course service for handling course-related operations
   */
  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  /**
   * Handles the request to get all courses.
   *
   * @return a list of all courses
   */
  @Operation(summary = "Get all courses", description = "Retrieve a list of all available courses")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved list of courses")
  @PermitAll
  @GetMapping
  public List<CourseResponse> getAllCourses() {
    return courseService.getAllCourses();
  }

  /**
   * Handles the request to get a course by its ID.
   *
   * @param id the ID of the course to retrieve
   * @return the course with the specified ID, or a 404 response if not found
   */
  @Operation(summary = "Get course by ID", description = "Retrieve a course by its ID")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved the course")
  @ApiResponse(responseCode = "404", description = "Course not found")
  @PermitAll
  @GetMapping("/{id}")
  public ResponseEntity<CourseResponse> getCourseById(@PathVariable Long id) {
    return courseService.getCourseById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  /**
   * Handles the request to search for courses based on query parameters.
   *
   * @param query The search query string - null to search all courses
   * @param categoryId The ID of the category to filter by - null to search all categories
   * @param minPrice The minimum price to filter by - null to ignore minimum price
   * @param maxPrice The maximum price to filter by - null to ignore maximum price
   * @return a list of courses matching the search criteria
   */
  @Operation(
      summary = "Search courses",
      description = "Search for courses by query, category, and optional price range"
  )
  @ApiResponse(responseCode = "200", description = "Successfully retrieved search results")
  @ApiResponse(responseCode = "400", description = "Invalid query parameters")
  @PermitAll
  @GetMapping("/search")
  public List<CourseResponse> searchCourses(
      @RequestParam(name = "q", required = false) String query,
      @RequestParam(name = "category", required = false) Long categoryId,
      @RequestParam(name = "minPrice", required = false) Double minPrice,
      @RequestParam(name = "maxPrice", required = false) Double maxPrice
  ) {
    return courseService.searchCourses(query, categoryId, minPrice, maxPrice);
  }

  /**
   * Handles the request to create a new course.
   *
   * @param course the course data to create
   * @param user the current user creating the course
   * @return the created course
   */
  @Operation(summary = "Create a new course", description = "Add a new course to the system")
  @ApiResponse(responseCode = "201", description = "Successfully created the course")
  @PermitAll
  @PostMapping
  public ResponseEntity<CourseResponse> createCourse(@RequestBody CourseCreateRequest course,
                                                     @CurrentUser User user) {
    CourseResponse createdCourse = courseService.createCourse(course, user);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
  }

  /**
   * Handles the request to get the minimum price of courses.
   *
   * @return the minimum price of courses
   */
  @Operation(
          summary = "Get the maximum price of courses",
          description = "Retrieve the maximum price of courses")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved the maximum price")
  @PermitAll
  @GetMapping("/maxPrice")
  public ResponseEntity<Long> getMaxPrice() {
    return ResponseEntity.ok(courseService.getMaxPrice());
  }

  /**
   * Handles the request to get the minimum price of courses.
   *
   * @param id the ID of the course to retrieve the minimum price for
   * @param user the current user
   * @return the minimum price of the course
   */
  @Operation(summary = "Delete a course", description = "Delete a course by its ID")
  @ApiResponse(responseCode = "204", description = "Successfully deleted the course")
  @DeleteMapping("/{id}")
  @PreAuthorize("hasAnyRole('PROVIDER', 'SUPPORT', 'ADMIN')")
  public ResponseEntity<Void> deleteCourse(@PathVariable Long id,
                                           @CurrentUser User user) {
    courseService.deleteCourse(id, user);
    return ResponseEntity.noContent().build();
  }
}
