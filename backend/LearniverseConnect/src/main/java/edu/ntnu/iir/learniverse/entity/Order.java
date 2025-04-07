package edu.ntnu.iir.learniverse.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long orderId;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  private BigDecimal totalPrice;
  private LocalDateTime orderDate;
  private boolean status; // true = paid/completed

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private List<OrderCourse> orderCourses;
}
