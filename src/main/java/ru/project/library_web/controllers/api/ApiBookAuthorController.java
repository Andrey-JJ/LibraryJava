package ru.project.library_web.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.project.library_web.models.BookAuthor;
import ru.project.library_web.repositories.BookAuthorRepository;

import java.util.List;

@RequestMapping("/api/books_authors")
@RestController
public class ApiBookAuthorController {
    @Autowired
    BookAuthorRepository bookAuthorRepository;

    @GetMapping("/")
    public List<BookAuthor> getAll(){
        List<BookAuthor> list = (List<BookAuthor>) bookAuthorRepository.findAll();
        return list;
    }
}
