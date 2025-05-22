package edu.ntnu.iir.learniverse.dto;

import edu.ntnu.iir.learniverse.entity.CourseLevel;

import java.math.BigDecimal;
import java.time.LocalDate;

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
