package com.javappa.start.calorie_calculator_classes;

import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Setter
@NoArgsConstructor
public class Meal {
    private String name;
    private int carbs;
    private int fats;
    private int proteins;
    private int kcal;
    private List<Product> products;
    private long id;

    public Meal(long id, String name) {
        this.name = name;
        this.id = id;
        this.carbs = 0;
        this.fats = 0;
        this.kcal = 0;
        this.proteins = 0;
        products = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public long getId() {
        return id;
    }

    public int getCarbs() {
        return carbs;
    }

    public int getFats() {
        return fats;
    }

    public int getProteins() {
        return proteins;
    }

    public int getKcal() {
        return kcal;
    }
    public List<Product> getProducts() {
        return products;
    }
    public void removeProductById(long productId) {
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getId() == productId) {
                subtractMacro(product);
                iterator.remove();
                System.out.println("Product deleted: " + product.getName());
                return;
            }
        }
        System.out.println("Product under such id does not exist!.");
    }
    public void addNewProduct(Product product){
        this.products.add(product);
        addMacro(product);
    }
    private void subtractMacro(Product product){
        this.kcal -= product.getKcal();
        this.fats -= product.getFats();
        this.carbs -= product.getCarbs();
        this.proteins -= product.getProteins();
    }

    private void addMacro (Product product){
        this.kcal += product.getKcal();
        this.fats += product.getFats();
        this.carbs += product.getCarbs();
        this.proteins += product.getProteins();
    }

    @Override
    public String toString() {
        return "Meal{" +
                "name='" + name + '\'' +
                ", carbs=" + carbs +
                ", fats=" + fats +
                ", proteins=" + proteins +
                ", kcal=" + kcal +
                ", products=" + products +
                ", id=" + id +
                '}';
    }
}
