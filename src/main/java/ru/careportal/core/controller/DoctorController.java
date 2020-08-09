package ru.careportal.core.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.careportal.core.db.model.Doctor;
import ru.careportal.core.db.model.User;
import ru.careportal.core.dto.UserDto;
import ru.careportal.core.service.AnketaService;
import ru.careportal.core.service.UserService;

import java.security.Principal;
import java.util.Optional;

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
        String email = principal.getName();
        Optional<User> userFromDB = userService.findByEmail(email);
        User user = userFromDB.orElseThrow(()-> new RuntimeException(String.format("Доктор с параметром email='%s' не найден", email)));

            model.addAttribute("user", user);
            model.addAttribute("PageTitle", "Страница врача");
            model.addAttribute("PageBody", "doctor.jsp");
            return "baseTemplate";
    }

    @GetMapping(value = "/doctor/showPatient/{id}")
    public String showPatient(Model model, @PathVariable("id") Long id){
        Optional<User> userFromDB = userService.findById(id);
        User user = userFromDB.orElseThrow(()-> new RuntimeException(String.format("Пациент с параметром id='%s' не найден", id)));

            model.addAttribute("user", user);
            model.addAttribute("PageTitle", "Информация о пациенте");
            model.addAttribute("PageBody", "patient.jsp");
            return "baseTemplate";
    }

    @PostMapping(value = "/doctor/saveUsersChanges")
    public String saveChanges(Model model, UserDto user) {
        Optional<User> byId = userService.findById(user.getId());
        Doctor userFromDb = (Doctor) byId.orElseThrow(() -> new RuntimeException
                (String.format("Врач с параметром id='%s' не найден", user.getId())));

        if (!userFromDb.getEmail().equals(user.getEmail())) {
            Optional<User> byEmail = userService.findByEmail(user.getEmail());
            if (byEmail.isPresent()) {
                model.addAttribute("message", "Пользователь с таким email уже зарегистрирован");
                model.addAttribute("PageTitle", "Страница врача");
                model.addAttribute("PageBody", "doctor.jsp");
                model.addAttribute("user", userFromDb);
                return "baseTemplate";
            }

        }

        userFromDb.setEmail(user.getEmail());
        userFromDb.setFullName(user.getFullName());
        userFromDb.setSex(user.getSex());
        userService.save(userFromDb);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        userDetails.setUserName(userFromDb.getUsername());


        model.addAttribute("PageTitle", "Страница врача");
        model.addAttribute("PageBody", "doctor.jsp");
        model.addAttribute("user", userFromDb);

        return "baseTemplate";
    }

    @PostMapping(value = "/doctor/changePass")
    public String changeUserPassword(Model model, @RequestParam("password") String password,
                                     @RequestParam("oldpassword") String oldPassword) {
        User user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();

        if (!userService.validateOldPassword(user, oldPassword)) {
            model.addAttribute("message", "Введен недействительный пароль!");
        } else {
            userService.saveWithNewPassword(user, password);
        }
        model.addAttribute("PageTitle", "Страница врача");
        model.addAttribute("PageBody", "doctor.jsp");
        model.addAttribute("user", user);

        return "baseTemplate";
    }

}
