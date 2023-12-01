package ru.project.library_web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.project.library_web.models.Publisher;
import ru.project.library_web.repositories.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/publishers")
@Controller
public class PublisherController {
    @Autowired
    private PublisherRepository publisherRepository;

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
}