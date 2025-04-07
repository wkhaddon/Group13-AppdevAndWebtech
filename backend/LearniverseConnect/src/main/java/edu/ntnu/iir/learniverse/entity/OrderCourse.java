package edu.ntnu.iir.learniverse.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_courses")
public class OrderCourse {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long orderCourseId;

  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;

  @ManyToOne
  @JoinColumn(name = "course_id")
  private Course course;

  private Integer quantity;
}
