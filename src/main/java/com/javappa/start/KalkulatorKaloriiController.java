package com.javappa.start;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class KalkulatorKaloriiController {
    @GetMapping("/Kalkulator Kalorii")
    public String calculator(Model model) {
        model.addAttribute("logo", "Gym Helper");
        model.addAttribute("logo2", "Optimal Fitness Lifestyle");

        List<String> navLinks = new ArrayList<>();
        navLinks.add("/");
        navLinks.add("/Plany Treningowe");
        navLinks.add("/Kalkulator Kalorii");
        navLinks.add("/Porady");
        model.addAttribute("navLinks", navLinks);

        return "calorie_calculator";
    }

}
