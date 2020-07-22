package ru.careportal.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.careportal.core.data.UserRepo;
import ru.careportal.core.dto.User;
import ru.careportal.core.security.RegistrationForm;

import javax.validation.Valid;

@Slf4j
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
    public String registration(Model model) {
        log.debug("registration");
        model.addAttribute("PageTitle", "Registration Page");
        model.addAttribute("PageBody", "reg.jsp");
        return "baseTemplate";
    }

    @PostMapping
    public String processRegistration(@Valid RegistrationForm form, Model model, Errors errors) {
        log.debug("processRegistration");
        if (errors.hasErrors()) {
            model.addAttribute("message", "Не все данные были заполнены");
            model.addAttribute("PageTitle", "Страница регистрации");
            model.addAttribute("PageBody", "reg.jsp");
            log.error(errors.getAllErrors().toString());
            return "baseTemplate";
        }

        User user = form.toUser(passwordEncoder);
        User userFromDB = userRepo.findByUsername(user.getUsername());
        if (userFromDB != null) {
            model.addAttribute("message", "Пользователь с таким именем уже зарегестрирован");
            model.addAttribute("PageTitle", "Страница регистрации");
            model.addAttribute("PageBody", "reg.jsp");
            log.debug(String.format("Пользователь с именем '%s' уже зарегестрирован", userFromDB.getUsername()));
            return "baseTemplate";
        }

        userRepo.save(user);
        log.info(String.format("Сохранен пользователь: %s", user));
        return "redirect:/login";
    }
}