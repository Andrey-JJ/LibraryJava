package ru.project.library_web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.project.library_web.models.Book;
import ru.project.library_web.repositories.BookRepository;

import java.util.*;

@RequestMapping("/books")
@Controller
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/main")
    public String getBooks(Model model){
        List<Book> books = (List<Book>) bookRepository.findAll();
        model.addAttribute("books", books);
        return "book/main";
    }

    @GetMapping("/details/{id}")
    public String getBookDetails(Model model, @PathVariable("id") Long id){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isEmpty()){
            //System.out.println(String.format("Запись с id %d не была найдена", book.get().getId()));
            return "redirect:/books/main";
        }
        model.addAttribute("selectedBook", book.get());
        //System.out.println("Открыта страница детализации книги №" + book.get().getId());
        return "book/details";
    }


}
