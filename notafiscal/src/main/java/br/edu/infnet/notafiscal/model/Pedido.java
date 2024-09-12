package br.edu.infnet.notafiscal.model;

import java.math.BigDecimal;
import java.util.List;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Pedido {
  private String id;
  private List<ItemPedido> item;
  private BigDecimal totalImposto;
}
