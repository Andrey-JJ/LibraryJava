package ru.project.library_web.controllers.api.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.project.library_web.models.mobile.AuthorShop;
import ru.project.library_web.models.mobile.BookShop;
import ru.project.library_web.models.mobile.CategoryShop;
import ru.project.library_web.models.mobile.PublisherShop;
import ru.project.library_web.repositories.mobile.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/shop")
@RestController
public class ApiShopController {
    @Autowired
    BookShopRepository bookShopRepository;

    @GetMapping("/books")
    public List<BookShop> getBooks(){
        List<BookShop> books = (List<BookShop>) bookShopRepository.findAll();
        return books;
    }

    @GetMapping("/books/details/{id}")
    public BookShop getBookDetails(@PathVariable("id") Long id){
        Optional<BookShop> book = bookShopRepository.findById(id);
        if (book.isEmpty())
            throw new RuntimeException("Книга не была найдена");
        return book.get();
    }

    @Autowired
    AuthorShopRepository authorShopRepository;

    @GetMapping("/authors")
    public List<AuthorShop> getAuthors(){
        List<AuthorShop> authors = (List<AuthorShop>) authorShopRepository.findAll();
        return authors;
    }

    @GetMapping("/authors/details/{id}")
    public AuthorShop getAuthorsDetails(@PathVariable("id") Long id){
        Optional<AuthorShop> author = authorShopRepository.findById(id);
        if (author.isEmpty())
            throw new RuntimeException("Автор не был найден");
        return author.get();
    }

    @Autowired
    PublisherShopRepository publisherShopRepository;

    @GetMapping("/publishers")
    public List<PublisherShop> getPublishers(){
        List<PublisherShop> publishers = (List<PublisherShop>) publisherShopRepository.findAll();
        return publishers;
    }

    @GetMapping("/publishers/details/{id}")
    public PublisherShop getPublishersDetails(@PathVariable("id") Long id){
        Optional<PublisherShop> publisher = publisherShopRepository.findById(id);
        if (publisher.isEmpty())
            throw new RuntimeException("Издатель не был найден");
        return publisher.get();
    }

    @Autowired
    CategoryShopRepository categoryShopRepository;

    @GetMapping("/categories")
    public List<CategoryShop> getCategories(){
        List<CategoryShop> categories = (List<CategoryShop>) categoryShopRepository.findAll();
        return categories;
    }

    @GetMapping("/categories/details/{id}")
    public CategoryShop getCategoriesDetails(@PathVariable("id") Long id){
        Optional<CategoryShop> category = categoryShopRepository.findById(id);
        if (category.isEmpty())
            throw new RuntimeException("Категория не была найдена");
        return category.get();
    }

    @Autowired
    CopyBookShopRepository copyBookShopRepository;

    @Autowired
    BookingShopRepository bookingShopRepository;
}
