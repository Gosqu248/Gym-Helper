package com.javappa.start;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.Arrays;
import java.util.List;

@Controller
public class Porada3Controller extends DBFetch{

    @GetMapping("/Porada3")
    public String porada3(Model model) {
        model.addAttribute("logo", "Gym Helper");
        model.addAttribute("logo2", "Optimal Fitness Lifestyle");

        List<String> navLinks = Arrays.asList("/", "/Plany Treningowe", "/Kalkulator Kalorii", "/Porady");
        model.addAttribute("navLinks", navLinks);

        model.addAttribute("name", "Jak zacząć z rosnąć?");

        List<PoradaTreningowa> porady = retrievePoradyFromDatabase();

        model.addAttribute("name2",  porady.get(6).getTytul());
        model.addAttribute("text21", "To oczywiste, że nie jesteśmy tym, co jemy. Jesteśmy tym, co możemy wchłonąć, ale też tym, czego nie potrafimy zutylizować. Trzeba jednak przyznać, że zdrowa dieta i odpowiednie odżywienie, to kamień węgielny wszystkiego, co dobre w naszych ciałach. Jeśli chcesz zwiększyć swój wzrost, no dobra, powiem to, a więc jeśli chcesz urosnąć, potrzebujesz zaplanować dietę, która może pobudzać wzrost hormonów w organizmie i uzupełniać utracone komórki.");
        model.addAttribute("text22", "Zadbaj szczególnie o podaż witamin z grupy B, witaminy D oczywiście w protokole z K2, fosforu, cynku, oraz wapnia. Nie zapomnij też o nawadnianiu! ");
        model.addAttribute("text23", "Witamin D3+K2");
        model.addAttribute("text24", "Mariaż witamin D3+K2 pomaga kościom absorbować wapń i dobrze wykorzystać magnez. Przyczynia się to do wzmocnienia kości i wspomaga ich rozwój. Nie tylko przyjmij wysokiej jakości witaminy, ale także i przede wszystkim absorbuj witaminę D ze słońca.");
        model.addAttribute("text25", "Wapń");
        model.addAttribute("text26", "Kości potrzebują wapnia aby rozwijać się i rosnąć.");
        model.addAttribute("text27", "Bogate w wapń są na przykład:");
        List<String> wapnList = Arrays.asList(
                "nasiona chia",
                "sezam",
                "migdały",
                "biała fasola",
                "rabarbar",
                "szpinak",
                "jarmuż",
                "komosa"
        );
        model.addAttribute("wapn", wapnList);
        model.addAttribute("text28", "Cynk");
        model.addAttribute("text29", "Warto przy okazji zwiększyć konsumpcję cynku jedząc szparagi, groszek, arbuzy, dynie, cukinie, surową gorzką czekoladę, a także jajka i ostrygi.");


        model.addAttribute("name3", porady.get(7).getTytul());
        model.addAttribute("text31", porady.get(7).getTresc());

        model.addAttribute("name4", porady.get(8).getTytul());
        model.addAttribute("text41", porady.get(8).getTresc());


        model.addAttribute("footerText", "CORPORATE FITNESS 🔸 NUTRITIONAL ADVICE 🔸 WEIGHT LOSS 🔸 MUSCLE TONE 🔸 CORE STRENGTH 🔸 POSTURE CORRECTION 🔸 CARDIO FITNESS");

        model.addAttribute("endText", "@ 2035 by GymHelper");

        return "porada3";
    }
}
