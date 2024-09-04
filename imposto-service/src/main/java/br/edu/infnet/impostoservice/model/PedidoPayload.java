package br.edu.infnet.impostoservice.model;

import java.util.List;

public record PedidoPayload(List<ItemPedido> items) {
}
