package com.javappa.start.Exercises;

import com.javappa.start.Classes.DBFetch;
import com.javappa.start.Exercises.Cwiczenia;
import com.javappa.start.Exercises.Plan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class Plan1Controller extends DBFetch {

    @GetMapping("/Plan1")
    public String plan1(Model model) {
        model.addAttribute("logo", "Gym Helper");
        model.addAttribute("logo2", "Optimal Fitness Lifestyle");

        List<String> navLinks = Arrays.asList("/", "/Plany Treningowe", "/Kalkulator Kalorii", "/Porady");
        model.addAttribute("navLinks", navLinks);

        model.addAttribute("porady", "Porady");
        model.addAttribute("description", "Porady skutecznie pomogą Ci zacząć z siłownią i dzięki nim dowierz się, jaki cel chcesz osiągnąć i  w jaki sposób.");

        List<Plan> plany = retrievePlanFromDatabase();

        model.addAttribute("name", "Plan " +plany.get(65).getName() + "  (" + plany.get(65).getLvl() + ")");

        List<Cwiczenia> cwiczenia1 =  retrieveExerciseFromDatabase(7,1);

        model.addAttribute("cwiczenia", cwiczenia1);

        List<Cwiczenia> cwiczenia2 =  retrieveExerciseFromDatabase(7,2);

        model.addAttribute("cwiczenia2", cwiczenia2);

        List<Cwiczenia> cwiczenia3 =  retrieveExerciseFromDatabase(7,3);

        model.addAttribute("cwiczenia3", cwiczenia3);



        model.addAttribute("cz", "Częstotliwość treningowa");
        model.addAttribute("opis", " Trening wykonujemy 3 razy w tygodniu. Po każdym treningu musi być min. 1 dzień odpoczynku");



        model.addAttribute("footerText", "CORPORATE FITNESS 🔸 NUTRITIONAL ADVICE 🔸 WEIGHT LOSS 🔸 MUSCLE TONE 🔸 CORE STRENGTH 🔸 POSTURE CORRECTION 🔸 CARDIO FITNESS");

        model.addAttribute("endText", "@ 2035 by GymHelper");

        return "plan1";
    }
}
