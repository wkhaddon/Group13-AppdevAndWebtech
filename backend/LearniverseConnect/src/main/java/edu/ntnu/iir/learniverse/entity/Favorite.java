package edu.ntnu.iir.learniverse.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "favorites")
public class Favorite {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "favorite_id")
  private Long id;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "course_id", nullable = false)
  private Course course;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

}
