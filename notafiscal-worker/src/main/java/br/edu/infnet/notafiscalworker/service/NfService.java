package br.edu.infnet.notafiscalworker.service;

import br.edu.infnet.notafiscalworker.model.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NfService {
  private final EmailService emailService;

  public void processar(Pedido pedido) {
    try {
      log.info("Gerando nota Fiscal do pedido: " + pedido);

    } catch (RuntimeException e) {
      log.error(e.getMessage());
    }

    try {
      emailService.sendMail(
          "pedro.nora@al.infnet.edu.br",
          "Nota fiscal para o pedido " + pedido.getId(),
          "Segue a Nota Fiscal para o seu pedido: " + pedido.getId());
      log.info("Nota Fiscal enviada por email.");

    } catch (MailException e) {
      throw new RuntimeException(e);
    }
  }
}
