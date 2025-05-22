package edu.ntnu.iir.learniverse.service;

import edu.ntnu.iir.learniverse.dto.OrderCreateRequest;
import edu.ntnu.iir.learniverse.dto.OrderResponse;
import edu.ntnu.iir.learniverse.entity.Course;
import edu.ntnu.iir.learniverse.entity.Order;
import edu.ntnu.iir.learniverse.entity.OrderCourse;
import edu.ntnu.iir.learniverse.entity.User;
import edu.ntnu.iir.learniverse.exception.NotFoundException;
import edu.ntnu.iir.learniverse.repository.CourseRepository;
import edu.ntnu.iir.learniverse.repository.OrderRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Service class for managing orders.
 */
@Service
public class OrderService {
  private final OrderRepository orderRepository;
  private final CourseRepository courseRepository;

  /**
   * Constructor for OrderService.
   *
   * @param orderRepository the repository to manage orders
   * @param courseRepository the repository to manage courses
   */
  public OrderService(OrderRepository orderRepository,
                      CourseRepository courseRepository) {
    this.orderRepository = orderRepository;
    this.courseRepository = courseRepository;
  }

  /**
   * Get all orders for a user.
   *
   * @param userId the ID of the user
   * @return a list of orders for the user
   */
  public List<OrderResponse> getAllByUser(Long userId) {
    return orderRepository.findByUserId(userId).stream().map(OrderResponse::new).toList();
  }

  /**
   * Get an order by ID.
   *
   * @param orderId the ID of the order
   * @return an Optional containing the order if found, or empty if not found
   */
  public Optional<OrderResponse> getById(Long orderId) {
    return orderRepository.findById(orderId).map(OrderResponse::new);
  }

  /**
   * Save an order.
   *
   * @param order the order to save
   * @return the saved order
   */
  public OrderResponse save(OrderCreateRequest order, User user) {
    List<Course> courses = order.courses().stream()
        .map(course -> courseRepository.findById(course.courseId())
            .orElseThrow(() -> new NotFoundException("Course not found")))
        .toList();

    List<OrderCourse> orderCourses = courses.stream()
        .map(course -> {
          OrderCourse orderCourse = new OrderCourse();
          orderCourse.setCourse(course);
          return orderCourse;
        })
        .toList();

    BigDecimal totalPrice = orderCourses.stream()
        .map(OrderCourse::getCourse)
        .map(Course::getPrice)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    Order newOrder = new Order();
    newOrder.setUser(user);
    newOrder.setItems(orderCourses);
    newOrder.setTotalPrice(totalPrice);
    return new OrderResponse(orderRepository.save(newOrder));
  }
}
