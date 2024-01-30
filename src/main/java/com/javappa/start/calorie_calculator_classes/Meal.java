package com.javappa.start.calorie_calculator_classes;

import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private int id;

    public Meal(int id, String name, List<Product> products) {
        this.name = name;
        this.products = products;
        this.id = id;
        this.carbs = 0;
        this.fats = 0;
        this.kcal = 0;
        this.proteins = 0;
    }

    public String getName() {
        return name;
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

    public int getProteins() {
        return proteins;
    }

    public int getKcal() {
        return kcal;
    }
    public List<Product> getProducts() {
        return products;
    }
    public void removeProductById(int productId) {
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
        return;
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
}
