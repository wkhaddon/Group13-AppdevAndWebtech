package edu.ntnu.iir.learniverse.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

/**
 * Entity class representing a course in an order.
 */
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
