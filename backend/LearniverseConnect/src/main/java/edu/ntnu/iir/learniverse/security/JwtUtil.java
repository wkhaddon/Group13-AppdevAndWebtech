package edu.ntnu.iir.learniverse.security;

import edu.ntnu.iir.learniverse.config.Env;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;

/**
 * Utility class for handling JWT tokens.
 */
@Component
public class JwtUtil {
  private final SecretKey key = Keys.hmacShaKeyFor(Env.get(Env.EnvVar.JWT_SECRET).getBytes());
  public static final long EXPIRATION_TIME_MILLIS = 1000L * 60L * 60L * 24L; // 24h

  /**
   * Validates the environment variables required for JWT generation.
   */
  @PostConstruct
  public void validateEnvVars() {
    String jwtSecret = Env.get(Env.EnvVar.JWT_SECRET);
    if (jwtSecret.isEmpty()) {
      throw new IllegalStateException("JWT_SECRET environment variable is not set");
    }

    if (jwtSecret.length() < 32) {
      throw new IllegalStateException("JWT_SECRET must be at least 32 characters long");
    }
  }

  /**
   * Generates a JWT token for the given username and role.
   *
   * @param username the username for which the token is generated
   * @param role the role of the user
   * @return the generated JWT token
   */
  public String generateToken(String username, String role) {
    return Jwts.builder()
        .subject(username)
        .claim("role", role)
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MILLIS))
        .signWith(key)
        .compact();
  }

  /**
   * Validates the given JWT token.
   *
   * @param token the JWT token to validate
   * @return the parsed JWT token
   * @throws InvalidJwtException if the token is invalid
   */
  public Jws<Claims> validateToken(String token) throws InvalidJwtException {
    try {
      return Jwts.parser()
          .verifyWith(key)
          .build()
          .parseSignedClaims(token);
    } catch (JwtException | IllegalArgumentException e) {
      throw new InvalidJwtException("Invalid JWT token: " + e.getMessage(), e);
    }
  }

  /**
   * Extracts the username from the given JWT token.
   *
   * @param token the JWT token
   * @return the username extracted from the token
   */
  public String getUsernameFromToken(String token) {
    return validateToken(token).getPayload().getSubject();
  }

  /**
   * Extracts the role from the given JWT token.
   *
   * @param token the JWT token
   * @return the role extracted from the token
   */
  public String getRoleFromToken(String token) {
    return validateToken(token).getPayload().get("role", String.class);
  }
}
