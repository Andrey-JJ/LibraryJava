package ru.project.library_web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/new")
    public String addNewStatus(Model model){
        model.addAttribute("status", new Status());
        return "status/add";
    }

    @PostMapping("/new")
    public String addNewStatus(@ModelAttribute Status status, Model model){
        statusRepository.save(status);
        return "redirect:/statuses/main";
    }

    @GetMapping("/update/{id}")
    public String editStatus(Model model, @PathVariable("id") Long id){
        Optional<Status> status = statusRepository.findById(id);
        if (status.isEmpty())
            return "redirect:/statuses/main";
        model.addAttribute("status", status);
        return "status/edit";
    }

    @PostMapping("/update")
    public String editStatus(@ModelAttribute Status status, Model model){
        statusRepository.save(status);
        return "redirect:/statuses/main";
    }

    @GetMapping("/delete/{id}")
    public String deleteStatus(Model model, @PathVariable("id") Long id){
        Optional<Status> status = statusRepository.findById(id);
        if (status.isEmpty()) {
            return "redirect:/statuses/main";
        }
        model.addAttribute("status", status.get());
        return "status/delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteStatus(@PathVariable("id") Long id) {
        // Проверка существования статуса перед удалением
        Optional<Status> status = statusRepository.findById(id);
        if (status.isPresent()) {
            statusRepository.deleteById(id);
        }
        return "redirect:/statuses/main";
    }
}
