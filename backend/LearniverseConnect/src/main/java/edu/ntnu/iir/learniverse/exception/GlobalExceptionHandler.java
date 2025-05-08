package edu.ntnu.iir.learniverse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<?> handleIllegalArgument(IllegalArgumentException e) {
    return buildError(HttpStatus.BAD_REQUEST, e.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(error ->
        errors.put(error.getField(), error.getDefaultMessage()));
    return ResponseEntity.badRequest().body(errors);
  }


  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleUnexpected(Exception e) {
    e.printStackTrace();

    return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
  }

  private ResponseEntity<?> buildError(HttpStatus status, String message) {
    return new ResponseEntity<>(new ErrorResponse(status.value(), message), status);
  }

  private static class ErrorResponse {
    public final int status;
    public final String message;
    public final LocalDateTime timestamp;

    public ErrorResponse(int status, String message) {
      this.status = status;
      this.message = message;
      this.timestamp = LocalDateTime.now();
    }
  }
}
