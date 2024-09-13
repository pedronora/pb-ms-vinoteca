package br.edu.infnet.notafiscalworker.model;

import br.edu.infnet.pedidoservice.model.ItemPedido;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "pedidos")
public class Pedido {
  @Id private String id;
  private List<ItemPedido> items;
  private BigDecimal totalImposto;
}
