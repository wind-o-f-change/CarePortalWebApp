package ru.careportal.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.careportal.core.db.model.User;
import ru.careportal.core.security.RegistrationForm;
import ru.careportal.core.service.UserService;

import java.util.Optional;
@Slf4j
@Controller
public class SecurityController {
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public SecurityController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("PageTitle", "Страница регистрации");
        model.addAttribute("PageBody", "reg.jsp");
        return "baseTemplate";
    }

    @PostMapping("/registration")
    public String processRegistration(RegistrationForm form, Model model) {

        User user = form.toUser(passwordEncoder);
        Optional<User> userFromDB = userService.findByEmail(user.getEmail());
        if (userFromDB.isPresent()) {
            model.addAttribute("error", "Пользователь с таким именем уже зарегистрирован");
            model.addAttribute("PageTitle", "Страница регистрации");
            model.addAttribute("PageBody", "reg.jsp");
            log.debug(String.format("Пользователь с email '%s' уже зарегестрирован", userFromDB.get().getEmail()));
            return "baseTemplate";
        }

        userService.save(user);
        model.addAttribute("message", "Вы успешно зарегистрированы!");
        log.info(String.format("Сохранен пользователь: %s", user));
        return "login";
    }


    @GetMapping({ "/", "/login" })
    public String loginFailure(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null && error.isEmpty()) {
            model.addAttribute("error", "Аккаунт не подтвержден администратором или использован неверный e-mail или пароль");
        }
        return "login";
    }
}