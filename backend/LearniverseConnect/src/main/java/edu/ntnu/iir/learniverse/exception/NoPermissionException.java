package edu.ntnu.iir.learniverse.exception;

/**
 * Exception thrown when a user does not have permission to perform an action.
 * Equivalent to HTTP status code 403.
 */
public class NoPermissionException extends ApiException {
  /**
   * The default constructor for the NoPermissionException class.
   *
   * @param message the message to be displayed when the exception is thrown
   */
  public NoPermissionException(String message) {
    super(message);
  }

  /**
   * The constructor for the NoPermissionException class that takes a message and a cause.
   *
   * @param message the message to be displayed when the exception is thrown
   * @param cause the cause of the exception
   */
  public NoPermissionException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * The constructor for the NoPermissionException class that takes a cause.
   *
   * @param cause the cause of the exception
   */
  public NoPermissionException(Throwable cause) {
    super(cause);
  }
}
