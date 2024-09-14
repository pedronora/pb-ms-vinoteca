package br.edu.infnet.notafiscalworker.model;


import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pedido {
  private String id;
  private List<ItemPedido> items;
  private BigDecimal totalImposto;
}
