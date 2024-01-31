package com.javappa.start;

import com.javappa.start.calorie_calculator_classes.Meal;
import com.javappa.start.calorie_calculator_classes.Product;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    public List<Plan> retrievePlanFromDatabase() {
        List<Plan> plany = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM aplikacja.plan_treningowy";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id_planu");
                    String name = resultSet.getString("nazwa_planu");
                    String description = resultSet.getString("opis");
                    String lvl = resultSet.getString("poziom_zaawansowania");
                    int id_user = resultSet.getInt("id_uzytkownika");
                    Plan plan = new Plan(id, name, description, lvl, id_user);
                    plany.add(plan);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plany;
    }

    public List<Cwiczenia> retrieveExerciseFromDatabase(int nrPlanu, int jedn) {
        List<Cwiczenia> cwiczenia = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT pt.id_planu, cwt.\"id_jednostki\", cw.\"nazwa_cwiczenia\", cwt.\"liczba_serii\", cwt.\"liczba_powtorzen\", cw.opis\n" +
                    "FROM aplikacja.plan_treningowy pt\n" +
                    "JOIN aplikacja.cwiczenie_w_treningu cwt ON pt.\"id_planu\" = cwt.\"id_planu\"\n" +
                    "JOIN aplikacja.cwiczenie cw ON cwt.\"id_cwiczenia\" = cw.\"id_cwiczenia\"\n" +
                    "WHERE pt.\"id_planu\" = ? AND cwt.\"id_jednostki\" = ?\n"; // Zmiana: wyszukiwanie po id_planu
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, nrPlanu);
                statement.setInt(2, jedn);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id_planu");
                        int id_jednostki = resultSet.getInt("id_jednostki");
                        String name = resultSet.getString("nazwa_cwiczenia");
                        int series = resultSet.getInt("liczba_serii");
                        int reps = resultSet.getInt("liczba_powtorzen");
                        String description = resultSet.getString("opis");

                        Cwiczenia cw = new Cwiczenia(id, id_jednostki, name, series, reps, description);
                        cwiczenia.add(cw);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cwiczenia;
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

        while (meals.size()<5){

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
    //Id generating
    public static long generateUniqueIdAsLong() {
        UUID uuid = UUID.randomUUID();
        long mostSignificantBits = uuid.getMostSignificantBits();
        long leastSignificantBits = uuid.getLeastSignificantBits();

        return mostSignificantBits ^ leastSignificantBits;
    }
    public static boolean isUniqueIdExists(long uniqueId) {
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

    public List<User> retrieveUserFromDatabase() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM aplikacja.uzytkownik";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id_uzytkownika");
                    String name = resultSet.getString("imie");
                    String surrname = resultSet.getString("nazwisko");
                    String email = resultSet.getString("email");
                    String username = resultSet.getString("nazwa_uzytkownika");
                    String password = resultSet.getString("haslo");

                    User user = new User(id, username, password, name, surrname, email);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        User admin = new User(1, "admin", "1234", "admin@example.com", "admin", "admin123");
        users.add(admin);


        return users;
    }
}


