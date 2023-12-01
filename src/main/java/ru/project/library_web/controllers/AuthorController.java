package ru.project.library_web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.project.library_web.models.Author;
import ru.project.library_web.repositories.AuthorRepository;

import java.util.List;
import java.util.Optional;

@RequestMapping("/authors")
@Controller
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/main")
    public String getAuthors(Model model){
        List<Author> authors = (List<Author>) authorRepository.findAll();
        model.addAttribute("authors", authors);
        return "author/main";
    }

    @GetMapping("/details/{id}")
    public String getAuthorDetails(Model model, @PathVariable("id") Long id){
        Optional<Author> author = authorRepository.findById(id);
        if(author.isEmpty())
            return "redirect:/authors/main";
        model.addAttribute("selectedAuthor", author);
        return "author/details";
    }
}
