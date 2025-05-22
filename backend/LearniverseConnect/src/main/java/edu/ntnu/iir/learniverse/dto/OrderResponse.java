package edu.ntnu.iir.learniverse.dto;

import edu.ntnu.iir.learniverse.entity.Order;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Data transfer object for an order response.
 *
 * @param id the ID of the order
 * @param orderDate the date and time of the order
 * @param totalPrice the total price of the order
 * @param items the list of items in the order
 */
public record OrderResponse(
    Long id,
    LocalDateTime orderDate,
    BigDecimal totalPrice,
    List<OrderCourseResponse> items
) {
  /**
   * Constructor for OrderResponse.
   *
   * @param order the Order entity to convert
   */
  public OrderResponse(Order order) {
    this(
        order.getId(),
        order.getOrderDate(),
        order.getTotalPrice(),
        order.getItems().stream().map(OrderCourseResponse::new).toList()
    );
  }
}
