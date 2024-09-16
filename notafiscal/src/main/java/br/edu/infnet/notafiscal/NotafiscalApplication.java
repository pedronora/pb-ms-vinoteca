package br.edu.infnet.notafiscal;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
    info =
        @Info(
            title = "Nota Fiscal Service",
            description = "Microsserviço que integra o sistema da VINOTECA"))
@SpringBootApplication
public class NotafiscalApplication {

  public static void main(String[] args) {
    SpringApplication.run(NotafiscalApplication.class, args);
  }
}
