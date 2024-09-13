package br.edu.infnet.impostoservice.service.client;

import br.edu.infnet.impostoservice.model.Vinho;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "VINHO-SERVICE")
public interface VinhoClient {
  @GetMapping("/{id}")
  Vinho getById(@PathVariable Long id);
}
