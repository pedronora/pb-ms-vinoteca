package br.edu.infnet.impostoservice.service;

import br.edu.infnet.impostoservice.model.Vinicola;
import br.edu.infnet.impostoservice.service.client.VinicolaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VinicolaService {
  private final VinicolaClient vinicolaClient;

  public Vinicola getById(Long id) {
    return vinicolaClient.getById(id);
  }
}
