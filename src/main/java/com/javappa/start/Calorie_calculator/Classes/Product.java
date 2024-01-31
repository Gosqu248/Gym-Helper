package com.javappa.start.Calorie_calculator.Classes;

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

    private long id;
    private String name;
    private int kcal;
    private int carbs;
    private int fats;
    private int proteins;
    private int weight;

    public Product(String _name, int kcal, int carbs, int fats, int proteins, int weight){
        this.name=_name;
        this.kcal = kcal;
        this.carbs = carbs;
        this.fats = fats;
        this.proteins = proteins;
        this.weight = weight;
    }
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", kcal=" + kcal +
                ", carbs=" + carbs +
                ", fats=" + fats +
                ", proteins=" + proteins +
                ", weight=" + weight +
                '}';
    }
    public void adjustToWeight(){
        this.kcal *= (this.weight)/100.0;
        this.fats *= (this.weight)/100.0;
        this.carbs *= (this.weight)/100.0;
        this.proteins *= (this.weight)/100.0;

    }
}
