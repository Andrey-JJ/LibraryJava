package ru.project.library_web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.project.library_web.models.Status;
import ru.project.library_web.repositories.StatusRepository;

import java.util.List;
import java.util.Optional;

@RequestMapping("/statuses")
@Controller
public class StatusController {

    @Autowired
    private StatusRepository statusRepository;

    @GetMapping("/main")
    public String getStatuses(Model model){
        List<Status> statuses = (List<Status>) statusRepository.findAll();
        model.addAttribute("statuses", statuses);
        return "status/main";
    }

    @GetMapping("/details/{id}")
    public String getStatusDetails(Model model, @PathVariable("id") Long id){
        Optional<Status> status = statusRepository.findById(id);
        if(status.isEmpty()){
            return "redirect:/statuses/main";
        }
        model.addAttribute("selectedStatus", status.get());
        return "status/details";
    }
}
