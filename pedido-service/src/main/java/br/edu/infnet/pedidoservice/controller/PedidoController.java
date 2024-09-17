package br.edu.infnet.pedidoservice.controller;

import br.edu.infnet.pedidoservice.payload.ImpostoResponsePayload;
import br.edu.infnet.pedidoservice.model.Pedido;
import br.edu.infnet.pedidoservice.payload.DetailPayload;
import br.edu.infnet.pedidoservice.service.ImpostoService;
import br.edu.infnet.pedidoservice.service.NFService;
import br.edu.infnet.pedidoservice.service.PedidoService;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
@Tag(
    name = "Pedido Controller",
    description = "Gerencia as operações relacionadas à emissão de Pedidos")
public class PedidoController {
  private final PedidoService pedidoService;
  private final ImpostoService impostoService;
  private final NFService nfService;

  @Operation(summary = "Criar um pedido")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "201",
            description = "Pedido criado com sucesso",
            content = {@Content(schema = @Schema(implementation = Pedido.class))}),
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
  public ResponseEntity<?> cadastrarPedido(@RequestBody Pedido pedido) {
    if (pedido.getItems().isEmpty()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O pedido não possui itens.");
    }

    try {
      log.info("Cadastrando pedido: {}", pedido);
      BigDecimal totalImposto = impostoService.getTotalImposto(pedido).totalImposto();

      BigDecimal subtotal = pedidoService.calcularSubtotal(pedido);
      pedido.setSubtotal(subtotal);
      pedido.setTotalImposto(totalImposto);
      pedido.setTotal(subtotal.add(totalImposto));

      Pedido pedidoSalvo = pedidoService.salvar(pedido);

      log.info("Items: {}", pedidoSalvo.getItems());
      log.info("Subtotal: {}", pedidoSalvo.getSubtotal());
      log.info("TotalImposto: {}", pedidoSalvo.getTotalImposto());
      log.info("Total: {}", pedidoSalvo.getTotal());

      nfService.emitirNotaFiscal(pedidoSalvo);

      return ResponseEntity.status(HttpStatus.CREATED).body(pedidoSalvo);
    } catch (Exception e) {
      log.error("Erro ao cadastrar pedido: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new DetailPayload(e.getMessage()));
    }
  }

  @Operation(summary = "Retorna a lista de pedidos")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = {
              @Content(
                  mediaType = "application/json",
                  array = @ArraySchema(schema = @Schema(implementation = Pedido.class))),
            }),
        @ApiResponse(responseCode = "404", description = "Não há vinhos para exibir")
      })
  @GetMapping
  public ResponseEntity<List<Pedido>> listarPedidos() {
    List<Pedido> pedidos = pedidoService.getAll();

    if (pedidos.isEmpty()) {
      log.info("Não há PEDIDOS para serem apresentados.");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    } else {
      log.info("Buscando lista de pedidos");
      return ResponseEntity.ok(pedidos);
    }
  }

  @Operation(summary = "Retorna um pedido por ID")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Pedido encontrado",
            content = {@Content(schema = @Schema(implementation = Pedido.class))}),
        @ApiResponse(
            responseCode = "404",
            description = "Pedido não encontrado",
            content = {@Content(schema = @Schema(implementation = DetailPayload.class))}),
      })
  @GetMapping("/{id}")
  public ResponseEntity<?> buscarPedidoPorId(@PathVariable String id) {
    Optional<Pedido> pedidoEncontrado = pedidoService.getById(id);

    if (pedidoEncontrado.isEmpty()) {
      log.error("Pedido com id " + id + " não encontrado!");
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new DetailPayload("Pedido com id " + id + " não encontrado!"));
    }
    log.info("Pedido com id " + id + " encontrado!");
    return ResponseEntity.status(HttpStatus.OK).body(pedidoEncontrado);
  }

  @Operation(summary = "Atualizar um pedido existente")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Pedido atualizado com sucesso",
            content = {@Content(schema = @Schema(implementation = Pedido.class))}),
        @ApiResponse(
            responseCode = "400",
            description = "ID inválido",
            content = {@Content(schema = @Schema(implementation = DetailPayload.class))}),
        @ApiResponse(
            responseCode = "404",
            description = "Pedido não encontrado!",
            content = {@Content(schema = @Schema(implementation = DetailPayload.class))})
      })
  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable String id, @RequestBody Pedido pedido) {
    if (pedido.getId() == null || !pedido.getId().equals(id)) {
      log.error("Erro ao atualizar o pedido!");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DetailPayload("ID invalido!"));
    }

    Optional<Pedido> pedidoToUpdate = pedidoService.getById(id);
    if (pedidoToUpdate.isEmpty()) {
      log.error("Pedido não encontrado!");
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new DetailPayload("Pedido não encontrado!"));
    }

    Pedido pedidoUpdated = new Pedido();
    pedidoUpdated.setId(id);
    pedidoUpdated.setItems(pedido.getItems());
    pedidoUpdated.setSubtotal(pedido.getSubtotal());
    pedidoUpdated.setTotalImposto(pedido.getTotalImposto());
    pedidoUpdated.setTotal(pedido.getTotal());

    Pedido updated = pedidoService.update(pedidoUpdated);
    log.info("Pedido atualizado com sucesso!");
    return ResponseEntity.status(HttpStatus.OK).body(updated);
  }

  @Operation(summary = "Deletar um pedido por ID")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Pedido deletado com sucesso",
            content = {@Content(schema = @Schema(implementation = DetailPayload.class))}),
        @ApiResponse(
            responseCode = "404",
            description = "Pedido não encontrado",
            content = {@Content(schema = @Schema(implementation = DetailPayload.class))})
      })
  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable String id) {
    Optional<Pedido> pedidoToDelete = pedidoService.getById(id);
    if (pedidoToDelete.isEmpty()) {
      log.error("Pedido não encontrado!");
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new DetailPayload("Vinho não encontrado!"));
    }

    pedidoService.delelteById(id);
    log.info("Pedido deletado com sucesso!");
    return ResponseEntity.status(HttpStatus.OK)
        .body(new DetailPayload("Vinho com id: " + id + " deletado com sucesso!"));
  }
}
