package edu.ntnu.iir.learniverse.exception;

/**
 * Base class for all exceptions that are thrown by the API.
 */
public abstract class ApiException extends RuntimeException {
  /**
   * The default constructor for the ApiException class.
   *
   * @param message the message to be displayed when the exception is thrown
   */
  protected ApiException(String message) {
    super(message);
  }

  /**
   * The constructor for the ApiException class that takes a message and a cause.
   *
   * @param message the message to be displayed when the exception is thrown
   * @param cause the cause of the exception
   */
  protected ApiException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * The constructor for the ApiException class that takes a cause.
   *
   * @param cause the cause of the exception
   */
  protected ApiException(Throwable cause) {
    super(cause);
  }
}
