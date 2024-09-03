package br.edu.infnet.vinhoservice.controller;

import br.edu.infnet.vinhoservice.model.Vinho;
import br.edu.infnet.vinhoservice.service.VinhoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/vinhos")
@RequiredArgsConstructor
@Slf4j
public class VinhoController {
    private final VinhoService vinhoService;

    @PostMapping
    public ResponseEntity<Vinho> create(@RequestBody Vinho vinho) {
        log.info("Criando um vinho");
        Vinho saved = vinhoService.create(vinho);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        log.info("Buscando a lista de vinhos");
        return ResponseEntity.ok(vinhoService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Vinho> vinho = vinhoService.findById(id);
        if (vinho.isPresent()) {
            return ResponseEntity.ok(vinho.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Vinho vinho) {
        if (vinho.getId() == null || !vinho.getId().equals(id)) {
            return ResponseEntity.badRequest().build();
        }

        vinhoService.update(vinho);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (vinhoService.findById(id).isPresent()) {
            vinhoService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
