package br.edu.infnet.pedidoservice.service;

import br.edu.infnet.pedidoservice.model.Vinho;
import br.edu.infnet.pedidoservice.service.client.VinhoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VinhoService {
    private final VinhoClient vinhoClient;

    public Vinho getById(Long id) {
        return vinhoClient.getById(id);
    }
}
