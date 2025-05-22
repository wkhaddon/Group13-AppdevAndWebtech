package edu.ntnu.iir.learniverse.dto;

import edu.ntnu.iir.learniverse.entity.CourseLevel;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) for creating a course.
 *
 * @param title The title of the course
 * @param description The description of the course
 * @param level The level of the course (e.g., beginner, intermediate, advanced)
 * @param price The price of the course
 * @param startDate The start date of the course
 * @param endDate The end date of the course
 * @param ectsCredits The ECTS credits for the course
 * @param hoursPerWeek The number of hours per week for the course
 * @param relatedCertification The related certification for the course
 * @param isHidden Whether the course is hidden or not
 * @param categoryId The ID of the category the course belongs to
 * @param providerId The ID of the provider offering the course
 */
public record CourseCreateRequest(
    String title,
    String description,
    CourseLevel level,
    BigDecimal price,
    LocalDate startDate,
    LocalDate endDate,
    BigDecimal ectsCredits,
    BigDecimal hoursPerWeek,
    String relatedCertification,
    Boolean isHidden,
    Long categoryId,
    Long providerId
) {
}
