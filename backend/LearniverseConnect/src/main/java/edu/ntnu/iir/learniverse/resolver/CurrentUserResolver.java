package edu.ntnu.iir.learniverse.resolver;

import edu.ntnu.iir.learniverse.annotation.CurrentUser;
import edu.ntnu.iir.learniverse.entity.User;
import edu.ntnu.iir.learniverse.repository.UserRepository;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Resolver for the current user in the security context.
 */
@Component
public class CurrentUserResolver implements HandlerMethodArgumentResolver {
  private final UserRepository userRepository;

  /**
   * Constructor for CurrentUserResolver.
   *
   * @param userRepository the repository for managing users
   */
  public CurrentUserResolver(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Checks if the method parameter is supported for resolution.
   *
   * @param parameter the method parameter to check
   * @return true if the parameter is supported, false otherwise
   */
  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.hasParameterAnnotation(CurrentUser.class)
        && parameter.getParameterType().equals(User.class);
  }

  /**
   * Resolves the argument for the method parameter.
   *
   * @param parameter the method parameter to resolve
   * @param mavContainer the ModelAndViewContainer
   * @param webRequest the web request
   * @param binderFactory the WebDataBinderFactory
   * @return the resolved argument, or null if not authenticated
   * @throws IllegalStateException if the user is not found
   */
  @Override
  public Object resolveArgument(@NonNull MethodParameter parameter,
                                ModelAndViewContainer mavContainer,
                                @NonNull NativeWebRequest webRequest,
                                WebDataBinderFactory binderFactory) throws IllegalStateException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null || !authentication.isAuthenticated()) {
      return null;
    }

    String username = authentication.getName();
    Optional<User> user = userRepository.findByUsernameIgnoreCase(username);

    if (user.isPresent()) {
      return user.get();
    } else {
      throw new IllegalStateException("User not found");
    }
  }
}
