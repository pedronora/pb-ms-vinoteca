package br.edu.infnet.notafiscal.controller;

import br.edu.infnet.notafiscal.model.Pedido;
import br.edu.infnet.notafiscal.service.NotaFiscalService;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
@Tag(name = "NotaFiscal Controller", description = "Gerencia as operações relacionadas à emissão da Nota Fiscal")
public class NotaFiscalController {
  private final NotaFiscalService notaFiscalService;

  @Operation(summary = "Emite uma Nota Fiscal para um pedido")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Nota Fiscal gerada com sucesso",
                  content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Map.class))}),
          @ApiResponse(responseCode = "500", description = "Erro ao processar o pedido",
                  content = @Content)
  })
  @PostMapping
  public ResponseEntity<Map<String, String>> gerarNotaFiscal(@RequestBody Pedido pedido) {
    try {
      notaFiscalService.emitirNotaFiscal(pedido);
      log.info("Nota Fiscal emitida para o pedido " + pedido.getId());
    } catch (JsonProcessingException e) {
      log.error("Erro ao emitir Nota Fiscal para o pedido " + pedido.getId(), e);
      return ResponseEntity.internalServerError().build();
    }

    return ResponseEntity.ok(Map.of("message", "Nota Fiscal Gerada com sucesso!"));
  }
}
