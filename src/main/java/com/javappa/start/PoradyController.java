package com.javappa.start;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.Arrays;
import java.util.List;

@Controller
public class PoradyController {

    @GetMapping("/Porady")
    public String porady(Model model) {
        model.addAttribute("logo", "Gym Helper");
        model.addAttribute("logo2", "Optimal Fitness Lifestyle");

        List<String> navLinks = Arrays.asList("/", "/Plany Treningowe", "/Kalkulator Kalorii", "/Porady");
        model.addAttribute("navLinks", navLinks);

        model.addAttribute("porady", "Porady");
        model.addAttribute("description", "Porady skutecznie pomogą Ci zacząć z siłownią i dzięki nim dowierz się, jaki cel chcesz osiągnąć i  w jaki sposób.");


        model.addAttribute("nav1", "/Porada1");
        model.addAttribute("nav2", "/Porada2");
        model.addAttribute("nav3", "/Porada3");

        model.addAttribute("footerText", "CORPORATE FITNESS 🔸 NUTRITIONAL ADVICE 🔸 WEIGHT LOSS 🔸 MUSCLE TONE 🔸 CORE STRENGTH 🔸 POSTURE CORRECTION 🔸 CARDIO FITNESS");

        model.addAttribute("endText", "@ 2035 by GymHelper");

        return "porady";
    }

}
