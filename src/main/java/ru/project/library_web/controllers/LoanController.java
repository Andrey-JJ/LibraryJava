package ru.project.library_web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.project.library_web.models.*;
import ru.project.library_web.repositories.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequestMapping("/loans")
@Controller
public class LoanController {
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CopyBookRepository copyBookRepository;
    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/main")
    public String getLoans(Model model){
        List<Loan> loans = (List<Loan>) loanRepository.findAll();
        model.addAttribute("loans", loans);
        return "loan/main";
    }

    @GetMapping("/new")
    public String addNewLoan(Model model){
        List<Book> books = (List<Book>) bookRepository.findAll();
        List<Reader> readers = (List<Reader>) readerRepository.findAll();

        model.addAttribute("books", books);
        model.addAttribute("readers", readers);

        return "loan/add";
    }

    @PostMapping("/new")
    public String addNewLoan(@RequestParam("selectedBook") Long selectedBookId,
                             @RequestParam("selectedReader") Long selectedReaderId,
                             @RequestParam("loanDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date loanDate,
                             @RequestParam(name = "returnDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date returnDate,
                             Model model) {
        // Логика поиска первого свободного экземпляра книги (copybook) по id книги
        Optional<Book> optionalBook = bookRepository.findById(selectedBookId);
        Optional<Status> status = statusRepository.findById(1L);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            List<CopyBook> availableCopies = copyBookRepository.findByBookIdAndStatusId(selectedBookId, status.get().getId());

            if (!availableCopies.isEmpty()) {
                // Найден доступный экземпляр, создаем объект Loan
                CopyBook selectedCopyBook = availableCopies.get(0);

                Loan loan = new Loan();
                loan.setCopyBook(selectedCopyBook);

                Optional<Reader> optionalReader = readerRepository.findById(selectedReaderId);
                optionalReader.ifPresent(loan::setReader);

                loan.setLoan_date(loanDate);
                loan.setReturn_date(returnDate);

                loanRepository.save(loan);

                // Вернуться к странице с бронированиями после успешного добавления
                return "redirect:/loans/main";
            } else {
                // Нет доступных экземпляров
                model.addAttribute("error", "Нет доступных экземпляров выбранной книги");
            }
        } else {
            // Книга не найдена
            model.addAttribute("error", "Книга не найдена");
        }

        // Вернуться на страницу добавления выдачи с сообщением об ошибке
        return "redirect:/loans/add";
    }

    @GetMapping("/delete/{id}")
    public String deleteLoan(@PathVariable("id") Long id){
        Optional<Loan> loan = loanRepository.findById(id);
        if (loan.isPresent()){
            loanRepository.deleteById(id);
        }
        return "redirect:/loans/main";
    }

    @GetMapping("/getreaders")
    public String getReaders(Model model){
        List<Reader> readers = (List<Reader>) readerRepository.findAll();
        model.addAttribute("readers", readers);
        return "loan/from_readers";
    }

    @GetMapping("/getbookings/{id}")
    public String getBookingsFromReader(@PathVariable("id") Long selectedReader, Model model){
        List<Booking> bookings = (List<Booking>) bookingRepository.findByReaderId(selectedReader);
        model.addAttribute("bookings", bookings);
        return "loan/from_bookings";
    }

    @GetMapping("/confirm/{id}")
    public String confirmBooking(@PathVariable("id") Long selectedBookingId, Model model) {
        Optional<Booking> bookingOptional = bookingRepository.findById(selectedBookingId);

        if (bookingOptional.isPresent()) {
            Booking selectedBooking = bookingOptional.get();
            Reader reader = selectedBooking.getReader();
            Book book = selectedBooking.getBook();

            model.addAttribute("reader", reader);
            model.addAttribute("book", book);

            return "loan/confirm";
        } else {
            // Handle the case where the booking is not found
            return "redirect:/error";  // You can replace "/error" with an appropriate error page or redirect
        }
    }

    @PostMapping("/confirm")
    public String confirmLoan(@RequestParam("readerId") Long readerId,
                              @RequestParam("bookId") Long bookId,
                              @RequestParam("loanDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date loanDate,
                              @RequestParam(name = "returnDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date returnDate,
                              Model model) {
        Optional<Status> status = statusRepository.findById(1L);
        List<CopyBook> availableCopies = copyBookRepository.findByBookIdAndStatusId(bookId, status.get().getId());
        if (!availableCopies.isEmpty()) {
            // Найден доступный экземпляр, создаем объект Loan
            CopyBook selectedCopyBook = availableCopies.get(0);

            Loan loan = new Loan();
            loan.setCopyBook(selectedCopyBook);

            Optional<Book> book = bookRepository.findById(bookId);
            Optional<Reader> optionalReader = readerRepository.findById(readerId);
            optionalReader.ifPresent(loan::setReader);

            loan.setLoan_date(loanDate);
            loan.setReturn_date(returnDate);

            if(isBookAlreadyReservedByUser(bookId, readerId)){
                Booking booking = new Booking();
                booking.setReader(optionalReader.get());
                booking.setBook(book.get());
                bookingRepository.delete(booking);
            }

            loanRepository.save(loan);

            // Вернуться к странице с бронированиями после успешного добавления
            return "redirect:/loans/main";
        }
        else {
            // Нет доступных экземпляров
            model.addAttribute("error", "Нет доступных экземпляров выбранной книги");
            return "redirect:/loans/main";
        }
    }

    private boolean isBookAlreadyReservedByUser(Long userId, Long bookId) {
        Booking existingBooking = bookingRepository.findByReaderIdAndBookId(userId, bookId);
        return existingBooking != null;
    }
}
