package edu.ntnu.iir.learniverse.dto;

import edu.ntnu.iir.learniverse.entity.Course;
import edu.ntnu.iir.learniverse.entity.CourseLevel;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CourseResponse(
    Long id,
    String title,
    String description,
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
    String providerName
) {
  public CourseResponse(Course course) {
    this(
        course.getId(),
        course.getTitle(),
        course.getDescription(),
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
        course.getProvider() != null ? course.getProvider().getName() : null
    );
  }
}
