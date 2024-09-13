package br.edu.infnet.pedidoservice.service.client;

import br.edu.infnet.pedidoservice.model.Pedido;
import br.edu.infnet.pedidoservice.payload.NotaFiscalPayload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "NOTAFISCAL-SERVICE")
public interface NFClient {
    @PostMapping
    NotaFiscalPayload emitirNotaFiscal(@RequestBody Pedido pedido);
}
