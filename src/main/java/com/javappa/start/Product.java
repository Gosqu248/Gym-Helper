package com.javappa.start;

public class Product {
    private String name;
    private int kcal;
    private int id;
    private int carbs;
    private int fats;
    private int proteins;
    private int weight;
    public Product(int id, String _name, int kcal, int carbs, int fats, int proteins, int weight){
        this.name=_name;
        this.kcal = kcal;
        this.id = id;
        this.carbs = carbs;
        this.fats = fats;
        this.proteins = proteins;
        this.weight = weight;
    }

    public int getProteins() {
        return proteins;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public int getKcal() {
        return kcal;
    }

    public int getId() {
        return id;
    }

    public int getCarbs() {
        return carbs;
    }

    public int getFats() {
        return fats;
    }
}
