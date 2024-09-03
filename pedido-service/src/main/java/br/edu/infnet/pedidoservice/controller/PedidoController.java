package br.edu.infnet.pedidoservice.controller;

import br.edu.infnet.pedidoservice.model.Pedido;
import br.edu.infnet.pedidoservice.model.ImpostoResponsePayload;
import br.edu.infnet.pedidoservice.service.ImpostoService;
import br.edu.infnet.pedidoservice.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class PedidoController {
    private final PedidoService pedidoService;
    private final ImpostoService impostoService;

    @PostMapping
    public ResponseEntity<Pedido> cadastrarPedido(@RequestBody Pedido pedido) {
        // Calculo do Imposto a ser aplicado no Pedido
        ImpostoResponsePayload impostoResponse = impostoService.getTotalImposto(pedido);
        System.out.println("totalImposto: " + impostoResponse);
        // Salvar pedido no banco com o imposto calculado
        pedido.setTotalImposto(impostoResponse.totalImposto());
        pedidoService.salvar(pedido);

        return ResponseEntity.ok(pedido);
    }
}
