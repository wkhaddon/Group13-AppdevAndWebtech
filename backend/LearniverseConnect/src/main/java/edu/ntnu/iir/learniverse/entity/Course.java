package edu.ntnu.iir.learniverse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

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
  @JsonIgnore
  @JoinColumn(name = "category_id")
  private Category category;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "provider_id")
  private ProviderOrganization provider;
}
