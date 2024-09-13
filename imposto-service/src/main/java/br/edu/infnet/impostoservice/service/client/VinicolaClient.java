package br.edu.infnet.impostoservice.service.client;

import br.edu.infnet.impostoservice.model.Vinicola;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "VINICOLA-SERVICE")
public interface VinicolaClient {
  @GetMapping("/{id}")
  Vinicola getById(@PathVariable Long id);
}
