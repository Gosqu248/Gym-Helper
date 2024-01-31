package com.javappa.start.Exercises;

import com.javappa.start.Other_classes.DBFetch;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;
@Controller
public class Plan2Controller extends DBFetch {
    @GetMapping("/Plan2")
    public String plan2(Model model) {
        model.addAttribute("logo", "Gym Helper");
        model.addAttribute("logo2", "Optimal Fitness Lifestyle");

        List<String> navLinks = Arrays.asList("/", "/Plany Treningowe", "/Kalkulator Kalorii", "/Porady");
        model.addAttribute("navLinks", navLinks);

        model.addAttribute("porady", "Porady");
        model.addAttribute("description", "Porady skutecznie pomogą Ci zacząć z siłownią i dzięki nim dowierz się, jaki cel chcesz osiągnąć i  w jaki sposób.");

        List<Plan> plany = retrievePlanFromDatabase();

        model.addAttribute("name", "Plan " +plany.get(8).getName() + "  (" + plany.get(8).getLvl() + ")");

        List<Cwiczenia> cwiczenia1 =  retrieveExerciseFromDatabase(10,1);

        model.addAttribute("cwiczenia", cwiczenia1);

        List<Cwiczenia> cwiczenia2 =  retrieveExerciseFromDatabase(10,2);

        model.addAttribute("cwiczenia2", cwiczenia2);

        List<Cwiczenia> cwiczenia3 =  retrieveExerciseFromDatabase(10,3);

        model.addAttribute("cwiczenia3", cwiczenia3);



        model.addAttribute("cz", "Częstotliwość treningowa");
        model.addAttribute("opis", " Trening wykonujemy 3 razy w tygodniu. Po każdym treningu musi być min. 1 dzień odpoczynku");



        model.addAttribute("footerText", "CORPORATE FITNESS 🔸 NUTRITIONAL ADVICE 🔸 WEIGHT LOSS 🔸 MUSCLE TONE 🔸 CORE STRENGTH 🔸 POSTURE CORRECTION 🔸 CARDIO FITNESS");

        model.addAttribute("endText", "@ 2035 by GymHelper");

        return "plan2";
    }
}
