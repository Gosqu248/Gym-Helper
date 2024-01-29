package com.javappa.start;

import com.javappa.start.calorie_calculator_classes.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

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
