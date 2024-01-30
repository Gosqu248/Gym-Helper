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
    public List<Product> retrieveProductFromDatabase() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM aplikacja.produkt_spozywczy";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String name = resultSet.getString("nazwa_produktu");
                    long id = resultSet.getInt("id_porady");
                    int kcal = resultSet.getInt("kalorie");
                    int carbs = resultSet.getInt("weglowodany");
                    int fats = resultSet.getInt("tluszcze");
                    int proteins = resultSet.getInt("bialka");
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



}
