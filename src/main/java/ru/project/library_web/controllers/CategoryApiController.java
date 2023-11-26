package ru.project.library_web.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.project.library_web.models.Category;
import ru.project.library_web.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class CategoryApiController {
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/category/{id}")
    public Category getCategory(@PathVariable("id") Long id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty())
            throw new EntityNotFoundException("Category Not Found");
        return category.get();
    }

    @GetMapping("/category")
    public List<Category> getCategories(){
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        return categories;
    }
}
