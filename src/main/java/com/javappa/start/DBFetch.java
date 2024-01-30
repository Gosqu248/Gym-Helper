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
    public void addNewMeal(long mealId, long userId, LocalDate date, String name){
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO aplikacja.odzywianie(id_uzytkownika, id_posilku, data, wykonany) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, userId);
                statement.setLong(2, mealId);
                statement.setDate(3, java.sql.Date.valueOf(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
                statement.setBoolean(4, true);
                statement.executeUpdate();
            }
            query = "INSERT INTO aplikacja.posilek(id_posilku, nazwa_posilku) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, mealId);
                statement.setString(2, name);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void removeEmptyMeal(long mealId){
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "DELETE FROM aplikacja.odzywianie WHERE id_posilku = ?;";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, mealId);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Rekord został usunięty.");
                } else {
                    System.out.println("Nie znaleziono rekordu o podanym ID.");
                }
            }
            query = "DELETE FROM aplikacja.posilek WHERE id_posilku = ?;";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, mealId);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Rekord został usunięty.");
                } else {
                    System.out.println("Nie znaleziono rekordu o podanym ID.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void removeProductFromMeal(long mealId, long productId){
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "DELETE FROM aplikacja.produkt_w_posilku WHERE id_posilku = ? AND id_produktu = ?;";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, mealId);
                statement.setLong(2,productId);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Rekord został usunięty.");
                } else {
                    System.out.println("Nie znaleziono rekordu o podanym ID.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static long generateUniqueId() {
        return Long.valueOf(UUID.randomUUID().toString());
    }
    private static boolean isUniqueIdExists(long uniqueId) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT COUNT(*) FROM aplikacja.odzywianie WHERE id_posilku = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, uniqueId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        return count > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}