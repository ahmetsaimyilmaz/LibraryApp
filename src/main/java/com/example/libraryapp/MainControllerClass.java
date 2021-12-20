package com.example.libraryapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainControllerClass {
    @GetMapping
    public String showMainMenu() {
        return "index";
    }
}
