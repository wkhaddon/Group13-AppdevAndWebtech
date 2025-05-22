package edu.ntnu.iir.learniverse.service;

import edu.ntnu.iir.learniverse.entity.Order;
import edu.ntnu.iir.learniverse.repository.OrderRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Service class for managing orders.
 */
@Service
public class OrderService {
  private final OrderRepository orderRepository;

  /**
   * Constructor for OrderService.
   *
   * @param orderRepository the repository to manage orders
   */
  public OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  /**
   * Get all orders for a user.
   *
   * @param userId the ID of the user
   * @return a list of orders for the user
   */
  public List<Order> getAllByUser(Long userId) {
    return orderRepository.findByUserId(userId);
  }

  /**
   * Get an order by ID.
   *
   * @param orderId the ID of the order
   * @return an Optional containing the order if found, or empty if not found
   */
  public Optional<Order> getById(Long orderId) {
    return orderRepository.findById(orderId);
  }

  /**
   * Save an order.
   *
   * @param order the order to save
   * @return the saved order
   */
  public Order save(Order order) {
    return orderRepository.save(order);
  }
}
