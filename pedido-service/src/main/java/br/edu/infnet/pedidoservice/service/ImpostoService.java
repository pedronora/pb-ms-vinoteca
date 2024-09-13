package br.edu.infnet.pedidoservice.service;

import br.edu.infnet.pedidoservice.model.ImpostoResponsePayload;
import br.edu.infnet.pedidoservice.model.Pedido;
import br.edu.infnet.pedidoservice.service.client.ImpostoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImpostoService {

    private final ImpostoClient impostoClient;

    public ImpostoResponsePayload getTotalImposto(Pedido pedido) {
        return impostoClient.calcularImposto(pedido);
    }
}
