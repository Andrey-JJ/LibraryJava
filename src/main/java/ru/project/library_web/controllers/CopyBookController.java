package ru.project.library_web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.project.library_web.models.CopyBook;
import ru.project.library_web.repositories.CopyBookRepository;

import java.util.List;
import java.util.Optional;

@RequestMapping("/copybooks")
@Controller
public class CopyBookController {
    @Autowired
    private CopyBookRepository copyBookRepository;

    @GetMapping("/main")
    public String getCopyBooks(Model model){
        List<CopyBook> copyBooks = (List<CopyBook>) copyBookRepository.findAll();
        model.addAttribute("copyBooks", copyBooks);
        return "copybook/main";
    }

    @GetMapping("/details/{id}")
    public String getDetails(@PathVariable("id") Long id, Model model){
        Optional<CopyBook> copyBook = copyBookRepository.findById(id);
        if(copyBook.isEmpty()){
            return "redirect:/copybooks/main";
        }
        model.addAttribute("selectedCopyBook", copyBook.get());
        return "copybook/details";
    }

    @GetMapping("/delete/{id}")
    public String deleteCopybook(@PathVariable("id") Long id, Model model){
        Optional<CopyBook> copyBook = copyBookRepository.findById(id);
        if(copyBook.isEmpty()){
            return "redirect:/copybooks/main";
        }
        model.addAttribute("selectedCopyBook", copyBook.get());
        return "copybook/delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteCopybook(@PathVariable("id") Long id){
        Optional<CopyBook> copyBook = copyBookRepository.findById(id);
        if(copyBook.isPresent()){
            copyBookRepository.deleteById(id);
        }
        return "redirect:/copybooks/main";
    }
}
