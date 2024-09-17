package br.edu.infnet.notafiscalworker.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

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
  private BigDecimal subtotal;
  private BigDecimal totalImposto;
  private BigDecimal total;

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    NumberFormat formatadorMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    for (ItemPedido item : items) {

      sb.append(
          String.format(
              "Vinho ID: %d    Quantidade: %d \n", item.getVinhoId(), item.getQuantidade()));
    }

    sb.append(String.format("\nSubtotal: %s\n", formatadorMoeda.format(subtotal)));
    sb.append(String.format("Total Imposto: %s\n", formatadorMoeda.format(totalImposto)));
    sb.append(String.format("Total da compra: %s\n", formatadorMoeda.format(total)));
    return sb.toString();
  }
}
