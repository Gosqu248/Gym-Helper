package com.javappa.start.calorie_calculator_classes;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.javappa.start.DBFetch;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class KalkulatorKaloriiController extends DBFetch {
    private DateTimeFormatter formatterPl = DateTimeFormatter.ofPattern("EEEE", new Locale("pl"));
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private LocalDate currentDate = LocalDate.now();
    //Calendar days section
    private List<Day> circleContentsPrev = new ArrayList<>();
    private List<Day> circleContentsPost = new ArrayList<>();
    private Map<String,LocalDate> datesInCalendar = new HashMap<>();
    //Meals
    private List<Meal> meals = new ArrayList<>();
    //Controller service
    private Boolean tmp = true;
    private long chosenMealId;
    //User settings
    private int amountOfMeals = 5;
    private long userId =1;
    @GetMapping("/Kalkulator Kalorii")
    public String calculator(Model model) {
        model.addAttribute("logo", "Gym Helper");
        model.addAttribute("logo2", "Optimal Fitness Lifestyle");

        if(tmp) {
            changeDates();
            meals = retrieveMealsContent(currentDate, userId);
            tmp = false;
        }

        List<String> navLinks = new ArrayList<>();
        navLinks.add("/");
        navLinks.add("/Plany Treningowe");
        navLinks.add("/Kalkulator Kalorii");
        navLinks.add("/Porady");
        model.addAttribute("navLinks", navLinks);

        model.addAttribute("circleContentsPrev", circleContentsPrev);
        model.addAttribute("circleCurrentLabel", currentDate.format(formatterPl).substring(0, 1).toUpperCase());
        model.addAttribute("circleCurrentValue", Integer.toString(currentDate.getDayOfMonth()));
        model.addAttribute("circleContentsPost", circleContentsPost);

        //TODO overview based on meals
        ArrayList<MacroOverview> dailies = new ArrayList<>();

        dailies.add(new MacroOverview("Białko",10,100));
        dailies.add(new MacroOverview("Węgle",70,600));
        dailies.add(new MacroOverview("Tłuszcz",87,200));
        dailies.add(new MacroOverview("Kcal",87,200));

        model.addAttribute("meals", meals);
        model.addAttribute("dailies", dailies);

        model.addAttribute("footerText", "CORPORATE FITNESS 🔸 NUTRITIONAL ADVICE 🔸 WEIGHT LOSS 🔸 MUSCLE TONE 🔸 CORE STRENGTH 🔸 POSTURE CORRECTION 🔸 CARDIO FITNESS");

        model.addAttribute("endText", "@ 2035 by GymHelper");

        return "calorie_calculator";
    }
    @PostMapping("/updateCircle")
    public String updateCircle(@RequestParam String label, @RequestParam String value) {
        currentDate = datesInCalendar.get((label+value));
        changeDates();
        meals = retrieveMealsContent(currentDate, userId);
        return "redirect:/Kalkulator Kalorii";
    }
    @PostMapping("/scrollCircles")
    public String scrollCirles(@RequestParam String direction) {

        if (direction.equals("right")){
            currentDate = currentDate.plusDays(11);
        }else if(direction.equals("left")){
            currentDate = currentDate.minusDays(11);
        }
        changeDates();
        meals = retrieveMealsContent(currentDate, userId);
        return "redirect:/Kalkulator Kalorii";
    }
    @PostMapping("/removeProduct")
    private String removeProduct(@RequestParam long mealId, @RequestParam long productId){
        Iterator<Meal> iterator = meals.iterator();
        while (iterator.hasNext()) {
            Meal meal = iterator.next();
            if (meal.getId() == mealId) {
                meal.removeProductById(productId);
                removeProductFromMeal(mealId,productId);
                if(meal.isEmpty()){
                    removeEmptyMeal(mealId);
                }
                System.out.println("Usuwam produkt o ID: " + productId+" "+mealId);
                break;
            }
        }
        return "redirect:/Kalkulator Kalorii";
    }
    @PostMapping("/addProduct")
    private String addProduct (@RequestParam long mealId, Model model){
        chosenMealId = mealId;
        return "redirect:/Add product";
    }
    @PostMapping("/configureProduct")
    public String configureProduct(@RequestParam long productId,@RequestParam String name,
                                   @RequestParam int kcal, @RequestParam int carbs,
                                   @RequestParam int fats, @RequestParam int proteins,
                                   @RequestParam int weight, Model model){

        Product product = new Product(productId,name,kcal,carbs,fats,proteins,weight);
        product.adjustToWeight();
        System.out.println(product.toString());
        System.out.println(chosenMealId);

        Iterator<Meal> iterator = meals.iterator();
        while (iterator.hasNext()) {
            Meal meal = iterator.next();
            if (meal.getId() == chosenMealId) {
                if(meal.isEmpty()){
                    addNewMeal(userId, meal.getId(), currentDate, meal.getName());
                }
                meal.addNewProduct(product);
                saveProductInMealToDatabase(chosenMealId, productId, weight);
                System.out.println("Added: " + product.getName());
                break;
            }
        }
        return "redirect:/Kalkulator Kalorii";
    }

    @PostMapping("/showDetails")
    private String showDetails (@RequestParam String name){
        return "redirect:/Kalkulator Kalorii";
    }
    private void changeDates() {
        circleContentsPrev.clear();
        circleContentsPost.clear();
        datesInCalendar.clear();
        for (int i = 5; i >= 1; i--) {
            LocalDate dateBefore = currentDate.minusDays(i);
            datesInCalendar.put((dateBefore.format(formatterPl).substring(0, 1).toUpperCase() + Integer.toString(dateBefore.getDayOfMonth())), dateBefore);
            circleContentsPrev.add(new Day(dateBefore.format(formatterPl).substring(0, 1).toUpperCase(), dateBefore.getDayOfMonth()));
        }
        for (int i = 1; i <= 5; i++) {
            LocalDate dateAfter = currentDate.plusDays(i);
            datesInCalendar.put((dateAfter.format(formatterPl).substring(0, 1).toUpperCase() + Integer.toString(dateAfter.getDayOfMonth())), dateAfter);
            circleContentsPost.add(new Day(dateAfter.format(formatterPl).substring(0, 1).toUpperCase(), dateAfter.getDayOfMonth()));
        }
    }
}

