package edu.ntnu.iir.learniverse.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
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
  private boolean isHidden;

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
}
