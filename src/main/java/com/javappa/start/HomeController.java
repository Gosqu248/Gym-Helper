package com.javappa.start;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping
    public String hello(Model model) {
        model.addAttribute("logo", "Gym Helper");
        model.addAttribute("logo2", "Optimal Fitness Lifestyle");

        List<String> navLinks = Arrays.asList("/", "/Plany Treningowe","/Kalkulator Kalorii","/Porady");
        model.addAttribute("navLinks", navLinks);
        return "hello";
    }
}
