package br.edu.infnet.notafiscal.service;

import br.edu.infnet.notafiscal.model.Pedido;
import br.edu.infnet.notafiscal.rabbitmq.NotaFiscalProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotaFiscalService {
    private final NotaFiscalProducer producer;
    public void emitirNotaFiscal(Pedido pedido) throws JsonProcessingException {
        producer.send(pedido);
    }
}