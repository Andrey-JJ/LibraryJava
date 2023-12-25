package ru.project.library_web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.project.library_web.models.*;
import ru.project.library_web.repositories.*;

import java.io.IOException;
import java.nio.file.*;
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
    @Autowired
    private BookAuthorRepository bookAuthorRepository;

    @GetMapping("/main")
    public String getBooks(Model model){
        List<Book> books = (List<Book>) bookRepository.findAll();
        //Для фильтрации
        /*List<Author> authors = (List<Author>) authorRepository.findAll();
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        List<Publisher> publishers = (List<Publisher>) publisherRepository.findAll();*/
        //Вывод
        /*model.addAttribute("categories", categories);
        model.addAttribute("authors", authors);
        model.addAttribute("publishers", publishers);*/
        model.addAttribute("books", books);
        return "book/main";
    }

    @GetMapping("/details/{id}")
    public String getBookDetails(Model model, @PathVariable("id") Long id){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isEmpty()){
            return "redirect:/books/main";
        }
        model.addAttribute("selectedBook", book.get());
        return "book/details";
    }

    @GetMapping("/new")
    public String addNewBook(Model model){
        //Для выбора
        List<Author> authors = (List<Author>) authorRepository.findAll();
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        List<Publisher> publishers = (List<Publisher>) publisherRepository.findAll();

        model.addAttribute("book", new Book());
        model.addAttribute("categories", categories);
        model.addAttribute("authors", authors);
        model.addAttribute("publishers", publishers);
        return "book/add";
    }

    @PostMapping("/new")
    public String addNewBook(@ModelAttribute Book book,
                             @RequestParam("imageFile") MultipartFile imageFile,
                             @RequestParam(name = "selectedAuthors") List<Long> authorIds,
                             Model model){
        // Обработка файла изображения
        if (!imageFile.isEmpty()) {
            try {
                // Сохранение файла на сервере, например, в папку resources\static\images
                String imagePath = imageFile.getOriginalFilename();
                Path uploadPath = Paths.get("src/main/resources/static/images/");
                Files.copy(imageFile.getInputStream(), uploadPath.resolve(imageFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

                // Установка пути изображения в модель книги
                book.setImage(imagePath);
            } catch (IOException e) {
                e.printStackTrace(); // Обработка ошибок ввода-вывода
            }
        }

        if (authorIds != null) {
            List<Author> authors = (List<Author>) authorRepository.findAllById(authorIds);

            // Добавление связи в дополнительную таблицу bookauthor
            for (var author : authors) {
                book.getBookAuthors().add(new BookAuthor(book, author));
            }

            bookRepository.save(book);
            bookAuthorRepository.saveAll(book.getBookAuthors());
        }

        return "redirect:/books/main";
    }

    @GetMapping("/update/{id}")
    public String updateBook(@PathVariable("id") Long id, Model model){
        Optional<Book> book = bookRepository.findById(id);

        if (book.isEmpty()) {
            return "redirect:/books/main";
        }

        // Получение необходимых данных для выпадающих списков
        List<Author> authors = (List<Author>) authorRepository.findAll();
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        List<Publisher> publishers = (List<Publisher>) publisherRepository.findAll();

        model.addAttribute("selectedBook", book.get());
        model.addAttribute("categories", categories);
        model.addAttribute("authors", authors);
        model.addAttribute("publishers", publishers);

        return "book/edit";
    }

    @PostMapping("/update/{id}")
    public String editBook(@PathVariable Long id,
                           @ModelAttribute Book book,
                           @RequestParam("imageFile") MultipartFile imageFile,
                           @RequestParam(name = "selectedAuthors") List<Long> authorIds,
                           Model model) {
        // Обработка файла изображения
        if (!imageFile.isEmpty()) {
            try {
                // Сохранение файла на сервере, например, в папку resources\static\images
                String imagePath = imageFile.getOriginalFilename();
                Path uploadPath = Paths.get("src/main/resources/static/images/");
                Files.copy(imageFile.getInputStream(), uploadPath.resolve(imageFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

                // Установка пути изображения в модель книги
                book.setImage(imagePath);
            } catch (IOException e) {
                e.printStackTrace(); // Обработка ошибок ввода-вывода
            }
        }

        if (authorIds != null) {
            List<Author> authors = (List<Author>) authorRepository.findAllById(authorIds);
            List<BookAuthor> old = (List<BookAuthor>) bookAuthorRepository.findByBookId(book.getId());
            // Удаление существующих связей с авторами

            // Добавление новых связей в дополнительную таблицу bookauthor
            for (var author : authors) {
                book.getBookAuthors().add(new BookAuthor(book, author));
            }
        }

        // Обновление книги
        bookRepository.save(book);

        return "redirect:/books/details/" + id; // Перенаправление на страницу деталей книги
    }
}
