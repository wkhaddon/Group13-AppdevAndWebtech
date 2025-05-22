package edu.ntnu.iir.learniverse.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 * Global exception handler for the application.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandler.class);

  /**
   * Handles NoPermissionException.
   *
   * @param e the exception
   * @return a ResponseEntity with the error message
   */
  @ExceptionHandler(NoPermissionException.class)
  public ResponseEntity<ErrorResponse> handleNoPermission(NoPermissionException e) {
    return buildError(HttpStatus.FORBIDDEN, e.getMessage());
  }

  /**
   * Handles UnAuthorizedException.
   *
   * @param e the exception
   * @return a ResponseEntity with the error message
   */
  @ExceptionHandler(UnAuthorizedException.class)
  public ResponseEntity<ErrorResponse> handleNotAuthenticated(UnAuthorizedException e) {
    return buildError(HttpStatus.UNAUTHORIZED, e.getMessage());
  }

  /**
   * Handles NotFoundException.
   *
   * @param e the exception
   * @return a ResponseEntity with the error message
   */
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException e) {
    return buildError(HttpStatus.NOT_FOUND, e.getMessage());
  }

  /**
   * Handles ConflictException.
   *
   * @param e the exception
   * @return a ResponseEntity with the error message
   */
  @ExceptionHandler(ConflictException.class)
  public ResponseEntity<ErrorResponse> handleConflict(ConflictException e) {
    return buildError(HttpStatus.CONFLICT, e.getMessage());
  }

  /**
   * Handles BadRequestException.
   *
   * @param e the exception
   * @return a ResponseEntity with the error message
   */
  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException e) {
    return buildError(HttpStatus.BAD_REQUEST, e.getMessage());
  }

  /**
   * Handles NoResourceFoundException.
   *
   * @param e the exception
   * @return a ResponseEntity with the error message
   */
  @ExceptionHandler(NoResourceFoundException.class)
  public ResponseEntity<ErrorResponse> handleNoResourceFound(NoResourceFoundException e) {
    return buildError(HttpStatus.NOT_FOUND, e.getMessage());
  }

  /**
   * Handles all other exceptions.
   *
   * @param e the exception
   * @return a ResponseEntity with a generic error message
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleUnexpected(Exception e) {
    logger.error("Unexpected error", e);
    return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
  }

  /**
   * Builds a ResponseEntity with the given status and message.
   *
   * @param status the HTTP status
   * @param message the error message
   * @return a ResponseEntity with the error response
   */
  private ResponseEntity<ErrorResponse> buildError(HttpStatus status, String message) {
    return new ResponseEntity<>(new ErrorResponse(status.value(), message), status);
  }

  /**
   * Error response class.
   */
  public static class ErrorResponse {
    public final int status;
    public final String message;
    public final LocalDateTime timestamp;

    /**
     * Constructor for ErrorResponse.
     *
     * @param status the HTTP status
     * @param message the error message
     */
    public ErrorResponse(int status, String message) {
      this.status = status;
      this.message = message;
      this.timestamp = LocalDateTime.now();
    }
  }
}
