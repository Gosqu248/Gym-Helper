package com.javappa.start;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class Porada1Controller extends DBFetch{
    @GetMapping("/Porada1")
    public String porada1(Model model) {
        model.addAttribute("logo", "Gym Helper");
        model.addAttribute("logo2", "Optimal Fitness Lifestyle");

        List<String> navLinks = Arrays.asList("/", "/Plany Treningowe", "/Kalkulator Kalorii", "/Porady");
        model.addAttribute("navLinks", navLinks);

        List<PoradaTreningowa> porady = retrievePoradyFromDatabase();

        if (!porady.isEmpty()) {
            model.addAttribute("name", porady.get(0).getTytul());
        }

        model.addAttribute("text1", porady.get(0).getTresc());

        model.addAttribute("name2", porady.get(1).getTytul());
        model.addAttribute("text2", porady.get(1).getTresc());

        model.addAttribute("name3", porady.get(2).getTytul());
        model.addAttribute("text3", "Początki na siłowni nie należą do łatwych. Aby nie zniechęcić się do aktywności fizycznej, warto pamiętać o kilku podstawowych zasadach:");

        List<String> podsumowanieList = Arrays.asList(
                "postaw sobie cel, który jesteś w stanie zrealizować,",
                "trenuj regularnie,",
                "stopniowo zwiększaj intensywność ćwiczeń,",
                "w razie wątpliwości skorzystaj ze wsparcia trenera."
        );

        model.addAttribute("podsumowanieList", podsumowanieList);
        model.addAttribute("text4", "Dzięki temu sport stanie się dla ciebie przyjemnością, będzie bezpieczny i przyniesie oczekiwane rezultaty!");



        model.addAttribute("footerText", "CORPORATE FITNESS 🔸 NUTRITIONAL ADVICE 🔸 WEIGHT LOSS 🔸 MUSCLE TONE 🔸 CORE STRENGTH 🔸 POSTURE CORRECTION 🔸 CARDIO FITNESS");

        model.addAttribute("endText", "@ 2035 by GymHelper");

        return "porada1";
    }




}

