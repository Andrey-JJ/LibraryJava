package ru.project.library_web.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.project.library_web.models.*;
import ru.project.library_web.repositories.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/publishers")
@RestController
public class ApiPublisherController {

    @Autowired
    PublisherRepository publisherRepository;

    @GetMapping("/")
    public List<Publisher> getPublishers(){
        List<Publisher> publishers = (List<Publisher>) publisherRepository.findAll();
        return publishers;
    }

    @GetMapping("/details/{id}")
    public Publisher getPublisher(@PathVariable("id") Long id){
        Optional<Publisher> publisher = publisherRepository.findById(id);
        if(publisher.isEmpty())
            throw new RuntimeException("Publisher Not Found");
        return publisher.get();
    }
}
