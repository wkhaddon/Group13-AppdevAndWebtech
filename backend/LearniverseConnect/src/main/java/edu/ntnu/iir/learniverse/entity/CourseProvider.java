package edu.ntnu.iir.learniverse.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "course_providers")
public class CourseProvider {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long courseProviderId;

  @ManyToOne
  @JoinColumn(name = "course_id", nullable = false)
  private Course course;

  @Column(nullable = false)
  private String providerName;

  @Column(nullable = false)
  private BigDecimal price;

  @Column(nullable = false)
  private String currency;
}
