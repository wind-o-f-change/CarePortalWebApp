package ru.careportal.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.careportal.core.db.model.User;
import ru.careportal.core.service.AnketaService;
import ru.careportal.core.service.UserService;

import java.security.Principal;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/doctor/**")
@PreAuthorize("hasAuthority('ROLE_DOCTOR')")
public class DoctorController {
    private UserService userService;
    private AnketaService anketaService;

    @Autowired
    public DoctorController (UserService userService){
        this.userService = userService;
    }

    @GetMapping(value = "/doctor")
    public String doctorPage(Model model, Principal principal){
        log.debug("Load doctorPage");
        String email = principal.getName();
        Optional<User> userFromDB = userService.findByEmail(email);
        if (userFromDB.isPresent()) {
            model.addAttribute("user", userFromDB.get());
            model.addAttribute("PageTitle", "Страница врача");
            model.addAttribute("PageBody", "doctor.jsp");
            return "baseTemplate";
        }
        log.warn("Пользователь не найден");
        return "login";
    }

    @GetMapping(value = "/doctor/showPatient/{id}")
    public String showPatient(Model model, @PathVariable("id") Long id){
        Optional<User> userFromDB = userService.findById(id);
        if (userFromDB.isPresent()) {
            model.addAttribute("user", userFromDB.get());
            model.addAttribute("PageTitle", "Информация о пациенте");
            model.addAttribute("PageBody", "patient.jsp");
            return "baseTemplate";
        }
        log.warn("Пациент не найден");
        return "redirect:/doctor";
    }
    
}
