package br.edu.infnet.pedidoservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(
    info =
        @Info(
            title = "Pedido Service",
            description = "Microsserviço que integra o sistema da VINOTECA"))
@SpringBootApplication
@EnableFeignClients
public class PedidoServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(PedidoServiceApplication.class, args);
  }
}
