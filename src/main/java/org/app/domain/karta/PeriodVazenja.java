package org.app.domain.karta;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Getter
@Setter

@Entity
public class PeriodVazenja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long preiodVazenjaId;
    private String periodVazenja;
    private BigDecimal cenaZaPeriod;


}
