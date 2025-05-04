package edu.ntnu.iir.learniverse.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
  private final SecretKey key = Keys.hmacShaKeyFor(System.getenv("JWT_SECRET").getBytes());
  public static final long EXPIRATION_TIME_MILLIS = 1000L * 60L * 60L * 24L; // 24h

  @PostConstruct
  public void validateEnvVars() {
    String jwtSecret = System.getenv("JWT_SECRET");
    if (jwtSecret == null || jwtSecret.isEmpty()) {
      throw new IllegalStateException("JWT_SECRET environment variable is not set");
    }

    if (jwtSecret.length() < 32) {
      throw new IllegalStateException("JWT_SECRET must be at least 32 characters long");
    }
  }

  public String generateToken(String username, String role) {
    return Jwts.builder()
        .subject(username)
        .claim("role", role)
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MILLIS))
        .signWith(key)
        .compact();
  }

  public Jws<Claims> validateToken(String token) {
    return Jwts.parser()
        .verifyWith(key)
        .build()
        .parseSignedClaims(token);
  }

  public String getUsernameFromToken(String token) {
    return validateToken(token).getPayload().getSubject();
  }

  public String getRoleFromToken(String token) {
    return validateToken(token).getPayload().get("role", String.class);
  }
}
