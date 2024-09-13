package br.edu.infnet.vinhoservice.service;

import br.edu.infnet.vinhoservice.model.Vinho;
import br.edu.infnet.vinhoservice.repository.VinhoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VinhoService {
  private final VinhoRepository vinhoRepository;

  public Vinho create(Vinho vinho) {
    return vinhoRepository.save(vinho);
  }

  public List<Vinho> findAll() {
    return vinhoRepository.findAll();
  }

  public Optional<Vinho> findById(Long id) {
    return vinhoRepository.findById(id);
  }

  public Vinho update(Vinho vinho) {
    return vinhoRepository.save(vinho);
  }

  public void deleteById(Long id) {
    vinhoRepository.deleteById(id);
  }
}
