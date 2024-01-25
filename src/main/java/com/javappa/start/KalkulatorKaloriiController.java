package com.javappa.start;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class KalkulatorKaloriiController {
    private LocalDate currentdate;
    @GetMapping("/Kalkulator Kalorii")
    public String calculator(Model model) {
        model.addAttribute("logo", "Gym Helper");
        model.addAttribute("logo2", "Optimal Fitness Lifestyle");

        currentdate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String preparedDate = currentdate.format(dateFormatter);

        List<String> navLinks = new ArrayList<>();
        navLinks.add("/");
        navLinks.add("/Plany Treningowe");
        navLinks.add("/Kalkulator Kalorii");
        navLinks.add("/Porady");
        model.addAttribute("navLinks", navLinks);
        List<Day> circleContentsPost= Arrays.asList(
                new Day("F", 6),
                new Day("G", 7),
                new Day("H", 8),
                new Day("B", 9),
                new Day("J", 10)
        );
        List<Day> circleContentsPrev = Arrays.asList(
                new Day("N", 1),
                new Day("AB", 2),
                new Day("M", 3),
                new Day("D", 4),
                new Day("B", 5)
        );

        model.addAttribute("circleContentsPrev", circleContentsPrev);
        model.addAttribute("circleContentsPost", circleContentsPost);

        List<Meal> meals = Arrays.asList(
                new Meal("Śniadanie", Arrays.asList("Nazwa 1", "Nazwa 2", "Nazwa 3")),
                new Meal("Obiad", Arrays.asList("Nazwa A", "Nazwa B", "Nazwa C")),
                new Meal("Kolacja", Arrays.asList("Nazwa X", "Nazwa Y", "Nazwa Z"))
        );

        model.addAttribute("meals", meals);

        model.addAttribute("footerText", "CORPORATE FITNESS 🔸 NUTRITIONAL ADVICE 🔸 WEIGHT LOSS 🔸 MUSCLE TONE 🔸 CORE STRENGTH 🔸 POSTURE CORRECTION 🔸 CARDIO FITNESS");

        model.addAttribute("endText", "@ 2035 by GymHelper");

        return "calorie_calculator";
    }
    @PostMapping("/updateCircle")
    public String updateCircle(@RequestParam String label, @RequestParam String value, Model model) {

        model.addAttribute("circleCurrentLabel", label);
        model.addAttribute("circleCurrentValue", value);
        return "redirect:/Kalkulator Kalorii";
    }
}

