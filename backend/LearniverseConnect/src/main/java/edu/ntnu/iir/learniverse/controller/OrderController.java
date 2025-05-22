package edu.ntnu.iir.learniverse.controller;

import edu.ntnu.iir.learniverse.entity.Order;
import edu.ntnu.iir.learniverse.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Orders", description = "Endpoints for managing orders")
public class OrderController {
  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @Operation(summary = "Get all orders for a user", description = "Retrieve a list of all orders for a specific user")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved list of orders")
  @GetMapping("/user/{userId}")
  public List<Order> getAllForUser(@PathVariable Long userId) {
    return orderService.getAllByUser(userId);
  }

  @Operation(summary = "Get order by ID", description = "Retrieve an order by its ID")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved the order")
  @GetMapping("/{id}")
  public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
    return orderService.getById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @Operation(summary = "Create a new order", description = "Create a new order with the provided details")
  @ApiResponse(responseCode = "201", description = "Successfully created the order")
  @PostMapping
  public ResponseEntity<Order> create(@RequestBody Order order) {
    return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(order));
  }
}
