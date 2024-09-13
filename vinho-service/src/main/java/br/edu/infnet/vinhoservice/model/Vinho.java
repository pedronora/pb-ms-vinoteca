package br.edu.infnet.vinhoservice.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Vinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal preco;
    private String uva;

    @Enumerated(EnumType.STRING)
    private Docura docura;
    @Enumerated(EnumType.STRING)
    private Cor cor;

    @Column(name = "vinicola_id")
    private int vinicolaId;

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