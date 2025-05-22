package edu.ntnu.iir.learniverse.exception;

/**
 * Exception thrown when a bad request is made to the API.
 * Equivalent to HTTP status code 400.
 */
public class BadRequestException extends ApiException {
  /**
   * The default constructor for the BadRequestException class.
   *
   * @param message the message to be displayed when the exception is thrown
   */
  public BadRequestException(String message) {
    super(message);
  }

  /**
   * The constructor for the BadRequestException class that takes a message and a cause.
   *
   * @param message the message to be displayed when the exception is thrown
   * @param cause the cause of the exception
   */
  public BadRequestException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * The constructor for the BadRequestException class that takes a cause.
   *
   * @param cause the cause of the exception
   */
  public BadRequestException(Throwable cause) {
    super(cause);
  }
}
