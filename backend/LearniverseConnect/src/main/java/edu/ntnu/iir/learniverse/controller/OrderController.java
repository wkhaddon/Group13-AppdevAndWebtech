package edu.ntnu.iir.learniverse.controller;

import edu.ntnu.iir.learniverse.entity.Order;
import edu.ntnu.iir.learniverse.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping("/user/{userId}")
  public List<Order> getAllForUser(@PathVariable Long userId) {
    return orderService.getAllByUser(userId);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
    return orderService.getById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Order> create(@RequestBody Order order) {
    return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(order));
  }
}
