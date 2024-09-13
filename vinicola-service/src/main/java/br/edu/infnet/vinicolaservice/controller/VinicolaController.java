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
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> create(@RequestBody Vinicola vinicola) {
        if(vinicola.getNome() == null || vinicola.getNome().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O nome da vinícula é obrigatório.");
        }

        if(vinicola.getNome().length() > 100) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DetailPayload("O nome da vinícola não pode conter mais de 100 caracteres."));
        }

        try{
            log.info("Criando uma vinicola");
            Vinicola saved = vinicolaService.create(vinicola);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            log.error("Erro ao criar uma vinicola", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new DetailPayload(e.getMessage()));
        }

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
        List<Vinicola> vinicolas = vinicolaService.findAll();

        if(vinicolas.isEmpty()){
            log.info("Não há VINÍCOLAS para apresentação.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else{
            log.info("Buscando a lista de vinícolas.");
            return ResponseEntity.ok(vinicolas);
        }
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
        if (vinicola.isEmpty()) {
            log.error("Vinícola com id {} não encontrada!", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DetailPayload("Vinícola com id " + id + " não encontrada!"));
        }
        log.info("Vinícola com id {} encontrada!", id);
        return ResponseEntity.status(HttpStatus.OK).body(vinicola);
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
            log.error("Erro ao atualizar o vinho!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DetailPayload("ID invalido!"));
        }

        Optional<Vinicola> vinhoToUpdate = vinicolaService.findById(id);
        if (vinhoToUpdate.isEmpty()) {
            log.error("Vinícola não encontrada!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DetailPayload("Vinícola não encontrada!"));
        }

        Vinicola updated = vinicolaService.update(vinicola);
        log.info("Vinícola atualizada com sucesso!");
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
        Optional<Vinicola> vinicolaToDelete = vinicolaService.findById(id);
        if (vinicolaToDelete.isEmpty()) {
            log.error("Vinícola não encontrada!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DetailPayload("Vinícola não encontrada!"));
        }

        vinicolaService.deleteById(id);
        log.info("Vinícola deletada com sucesso!");
        return ResponseEntity.status(HttpStatus.OK)
                .body(new DetailPayload("Vinícola com id: " + id + " deletada com sucesso!"));
    }
}
