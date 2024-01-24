package com.javappa.start;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class KalkulatorKaloriiController {
    private List<Day> circleContentsPrev = List.of(
            new Day("N", 1),
            new Day("B", 2),
            new Day("M", 3),
            new Day("D", 4),
            new Day("B", 5),
            new Day("F", 6),
            new Day("G", 7),
            new Day("H", 8),
            new Day("b", 9),
            new Day("J", 10)
    );

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

        model.addAttribute("circleContents", circleContentsPrev);

        model.addAttribute("footerText", "CORPORATE FITNESS 🔸 NUTRITIONAL ADVICE 🔸 WEIGHT LOSS 🔸 MUSCLE TONE 🔸 CORE STRENGTH 🔸 POSTURE CORRECTION 🔸 CARDIO FITNESS");

        model.addAttribute("endText", "@ 2035 by GymHelper");

        return "calorie_calculator";
    }
    @PostMapping("/updateCircle")
    @ResponseBody
    public String updateCircle(@RequestBody Map<String, String> circleData, Model model) {
        String currentCircle = circleData.get("label") + "\n" + circleData.get("value");
        return currentCircle;
    }
}

