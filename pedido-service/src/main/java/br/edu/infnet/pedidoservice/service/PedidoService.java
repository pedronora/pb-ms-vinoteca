package br.edu.infnet.pedidoservice.service;

import br.edu.infnet.pedidoservice.model.Pedido;
import br.edu.infnet.pedidoservice.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    public Pedido salvar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> getAll(){
        return pedidoRepository.findAll();
    }
}
