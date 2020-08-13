package ru.careportal.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.careportal.core.db.model.Patient;
import ru.careportal.core.db.model.User;
import ru.careportal.core.dto.PassedAnketaDto;
import ru.careportal.core.dto.UserDto;
import ru.careportal.core.service.AnketaService;
import ru.careportal.core.service.PassedAnketaService;
import ru.careportal.core.service.UserService;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/patient/**")
@PreAuthorize("hasAuthority('ROLE_PATIENT')")
public class PatientController {
    private UserService userService;
    private AnketaService anketaService;
    private PassedAnketaService passedAnketaService;

    @Autowired
    public PatientController(UserService userService, AnketaService anketaService, PassedAnketaService passedAnketaService) {
        this.userService = userService;
        this.anketaService = anketaService;
        this.passedAnketaService = passedAnketaService;
    }

    @GetMapping(value = "/patient")
    public String patientPage(Model model, Principal principal) {
        String email = principal.getName();
        Optional<User> userFromDB = userService.findByEmail(email);
        User user = userFromDB.orElseThrow(() -> new RuntimeException(String.format("Пациент с параметром email='%s' не найден", email)));

        model.addAttribute("user", user);
        model.addAttribute("PageTitle", "Страница пациента");
        model.addAttribute("PageBody", "patient.jsp");
        model.addAttribute("ankets", anketaService.getAllAnkets());
        List<PassedAnketaDto> passedAnketaDtoList = passedAnketaService.getPassedAnketaDtoListByEmail(email);
        model.addAttribute("passedAnketaDtoList", passedAnketaDtoList);
        model.addAttribute("passedAnketaTable", "passed-anketa-list.jsp");

        return "baseTemplate";
    }


    @PostMapping(value = "/patient/saveUsersChanges")
    public String saveChanges(Model model, UserDto user) {
        Optional<User> byId = userService.findById(user.getId());
        Patient userFromDb = (Patient) byId.orElseThrow(() -> new RuntimeException
                (String.format("Пациент с параметром id='%s' не найден", user.getId())));

        if (!userFromDb.getEmail().equals(user.getEmail())) {
            Optional<User> byEmail = userService.findByEmail(user.getEmail());
            if (byEmail.isPresent()) {
                model.addAttribute("error", "Пользователь с таким email уже зарегистрирован");
                model.addAttribute("PageTitle", "Страница пациента");
                model.addAttribute("PageBody", "patient.jsp");
                model.addAttribute("user", userFromDb);

                model.addAttribute("ankets", anketaService.getAllAnkets());
                List<PassedAnketaDto> passedAnketaDtoList = passedAnketaService.getPassedAnketaDtoListByEmail(userFromDb.getEmail());
                model.addAttribute("passedAnketaDtoList", passedAnketaDtoList);
                model.addAttribute("passedAnketaTable", "passed-anketa-list.jsp");
                return "baseTemplate";
            }

        }

        userFromDb.setEmail(user.getEmail());
        userFromDb.setFullName(user.getFullName());
        userFromDb.setSex(user.getSex());
        setBday(user, userFromDb);
        userService.save(userFromDb);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        userDetails.setUserName(userFromDb.getUsername());

        model.addAttribute("PageTitle", "Страница пациента");
        model.addAttribute("PageBody", "patient.jsp");
        model.addAttribute("ankets", anketaService.getAllAnkets());
        List<PassedAnketaDto> passedAnketaDtoList = passedAnketaService.getPassedAnketaDtoListByEmail(userFromDb.getEmail());
        model.addAttribute("passedAnketaDtoList", passedAnketaDtoList);
        model.addAttribute("passedAnketaTable", "passed-anketa-list.jsp");
        model.addAttribute("user", userFromDb);
        model.addAttribute("message", "Изменения успешно сохранены!");

        return "baseTemplate";
    }

    private void setBday(UserDto user, Patient userFromDb) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(user.getBirthDay()));
            userFromDb.setBirthDay(cal);
        } catch (ParseException e) {
            throw new RuntimeException("Unable to parse birthDay");
        }
    }

    @PostMapping(value = "/patient/changePass")
    public String changeUserPassword(Model model, @RequestParam("password") String password,
                                     @RequestParam("oldpassword") String oldPassword) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException
                (String.format("Пациент с параметром email='%s' не найден", email)));

        if (!userService.validateOldPassword(user, oldPassword)) {
            model.addAttribute("error", "Введен недействительный пароль!");
        } else {
            userService.saveWithNewPassword(user, password);
            model.addAttribute("message", "Изменения успешно сохранены!");
        }
        model.addAttribute("PageTitle", "Страница пациента");
        model.addAttribute("PageBody", "patient.jsp");
        model.addAttribute("ankets", anketaService.getAllAnkets());
        List<PassedAnketaDto> passedAnketaDtoList = passedAnketaService.getPassedAnketaDtoListByEmail(email);
        model.addAttribute("passedAnketaDtoList", passedAnketaDtoList);
        model.addAttribute("passedAnketaTable", "passed-anketa-list.jsp");
        model.addAttribute("user", user);

        return "baseTemplate";
    }
}
