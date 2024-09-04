package br.edu.infnet.pedidoservice.service.client;

import br.edu.infnet.pedidoservice.model.Vinho;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "VINHO-SERVICE")
public interface VinhoClient {
    @GetMapping("/api/vinhos/{id}")
    Vinho getById(@PathVariable Long id);
}
