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

        // Dodaj logikę generowania kalendarza
        LocalDate currentDate = LocalDate.now();
        List<LocalDate> calendarDays = generateCalendarDays(currentDate);
        model.addAttribute("currentDate", currentDate);
        model.addAttribute("calendarDays", calendarDays);

        return "calorie_calculator";
    }

    private List<LocalDate> generateCalendarDays(LocalDate currentDate) {
        List<LocalDate> calendarDays = new ArrayList<>();
        int daysBeforeAndAfter = 5;

        // Dodaj dni przed bieżącą datą
        for (int i = daysBeforeAndAfter; i > 0; i--) {
            calendarDays.add(currentDate.minusDays(i));
        }

        // Dodaj bieżącą datę
        calendarDays.add(currentDate);

        // Dodaj dni po bieżącej dacie
        for (int i = 1; i <= daysBeforeAndAfter; i++) {
            calendarDays.add(currentDate.plusDays(i));
        }

        return calendarDays;
    }
}
