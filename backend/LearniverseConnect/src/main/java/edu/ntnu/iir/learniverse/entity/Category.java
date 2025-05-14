package edu.ntnu.iir.learniverse.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
@Table(name = "categories")
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long categoryId;

  @Column(nullable = false, unique = true)
  private String name;

  @Column(columnDefinition = "TEXT")
  private String description;

  @OneToMany(mappedBy = "category")
  private List<Course> courses;
}
