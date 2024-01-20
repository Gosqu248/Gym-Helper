package com.javappa.start;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.Arrays;
import java.util.List;

@Controller
public class Porada1Controller {

    @GetMapping("/Porada1")
    public String porada1(Model model) {
        model.addAttribute("logo", "Gym Helper");
        model.addAttribute("logo2", "Optimal Fitness Lifestyle");

        List<String> navLinks = Arrays.asList("/", "/Plany Treningowe", "/Kalkulator Kalorii", "/Porady");
        model.addAttribute("navLinks", navLinks);

        model.addAttribute("name", "Jak zacząć z siłownią?");
        model.addAttribute("text1", "Zanim wybierzesz się na siłownię, zastanów się, co dokładnie chcesz osiągnąć dzięki treningom. Czy szukasz sposobu, by poprawić swoje samopoczucie i ogólny stan zdrowia? Zależy Ci na lepszej kondycji? A może masz konkretne plany wobec swojej sylwetki? Ważne, by cel, który sobie postawisz, był realny – dostosowany do Twoich możliwości. Jeśli założysz, że chcesz schudnąć kilkanaście kilogramów lub przygotować się do maratonu w ciągu miesiąca, możesz mocno się rozczarować.");

        model.addAttribute("name2", "Trening na siłowni dla początkujących – o czym jeszcze pamiętać?");
        model.addAttribute("text2", "Przed wizytą na siłowni zadbaj o odpowiednie akcesoria. Najważniejsze są komfortowe, oddychające ubrania sportowe, w których bez problemu wykonasz różne ćwiczenia. Obuwie powinno być stabilne, przewiewne i przede wszystkim wygodne – dzięki temu łatwiej będzie Ci zachować równowagę, unikniesz odcisków, obtarć, a Twoje stopy nie będą nadmiernie się pocić. Pamiętaj też, by zabrać ze sobą ręcznik i wodę.\n");

        model.addAttribute("name3", "Podsumowanie");
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

