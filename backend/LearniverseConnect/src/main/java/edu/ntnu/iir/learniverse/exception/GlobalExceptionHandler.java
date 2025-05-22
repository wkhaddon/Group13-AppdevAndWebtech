package edu.ntnu.iir.learniverse.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for the application.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Handles IllegalArgumentException.
   *
   * @param e the exception
   * @return a ResponseEntity with the error message and status
   */
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<?> handleIllegalArgument(IllegalArgumentException e) {
    return buildError(HttpStatus.BAD_REQUEST, e.getMessage());
  }

  /**
   * Handles MethodArgumentNotValidException.
   *
   * @param e the exception
   * @return a ResponseEntity with the validation errors
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException e) {
    Map<String, String> errors = new HashMap<>();
    e.getBindingResult().getFieldErrors().forEach(error ->
        errors.put(error.getField(), error.getDefaultMessage()));
    return ResponseEntity.badRequest().body(errors);
  }

  /**
   * Handles all other exceptions.
   *
   * @param e the exception
   * @return a ResponseEntity with a generic error message
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleUnexpected(Exception e) {
    // TODO: Remove this in production
    e.printStackTrace();

    return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
  }

  /**
   * Builds a ResponseEntity with the given status and message.
   *
   * @param status the HTTP status
   * @param message the error message
   * @return a ResponseEntity with the error response
   */
  private ResponseEntity<?> buildError(HttpStatus status, String message) {
    return new ResponseEntity<>(new ErrorResponse(status.value(), message), status);
  }

  /**
   * Error response class.
   */
  private static class ErrorResponse {
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
