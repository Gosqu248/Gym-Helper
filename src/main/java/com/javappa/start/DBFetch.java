package com.javappa.start;

import com.javappa.start.calorie_calculator_classes.Product;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class DBFetch
{
    public static final String url = "jdbc:postgresql://195.150.230.208:5432/2023_urban_grzegorz";
    public static final String username = "2023_urban_grzegorz";
    public static final String password = "35240";

    public List<PoradaTreningowa> retrievePoradyFromDatabase() {
        List<PoradaTreningowa> porady = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM aplikacja.porada_treningowa";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id_porady");
                    String tytul = resultSet.getString("tytul");
                    String kategoria = resultSet.getString("kategoria");
                    String tresc = resultSet.getString("tresc");
                    PoradaTreningowa porada = new PoradaTreningowa(id, tytul, kategoria, tresc);
                    porady.add(porada);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return porady;
    }
    //Products and meals
    public List<Product> retrieveProductFromDatabase() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM aplikacja.produkt_spozywczy LIMIT 20;";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String name = resultSet.getString("nazwa_produktu");
                    long id = resultSet.getInt("id_produktu");
                    int kcal = resultSet.getInt("kalorie");
                    int carbs = resultSet.getInt("weglowodany");
                    int fats = resultSet.getInt("tluszcze");
                    int proteins = resultSet.getInt("bialko");
                    int weight = resultSet.getInt("waga_porcji");
                    Product product = new Product(id, name, kcal, carbs, fats, proteins, weight);
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
    public void saveProductInMealToDatabase(long mealId, long productId, int weight) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO aplikacja.produkt_w_posilku(id_posilku, id_produktu, waga_produktu) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, mealId);
                statement.setLong(2,productId);
                statement.setLong(3, weight);

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
