package br.edu.infnet.vinicolaservice.service;

import br.edu.infnet.vinicolaservice.model.Vinicola;
import br.edu.infnet.vinicolaservice.repository.VinicolaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VinicolaService {
    private final VinicolaRepository vinicolaRepository;

    public Vinicola create(Vinicola vinicola) {
        return vinicolaRepository.save(vinicola);
    }

    public List<Vinicola> findAll() {
        return vinicolaRepository.findAll();
    }

    public Optional<Vinicola> findById(Long id) {
        return vinicolaRepository.findById(id);
    }

    public Vinicola update(Vinicola vinicola) {
        return vinicolaRepository.save(vinicola);
    }

    public void deleteById(Long id) {
        vinicolaRepository.deleteById(id);
    }
}
