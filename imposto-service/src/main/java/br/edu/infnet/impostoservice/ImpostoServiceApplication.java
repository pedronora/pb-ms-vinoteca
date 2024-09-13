package br.edu.infnet.impostoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ImpostoServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ImpostoServiceApplication.class, args);
  }
}
