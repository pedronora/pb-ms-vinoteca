package br.edu.infnet.pedidoservice.service;

import br.edu.infnet.pedidoservice.model.Pedido;
import br.edu.infnet.pedidoservice.repository.PedidoRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PedidoService {
  private final PedidoRepository pedidoRepository;

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
}
