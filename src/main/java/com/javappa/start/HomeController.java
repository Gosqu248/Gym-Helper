package com.javappa.start;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

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

    @GetMapping
    public String hello(Model model) {
        model.addAttribute("logo", "Gym Helper");
        model.addAttribute("logo2", "Optimal Fitness Lifestyle");

        List<String> navLinks = Arrays.asList("/", "/Plany Treningowe","/Kalkulator Kalorii","/Porady");
        model.addAttribute("navLinks", navLinks);

        model.addAttribute("welcome", "Witamy na stronie GymHelper !");

        model.addAttribute("sectionText", "Strona została stworzona dla amatorów, którzy chcą zacząć z siłownią, a nie wiedzą jak się za to wziąć, a także dla tych, którzy chcą sobie przypomnieć wiedzę lub dowiedzieć się czegoś nowego.");

        List<String> pageFeatures = Arrays.asList(
                "🔸 Gotowe plany treningowe, dla różnego stopnia zaawansowania",
                "🔸 Kalkulator kalorii, dzięki czemu możesz obliczyć swoje spożyte kalorie",
                "🔸 Porady: jak zacząć z siłownią, jak zacząć redukować, jak zacząć masować");
        model.addAttribute("pageFeatures", pageFeatures);

        model.addAttribute("what", "Na stronie znajdziesz przede wszystkim:");

        model.addAttribute("footerText", "CORPORATE FITNESS 🔸 NUTRITIONAL ADVICE 🔸 WEIGHT LOSS 🔸 MUSCLE TONE 🔸 CORE STRENGTH 🔸 POSTURE CORRECTION 🔸 CARDIO FITNESS");

        model.addAttribute("endText", "@ 2035 by GymHelper");

        return "hello";
    }
}
