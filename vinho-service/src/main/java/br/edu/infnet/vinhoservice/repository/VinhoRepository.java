package br.edu.infnet.vinhoservice.repository;

import br.edu.infnet.vinhoservice.model.Vinho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VinhoRepository extends JpaRepository<Vinho, Long> {
}
