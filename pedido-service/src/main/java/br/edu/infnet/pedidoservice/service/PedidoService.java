package br.edu.infnet.pedidoservice.service;

import br.edu.infnet.pedidoservice.model.ItemPedido;
import br.edu.infnet.pedidoservice.model.Pedido;
import br.edu.infnet.pedidoservice.model.Vinho;
import br.edu.infnet.pedidoservice.repository.PedidoRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PedidoService {
  private final PedidoRepository pedidoRepository;
  private final VinhoService vinhoService;

  public Pedido salvar(Pedido pedido) {
    return pedidoRepository.save(pedido);
  }

  public List<Pedido> getAll() {
    return pedidoRepository.findAll();
  }

  public Optional<Pedido> getById(String id) {
    return pedidoRepository.findById(id);
  }

  public Pedido update(Pedido pedido) {
    return pedidoRepository.save(pedido);
  }

  public void delelteById(String id) {
    pedidoRepository.deleteById(id);
  }

  public BigDecimal calcularSubtotal(Pedido pedido) {
    return pedido.getItems().stream()
        .map(this::calcularItem)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public BigDecimal calcularItem(ItemPedido itemPedido) {
    Vinho vinho = vinhoService.getById(itemPedido.getVinhoId());
    return vinho.getPreco().multiply(BigDecimal.valueOf(itemPedido.getQuantidade()));
  }
}
