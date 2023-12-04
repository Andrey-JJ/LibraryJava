package ru.project.library_web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.project.library_web.models.Author;
import ru.project.library_web.models.Book;
import ru.project.library_web.models.Category;
import ru.project.library_web.models.Publisher;
import ru.project.library_web.repositories.AuthorRepository;
import ru.project.library_web.repositories.BookRepository;
import ru.project.library_web.repositories.CategoryRepository;
import ru.project.library_web.repositories.PublisherRepository;

import java.util.*;

@RequestMapping("/books")
@Controller
public class BookController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PublisherRepository publisherRepository;

    @GetMapping("/main")
    public String getBooks(Model model){
        List<Book> books = (List<Book>) bookRepository.findAll();
        //Для фильтрации
        List<Author> authors = (List<Author>) authorRepository.findAll();
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        List<Publisher> publishers = (List<Publisher>) publisherRepository.findAll();
        //Вывод
        model.addAttribute("categories", categories);
        model.addAttribute("authors", authors);
        model.addAttribute("publishers", publishers);
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

    // Ваш текущий код контроллера

    @GetMapping("/filter")
    public String filterBooks(
            @RequestParam(name = "author", required = false) Long authorId,
            @RequestParam(name = "publisher", required = false) Long publisherId,
            @RequestParam(name = "category", required = false) Long categoryId,
            Model model
    ) {
        List<Book> filteredBooks;

        // Получаем список всех книг
        List<Book> allBooks = (List<Book>) bookRepository.findAll();

        // Если выбран автор, фильтруем по автору
        if (authorId != null) {
            Optional<Author> author = authorRepository.findById(authorId);
            if (author.isPresent()) {
                filteredBooks = bookRepository.findByAuthors(author.get());
            } else {
                // Обработка, если автор не найден
                return "redirect:/books/main";
            }
        } else {
            // Если автор не выбран, используем все книги
            filteredBooks = allBooks;
        }

        // Если выбран издатель, фильтруем по издателю
        if (publisherId != null) {
            Optional<Publisher> publisher = publisherRepository.findById(publisherId);
            if (publisher.isPresent()) {
                filteredBooks = bookRepository.findByPublisher(publisher.get());
            } else {
                // Обработка, если издатель не найден
                return "redirect:/books/main";
            }
        }

        // Если выбрана категория, фильтруем по категории
        if (categoryId != null) {
            Optional<Category> category = categoryRepository.findById(categoryId);
            if (category.isPresent()) {
                filteredBooks = bookRepository.findByCategory(category.get());
            } else {
                // Обработка, если категория не найдена
                return "redirect:/books/main";
            }
        }

        // Сохранение выбранных параметров в модели
        model.addAttribute("selectedAuthor", authorId);
        model.addAttribute("selectedPublisher", publisherId);
        model.addAttribute("selectedCategory", categoryId);

        // Выводим отфильтрованные книги
        model.addAttribute("books", filteredBooks);
        return "book/main";
    }


}
