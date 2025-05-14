package edu.ntnu.iir.learniverse.security;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;

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
            var auth = new UsernamePasswordAuthenticationToken(username, null, Collections.singleton(authority));

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
