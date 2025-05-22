package edu.ntnu.iir.learniverse.dto;

import edu.ntnu.iir.learniverse.entity.OrderCourse;

/**
 * Data transfer object for an order course response.
 *
 * @param id the ID of the order course
 * @param courseId the ID of the course
 * @param quantity the quantity of the course
 */
public record OrderCourseResponse(
    Long id,
    Long courseId,
    int quantity
) {
  /**
   * Constructor for OrderCourseResponse.
   *
   * @param orderCourse the OrderCourse entity to convert
   */
  public OrderCourseResponse(OrderCourse orderCourse) {
    this(orderCourse.getId(), orderCourse.getCourse().getId(), orderCourse.getQuantity());
  }
}
