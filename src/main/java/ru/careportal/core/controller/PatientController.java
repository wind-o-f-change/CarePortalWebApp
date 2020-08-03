package ru.careportal.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.careportal.core.db.model.User;
import ru.careportal.core.service.AnketaService;
import ru.careportal.core.service.UserService;

import java.security.Principal;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/patient")
@PreAuthorize("hasAuthority('ROLE_PATIENT')")
public class PatientController {
    private UserService userService;
    private AnketaService anketaService;

    @Autowired
    public PatientController (UserService userService, AnketaService anketaService){
        this.userService = userService;
        this.anketaService = anketaService;
    }

    @GetMapping
    public String patientPage(Model model, Principal principal){
        log.debug("Load patientPage");
        String email = principal.getName();
        Optional<User> userFromDB = userService.findByEmail(email);
        if (userFromDB.isPresent()) {
            model.addAttribute("user", userFromDB.get());
            model.addAttribute("PageTitle", "Страница пациента");
            model.addAttribute("PageBody", "patient.jsp");
            model.addAttribute("ankets", anketaService.getAllAnkets());
            return "baseTemplate";
        }
        log.warn("Пользователь не найден");
        return "login";
    }
}
