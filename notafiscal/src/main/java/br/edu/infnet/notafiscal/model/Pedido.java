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
  private List<ItemPedido> items;
  private BigDecimal subtotal;
  private BigDecimal totalImposto;
  private BigDecimal total;
}
