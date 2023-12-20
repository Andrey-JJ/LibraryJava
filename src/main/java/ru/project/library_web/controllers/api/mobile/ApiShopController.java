package ru.project.library_web.controllers.api.mobile;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import ru.project.library_web.models.mobile.*;
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

    @Autowired
    AuthorShopRepository authorShopRepository;

    @GetMapping("/authors")
    public List<AuthorShop> getAuthors(){
        List<AuthorShop> authors = (List<AuthorShop>) authorShopRepository.findAll();
        return authors;
    }

    @Autowired
    PublisherShopRepository publisherShopRepository;

    @GetMapping("/publishers")
    public List<PublisherShop> getPublishers(){
        List<PublisherShop> publishers = (List<PublisherShop>) publisherShopRepository.findAll();
        return publishers;
    }

    @Autowired
    CategoryShopRepository categoryShopRepository;

    @GetMapping("/categories")
    public List<CategoryShop> getCategories(){
        List<CategoryShop> categories = (List<CategoryShop>) categoryShopRepository.findAll();
        return categories;
    }

    @Autowired
    CopyBookShopRepository copyBookShopRepository;

    @GetMapping("/copybooks")
    public List<CopyBookShop> getCopyBooks(){
        List<CopyBookShop> copyBooks = (List<CopyBookShop>) copyBookShopRepository.findAll();
        return copyBooks;
    }

    @PutMapping("/copybooks/update/{id}")
    public HttpStatusCode editCopyBook(@PathVariable("id") Long id, @RequestBody @Valid CopyBookShop updatedCopyBook){
        Optional<CopyBookShop> copyBook = copyBookShopRepository.findById(id);
        if(copyBook.isEmpty())
            throw new EntityNotFoundException("Экземпляр книги для изменения не был найден");
        //updatedCopyBook.setId(id);
        copyBookShopRepository.save(updatedCopyBook);
        return HttpStatusCode.valueOf(200);
    }

    @GetMapping("/copybooks/delete/{id}")
    public HttpStatusCode deleteCopyBook(@PathVariable("id") Long id){
        Optional<CopyBookShop> copyBook = copyBookShopRepository.findById(id);
        if(copyBook.isEmpty())
            throw new EntityNotFoundException("Экземпляр книги не был найден для удаления");
        copyBookShopRepository.deleteById(id);
        return HttpStatusCode.valueOf(200);
    }

    @Autowired
    BookingShopRepository bookingShopRepository;

    @GetMapping("/bookings")
    public List<BookingShop> getBookings(){
        List<BookingShop> bookings = (List<BookingShop>) bookingShopRepository.findAll();
        return bookings;
    }

    @PostMapping("/bookings/add")
    public HttpStatusCode addBooking(@RequestBody @Valid BookingShop bookingShop){
        bookingShopRepository.save(bookingShop);
        return HttpStatusCode.valueOf(200);
    }

    @GetMapping("/bookings/delete/{id}")
    public HttpStatusCode deleteBooking(@PathVariable("id") Long id){
        Optional<BookingShop> booking = bookingShopRepository.findById(id);
        if(booking.isEmpty())
            throw new EntityNotFoundException("Бронирование книги не было найдено для удаления");
        bookingShopRepository.deleteById(id);
        return HttpStatusCode.valueOf(200);
    }
}
