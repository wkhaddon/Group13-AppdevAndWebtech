package edu.ntnu.iir.learniverse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Entity
@Table(name = "order_courses")
public class OrderCourse {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "order_id")
  private Order order;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "course_id")
  private Course course;

  private int quantity;
}
