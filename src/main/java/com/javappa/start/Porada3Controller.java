package com.javappa.start;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.Arrays;
import java.util.List;

@Controller
public class Porada3Controller {

    @GetMapping("/Porada3")
    public String porada3(Model model) {
        model.addAttribute("logo", "Gym Helper");
        model.addAttribute("logo2", "Optimal Fitness Lifestyle");

        List<String> navLinks = Arrays.asList("/", "/Plany Treningowe", "/Kalkulator Kalorii", "/Porady");
        model.addAttribute("navLinks", navLinks);

        model.addAttribute("name", "Jak zacząć z rosnąć?");

        model.addAttribute("name2", "Jaka dieta ma wpływ na wzrost wzrostu?");
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


        model.addAttribute("name3", "Dieta i odpowiednia ilość kalorii");
        model.addAttribute("text31", "Dieta na masę charakteryzuje się tym, że posiada dodatni bilans kaloryczny. Niestety wiele osób wpada ze skrajności w skrajność. Osobom trenującym zależeć powinno na wzroście wyłącznie beztłuszczowej masy ciała.");
        model.addAttribute("text32", "W tym celu należy stopniowo i powoli zwiększać dzienną podaż kalorii. Ektomorficy muszą spożywać zdecydowanie więcej pożywienia aby ich organizm rozpoczął procesy anaboliczne. Zjedzenie 4 czy 5 tysięcy kalorii dziennie może stanowić poważne wyzwanie.");

        model.addAttribute("name4", "Nie zapomnij o śnie");
        model.addAttribute("text41", "Sen jest bardzo ważny dla odpowiedniego wydzielania i funkcjonowania hormonu wzrostu.");
        model.addAttribute("text42", "Pamiętaj o tym aby spać 8 godzin dziennie.");


        model.addAttribute("footerText", "CORPORATE FITNESS 🔸 NUTRITIONAL ADVICE 🔸 WEIGHT LOSS 🔸 MUSCLE TONE 🔸 CORE STRENGTH 🔸 POSTURE CORRECTION 🔸 CARDIO FITNESS");

        model.addAttribute("endText", "@ 2035 by GymHelper");

        return "porada3";
    }
}
