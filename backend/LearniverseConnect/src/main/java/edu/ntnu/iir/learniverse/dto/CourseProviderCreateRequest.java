package edu.ntnu.iir.learniverse.dto;

/**
 * Data Transfer Object (DTO) for creating a course provider.
 *
 * @param name the name of the course provider
 * @param currency the currency used by the course provider
 */
public record CourseProviderCreateRequest(
        String name,
        String currency
) {
}
