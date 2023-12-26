package ru.project.library_web.controllers.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;
import ru.project.library_web.services.EmailService;

@RequestMapping("/email")
@RestController
public class EmailController {
    @Autowired
    EmailService emailService;
    @GetMapping("/send-email/{user-email}")
    public @ResponseBody ResponseEntity sendSimpleEmail(@PathVariable("user-email") String email) {
        try {
            emailService.sendSimpleEmail(email, "Уведомление","Данное письмо сформировано автоматически от сервиса Spring Boot. Владелец Орехов А.А.");
        } catch (MailException mailException) {
            System.out.println(mailException.getMessage());
            return new ResponseEntity<>("Невозможно отправить почту", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Письмо успешно отправлено.", HttpStatus.OK);
    }
}