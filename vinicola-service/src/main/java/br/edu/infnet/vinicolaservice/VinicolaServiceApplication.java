package br.edu.infnet.vinicolaservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(title = "Vinícola Service", description = "Microsserviço que integra o sistema da VINOTECA"))
@SpringBootApplication
public class VinicolaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VinicolaServiceApplication.class, args);
    }

}
