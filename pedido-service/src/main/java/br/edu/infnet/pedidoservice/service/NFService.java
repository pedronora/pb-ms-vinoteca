package br.edu.infnet.pedidoservice.service;

import br.edu.infnet.pedidoservice.model.Pedido;
import br.edu.infnet.pedidoservice.payload.NotaFiscalPayload;
import br.edu.infnet.pedidoservice.service.client.NFClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NFService {
  private final NFClient client;

  public NotaFiscalPayload emitirNotaFiscal(Pedido pedido) {
    return client.emitirNotaFiscal(pedido);
  }
}
