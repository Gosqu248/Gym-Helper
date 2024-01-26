package com.javappa.start;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class KalkulatorKaloriiController {
    private DateTimeFormatter formatterPl = DateTimeFormatter.ofPattern("EEEE", new Locale("pl"));
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private LocalDate currentDate = LocalDate.now();
    //Calendar days section
    private List<Day> circleContentsPrev = new ArrayList<>();
    private List<Day> circleContentsPost = new ArrayList<>();
    private Map<String,LocalDate> datesInCalendar = new HashMap<>();
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
        changeDates();

        model.addAttribute("circleContentsPrev", circleContentsPrev);
        model.addAttribute("circleCurrentLabel", currentDate.format(formatterPl).substring(0, 1).toUpperCase());
        model.addAttribute("circleCurrentValue", Integer.toString(currentDate.getDayOfMonth()));
        model.addAttribute("circleContentsPost", circleContentsPost);

        List<Meal> meals = Arrays.asList(
                new Meal("Śniadanie", Arrays.asList("Nazwa 1", "Nazwa 2", "Nazwa 3")),
                new Meal("Obiad", Arrays.asList("Nazwa A", "Nazwa B", "Nazwa C")),
                new Meal("Podwieczorek", Arrays.asList("Nazwa X", "Nazwa Y", "Nazwa Z")),
                new Meal("Kolacja", Arrays.asList("Nazwa X", "Nazwa Y", "Nazwa Z"))
        );

        model.addAttribute("meals", meals);

        model.addAttribute("footerText", "CORPORATE FITNESS 🔸 NUTRITIONAL ADVICE 🔸 WEIGHT LOSS 🔸 MUSCLE TONE 🔸 CORE STRENGTH 🔸 POSTURE CORRECTION 🔸 CARDIO FITNESS");

        model.addAttribute("endText", "@ 2035 by GymHelper");

        return "calorie_calculator";
    }
    @PostMapping("/updateCircle")
    public String updateCircle(@RequestParam String label, @RequestParam String value) {
        currentDate = datesInCalendar.get((label+value));
        return "redirect:/Kalkulator Kalorii";
    }
    @PostMapping("/scrollCircles")
    public String scrollCirles(@RequestParam String direction) {

        if (direction.equals("right")){
            currentDate = currentDate.plusDays(11);
        }else if(direction.equals("left")){
            currentDate = currentDate.minusDays(11);
        }
        return "redirect:/Kalkulator Kalorii";
    }
    private void changeDates(){
        circleContentsPrev.clear();
        circleContentsPost.clear();
        datesInCalendar.clear();
        for (int i = 5; i >= 1; i--) {
            LocalDate dateBefore = currentDate.minusDays(i);
            datesInCalendar.put((dateBefore.format(formatterPl).substring(0, 1).toUpperCase() + Integer.toString(dateBefore.getDayOfMonth())), dateBefore);
            circleContentsPrev.add(new Day(dateBefore.format(formatterPl).substring(0, 1).toUpperCase(),  dateBefore.getDayOfMonth()));
        }
        for (int i = 1; i <= 5; i++) {
            LocalDate dateAfter = currentDate.plusDays(i);
            datesInCalendar.put((dateAfter.format(formatterPl).substring(0, 1).toUpperCase() + Integer.toString(dateAfter.getDayOfMonth())), dateAfter);
            circleContentsPost.add(new Day(dateAfter.format(formatterPl).substring(0, 1).toUpperCase(), dateAfter.getDayOfMonth()));
        }
    }
}

