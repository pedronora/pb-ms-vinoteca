package br.edu.infnet.vinhoservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
    info =
        @Info(
            title = "Vinho Service",
            description = "Microsserviço que integra o sistema da VINOTECA"))
@SpringBootApplication
public class VinhoServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(VinhoServiceApplication.class, args);
  }
}
