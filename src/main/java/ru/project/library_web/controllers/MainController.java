package ru.project.library_web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.project.library_web.models.Category;
import ru.project.library_web.repositories.CategoryRepository;

import java.util.Optional;

@Controller
public class MainController {
    @GetMapping("/")
    public String showMain(Model model){
        return "main/index";
    }
}
