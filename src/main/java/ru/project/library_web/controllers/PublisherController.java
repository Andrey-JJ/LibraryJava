package ru.project.library_web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.project.library_web.models.Book;
import ru.project.library_web.models.Publisher;
import ru.project.library_web.repositories.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/publishers")
@Controller
public class PublisherController {
    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/main")
    public String getPublishers(Model model){
        List<Publisher> publishers = (List<Publisher>) publisherRepository.findAll();
        model.addAttribute("publishers", publishers);
        return "publisher/main";
    }

    @GetMapping("/details/{id}")
    public String getPublisherDetails(Model model, @PathVariable("id") Long id){
        Optional<Publisher> publisher = publisherRepository.findById(id);
        if(publisher.isEmpty()){
            return "redirect:/publishers/main";
        }
        model.addAttribute("selectedPublisher", publisher.get());
        return "publisher/details";
    }

    @GetMapping("/new")
    public String addNewPublisher(Model model){
        model.addAttribute("publisher", new Publisher());
        return "publisher/add";
    }

    @PostMapping("/new")
    public String addNewPublisher(@ModelAttribute Publisher publisher, Model model){
        publisherRepository.save(publisher);
        return "redirect:/publishers/main";
    }

    @GetMapping("/addBook/{publisherId}")
    public String showAddBookPage(Model model, @PathVariable Long publisherId) {
        Optional<Publisher> publisher = publisherRepository.findById(publisherId);
        List<Book> booksWithoutPublisher = bookRepository.findByPublisherIsNull(); // Замените на ваш репозиторий для книг

        if (publisher.isEmpty()) {
            return "redirect:/publishers/main";
        }

        model.addAttribute("selectedPublisher", publisher.get());
        model.addAttribute("allBooks", booksWithoutPublisher);

        return "publisher/addBook";
    }

    @PostMapping("/addBook/{publisherId}")
    public String addBooksToPublisher(@PathVariable Long publisherId, @RequestParam("bookIds") List<Long> bookIds) {
        Optional<Publisher> publisher = publisherRepository.findById(publisherId);

        if (publisher.isPresent()) {
            Publisher selectedPublisher = publisher.get();
            List<Book> selectedBooks = (List<Book>) bookRepository.findAllById(bookIds); // Замените на ваш репозиторий для книг

            selectedPublisher.getBooks().addAll(selectedBooks);
            publisherRepository.save(selectedPublisher);
        }

        return "redirect:/publishers/details/" + publisherId;
    }

    @GetMapping("/update/{id}")
    public String editPublisher(Model model, @PathVariable("id") Long id){
        Optional<Publisher> publisher = publisherRepository.findById(id);
        if (publisher.isEmpty())
            return "redirect:/publishers/main";
        model.addAttribute("publisher", publisher);
        return "publisher/edit";
    }

    @PostMapping("/update")
    public String editPublisher(@ModelAttribute Publisher publisher, Model model){
        publisherRepository.save(publisher);
        return "redirect:/publishers/main";
    }

    @GetMapping("/delete/{id}")
    public String deletePublisher(Model model, @PathVariable("id") Long id){
        Optional<Publisher> publisher = publisherRepository.findById(id);
        if (publisher.isEmpty())
            return "redirect:/publishers/main";
        model.addAttribute("publisher", publisher.get());
        return "publisher/delete";
    }

    @PostMapping("/delete/{id}")
    public String deletePublisher(@PathVariable("id") Long id){
        Optional<Publisher> publisher = publisherRepository.findById(id);
        if (publisher.isPresent())
            publisherRepository.deleteById(id);
        return "redirect:/publishers/main";
    }
}
