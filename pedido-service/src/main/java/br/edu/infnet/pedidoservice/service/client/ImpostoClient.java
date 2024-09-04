package br.edu.infnet.pedidoservice.service.client;

import br.edu.infnet.pedidoservice.model.ImpostoResponsePayload;
import br.edu.infnet.pedidoservice.model.Pedido;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "IMPOSTO-SERVICE")
public interface ImpostoClient {
    @PostMapping
    ImpostoResponsePayload getImposto(@RequestBody Pedido pedido);
}
