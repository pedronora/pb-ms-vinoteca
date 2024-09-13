package br.edu.infnet.impostoservice.controller;

import br.edu.infnet.impostoservice.payload.DetailPayload;
import br.edu.infnet.impostoservice.model.PedidoPayload;
import br.edu.infnet.impostoservice.service.ImpostoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Imposto Controller", description = "Gerencia as operações relacionadas ao cálculo de impostos dos pedidos")
public class ImpostoController {
    
    private final ImpostoService impostoService;

    @Operation(summary = "Calcular imposto")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Imposto calculado com sucesso.",
                            content = {@Content(schema = @Schema(implementation = PedidoPayload.class))}),
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
    public ResponseEntity calcularImposto(@RequestBody PedidoPayload pedidoPayload){
        BigDecimal impostoTotal = impostoService.calcularImpostoTotal(pedidoPayload);
        return ResponseEntity.ok(Map.of("totalImposto", impostoTotal));
    }
}
