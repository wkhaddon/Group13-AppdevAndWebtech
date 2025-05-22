package edu.ntnu.iir.learniverse.util;

import java.util.Map;

/**
 * Utility class for API responses.
 */
public class ApiUtils {
  private ApiUtils() {
    // Utility class, no instantiation allowed
  }

  /**
   * Creates a response map with a message.
   *
   * @param message the message to include in the response
   * @return a map containing the message
   */
  public static Map<String, String> message(String message) {
    return Map.of("message", message);
  }
}
