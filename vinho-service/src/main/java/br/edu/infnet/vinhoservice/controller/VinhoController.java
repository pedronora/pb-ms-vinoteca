package br.edu.infnet.vinhoservice.controller;

import br.edu.infnet.vinhoservice.model.Vinho;
import br.edu.infnet.vinhoservice.payload.DetailPayload;
import br.edu.infnet.vinhoservice.service.VinhoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Vinho Controller", description = "Gerencia as operações relacionadas aos vinhos")
public class VinhoController {
  private final VinhoService vinhoService;
  private final Validator validator;

  @Operation(summary = "Criar um vinho")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "201",
            description = "Vinho criado com sucesso",
            content = {@Content(schema = @Schema(implementation = Vinho.class))}),
        @ApiResponse(
            responseCode = "400",
            description = "Erro de validação",
            content = {@Content(schema = @Schema(implementation = DetailPayload.class))}),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno do servidor",
            content = {@Content(schema = @Schema(implementation = DetailPayload.class))})
      })
  @PostMapping
  public ResponseEntity<?> create(@RequestBody Vinho vinho) {
    if (vinho.getNome() == null || vinho.getNome().isEmpty()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O nome do vinho é obrigatório.");
    }

    if (vinho.getPreco() == null || vinho.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new DetailPayload("O preço do vinho deve ser maior que zero."));
    }

    try {
      log.info("Vinho criado!");
      Vinho saved = vinhoService.create(vinho);
      return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    } catch (Exception e) {
      log.error("Erro ao criar vinho: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new DetailPayload(e.getMessage()));
    }
  }

  @Operation(summary = "Retorna a lista de vinhos")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = {
              @Content(
                  mediaType = "application/json",
                  array = @ArraySchema(schema = @Schema(implementation = Vinho.class))),
            }),
        @ApiResponse(responseCode = "404", description = "Não há vinhos para exibir")
      })
  @GetMapping
  public ResponseEntity<?> findAll() {
    List<Vinho> vinhos = vinhoService.findAll();

    if (vinhos.isEmpty()) {
      log.info("Não há VINHOS para serem apresentados.");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    } else {
      log.info("Buscando a lista de vinhos");
      return ResponseEntity.ok(vinhoService.findAll());
    }
  }

  @Operation(summary = "Retorna um vinho por ID")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Vinho encontrado",
            content = {@Content(schema = @Schema(implementation = Vinho.class))}),
        @ApiResponse(
            responseCode = "404",
            description = "Vinho não encontrado",
            content = {@Content(schema = @Schema(implementation = DetailPayload.class))}),
      })
  @GetMapping("{id}")
  public ResponseEntity<?> findById(@PathVariable Long id) {
    Optional<Vinho> vinho = vinhoService.findById(id);
    if (vinho.isEmpty()) {
      log.error("Vinho com id " + id + " não encontrado!");
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new DetailPayload("Vinho com id " + id + " não encontrado!"));
    }
    log.info("Vinho com id " + id + " encontrado!");
    return ResponseEntity.status(HttpStatus.OK).body(vinho);
  }

  @Operation(summary = "Atualizar um vinho existente")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Vinho atualizado com sucesso",
            content = {@Content(schema = @Schema(implementation = Vinho.class))}),
        @ApiResponse(
            responseCode = "400",
            description = "ID inválido",
            content = {@Content(schema = @Schema(implementation = DetailPayload.class))}),
        @ApiResponse(
            responseCode = "404",
            description = "Vinho não encontrado!",
            content = {@Content(schema = @Schema(implementation = DetailPayload.class))})
      })
  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Vinho vinho) {
    if (vinho.getId() == null || !vinho.getId().equals(id)) {
      log.error("Erro ao atualizar o vinho!");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DetailPayload("ID invalido!"));
    }

    Optional<Vinho> vinhoToUpdate = vinhoService.findById(id);
    if (vinhoToUpdate.isEmpty()) {
      log.error("Vinho não encontrado!");
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new DetailPayload("Vinho não encontrado!"));
    }

    Vinho updated = vinhoService.update(vinho);
    log.info("Vinho atualizado com sucesso!");
    return ResponseEntity.status(HttpStatus.OK).body(updated);
  }

  @Operation(summary = "Deletar um vinho por ID")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Vinho deletado com sucesso",
            content = {@Content(schema = @Schema(implementation = DetailPayload.class))}),
        @ApiResponse(
            responseCode = "404",
            description = "Vinho não encontrado",
            content = {@Content(schema = @Schema(implementation = DetailPayload.class))})
      })
  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Optional<Vinho> vinhoToDelete = vinhoService.findById(id);
    if (vinhoToDelete.isEmpty()) {
      log.error("Vinho não encontrado!");
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
              .body(new DetailPayload("Vinho não encontrado!"));
    }

    vinhoService.deleteById(id);
    log.info("Vinho deletado com sucesso!");
    return ResponseEntity.status(HttpStatus.OK)
        .body(new DetailPayload("Vinho com id: " + id + " deletado com sucesso!"));
  }
}
