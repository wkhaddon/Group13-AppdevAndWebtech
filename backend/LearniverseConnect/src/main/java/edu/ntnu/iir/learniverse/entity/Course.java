package edu.ntnu.iir.learniverse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  @Column(length = 1000)
  private String description;

  private String level;
  private String keywords;
  private double ectsCredits;
  private int hoursPerWeek;

  private LocalDate closestSession;

  private String relatedCertification;

  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
  private List<CourseProvider> courseProviders;
}
