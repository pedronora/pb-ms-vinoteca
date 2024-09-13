package br.edu.infnet.pedidoservice.repository;

import br.edu.infnet.pedidoservice.model.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends MongoRepository<Pedido, String> {}
