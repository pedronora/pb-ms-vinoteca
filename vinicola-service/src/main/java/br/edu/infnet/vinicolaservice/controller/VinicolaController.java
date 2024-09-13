package br.edu.infnet.vinicolaservice.controller;

import br.edu.infnet.vinicolaservice.model.Vinicola;
import br.edu.infnet.vinicolaservice.payload.DetailPayload;
import br.edu.infnet.vinicolaservice.service.VinicolaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Vinicola Controller", description = "Gerencia as operações relacionadas a vinícolas")
public class VinicolaController {
    private final VinicolaService vinicolaService;

    @Operation(summary = "Criar uma vinícola")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Vinícola criada com sucesso",
                            content = {@Content(schema = @Schema(implementation = Vinicola.class))}),
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
    public ResponseEntity<Vinicola> create(@RequestBody Vinicola vinicola) {
        log.info("Criando uma vinicola");
        Vinicola saved = vinicolaService.create(vinicola);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Operation(summary = "Retorna a lista de vinícolas")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Vinicola.class))),
                            }),
                    @ApiResponse(responseCode = "404", description = "Não há vinícolas para exibir")
            })
    @GetMapping
    public ResponseEntity<?> findAll() {
        log.info("Buscando a lista de vinicolas");
        return ResponseEntity.ok(vinicolaService.findAll());
    }

    @Operation(summary = "Retorna uma vinícola por ID")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Vinícola encontrada",
                            content = {@Content(schema = @Schema(implementation = Vinicola.class))}),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Vinho não encontrado",
                            content = {@Content(schema = @Schema(implementation = DetailPayload.class))}),
            })
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

    @Operation(summary = "Atualizar uma viníola existente")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Vinícola atualizada com sucesso",
                            content = {@Content(schema = @Schema(implementation = Vinicola.class))}),
                    @ApiResponse(
                            responseCode = "400",
                            description = "ID inválido",
                            content = {@Content(schema = @Schema(implementation = DetailPayload.class))}),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Vinícola não encontrada!",
                            content = {@Content(schema = @Schema(implementation = DetailPayload.class))})
            })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Vinicola vinicola) {
        if (vinicola.getId() == null || !vinicola.getId().equals(id)) {
            return ResponseEntity.badRequest().build();
        }

        Vinicola updated = vinicolaService.update(vinicola);
        log.info("Atualizando vinicola de ID {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @Operation(summary = "Deletar uma vinícola por ID")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Vinícola deletada com sucesso",
                            content = {@Content(schema = @Schema(implementation = DetailPayload.class))}),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Vinícola não encontrada",
                            content = {@Content(schema = @Schema(implementation = DetailPayload.class))})
            })
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
