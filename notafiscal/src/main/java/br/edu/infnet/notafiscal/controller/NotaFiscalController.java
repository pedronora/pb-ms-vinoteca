package br.edu.infnet.notafiscal.controller;

import br.edu.infnet.notafiscal.model.Pedido;
import br.edu.infnet.notafiscal.service.NotaFiscalService;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Map;
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
public class NotaFiscalController {
  private final NotaFiscalService notaFiscalService;

  @PostMapping
  public ResponseEntity<Map<String, String>> gerarNotaFiscal(@RequestBody Pedido pedido) {
    try {
      notaFiscalService.emitirNotaFiscal(pedido);
    } catch (JsonProcessingException e) {
      ResponseEntity.internalServerError().build();
      throw new RuntimeException(e);
    }

    return ResponseEntity.ok(Map.of("message:", "Nota Fiscal Gerada Com sucesso!"));
  }
}
