package br.edu.infnet.impostoservice.service;

import br.edu.infnet.impostoservice.model.*;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImpostoService {
    private final VinhoService vinhoService;
    private final VinicolaService vinicolaService;

    public BigDecimal calcularImpostoTotal(PedidoPayload pedidoPayload){
        log.info("Recebido PedidoPayload: {}", pedidoPayload);
        return pedidoPayload.items().stream()
                .map(this::calcularImposto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calcularImposto(ItemPedido itemPedido){
        Vinho vinho = vinhoService.getById(itemPedido.getVinhoId());
        log.info("Recebido ItemPedido: {}", vinho);
        Vinicola vinicola = vinicolaService.getById(vinho.getVinicolaId());
        BigDecimal taxa = getTaxas(vinicola.getPais());
        return  vinho.getPreco()
                .multiply(taxa)
                .multiply(new BigDecimal(itemPedido.getQuantidade()));
    }

    private BigDecimal getTaxas(Pais pais){
        return switch (pais){
            case BRASIL -> new BigDecimal("0.15");
            case PORTUGAL -> new BigDecimal("0.05");
            case ESPANHA -> new BigDecimal("0.03");
            case ARGENINA -> new BigDecimal("0.01");
            case CHILE -> new BigDecimal("0.02");
            case ITALIA -> new BigDecimal("0.20");
            case FRANCA -> new BigDecimal("0.22");
            case AUSTRALIA -> new BigDecimal("0.3");
        };
    }
}
