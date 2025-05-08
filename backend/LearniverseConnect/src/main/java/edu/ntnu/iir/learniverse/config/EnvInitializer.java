package edu.ntnu.iir.learniverse.config;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class EnvInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

  @Override
  public void initialize(ConfigurableApplicationContext context) {
    Path envFilePath = Paths.get(".env");
    if (!Files.exists(envFilePath)) {
      System.out.println(".env file not found, skipping loading");
      return;
    }

    try (Stream<String> lines = Files.lines(envFilePath)) {
      lines
          .map(String::trim)
          .filter(line -> !line.startsWith("#") && line.contains("="))
          .forEach(line -> {
            String[] parts = line.split("=", 2);
            String key = parts[0].trim();
            String value = parts[1].trim();
            if (System.getProperty(key) == null && System.getenv(key) == null) {
              System.setProperty(key, value);
            }
          });

      System.out.println(".env loaded into system properties");
    } catch (IOException e) {
      System.err.println("Failed to read .env file: " + e.getMessage());
    }
  }
}
