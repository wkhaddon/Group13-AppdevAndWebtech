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

    dotenv.entries().forEach((key, value) -> System.setProperty(key, value));

    SpringApplication.run(LearniverseConnectApplication.class, args);
  }
}