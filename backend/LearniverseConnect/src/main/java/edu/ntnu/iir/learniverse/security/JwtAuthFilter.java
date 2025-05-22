package edu.ntnu.iir.learniverse.security;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.GenericFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Filter to authenticate JWT tokens in incoming requests.
 */
@Component
public class JwtAuthFilter extends GenericFilter {
  private final transient JwtUtil jwtUtil;

  /**
   * Constructor for JwtAuthFilter.
   *
   * @param jwtUtil the utility class for handling JWT tokens
   */
  public JwtAuthFilter(JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
          throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) request;
    Cookie[] cookies = req.getCookies();

    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if ("jwt".equals(cookie.getName())) {
          String token = cookie.getValue();
          try {
            jwtUtil.validateToken(token);
            String username = jwtUtil.getUsernameFromToken(token);
            String role = jwtUtil.getRoleFromToken(token);

            // Build an Authentication object
            var authority = new SimpleGrantedAuthority("ROLE_" + role.toUpperCase());
            var auth = new UsernamePasswordAuthenticationToken(username, null,
                    Collections.singleton(authority));

            // Register in security context
            SecurityContextHolder.getContext().setAuthentication(auth);
          } catch (JwtException e) {
            // Token invalid: clear any existing context just in case
            SecurityContextHolder.clearContext();
          }
          break;
        }
      }
    }

    chain.doFilter(request, response);
  }
}
