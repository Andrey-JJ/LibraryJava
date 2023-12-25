package ru.project.library_web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.project.library_web.models.*;
import ru.project.library_web.repositories.BookRepository;
import ru.project.library_web.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@RequestMapping("/categories")
@Controller
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;

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
            return "redirect:/categories/main";
        }
        model.addAttribute("selectedCategory", category.get());
        return "category/details";
    }

    @GetMapping("/new")
    public String addNewCategory(Model model){
        model.addAttribute("category", new Category());
        return "category/add";
    }

    @PostMapping("/new")
    public String addNewCategory(@ModelAttribute Category category, Model model){
        categoryRepository.save(category);
        return "redirect:/categories/main";
    }

    @GetMapping("/addBook/{categoryId}")
    public String showAddBookPage(Model model, @PathVariable Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        List<Book> booksWithoutCategory = bookRepository.findByCategoryIsNull();

        if (category.isEmpty()) {
            return "redirect:/categoreis/main";
        }

        model.addAttribute("selectedCategory", category.get());
        model.addAttribute("allBooks", booksWithoutCategory);

        return "category/addBook";
    }

    @PostMapping("/addBook/{categoryId}")
    public String addBooksToPublisher(@PathVariable Long categoryId, @RequestParam("bookIds") List<Long> bookIds) {
        Optional<Category> category = categoryRepository.findById(categoryId);

        if (category.isPresent()) {
            Category selectedCategory = category.get();
            List<Book> selectedBooks = (List<Book>) bookRepository.findAllById(bookIds);

            selectedCategory.getBooks().addAll(selectedBooks);
            categoryRepository.save(selectedCategory);
        }

        return "redirect:/categoreis/details/" + categoryId;
    }

    @GetMapping("/update/{id}")
    public String editCategory(Model model, @PathVariable("id") Long id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            return "redirect:/categories/main";
        }
        model.addAttribute("selectedCategory", category.get());
        return "category/edit";
    }

    @PostMapping("/update")
    public String editCategory(@ModelAttribute Category category, Model model){
        categoryRepository.save(category);
        return "redirect:/categories/main";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(Model model, @PathVariable("id") Long id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            return "redirect:/categories/main";
        }
        model.addAttribute("selectedCategory", category.get());
        return "category/delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            categoryRepository.deleteById(id);
        }
        return "redirect:/categories/main";
    }
}
