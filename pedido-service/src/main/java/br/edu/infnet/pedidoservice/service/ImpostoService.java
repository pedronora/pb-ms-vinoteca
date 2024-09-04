package br.edu.infnet.pedidoservice.service;

import br.edu.infnet.pedidoservice.model.ImpostoResponsePayload;
import br.edu.infnet.pedidoservice.model.Pedido;
import br.edu.infnet.pedidoservice.service.clients.ImpostoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImpostoService {

    private final ImpostoClient impostoClient;

    public ImpostoResponsePayload getTotalImposto(Pedido pedido) {
        return impostoClient.getImposto(pedido);
//        var serverUrl = "http://localhost:8084";
//        RestClient restClient = RestClient.create();
//        return restClient.post()
//                .uri(serverUrl)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(pedido)
//                .retrieve()
//                .toEntity(ImpostoResponsePayload.class).getBody();
    }
}
