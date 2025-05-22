package edu.ntnu.iir.learniverse.service;

import edu.ntnu.iir.learniverse.dto.CourseCreateRequest;
import edu.ntnu.iir.learniverse.dto.CourseResponse;
import edu.ntnu.iir.learniverse.entity.Category;
import edu.ntnu.iir.learniverse.entity.Course;
import edu.ntnu.iir.learniverse.entity.ProviderOrganization;
import edu.ntnu.iir.learniverse.entity.User;
import edu.ntnu.iir.learniverse.exception.NoPermissionException;
import edu.ntnu.iir.learniverse.exception.NotFoundException;
import edu.ntnu.iir.learniverse.repository.CategoryRepository;
import edu.ntnu.iir.learniverse.repository.CourseProviderRepository;
import edu.ntnu.iir.learniverse.repository.CourseRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Service for managing courses.
 */
@Service
public class CourseService {
  private final CourseRepository courseRepository;
  private final CategoryRepository categoryRepository;
  private final CourseProviderRepository courseProviderRepository;

  /**
   * Constructor for CourseService.
   *
   * @param courseRepository the repository for managing courses
   * @param categoryRepository the repository for managing categories
   */
  public CourseService(CourseRepository courseRepository,
                       CategoryRepository categoryRepository,
                       CourseProviderRepository courseProviderRepository) {
    this.courseRepository = courseRepository;
    this.categoryRepository = categoryRepository;
    this.courseProviderRepository = courseProviderRepository;
  }

  /**
   * Get all courses.
   *
   * @param showHidden whether to include hidden courses
   * @return a list of all courses
   */
  public List<CourseResponse> getAllCourses(boolean showHidden) {
    List<Course> courses = courseRepository.findAll(showHidden);

    return courses.stream()
        .map(CourseResponse::new)
        .toList();
  }

  /**
   * Get a course by its ID.
   *
   * @param id the ID of the course
   * @return an Optional containing the course if found, or empty if not found
   */
  public Optional<CourseResponse> getCourseById(Long id) {
    Optional<Course> course = courseRepository.findById(id);
    return course.map(CourseResponse::new);
  }

  /**
   * Search for courses based on a query string, category ID, and price range.
   *
   * @param query the search query string
   * @param categoryId the category ID to filter by
   * @param minPrice the minimum price to filter by
   * @param maxPrice the maximum price to filter by
   * @return a list of courses matching the search criteria
   */
  public List<CourseResponse> searchCourses(String query, Long categoryId,
                                            Double minPrice, Double maxPrice,
                                            boolean showHidden) {
    if (query == null && categoryId == null && minPrice == null && maxPrice == null) {
      return getAllCourses(showHidden);
    }

    List<Course> courses = courseRepository.searchCourses(query, categoryId, minPrice, maxPrice, showHidden);
    return courses.stream()
        .map(CourseResponse::new)
        .toList();
  }

  /**
   * Get the minimum price of all courses.
   *
   * @return the minimum price of all courses
   */
  public Long getMaxPrice(boolean showHidden) {
    return courseRepository.getMaxPrice(showHidden);
  }

  /**
   * Create a new course.
   *
   * @param course the course to create
   * @param user the user creating the course
   * @return the created course
   */
  public CourseResponse createCourse(CourseCreateRequest course, User user) {
    if (!isUserInOrganization(course.providerId(), user)) {
      throw new NoPermissionException("User is not a member of the provider organization");
    }

    Course newCourse = new Course();
    newCourse.setTitle(course.title());
    newCourse.setDescription(course.description());
    newCourse.setLevel(course.level());
    newCourse.setPrice(course.price());
    newCourse.setSessionStartDate(course.startDate());
    newCourse.setSessionEndDate(course.endDate());
    newCourse.setEctsCredits(course.ectsCredits());
    newCourse.setHoursPerWeek(course.hoursPerWeek());
    newCourse.setRelatedCertification(course.relatedCertification());
    newCourse.setIsHidden(course.isHidden());
    newCourse.setProvider(courseProviderRepository.findById(course.providerId())
        .orElseThrow(() -> new NotFoundException("Provider not found")));

    // Category from Id
    Long categoryId = course.categoryId();
    if (categoryId != null) {
      Category category = categoryRepository.findById(categoryId)
              .orElseThrow(() -> new NotFoundException("Category not found"));
      newCourse.setCategory(category);
    }

    return new CourseResponse(courseRepository.save(newCourse));
  }

  /**
   * Update an existing course.
   *
   * @param id the ID of the course to update
   * @param user the user updating the course
   */
  public void deleteCourse(Long id, User user) {
    Optional<Course> courseOpt = courseRepository.findById(id);
    if (courseOpt.isEmpty()) {
      throw new NotFoundException("Course not found");
    }

    Course course = courseOpt.get();
    if (!isUserInOrganization(course.getProvider().getId(), user)) {
      throw new NoPermissionException("User is not a member of any provider organization");
    }

    courseRepository.deleteById(id);
  }

  /**
   * Toggle the visibility of a course.
   *
   * @param courseId the ID of the course to toggle visibility for
   */
  public void toggleVisibility(Long courseId) {
    Course course = courseRepository.findById(courseId, true)
            .orElseThrow(() -> new NotFoundException("Course not found: " + courseId));
    course.setIsHidden(!course.getIsHidden());
    courseRepository.save(course);
  }

  private boolean isUserInOrganization(Long providerId, User user) {
    Optional<ProviderOrganization> providerOpt = courseProviderRepository.findById(providerId);

    if (providerOpt.isEmpty()) {
      return false;
    }

    ProviderOrganization providerOrganization = providerOpt.get();
    return providerOrganization.getMemberships().stream()
        .anyMatch(member -> member.getUser().getId().equals(user.getId()));
  }
}
