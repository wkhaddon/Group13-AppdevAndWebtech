package edu.ntnu.iir.learniverse.exception;

/**
 * Exception thrown when a conflict occurs in the API.
 * Equivalent to HTTP status code 409.
 */
public class ConflictException extends ApiException {
  /**
   * The default constructor for the ConflictException class.
   *
   * @param message the message to be displayed when the exception is thrown
   */
  public ConflictException(String message) {
    super(message);
  }

  /**
   * The constructor for the ConflictException class that takes a message and a cause.
   *
   * @param message the message to be displayed when the exception is thrown
   * @param cause the cause of the exception
   */
  public ConflictException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * The constructor for the ConflictException class that takes a cause.
   *
   * @param cause the cause of the exception
   */
  public ConflictException(Throwable cause) {
    super(cause);
  }
}
