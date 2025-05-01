package edu.ntnu.iir.learniverse.security;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthFilter extends GenericFilter {
  private final JwtUtil jwtUtil;

  public JwtAuthFilter(JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) request;
    Cookie[] cookies = req.getCookies();

    if (cookies == null) {
      chain.doFilter(request, response);
      return;
    }

    for (Cookie cookie : cookies) {
      if ("jwt".equals(cookie.getName())) {
        String token = cookie.getValue();
        try {
          jwtUtil.validateToken(token);
          String username = jwtUtil.getUsernameFromToken(token);
          String role = jwtUtil.getRoleFromToken(token);
          req.setAttribute("username", username);
          req.setAttribute("role", role);
        } catch (JwtException e) {
          // Invalid token, do nothing
        }
        break;
      }
    }

    chain.doFilter(request, response);
  }
}
