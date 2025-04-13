package edu.ntnu.iir.learniverse.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(columnDefinition = "TEXT", nullable=false)
  private String description;

  @Enumerated(EnumType.STRING)
  private CourseLevel level;

  private BigDecimal ectsCredits;
  private BigDecimal hoursPerWeek;

  private LocalDate sessionStartDate;
  private LocalDate sessionEndDate;

  private String relatedCertification;

  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  @OneToMany(mappedBy = "course")
  private List<Favorite> favorites;

  @OneToMany(mappedBy = "course")
  private List<CourseProvider> providers;

  @OneToMany(mappedBy = "course")
  private List<OrderCourse> orderCourses;
}
