package com.javappa.start.calorie_calculator_classes;

import com.javappa.start.Cwiczenia;
import com.javappa.start.DBFetch;
import com.javappa.start.Plan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class Plan3Controller extends DBFetch {
    @GetMapping("/Plan3")
    public String plan3(Model model) {
        model.addAttribute("logo", "Gym Helper");
        model.addAttribute("logo2", "Optimal Fitness Lifestyle");

        List<String> navLinks = Arrays.asList("/", "/Plany Treningowe", "/Kalkulator Kalorii", "/Porady");
        model.addAttribute("navLinks", navLinks);

        model.addAttribute("porady", "Porady");
        model.addAttribute("description", "Porady skutecznie pomogą Ci zacząć z siłownią i dzięki nim dowierz się, jaki cel chcesz osiągnąć i  w jaki sposób.");

        List<Plan> plany = retrievePlanFromDatabase();

        model.addAttribute("name", "Plan " +plany.get(17).getName() + "  (" + plany.get(17).getLvl() + ")");

        List<Cwiczenia> cwiczenia1 =  retrieveExerciseFromDatabase(19,1);

        model.addAttribute("cwiczenia", cwiczenia1);

        List<Cwiczenia> cwiczenia2 =  retrieveExerciseFromDatabase(19,2);

        model.addAttribute("cwiczenia2", cwiczenia2);

        List<Cwiczenia> cwiczenia3 =  retrieveExerciseFromDatabase(19,3);

        model.addAttribute("cwiczenia3", cwiczenia3);

        List<Cwiczenia> cwiczenia4 =  retrieveExerciseFromDatabase(19,4);

        model.addAttribute("cwiczenia4", cwiczenia4);



        model.addAttribute("cz", "Częstotliwość treningowa");
        model.addAttribute("opis", " Trening wykonujemy 4 razy w tygodniu. 2 dni treningowe, przerwa i kolejne 2 dni treningowe, od nowego tygodnia powtarzamy w taki sam sposób");



        model.addAttribute("footerText", "CORPORATE FITNESS 🔸 NUTRITIONAL ADVICE 🔸 WEIGHT LOSS 🔸 MUSCLE TONE 🔸 CORE STRENGTH 🔸 POSTURE CORRECTION 🔸 CARDIO FITNESS");

        model.addAttribute("endText", "@ 2035 by GymHelper");

        return "plan3";
    }
}
