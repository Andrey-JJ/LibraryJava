package ru.project.library_web.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.project.library_web.models.Book;
import ru.project.library_web.repositories.BookRepository;

import java.util.List;

@RequestMapping("/api/books")
@RestController
public class ApiBookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/main")
    public List<Book> getBooks(){
        List<Book> books = (List<Book>) bookRepository.findAll();
        return books;
    }
}
