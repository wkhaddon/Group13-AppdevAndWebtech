package edu.ntnu.iir.learniverse.dto;

import edu.ntnu.iir.learniverse.entity.Favorite;

/**
 * Data transfer object for a favorite response.
 *
 * @param id the ID of the favorite
 * @param course the course associated with the favorite
 */
public record FavoriteResponse(
        Long id,
        CourseResponse course
) {
  /**
   * Constructor for FavoriteResponse.
   *
   * @param favorite the Favorite entity to convert
   */
  public FavoriteResponse(Favorite favorite) {
    this(favorite.getId(), new CourseResponse(favorite.getCourse()));
  }
}
