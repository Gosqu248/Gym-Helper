package com.javappa.start;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@Entity
public class Porada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_porady;

    private String tytul;
    private String kategoria;
    private String tresc;


    public Porada() {
    }

    public Porada(String tytul, String kategoria, String tresc) {
        this.tytul = tytul;
        this.kategoria = kategoria;
        this.tresc = tresc;
    }

}
