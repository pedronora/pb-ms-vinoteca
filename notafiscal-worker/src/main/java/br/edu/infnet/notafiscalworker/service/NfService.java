package br.edu.infnet.notafiscalworker.service;

import br.edu.infnet.notafiscalworker.model.Pedido;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NfService {
  public void processar(Pedido pedido){
    int i = new Random().nextInt(1000);
    log.info("Random: " + i );
    if(i > 20){
     try {
    Thread.sleep(10_000);
    log.info("Gerando nota Fiscal do pedido: " +  pedido);
    throw new RuntimeException("Erro");
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
