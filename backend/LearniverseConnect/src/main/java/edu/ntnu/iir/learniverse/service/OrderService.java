package edu.ntnu.iir.learniverse.service;

import edu.ntnu.iir.learniverse.entity.Order;
import edu.ntnu.iir.learniverse.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
  private final OrderRepository orderRepository;

  public OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public List<Order> getAllByUser(Long userId) {
    return orderRepository.findByUserId(userId);
  }

  public Optional<Order> getById(Long orderId) {
    return orderRepository.findById(orderId);
  }

  public Order save(Order order) {
    return orderRepository.save(order);
  }
}
