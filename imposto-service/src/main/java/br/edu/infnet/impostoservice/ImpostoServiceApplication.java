package br.edu.infnet.impostoservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(
    info =
        @Info(
            title = "Imposto Service",
            description = "Microsserviço que integra o sistema da VINOTECA"))
@SpringBootApplication
@EnableFeignClients
public class ImpostoServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ImpostoServiceApplication.class, args);
  }
}
