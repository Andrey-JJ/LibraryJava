package ru.project.library_web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.project.library_web.models.Loan;
import ru.project.library_web.repositories.LoanRepository;

import java.util.List;

@RequestMapping("/loans")
@Controller
public class LoanController {
    @Autowired
    private LoanRepository loanRepository;

    @GetMapping("/main")
    public String getLoans(Model model){
        List<Loan> loans = (List<Loan>) loanRepository.findAll();
        model.addAttribute("loans", loans);
        return "loan/main";
    }
}
