package br.edu.infnet.impostoservice.service;

import br.edu.infnet.impostoservice.model.Vinho;
import org.springframework.web.client.RestClient;
import org.springframework.stereotype.Service;

@Service
public class VinhoService {
    public Vinho getById(Long id){
        RestClient restClient = RestClient.create();
        var serverUrl = String.format("http://localhost:8081/%d", id);
        return restClient.get()
                .uri(serverUrl)
                .retrieve()
                .toEntity(Vinho.class).getBody();
    }

}