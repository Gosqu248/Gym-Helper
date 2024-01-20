package com.javappa.start;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.Arrays;
import java.util.List;

@Controller
public class Porada2Controller {

    @GetMapping("/Porada2")
    public String porada2(Model model) {
        model.addAttribute("logo", "Gym Helper");
        model.addAttribute("logo2", "Optimal Fitness Lifestyle");

        List<String> navLinks = Arrays.asList("/", "/Plany Treningowe", "/Kalkulator Kalorii", "/Porady");
        model.addAttribute("navLinks", navLinks);

        model.addAttribute("name", "Jak zacząć z redukcją?");

        model.addAttribute("name2", "Zacznij od obliczenia swojego zapotrzebowania energetycznego");
        model.addAttribute("text21", "Znajomość własnego zapotrzebowania kalorycznego, czyli BMR jest istotna, by utrzymywać podstawowe funkcje naszego organizmu.");
        model.addAttribute("text22", "- Do diety powinniśmy podchodzić przede wszystkim zdroworozsądkowo - liczba 1000 kalorii dla dorosłej, uprawiającej sport osoby to zdecydowanie zbyt mało. Konsekwencjami wynikającymi ze stosowania tak restrykcyjnego sposobu odżywiania mogą być m.in. spowolnienie metabolizmu, efekt jojo, wzmożone zmęczenie, spadek masy mięśniowej, zaburzenia hormonalne czy zaburzenia cyklu miesiączkowego");
        model.addAttribute("text23", "- tłumaczy Patrycja Zabrzeska, psychodietetyk, trener personalny, dietetyk w Rukola Catering Dietetyczny");
        model.addAttribute("text24", "- Dietę zacznijmy od poznania dziennego zapotrzebowania kalorycznego. Możemy do tego użyć specjalnego kalkulatora, w którym podamy nasz cel, wagę, wzrost, wiek oraz określimy naszą aktywność fizyczną w ciągu dnia");
        model.addAttribute("text25", "- dodaje ekspertka.");


        model.addAttribute("name3", "Postaw na zbilansowaną dietę");
        model.addAttribute("text31", "Dieta mająca na celu spalenie tłuszczu zakłada wyeliminowanie z naszego menu kalorycznych potraw, które są bogate w duże ilości cukrów prostych i niskiej jakości tłuszcze. Powinniśmy zastąpić je produktami białkowymi, wysokobłonnikowymi, owocami, warzywami oraz ziołami.");
        model.addAttribute("text32", "Jeżeli zależy nam na utracie tkanki tłuszczowej musimy pamiętać, aby posiłki, które spożywamy, miały określoną liczbę kalorii - niższą od naszego zapotrzebowania kalorycznego. Ponadto powinniśmy zachować odpowiednią proporcję węglowodanów, tłuszczu i białka.");

        model.addAttribute("name4", "Rusz się !");
        model.addAttribute("text41", "Istotną składową redukcji tkanki tłuszczowej jest dodanie, bądź zwiększenie naszej aktywności fizycznej. Jest ona niezwykle ważna, gdyż nie tylko pozwoli nam zachować mobilność i kondycję, ale też utrzymać zdrowy styl życia. Należy jednak pamiętać, że treningi powinny być wprowadzane w miarę rozsądku i być połączone z odpowiednią regeneracją organizmu, w tym ze snem.");
        model.addAttribute("text42", "Jedną z polecanych form aktywności podczas redukcji są treningi cardio, do których zaliczamy m.in. spacery, bieganie, jazdę na rowerze czy pływanie. W trakcie wykonywania tych ćwiczeń zwiększa się zapotrzebowanie na energię, którą organizm pobiera ze zgromadzonych zapasów tkanki tłuszczowej. Cardio warto uzupełnić o ćwiczenia siłowe, które wpłyną na rozbudowę masy mięśniowej.");
        model.addAttribute("text43", "- Aby nasze treningi przyniosły zamierzony efekt, powinniśmy odpowiednio dobrać czas ich trwania, częstotliwość oraz intensywność ćwiczeń dostosowaną do naszych możliwości. Niestety wiele osób na początku popełnia błąd i zapomina o niezbędnej dla naszego organizmu regeneracji");
        model.addAttribute("text44", " - komentuje ekspertka");




        model.addAttribute("footerText", "CORPORATE FITNESS 🔸 NUTRITIONAL ADVICE 🔸 WEIGHT LOSS 🔸 MUSCLE TONE 🔸 CORE STRENGTH 🔸 POSTURE CORRECTION 🔸 CARDIO FITNESS");

        model.addAttribute("endText", "@ 2035 by GymHelper");

        return "porada2";
    }

}

