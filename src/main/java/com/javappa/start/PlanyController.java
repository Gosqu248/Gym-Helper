package com.javappa.start;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class PlanyController extends DBFetch {


    @GetMapping("/Plany Treningowe")
    public String plany(Model model) {
        model.addAttribute("logo", "Gym Helper");
        model.addAttribute("logo2", "Optimal Fitness Lifestyle");

        List<String> navLinks = Arrays.asList("/", "/Plany Treningowe", "/Kalkulator Kalorii", "/Porady");
        model.addAttribute("navLinks", navLinks);

        model.addAttribute("plan", "Plany treningowe");
        model.addAttribute("description", "Wybierz swój idealny plan treningowy");


        model.addAttribute("nav1", "/Plan1");
        model.addAttribute("nav2", "/Plan2");
        model.addAttribute("nav3", "/Plan3");

        List<Plan> plany = retrievePlanFromDatabase();



        model.addAttribute("plan1", plany.get(65).getName() + "  (" + plany.get(65).getLvl() + ")");
        model.addAttribute("plan2", plany.get(8).getName() + "  (" + plany.get(8).getLvl() + ")");
        model.addAttribute("plan3", plany.get(17).getName() + "  (" + plany.get(17).getLvl() + ")");


        model.addAttribute("footerText", "CORPORATE FITNESS 🔸 NUTRITIONAL ADVICE 🔸 WEIGHT LOSS 🔸 MUSCLE TONE 🔸 CORE STRENGTH 🔸 POSTURE CORRECTION 🔸 CARDIO FITNESS");

        model.addAttribute("endText", "@ 2035 by GymHelper");

        return "plany";
    }
}
