package ru.project.library_web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.project.library_web.models.Reader;
import ru.project.library_web.repositories.ReaderRepository;

import java.util.List;
import java.util.Optional;

@RequestMapping("/reader")
@Controller
public class ReaderController {
    @Autowired
    private ReaderRepository readerRepository;

    @GetMapping("/main")
    public String getReaders(Model model){
        List<Reader> readers = (List<Reader>) readerRepository.findAll();
        model.addAttribute("authors", readers);
        return "reader/main";
    }

    @GetMapping("/details/{id}")
    public String getReaderDetails(Model model, @PathVariable("id") Long id){
        Optional<Reader> reader = readerRepository.findById(id);
        if(reader.isEmpty())
            return "redirect:/readers/main";
        model.addAttribute("selectedReader", reader.get());
        return "reader/details";
    }
}
