package edu.ntnu.iir.learniverse.service;

import edu.ntnu.iir.learniverse.dto.CourseCreateRequest;
import edu.ntnu.iir.learniverse.dto.CourseResponse;
import edu.ntnu.iir.learniverse.entity.Category;
import edu.ntnu.iir.learniverse.entity.Course;
import edu.ntnu.iir.learniverse.entity.User;
import edu.ntnu.iir.learniverse.repository.CategoryRepository;
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

  /**
   * Constructor for CourseService.
   *
   * @param courseRepository the repository for managing courses
   * @param categoryRepository the repository for managing categories
   */
  public CourseService(CourseRepository courseRepository,
                       CategoryRepository categoryRepository) {
    this.courseRepository = courseRepository;
    this.categoryRepository = categoryRepository;
  }

  /**
   * Get all courses.
   *
   * @return a list of all courses
   */
  public List<CourseResponse> getAllCourses() {
    List<Course> courses = courseRepository.findAll();

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
                                            Double minPrice, Double maxPrice) {
    if (query == null && categoryId == null && minPrice == null && maxPrice == null) {
      return getAllCourses();
    }

    List<Course> courses = courseRepository.searchCourses(query, categoryId, minPrice, maxPrice);
    return courses.stream()
        .map(CourseResponse::new)
        .toList();
  }

  /**
   * Get the minimum price of all courses.
   *
   * @return the minimum price of all courses
   */
  public Long getMaxPrice() {
    return courseRepository.getMaxPrice();
  }

  /**
   * Create a new course.
   *
   * @param course the course to create
   * @param user the user creating the course
   * @return the created course
   */
  public CourseResponse createCourse(CourseCreateRequest course, User user) {
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

    // Category from Id
    Long categoryId = course.categoryId();
    if (categoryId != null) {
      Category category = categoryRepository.findById(categoryId)
          .orElseThrow(() -> new IllegalArgumentException("Category not found"));
      newCourse.setCategory(category);
    }

    // TODO: Make sure user is member of the provider organization

    return new CourseResponse(courseRepository.save(newCourse));
  }

  /**
   * Update an existing course.
   *
   * @param id the ID of the course to update
   * @param user the user updating the course
   */
  public void deleteCourse(Long id, User user) {
    // TODO: Make sure user is member of the provider organization
    courseRepository.deleteById(id);
  }
}
