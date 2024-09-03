package br.edu.infnet.impostoservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

enum Docura {
    SECO,
    DEMISEC,
    DOCE
}

enum Cor {
    BRANCO,
    ROSE,
    TINTO
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vinho {
    private Long id;
    private String nome;
    private BigDecimal preco;
    private String uva;
    private Docura docura;
    private Cor cor;
    private long vinicolaId;

    private enum Docura {
        SECO,
        DEMISEC,
        DOCE
    }

    private enum Cor {
        BRANCO,
        ROSE,
        TINTO
    }
}
