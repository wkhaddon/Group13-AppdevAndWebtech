package edu.ntnu.iir.learniverse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity class representing a course.
 */
@Getter
@Setter
@Entity
@Table(name = "courses")
public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
  @Column(columnDefinition = "TEXT")
  private String description;

  @Enumerated(EnumType.STRING)
  private CourseLevel level;

  private BigDecimal price;
  private Boolean isHidden;

  @Column(name = "session_start_date")
  private LocalDate sessionStartDate;

  @Column(name = "session_end_date")
  private LocalDate sessionEndDate;

  @Column(name = "ects_credits")
  private BigDecimal ectsCredits;

  @Column(name = "hours_per_week")
  private BigDecimal hoursPerWeek;

  @Column(name = "related_certification")
  private String relatedCertification;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @ManyToOne
  @JoinColumn(name = "provider_id")
  private ProviderOrganization provider;

  @Column(name = "image_url")
  private String imageUrl;
}
