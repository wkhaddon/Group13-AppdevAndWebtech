package edu.ntnu.iir.learniverse.config;

import edu.ntnu.iir.learniverse.resolver.CurrentUserResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  private final CurrentUserResolver currentUserResolver;

  public WebConfig(CurrentUserResolver currentUserResolver) {
    this.currentUserResolver = currentUserResolver;
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(currentUserResolver);
  }
}
