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
public class Povlasceni {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kartaId;
    private boolean dozivotna;
    private int countGodine;
    private BigDecimal popust;
}
