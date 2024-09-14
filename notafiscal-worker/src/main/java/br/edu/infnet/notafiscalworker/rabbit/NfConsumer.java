package br.edu.infnet.notafiscalworker.rabbit;

import br.edu.infnet.notafiscalworker.model.Pedido;
import br.edu.infnet.notafiscalworker.service.NfService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class NfConsumer {
  private final ObjectMapper objectMapper;
  private final NfService nfService;

  @RabbitListener(queues = {"nota-fiscal-queue"})
  public void receive(@Payload String json) {
    try {
      Pedido pedido = objectMapper.readValue(json, Pedido.class);
      nfService.processar(pedido);
      log.info("Nota fiscal received: {}", pedido);

    } catch (JsonProcessingException e) {
      log.error(e.getMessage());
      throw new RuntimeException(e);
    }
  }
}
