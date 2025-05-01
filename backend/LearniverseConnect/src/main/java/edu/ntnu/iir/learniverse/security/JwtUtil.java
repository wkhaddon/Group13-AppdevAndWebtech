package edu.ntnu.iir.learniverse.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
  private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
  private final long expirationTimeMillis = 1000L * 60L * 60L * 24L; // 24h

  public String generateToken(String username, String role) {
    return Jwts.builder()
        .setSubject(username)
        .claim("role", role)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + expirationTimeMillis))
        .signWith(key)
        .compact();
  }

  public Jws<Claims> validateToken(String token) {
    return Jwts.parser()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token);
  }

  public String getUsernameFromToken(String token) {
    return validateToken(token).getBody().getSubject();
  }

  public String getRoleFromToken(String token) {
    return validateToken(token).getBody().get("role", String.class);
  }
}
