package com.javappa.start.Calorie_calculator.Controllers;

import com.javappa.start.Other_classes.DBFetch;
import com.javappa.start.Calorie_calculator.Classes.Day;
import com.javappa.start.Calorie_calculator.Classes.MacroOverview;
import com.javappa.start.Calorie_calculator.Classes.Meal;
import com.javappa.start.Calorie_calculator.Classes.Product;
import freemarker.core.Macro;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class KalkulatorKaloriiController extends DBFetch {
    //Temporary classes till implementing User class
    //TODO User object, dont know how
    int userKcal = 2000;
    int userCarbs = 400;
    int userFats = 60;
    int userProteins = 120;
    //Date formater
    private DateTimeFormatter formatterPl = DateTimeFormatter.ofPattern("EEEE", new Locale("pl"));
    private LocalDate currentDate = LocalDate.now();
    //Calendar days section
    private List<Day> circleContentsPrev = new ArrayList<>();
    private List<Day> circleContentsPost = new ArrayList<>();
    private Map<String, LocalDate> datesInCalendar = new HashMap<>();
    //Meals
    private List<Meal> meals = new ArrayList<>();
    private List<MacroOverview> dailies = new ArrayList<>(Arrays.asList(
            new MacroOverview("Kcal", 0, userKcal),
            new MacroOverview("Węgle", 0, userCarbs),
            new MacroOverview("Tłuszcze", 0, userFats),
            new MacroOverview("Białka", 0, userProteins)));
    //Controller service
    private Boolean firstTime = true;
    private long chosenMealId;
    private List<String> navLinks = new ArrayList<>(Arrays.asList(
            "/",
            "/Plany Treningowe",
            "/Kalkulator Kalorii",
            "/Porady"
    ));
    //User settings
    //TODO implementation of user class
    //tmp amount of meals
    private int amountOfMeals = 5;
    //Tmp static id
    private long userId = 1;

    @GetMapping("/Kalkulator Kalorii")
    public String calculator(Model model) {
        if (firstTime) {
            changeDates();
            meals = retrieveMealsContent(currentDate, userId, amountOfMeals);
            calculateMacroOverview();
            firstTime = false;
        }

        model.addAttribute("logo", "Gym Helper");
        model.addAttribute("logo2", "Optimal Fitness Lifestyle");
        model.addAttribute("navLinks", navLinks);
        model.addAttribute("circleContentsPrev", circleContentsPrev);
        model.addAttribute("circleCurrentLabel", currentDate.format(formatterPl).substring(0, 1).toUpperCase());
        model.addAttribute("circleCurrentValue", Integer.toString(currentDate.getDayOfMonth()));
        model.addAttribute("circleContentsPost", circleContentsPost);
        model.addAttribute("meals", meals);
        model.addAttribute("dailies", dailies);
        model.addAttribute("footerText", "CORPORATE FITNESS 🔸 NUTRITIONAL ADVICE 🔸 WEIGHT LOSS 🔸 MUSCLE TONE 🔸 CORE STRENGTH 🔸 POSTURE CORRECTION 🔸 CARDIO FITNESS");
        model.addAttribute("endText", "@ 2035 by GymHelper");

        return "calorie_calculator";
    }

    @PostMapping("/updateCircle")
    public String updateCircle(@RequestParam String label, @RequestParam String value) {
        currentDate = datesInCalendar.get((label + value));
        changeDates();
        meals = retrieveMealsContent(currentDate, userId, amountOfMeals);
        calculateMacroOverview();
        return "redirect:/Kalkulator Kalorii";
    }

    @PostMapping("/scrollCircles")
    public String scrollCircles(@RequestParam String direction) {

        if (direction.equals("right")) {
            currentDate = currentDate.plusDays(11);
        } else if (direction.equals("left")) {
            currentDate = currentDate.minusDays(11);
        }
        changeDates();
        meals = retrieveMealsContent(currentDate, userId, amountOfMeals);
        calculateMacroOverview();
        return "redirect:/Kalkulator Kalorii";
    }

    @PostMapping("/removeProduct")
    private String removeProduct(@RequestParam long mealId, @RequestParam long productId) {
        Iterator<Meal> iterator = meals.iterator();
        while (iterator.hasNext()) {
            Meal meal = iterator.next();
            if (meal.getId() == mealId) {
                meal.removeProductById(productId);
                Product product = meal.getProductById(productId);
                removeProductFromMeal(mealId, productId);
                if (meal.isEmpty()) {
                    removeEmptyMeal(mealId);
                }
                dailies.get(0).subtractMacro(product.getKcal());
                dailies.get(1).subtractMacro(product.getCarbs());
                dailies.get(2).subtractMacro(product.getFats());
                dailies.get(3).subtractMacro(product.getProteins());
                System.out.println("Usuwam produkt o ID: " + productId + " " + mealId);
                break;
            }
        }
        return "redirect:/Kalkulator Kalorii";
    }

    @PostMapping("/addProduct")
    private String addProduct(@RequestParam long mealId, Model model) {
        chosenMealId = mealId;
        return "redirect:/Add product";
    }

    @PostMapping("/configureProduct")
    public String configureProduct(@RequestParam long productId, @RequestParam String name,
                                   @RequestParam int kcal, @RequestParam int carbs,
                                   @RequestParam int fats, @RequestParam int proteins,
                                   @RequestParam int weight, Model model) {

        Product product = new Product(productId, name, kcal, carbs, fats, proteins, weight);
        product.adjustToWeight();
        dailies.get(0).addMacro(product.getKcal());
        dailies.get(1).addMacro(product.getCarbs());
        dailies.get(2).addMacro(product.getFats());
        dailies.get(3).addMacro(product.getProteins());

        Iterator<Meal> iterator = meals.iterator();
        while (iterator.hasNext()) {
            Meal meal = iterator.next();
            if (meal.getId() == chosenMealId) {
                if (meal.isEmpty()) {
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
    private String showDetails(@RequestParam String name) {
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

    public void calculateMacroOverview() {
        dailies.get(0).setAmount(0);
        dailies.get(1).setAmount(0);
        dailies.get(2).setAmount(0);
        dailies.get(3).setAmount(0);
        Iterator<Meal> iterator = meals.iterator();
        while (iterator.hasNext()) {
            Meal meal = iterator.next();
            dailies.get(0).addMacro(meal.getKcal());
            dailies.get(1).addMacro(meal.getCarbs());
            dailies.get(2).addMacro(meal.getFats());
            dailies.get(3).addMacro(meal.getProteins());
        }
    }
}

