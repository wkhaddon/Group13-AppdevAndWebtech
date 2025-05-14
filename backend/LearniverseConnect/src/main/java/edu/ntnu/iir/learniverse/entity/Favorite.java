package edu.ntnu.iir.learniverse.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "favorites")
public class Favorite {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "favorite_id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "course_id", nullable = false)
  private Course course;
}
