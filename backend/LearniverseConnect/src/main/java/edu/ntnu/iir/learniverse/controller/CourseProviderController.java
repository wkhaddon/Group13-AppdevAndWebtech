package edu.ntnu.iir.learniverse.controller;

import edu.ntnu.iir.learniverse.entity.ProviderOrganization;
import edu.ntnu.iir.learniverse.service.CourseProviderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/providers")
public class CourseProviderController {
  private final CourseProviderService courseProviderService;

  public CourseProviderController(CourseProviderService courseProviderService) {
    this.courseProviderService = courseProviderService;
  }

  @GetMapping
  public List<ProviderOrganization> getAll() {
    return courseProviderService.getAll();
  }

  @PostMapping
  public ResponseEntity<ProviderOrganization> create(@RequestBody ProviderOrganization cp) {
    return ResponseEntity.status(HttpStatus.CREATED).body(courseProviderService.save(cp));
  }
}
