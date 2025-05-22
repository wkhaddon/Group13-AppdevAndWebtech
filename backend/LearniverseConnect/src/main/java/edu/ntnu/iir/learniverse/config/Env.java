package edu.ntnu.iir.learniverse.config;

import lombok.NonNull;

/**
 * Utility class to manage environment variables.
 */
public final class Env {
  /**
   * Enum representing the environment variables used in the application.
   */
  public enum EnvVar {
    DB_URL, DB_USER, DB_PASSWORD, JWT_SECRET, KEYSTORE_PASSWORD
  }

  /**
   * Retrieves the value of the specified environment variable.
   *
   * @param key the environment variable key
   * @return the value of the environment variable
   */
  @NonNull
  public static String get(EnvVar key) {
    String value = System.getProperty(key.name());
    if (value == null) {
      value = System.getenv(key.name());
    }
    if (value == null) {
      throw new IllegalStateException("Missing required env var: " + key.name());
    }
    return value;
  }
}
