package edu.ntnu.iir.learniverse.dto;

import edu.ntnu.iir.learniverse.entity.Course;
import edu.ntnu.iir.learniverse.entity.CourseLevel;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for Course.
 *
 * @param id The ID of the course
 * @param title The title of the course
 * @param description The description of the course
 * @param level The level of the course
 * @param price The price of the course
 * @param startDate The start date of the course
 * @param endDate The end date of the course
 * @param ectsCredits The ECTS credits of the course
 * @param hoursPerWeek The hours per week of the course
 * @param relatedCertification The related certification of the course
 * @param categoryId The ID of the category
 * @param categoryName The name of the category
 * @param providerId The ID of the provider
 * @param providerName The name of the provider
 */
public record CourseResponse(
    Long id,
    String title,
    String description,
    String imageUrl,
    CourseLevel level,
    BigDecimal price,
    LocalDate startDate,
    LocalDate endDate,
    BigDecimal ectsCredits,
    BigDecimal hoursPerWeek,
    String relatedCertification,
    Long categoryId,
    String categoryName,
    Long providerId,
    String providerName,
    Boolean isHidden
) {
  /**
   * Constructor for CourseResponse.
   *
   * @param course The course entity
   */
  public CourseResponse(Course course) {
    this(
        course.getId(),
        course.getTitle(),
        course.getDescription(),
        course.getImageUrl(),
        course.getLevel(),
        course.getPrice(),
        course.getSessionStartDate(),
        course.getSessionEndDate(),
        course.getEctsCredits(),
        course.getHoursPerWeek(),
        course.getRelatedCertification(),
        course.getCategory() != null ? course.getCategory().getId() : null,
        course.getCategory() != null ? course.getCategory().getName() : null,
        course.getProvider() != null ? course.getProvider().getId() : null,
        course.getProvider() != null ? course.getProvider().getName() : null,
        course.getIsHidden()
    );
  }
}
