package edu.ntnu.iir.learniverse.service;

import edu.ntnu.iir.learniverse.dto.CourseCreateRequest;
import edu.ntnu.iir.learniverse.dto.CourseResponse;
import edu.ntnu.iir.learniverse.entity.Category;
import edu.ntnu.iir.learniverse.entity.Course;
import edu.ntnu.iir.learniverse.entity.User;
import edu.ntnu.iir.learniverse.repository.CategoryRepository;
import edu.ntnu.iir.learniverse.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
  private final CourseRepository courseRepository;
  private final CategoryRepository categoryRepository;

  public CourseService(CourseRepository courseRepository,
                       CategoryRepository categoryRepository) {
    this.courseRepository = courseRepository;
    this.categoryRepository = categoryRepository;
  }

  public List<CourseResponse> getAllCourses() {
    List<Course> courses = courseRepository.findAll();

    return courses.stream()
        .map(CourseResponse::new)
        .toList();
  }

  public Optional<CourseResponse> getCourseById(Long id) {
    Optional<Course> course = courseRepository.findById(id);
    return course.map(CourseResponse::new);
  }

  public List<CourseResponse> searchCourses(String query, Long categoryId, Double minPrice, Double maxPrice) {
    if (query == null && categoryId == null && minPrice == null && maxPrice == null) {
      return getAllCourses();
    }

    List<Course> courses = courseRepository.searchCourses(query, categoryId, minPrice, maxPrice);
    return courses.stream()
        .map(CourseResponse::new)
        .toList();
  }

  public Long getMaxPrice() {
    return courseRepository.getMaxPrice();
  }

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

  public void deleteCourse(Long id, User user) {
    // TODO: Make sure user is member of the provider organization
    courseRepository.deleteById(id);
  }
}
