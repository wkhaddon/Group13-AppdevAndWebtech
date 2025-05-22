package edu.ntnu.iir.learniverse.dto;

/**
 * Data transfer object for creating an order course.
 *
 * @param courseId the ID of the course
 * @param quantity the quantity of the course
 */
public record OrderCourseCreateRequest(
    Long courseId,
    int quantity
) {
}
