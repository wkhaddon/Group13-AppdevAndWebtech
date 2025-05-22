package edu.ntnu.iir.learniverse.dto;

import java.util.List;

/**
 * Data transfer object for creating an order.
 *
 * @param userId the ID of the user
 * @param courses the list of courses in the order
 */
public record OrderCreateRequest(
    Long userId,
    List<OrderCourseCreateRequest> courses
) {
}
