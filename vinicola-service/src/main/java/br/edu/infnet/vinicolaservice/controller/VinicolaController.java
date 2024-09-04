package br.edu.infnet.vinicolaservice.controller;

import br.edu.infnet.vinicolaservice.model.Vinicola;
import br.edu.infnet.vinicolaservice.service.VinicolaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/vinicolas")
@RequiredArgsConstructor
@Slf4j
public class VinicolaController {
    private final VinicolaService vinicolaService;

    @PostMapping
    public ResponseEntity<Vinicola> create(@RequestBody Vinicola vinicola) {
        log.info("Criando uma vinicola");
        Vinicola saved = vinicolaService.create(vinicola);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        log.info("Buscando a lista de vinicolas");
        return ResponseEntity.ok(vinicolaService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Vinicola> vinicola = vinicolaService.findById(id);
        if (vinicola.isPresent()) {
            log.info("Buscando vinicola de ID {}", id);
            return ResponseEntity.ok(vinicola.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Vinicola vinicola) {
        if (vinicola.getId() == null || !vinicola.getId().equals(id)) {
            return ResponseEntity.badRequest().build();
        }

        Vinicola updated = vinicolaService.update(vinicola);
        log.info("Atualizando vinicola de ID {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (vinicolaService.findById(id).isPresent()) {
            vinicolaService.deleteById(id);
            log.info("Deletando vinicola de ID {}", id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
