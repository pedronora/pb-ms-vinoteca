package br.edu.infnet.impostoservice.service;

import br.edu.infnet.impostoservice.model.Vinicola;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class VinicolaService {
    public Vinicola getById(Long id) {
        var serverUrl = String.format("http://localhost:8082/api/vinicolas/%d", id);
        RestClient restClient = RestClient.create();
        return restClient
                .get()
                .uri(serverUrl)
                .retrieve()
                .toEntity(Vinicola.class).getBody();
    }
}
