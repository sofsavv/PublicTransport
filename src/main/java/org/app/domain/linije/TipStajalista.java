package org.app.domain.linije;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter

@Entity
public class TipStajalista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tipStajalistaId;
    private String tipStajalista;
}
