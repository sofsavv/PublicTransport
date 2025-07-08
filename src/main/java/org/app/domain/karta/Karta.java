package org.app.domain.karta;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter

@Entity
public class Karta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kartaId;
    private BigDecimal cenaKarte;

    @ManyToOne
    @JoinColumn(name = "preiodVazenjaId")
    private PeriodVazenja periodVazenja;

}
