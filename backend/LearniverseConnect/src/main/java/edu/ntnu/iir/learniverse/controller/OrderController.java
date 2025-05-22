package edu.ntnu.iir.learniverse.controller;

import edu.ntnu.iir.learniverse.annotation.CurrentUser;
import edu.ntnu.iir.learniverse.dto.OrderCreateRequest;
import edu.ntnu.iir.learniverse.dto.OrderResponse;
import edu.ntnu.iir.learniverse.entity.Order;
import edu.ntnu.iir.learniverse.entity.User;
import edu.ntnu.iir.learniverse.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for managing orders.
 */
@RestController
@RequestMapping("/api/orders")
@Tag(name = "Orders", description = "Endpoints for managing orders")
public class OrderController {
  private final OrderService orderService;

  /**
   * Constructor for OrderController.
   *
   * @param orderService the service to manage orders
   */
  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  /**
   * Get all orders for a user.
   *
   * @param userId the ID of the user
   * @return a list of orders for the user
   */
  @Operation(
          summary = "Get all orders for a user",
          description = "Retrieve a list of all orders for a specific user")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved list of orders")
  @GetMapping("/user/{userId}")
  public List<OrderResponse> getAllForUser(@PathVariable Long userId) {
    return orderService.getAllByUser(userId);
  }

  /**
   * Get an order by ID.
   *
   * @param id the ID of the order
   * @return the order with the specified ID, or 404 if not found
   */
  @Operation(summary = "Get order by ID", description = "Retrieve an order by its ID")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved the order")
  @GetMapping("/{id}")
  public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
    return orderService.getById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  /**
   * Create a new order.
   *
   * @param order the order to create
   * @return the created order
   */
  @Operation(
          summary = "Create a new order",
          description = "Create a new order with the provided details")
  @ApiResponse(responseCode = "201", description = "Successfully created the order")
  @PostMapping
  public ResponseEntity<OrderResponse> create(@RequestBody OrderCreateRequest order,
                                              @CurrentUser User user) {
    return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(order, user));
  }
}
