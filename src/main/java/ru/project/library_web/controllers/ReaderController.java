package ru.project.library_web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.project.library_web.models.Category;
import ru.project.library_web.models.Reader;
import ru.project.library_web.repositories.ReaderRepository;

import java.util.List;
import java.util.Optional;

@RequestMapping("/readers")
@Controller
public class ReaderController {
    @Autowired
    private ReaderRepository readerRepository;

    @GetMapping("/main")
    public String getReaders(Model model){
        List<Reader> readers = (List<Reader>) readerRepository.findAll();
        model.addAttribute("readers", readers);
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

    @GetMapping("/new")
    public String showAddReaderForm(Model model) {
        model.addAttribute("reader", new Reader());
        return "reader/add";
    }

    @PostMapping("/new")
    public String addReader(@ModelAttribute Reader reader, Model model) {
        readerRepository.save(reader);
        return "redirect:/readers/main";
    }

    @GetMapping("/update/{id}")
    public String editReader(Model model, @PathVariable("id") Long id){
        Optional<Reader> reader = readerRepository.findById(id);
        if(reader.isEmpty()){
            return "redirect:/readers/main";
        }
        model.addAttribute("selectedReader", reader.get());
        return "reader/edit";
    }

    @PostMapping("/update")
    public String editReader(@ModelAttribute Reader reader, Model model){
        readerRepository.save(reader);
        return "redirect:/readers/mains";
    }

    @GetMapping("/delete/{id}")
    public String deleteReader(Model model, @PathVariable("id") Long id){
        Optional<Reader> reader = readerRepository.findById(id);
        if(reader.isEmpty()){
            return "redirect:/readers/main";
        }
        model.addAttribute("selectedReader", reader.get());
        return "reader/delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteReader(@PathVariable("id") Long id){
        Optional<Reader> reader = readerRepository.findById(id);
        if(reader.isPresent()){
            readerRepository.deleteById(id);
        }
        return "redirect:/readers/main";
    }
}
