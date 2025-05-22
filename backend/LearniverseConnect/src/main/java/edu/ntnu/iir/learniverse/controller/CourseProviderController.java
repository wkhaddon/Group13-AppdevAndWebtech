package edu.ntnu.iir.learniverse.controller;

import edu.ntnu.iir.learniverse.entity.ProviderOrganization;
import edu.ntnu.iir.learniverse.service.CourseProviderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/providers")
@Tag(name = "Course Providers", description = "Endpoints for managing course providers")
public class CourseProviderController {
  private final CourseProviderService courseProviderService;

  public CourseProviderController(CourseProviderService courseProviderService) {
    this.courseProviderService = courseProviderService;
  }

  @Operation(summary = "Get all course providers", description = "Retrieve a list of all course providers")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved list of course providers")
  @PermitAll
  @GetMapping
  public List<ProviderOrganization> getAll() {
    return courseProviderService.getAll();
  }

  @Operation(summary = "Get course provider by ID", description = "Retrieve a course provider by its ID")
  @ApiResponse(responseCode = "201", description = "Successfully retrieved the course provider")
  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping
  public ResponseEntity<ProviderOrganization> create(@RequestBody ProviderOrganization cp) {
    return ResponseEntity.status(HttpStatus.CREATED).body(courseProviderService.save(cp));
  }
}
