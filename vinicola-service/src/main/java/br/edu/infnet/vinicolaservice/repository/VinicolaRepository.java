package br.edu.infnet.vinicolaservice.repository;

import br.edu.infnet.vinicolaservice.model.Vinicola;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VinicolaRepository extends JpaRepository<Vinicola, Long> {
}
