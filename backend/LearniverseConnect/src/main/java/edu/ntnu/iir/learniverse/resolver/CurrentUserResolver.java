package edu.ntnu.iir.learniverse.resolver;

import edu.ntnu.iir.learniverse.annotation.CurrentUser;
import edu.ntnu.iir.learniverse.entity.User;
import edu.ntnu.iir.learniverse.repository.UserRepository;
import lombok.NonNull;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Optional;

@Component
public class CurrentUserResolver implements HandlerMethodArgumentResolver {
  private final UserRepository userRepository;

  public CurrentUserResolver(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.hasParameterAnnotation(CurrentUser.class)
        && parameter.getParameterType().equals(User.class);
  }

  @Override
  public Object resolveArgument(@NonNull MethodParameter parameter,
                                ModelAndViewContainer mavContainer,
                                @NonNull NativeWebRequest webRequest,
                                WebDataBinderFactory binderFactory) throws Exception {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null || !authentication.isAuthenticated()) {
      return null;
    }

    String username = authentication.getName();
    Optional<User> user = userRepository.findByUsernameIgnoreCase(username);

    if (user.isPresent()) {
      return user.get();
    } else {
      throw new IllegalArgumentException("User not found");
    }
  }
}
