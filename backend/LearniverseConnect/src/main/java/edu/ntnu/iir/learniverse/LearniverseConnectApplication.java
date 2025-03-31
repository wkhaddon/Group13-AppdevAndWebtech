package edu.ntnu.iir.learniverse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class LearniverseConnectApplication {
  public static void main(String[] args) {
    Dotenv dotenv = Dotenv.configure()
      .directory("./src/main/resources")
      .load();

    // TODO: Find a better way to set environment variables
    // This assumes that .env is correctly loaded and contains the necessary variables
    System.setProperty("DB_URL", dotenv.get("DB_URL"));
    System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
    System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

    SpringApplication.run(LearniverseConnectApplication.class, args);
  }
}