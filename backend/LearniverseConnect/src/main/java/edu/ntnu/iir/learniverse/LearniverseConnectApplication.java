package edu.ntnu.iir.learniverse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class LearniverseConnectApplication {
  public static void main(String[] args) {
    Dotenv dotenv = Dotenv.configure()
      .directory(".")
      .load();

    String[] envVars = {
        "DB_URL",
        "DB_USERNAME",
        "DB_PASSWORD",
        "JWT_SECRET"
    };

    for (String key : envVars) {
      String value = dotenv.get(key);
      if (value == null) {
        System.err.println("Environment variable " + key + " is not set.");
        System.exit(1);
      }

      System.setProperty(key, value);
    }

    SpringApplication.run(LearniverseConnectApplication.class, args);
  }
}