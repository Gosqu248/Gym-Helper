package com.javappa.start.calorie_calculator_classes;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "produkt_spozywczy")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_produktu")
    private int id;
    @Column(name = "nazwa_produktu")
    private String name;
    @Column(name = "kalorie")
    private int kcal;
    @Column(name = "weglowodany")
    private int carbs;
    @Column(name = "tluszcze")
    private int fats;
    @Column(name = "bialka")
    private int proteins;
    @Column(name = "waga_porcji")
    private int weight;

    public Product() {
    }

    public Product(String _name, int kcal, int carbs, int fats, int proteins, int weight){
        this.name=_name;
        this.kcal = kcal;
        this.id = id;
        this.carbs = carbs;
        this.fats = fats;
        this.proteins = proteins;
        this.weight = weight;
    }
}
