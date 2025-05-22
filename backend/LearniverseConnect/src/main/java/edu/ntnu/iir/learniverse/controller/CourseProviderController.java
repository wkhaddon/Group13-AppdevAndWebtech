package edu.ntnu.iir.learniverse.controller;

import edu.ntnu.iir.learniverse.entity.ProviderOrganization;
import edu.ntnu.iir.learniverse.service.CourseProviderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for managing course providers.
 */
@RestController
@RequestMapping("/api/providers")
@Tag(name = "Course Providers", description = "Endpoints for managing course providers")
public class CourseProviderController {
  private final CourseProviderService courseProviderService;

  /**
   * Constructor for CourseProviderController.
   *
   * @param courseProviderService the service for managing course providers
   */
  public CourseProviderController(CourseProviderService courseProviderService) {
    this.courseProviderService = courseProviderService;
  }

  /**
   * Get all course providers.
   *
   * @return a list of all course providers
   */
  @Operation(
          summary = "Get all course providers",
          description = "Retrieve a list of all course providers")
  @ApiResponse(
          responseCode = "200",
          description = "Successfully retrieved list of course providers")
  @PermitAll
  @GetMapping
  public List<ProviderOrganization> getAll() {
    return courseProviderService.getAll();
  }

  /**
   * Create a new course provider.
   *
   * @param cp the course provider to create
   * @return the created course provider
   */
  @Operation(
          summary = "Get course provider by ID",
          description = "Retrieve a course provider by its ID")
  @ApiResponse(responseCode = "201", description = "Successfully retrieved the course provider")
  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping
  public ResponseEntity<ProviderOrganization> create(@RequestBody ProviderOrganization cp) {
    return ResponseEntity.status(HttpStatus.CREATED).body(courseProviderService.save(cp));
  }
}
