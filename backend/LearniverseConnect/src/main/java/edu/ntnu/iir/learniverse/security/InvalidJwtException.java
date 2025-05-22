package edu.ntnu.iir.learniverse.security;

/**
 * Custom exception class for handling invalid JWT tokens.
 */
public class InvalidJwtException extends RuntimeException {
  /**
   * Default constructor for InvalidJwtException.
   *
   * @param message the error message
   */
  public InvalidJwtException(String message) {
    super(message);
  }

  /**
   * Constructor for InvalidJwtException with a cause.
   *
   * @param message the error message
   * @param cause the cause of the exception
   */
  public InvalidJwtException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructor for InvalidJwtException with a cause.
   *
   * @param cause the cause of the exception
   */
  public InvalidJwtException(Throwable cause) {
    super(cause);
  }
}
