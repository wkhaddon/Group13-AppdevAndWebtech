package edu.ntnu.iir.learniverse.exception;

/**
 * Exception thrown when a user is not authorized.
 * Equivalent to HTTP status code 401.
 */
public class UnAuthorizedException extends ApiException {
  /**
   * The default constructor for the UnAuthenticatedException class.
   *
   * @param message the message to be displayed when the exception is thrown
   */
  public UnAuthorizedException(String message) {
    super(message);
  }

  /**
   * The constructor for the UnAuthenticatedException class that takes a message and a cause.
   *
   * @param message the message to be displayed when the exception is thrown
   * @param cause the cause of the exception
   */
  public UnAuthorizedException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * The constructor for the UnAuthenticatedException class that takes a cause.
   *
   * @param cause the cause of the exception
   */
  public UnAuthorizedException(Throwable cause) {
    super(cause);
  }
}
