package edu.ntnu.iir.learniverse.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Table(name = "course_providers")
public class CourseProvider {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long courseProviderId;

  @OneToMany(mappedBy = "provider")
  private List<Course> course;

  @Column(nullable = false)
  private String providerName;

  @Column(nullable = false)
  private BigDecimal price;

  @Column(nullable = false)
  private String currency;
}
