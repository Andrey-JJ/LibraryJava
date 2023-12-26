package ru.project.library_web.controllers;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.project.library_web.models.*;
import ru.project.library_web.repositories.BookRepository;
import ru.project.library_web.repositories.BookingRepository;
import ru.project.library_web.repositories.ReaderRepository;

import java.util.List;
import java.util.Optional;

@RequestMapping("/bookings")
@Controller
public class BookingController {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ReaderRepository readerRepository;

    @GetMapping("/main")
    public String getBookings(Model model){
        List<Booking> bookings = (List<Booking>) bookingRepository.findAll();
        model.addAttribute("bookings", bookings);
        return "booking/main";
    }

    @GetMapping("/new")
    public String addNewBooking(Model model){
        //Для выбора
        List<Book> books = (List<Book>) bookRepository.findAll();
        List<Reader> readers = (List<Reader>) readerRepository.findAll();
        model.addAttribute("books", books);
        model.addAttribute("readers", readers);
        return "booking/add";
    }

    @PostMapping("/new")
    public String addNewBooking(@RequestParam("selectedBook") Long selectedBookId,
                                @RequestParam("selectedReader") Long selectedReaderId,
                                Model model){
        //Для выбора
        Optional<Book> book = bookRepository.findById(selectedBookId);
        Optional<Reader> reader = readerRepository.findById(selectedReaderId);

        Booking booking = new Booking();
        booking.setBook(book.get());
        booking.setReader(reader.get());
        bookingRepository.save(booking);

        return "redirect:/bookings/main";
    }

    @Transactional
    @GetMapping("/delete/{id}")
    public String deleteBooking(@PathVariable("id") Long id, Model model){
        Optional<Booking> booking = bookingRepository.findById(id);
        if(booking.isPresent()){
            bookingRepository.deleteById(id);
        }
        return "redirect:/bookings/main";
    }
}
