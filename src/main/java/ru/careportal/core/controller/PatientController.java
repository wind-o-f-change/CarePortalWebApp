package ru.careportal.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.careportal.core.db.model.Patient;
import ru.careportal.core.db.model.User;
import ru.careportal.core.dto.UserDto;
import ru.careportal.core.service.AnketaService;
import ru.careportal.core.service.UserService;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/patient/**")
@PreAuthorize("hasAuthority('ROLE_PATIENT')")
public class PatientController {
    private UserService userService;
    private AnketaService anketaService;
    private AuthenticationManager authenticationManager;

    @Autowired
    public PatientController (UserService userService, AnketaService anketaService, AuthenticationManager authenticationManager){
        this.userService = userService;
        this.anketaService = anketaService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping(value = "/patient")
    public String patientPage(Model model, Principal principal){
        String email = principal.getName();
        Optional<User> userFromDB = userService.findByEmail(email);
        User user = userFromDB.orElseThrow(()-> new RuntimeException(String.format("Пациент с параметром email='%s' не найден", email)));

        model.addAttribute("user", user);
            model.addAttribute("PageTitle", "Страница пациента");
            model.addAttribute("PageBody", "patient.jsp");
            model.addAttribute("ankets", anketaService.getAllAnkets());
            return "baseTemplate";
    }


    @PostMapping(value = "/patient/saveUsersChanges")
    public String saveChanges(Model model, UserDto user, Principal principal){
        log.error("{}", user);
        Optional<User> byId = userService.findById(user.getId());
        Patient userFromDb = (Patient) byId.orElseThrow(()-> new RuntimeException(String.format("Пациент с параметром id='%s' не найден", user.getId())));
        if (!userFromDb.getEmail().equals(user.getEmail())) {
            Optional<User> byEmail = userService.findByEmail(user.getEmail());
            if (byEmail.isPresent()) {
                model.addAttribute("message", "Пользователь с таким email уже зарегистрирован");
                model.addAttribute("PageTitle", "Страница пациента");
                model.addAttribute("PageBody", "patient.jsp");
                model.addAttribute("user", userFromDb);
                log.debug(String.format("Пользователь с email '%s' уже зарегистрирован", byEmail.get().getEmail()));
                return "baseTemplate";
            }

        }
        userFromDb.setEmail(user.getEmail());
        userFromDb.setFullName(user.getFullName());
        userFromDb.setSex(user.getSex());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(user.getBirthDay()));
            userFromDb.setBirthDay(cal);
        } catch (ParseException e) {
            log.warn("Unable to parse birthDay");
        }
        userService.save(userFromDb);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        userDetails.setUserName(userFromDb.getUsername());

        model.addAttribute("PageTitle", "Страница пациента");
        model.addAttribute("PageBody", "patient.jsp");
        model.addAttribute("ankets", anketaService.getAllAnkets());
        model.addAttribute("user", userFromDb);

        return "baseTemplate";
    }
}
