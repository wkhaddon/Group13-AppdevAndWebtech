package edu.ntnu.iir.learniverse.security;

public class InvalidJwtException extends RuntimeException {
    public InvalidJwtException(String message) {
        super(message);
    }

    public InvalidJwtException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidJwtException(Throwable cause) {
        super(cause);
    }
}
