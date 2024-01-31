package com.javappa.start.Calorie_calculator.Controllers;

import com.javappa.start.Other_classes.DBFetch;
import com.javappa.start.Calorie_calculator.Classes.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class AddProductController extends DBFetch{
    private List<Product> products;
    private String searchPhrase = "";

    @GetMapping("/Add product")
    public String addProduct(Model model){
        model.addAttribute("logo", "Gym Helper");
        model.addAttribute("logo2", "Optimal Fitness Lifestyle");

        products = retrieveProductsFromDatabase(searchPhrase);
        List<String> navLinks = Arrays.asList("/", "/Plany Treningowe","/Kalkulator Kalorii","/Porady");
        model.addAttribute("products", products);
        model.addAttribute("navLinks", navLinks);
        model.addAttribute("footerText", "CORPORATE FITNESS 🔸 NUTRITIONAL ADVICE 🔸 WEIGHT LOSS 🔸 MUSCLE TONE 🔸 CORE STRENGTH 🔸 POSTURE CORRECTION 🔸 CARDIO FITNESS");

        model.addAttribute("endText", "@ 2035 by GymHelper");
        return "adding_product";
    }
    @PostMapping("/searchProducts")
    public String searchProducts(@RequestParam(name = "searchPhrase") String text){
        searchPhrase=text;
        return "redirect:/Add product";
    }
}
