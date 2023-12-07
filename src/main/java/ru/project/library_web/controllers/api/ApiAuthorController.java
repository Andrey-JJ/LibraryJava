package ru.project.library_web.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.project.library_web.models.Author;
import ru.project.library_web.repositories.AuthorRepository;

import java.util.List;

@RequestMapping("/api/authors")
@RestController
public class ApiAuthorController {
    @Autowired
    AuthorRepository authorRepository;

    @GetMapping("/")
    public List<Author> getAuthors(){
        List<Author> authors = (List<Author>) authorRepository.findAll();
        return authors;
    }
}
