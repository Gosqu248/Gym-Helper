package com.javappa.start;

import com.javappa.start.calorie_calculator_classes.Meal;
import com.javappa.start.calorie_calculator_classes.Product;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@NoArgsConstructor
public class DBFetch {
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

    //Products
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
                statement.setLong(2, productId);
                statement.setInt(3, weight);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Meals
    public List<Long> retrieveMealsByDay(LocalDate date, long userId) {
        List<Long> mealIds = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM aplikacja.odzywianie " +
                    "WHERE data = ? AND id_uzytkownika = ?;";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setDate(1, java.sql.Date.valueOf(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
                statement.setLong(2, userId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        mealIds.add(resultSet.getLong("id_posilku"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mealIds;
    }

    public List<Meal> retrieveMealsContent(LocalDate date, long userId) {
        List<Long> mealIds = retrieveMealsByDay(date, userId);
        List<Meal> meals = new ArrayList<>();
        if (mealIds.isEmpty()) {
            return meals;
        }

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String placeholders = String.join(",", Collections.nCopies(mealIds.size(), "?"));

            String query = "SELECT * FROM aplikacja.posilek WHERE id_posilku IN (" + placeholders + ");";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                for (int i = 0; i < mealIds.size(); i++) {
                    statement.setLong(i + 1, mealIds.get(i));
                }

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        long id = resultSet.getLong("id_posilku");
                        String name = resultSet.getString("nazwa_posilku");
                        Meal meal = new Meal(id, name);
                        meals.add(meal);
                    }
                }
            }
            Iterator<Meal> iterator = meals.iterator();
            query = "SELECT * FROM aplikacja.produkt_w_posilku p " +
                    "JOIN aplikacja.produkt_spozywczy ON p.id_produktu = produkt_spozywczy.id_produktu " +
                    "WHERE p.id_posilku = ?;";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                while (iterator.hasNext()) {
                    Meal meal = iterator.next();
                    statement.setLong(1, meal.getId());
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            String name = resultSet.getString("nazwa_produktu");
                            long id = resultSet.getInt("id_produktu");
                            int kcal = resultSet.getInt("kalorie");
                            int carbs = resultSet.getInt("weglowodany");
                            int fats = resultSet.getInt("tluszcze");
                            int proteins = resultSet.getInt("bialko");
                            int weight = resultSet.getInt("waga_produktu");
                            Product tmp = new Product(id, name, kcal, carbs, fats, proteins, weight);
                            tmp.adjustToWeight();
                            meal.addNewProduct(tmp);
                        }
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return meals;
    }
}