package edu.ntnu.iir.learniverse.dto;

import edu.ntnu.iir.learniverse.entity.ProviderOrganization;

/**
 * Data Transfer Object (DTO) for course provider response.
 *
 * @param id the unique identifier of the course provider
 * @param name the name of the course provider
 * @param currency the currency used by the course provider
 */
public record CourseProviderResponse(
        Long id,
        String name,
        String currency
) {
  /**
   * Constructor for CourseProviderResponse.
   *
   * @param provider the provider organization entity
   */
  public CourseProviderResponse(ProviderOrganization provider) {
    this(
        provider.getId(),
        provider.getName(),
        provider.getCurrency()
    );
  }
}
