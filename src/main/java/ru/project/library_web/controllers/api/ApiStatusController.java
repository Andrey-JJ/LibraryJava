package ru.project.library_web.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.project.library_web.models.Status;
import ru.project.library_web.repositories.StatusRepository;

import java.util.List;

@RequestMapping("/api/statuses")
public class ApiStatusController {
    @Autowired
    StatusRepository statusRepository;

    @GetMapping("/")
    public List<Status> getStatuses(){
        List<Status> statuses = (List<Status>) statusRepository.findAll();
        return statuses;
    }
}
