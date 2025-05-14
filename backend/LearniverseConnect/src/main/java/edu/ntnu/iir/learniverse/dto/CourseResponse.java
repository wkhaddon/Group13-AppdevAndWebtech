package edu.ntnu.iir.learniverse.dto;

import edu.ntnu.iir.learniverse.entity.Course;

public class CourseResponse {
  public final int id;
  public final String title;
  public final String description;
  public final String level;
  public final String ectsCredits;
  public final String hoursPerWeek;
  public final String sessionStartDate;
  public final String sessionEndDate;
  public final String relatedCertification;
  public final int categoryId;
  public final String categoryName;
  public final String providerName;

  public CourseResponse(Course course) {
    this.id = course.getId().intValue();
    this.title = course.getTitle();
    this.description = course.getDescription();
    this.level = course.getLevel().name();
    this.ectsCredits = course.getEctsCredits() != null ? course.getEctsCredits().toString() : null;
    this.hoursPerWeek = course.getHoursPerWeek() != null ? course.getHoursPerWeek().toString() : null;
    this.sessionStartDate = course.getSessionStartDate() != null ? course.getSessionStartDate().toString() : null;
    this.sessionEndDate = course.getSessionEndDate() != null ? course.getSessionEndDate().toString() : null;
    this.relatedCertification = course.getRelatedCertification();
    this.categoryId = course.getCategory().getCategoryId().intValue();
    this.categoryName = course.getCategory().getName();
    this.providerName = course.getProvider() != null ? course.getProvider().getProviderName() : null;
  }
}
