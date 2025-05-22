package edu.ntnu.iir.learniverse.exception;

/**
 * Exception thrown when a requested resource is not found.
 * Equivalent to HTTP status code 404.
 */
public class NotFoundException extends ApiException {
  /**
   * The default constructor for the NotFoundException class.
   *
   * @param message the message to be displayed when the exception is thrown
   */
  public NotFoundException(String message) {
    super(message);
  }

  /**
   * The constructor for the NotFoundException class that takes a message and a cause.
   *
   * @param message the message to be displayed when the exception is thrown
   * @param cause the cause of the exception
   */
  public NotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * The constructor for the NotFoundException class that takes a cause.
   *
   * @param cause the cause of the exception
   */
  public NotFoundException(Throwable cause) {
    super(cause);
  }
}
