package ru.project.library_web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.project.library_web.models.*;
import ru.project.library_web.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@RequestMapping("/categories")
@Controller
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/main")
    public String getCategories(Model model){
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "category/main";
    }

    @GetMapping("/details/{id}")
    public String getCategoryDetails(Model model, @PathVariable("id") Long id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            //System.out.println(String.format("Запись с id %d не была найдена", category.get().getId()));
            return "redirect:/categories/main";
        }
        model.addAttribute("selectedCategory", category.get());
        //System.out.println("Открыта страница детализации категории №" + category.get().getId());
        return "category/details";
    }
}
