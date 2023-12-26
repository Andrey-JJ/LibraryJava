package ru.project.library_web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.project.library_web.models.Author;
import ru.project.library_web.models.Reader;
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
        model.addAttribute("selectedAuthor", author.get());
        return "author/details";
    }

    @GetMapping("/new")
    public String showAddReaderForm(Model model) {
        model.addAttribute("author", new Author());
        return "author/add";
    }

    @PostMapping("/new")
    public String addReader(@ModelAttribute Author author, Model model) {
        authorRepository.save(author);
        return "redirect:/authors/main";
    }

    @GetMapping("/update/{id}")
    public String editReader(Model model, @PathVariable("id") Long id){
        Optional<Author> author = authorRepository.findById(id);
        if(author.isEmpty()){
            return "redirect:/authors/main";
        }
        model.addAttribute("selectedAuthor", author.get());
        return "author/edit";
    }

    @PostMapping("/update")
    public String editReader(@ModelAttribute Author author, Model model){
        authorRepository.save(author);
        return "redirect:/authors/mains";
    }

    @GetMapping("/delete/{id}")
    public String deleteReader(Model model, @PathVariable("id") Long id){
        Optional<Author> author = authorRepository.findById(id);
        if(author.isEmpty()){
            return "redirect:/authors/main";
        }
        model.addAttribute("selectedAuthor", author.get());
        return "author/delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteReader(@PathVariable("id") Long id){
        Optional<Author> author = authorRepository.findById(id);
        if(author.isPresent()){
            authorRepository.deleteById(id);
        }
        return "redirect:/authors/main";
    }
}
