package com.javappa.start.calorie_calculator_classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int id;
    private String name;
    private int kcal;
    private int carbs;
    private int fats;
    private int proteins;
    private int weight;

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
