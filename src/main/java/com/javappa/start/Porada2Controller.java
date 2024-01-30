package com.javappa.start;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.Arrays;
import java.util.List;

@Controller
public class Porada2Controller extends DBFetch{

    @GetMapping("/Porada2")
    public String porada2(Model model) {
        model.addAttribute("logo", "Gym Helper");
        model.addAttribute("logo2", "Optimal Fitness Lifestyle");

        List<String> navLinks = Arrays.asList("/", "/Plany Treningowe", "/Kalkulator Kalorii", "/Porady");
        model.addAttribute("navLinks", navLinks);

        List<PoradaTreningowa> porady = retrievePoradyFromDatabase();


        model.addAttribute("name", "Jak zacząć z redukcją?");

        model.addAttribute("name2", porady.get(3).getTytul());
        model.addAttribute("text21", porady.get(3).getTresc());



        model.addAttribute("name3", porady.get(4).getTytul());
        model.addAttribute("text31", porady.get(4).getTresc());

        model.addAttribute("name4",  porady.get(5).getTytul());
        model.addAttribute("text41",  porady.get(5).getTresc());





        model.addAttribute("footerText", "CORPORATE FITNESS 🔸 NUTRITIONAL ADVICE 🔸 WEIGHT LOSS 🔸 MUSCLE TONE 🔸 CORE STRENGTH 🔸 POSTURE CORRECTION 🔸 CARDIO FITNESS");

        model.addAttribute("endText", "@ 2035 by GymHelper");

        return "porada2";
    }

}

