package br.edu.infnet.impostoservice.controller;

import br.edu.infnet.impostoservice.model.PedidoPayload;
import br.edu.infnet.impostoservice.service.ImpostoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class ImpostoController {
    
    private final ImpostoService impostoService;

    @PostMapping
    public ResponseEntity calcularImposto(@RequestBody PedidoPayload pedidoPayload){
        BigDecimal impostoTotal = impostoService.calcularImpostoTotal(pedidoPayload);
        return ResponseEntity.ok(Map.of("totalImposto", impostoTotal));
    }
}
