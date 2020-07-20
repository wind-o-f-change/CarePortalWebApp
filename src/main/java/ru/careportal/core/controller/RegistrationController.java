package ru.careportal.core.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.careportal.core.data.UserRepo;
import ru.careportal.core.security.RegistrationForm;

import java.util.Map;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(
            UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registration() {
        return "reg";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form, Map<String, Object> model) {

        if (form == null) {
            model.put("message", "User is exist");
            return "/reg";
        }

        userRepo.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }
}