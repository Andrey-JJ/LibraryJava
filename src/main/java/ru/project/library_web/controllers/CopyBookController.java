package ru.project.library_web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.project.library_web.models.CopyBook;
import ru.project.library_web.repositories.CopyBookRepository;

import java.util.List;

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
}
