package edu.ntnu.iir.learniverse.config;

import lombok.NonNull;

public final class Env {
  public enum EnvVar {
    DB_URL, DB_USER, DB_PASSWORD, JWT_SECRET,
  }

  @NonNull
  public static String get(EnvVar key) {
    String value = System.getProperty(key.name());
    if (value == null) {
      throw new IllegalStateException("Missing required env var: " + key.name());
    }
    return value;
  }
}
