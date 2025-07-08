package org.app.domain.vozaci;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Embeddable
public class RadnoVreme {

    private int brojSatiTokomDana;
    private int brojSatiSubotom;
    private int brojSatiNedeljom;
}
