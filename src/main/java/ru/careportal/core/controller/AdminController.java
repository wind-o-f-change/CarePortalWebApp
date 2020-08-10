package ru.careportal.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.careportal.core.db.model.*;
import ru.careportal.core.dto.PassedAnketaDto;
import ru.careportal.core.dto.UserChangesDto;
import ru.careportal.core.dto.UserDto;
import ru.careportal.core.service.PassedAnketaService;
import ru.careportal.core.service.PatientService;
import ru.careportal.core.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/admin/**")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private UserService userService;
    private PassedAnketaService passedAnketaService;
    private PatientService patientService;

    @Autowired
    public void setUserService(UserService userService, PassedAnketaService passedAnketaService,
                               PatientService patientService) {
        this.userService = userService;
        this.passedAnketaService = passedAnketaService;
        this.patientService = patientService;
    }

    @GetMapping(value = "/admin")
    public String adminPage(Model model, @AuthenticationPrincipal User admin) {
        model.addAttribute("PageTitle", "Администратор");
        model.addAttribute("PageBody", "admin.jsp");
        model.addAttribute("admin_name", admin.getFullName());
        model.addAttribute("userChangesDto", new UserChangesDto());
        return "baseTemplate";
    }

    @PostMapping(value = "/admin")
    public String showUsers(Model model, String find_action, @AuthenticationPrincipal User admin,
                            @ModelAttribute("userChangesDto") UserChangesDto userChangesDto) {
        model.addAttribute("PageTitle", "Администратор");
        model.addAttribute("PageBody", "admin.jsp");
        model.addAttribute("list_body", "usersTable.jsp");
        model.addAttribute("admin_name", admin.getFullName());

        List<User> usersFromDb = new ArrayList<>();
        switch (find_action) {
            case FindAction.PATIENT_DOCTOR:
                usersFromDb = userService.findByRoleNot(Role.ROLE_ADMIN);
                break;
            case FindAction.PATIENT:
                usersFromDb =  userService.findByRole(Role.ROLE_PATIENT);
                break;
            case FindAction.DOCTOR:
                usersFromDb = userService.findByRole(Role.ROLE_DOCTOR);
                break;
            case FindAction.ADMIN:
                usersFromDb = userService.findByRole(Role.ROLE_ADMIN);
                break;
            case FindAction.ENABLED:
                usersFromDb = userService.findByEnabled(true);
                break;
            case FindAction.NOT_ENABLED:
                usersFromDb = userService.findByEnabled(false);
                break;
        }

        List<UserDto> usersDto = usersFromDb.stream().map(user -> new UserDto(user)).collect(Collectors.toList());
        userChangesDto.setUsers(usersDto);
        model.addAttribute("userChangesDto", userChangesDto);
        return "baseTemplate";
    }

    @PostMapping("/saveUsersChanges")
    public String saveChanges(Model model, @ModelAttribute("userChangesDto") UserChangesDto userChangesDto) {
        userChangesDto.getUsers().forEach(userDto -> userService.updateEnabledStatus(userDto.isEnabled(), userDto.getId()));
        model.addAttribute("PageTitle", "Администратор");
        model.addAttribute("PageBody", "admin.jsp");
        model.addAttribute("message","Изменения успешно сохранены!");
        return "baseTemplate";
    }

    @GetMapping(value = "/admin/showUser/{id}")
    public String showUser(Model model, @PathVariable("id") Long id){
        Optional<User> userFromDB = userService.findById(id);
        User user = userFromDB.orElseThrow(()-> new RuntimeException(String.format("Пациент с параметром id='%s' не найден", id)));
        Role role = user.getRole();
        switch (role){
            case ROLE_PATIENT:
                showPatient(model, user);
                break;
            case ROLE_DOCTOR:
                showDoctor(model, user);
                break;
        }

        return "baseTemplate";
    }

    @PostMapping(value = "/admin/showUser/{id}")
    public String showFreePatients(Model model, @AuthenticationPrincipal User admin,
                                   @ModelAttribute("userChangesDto") UserChangesDto userChangesDto,
                                   @PathVariable Long id){
        model.addAttribute("PageTitle", "Информация о враче");
        model.addAttribute("PageBody", "doctor.jsp");
        model.addAttribute("list_body", "usersWithoutDocTable.jsp");

        List<Patient> usersFromDb =  patientService.findByRoleAndDoctorIsNull(Role.ROLE_PATIENT);

        List<UserDto> usersDto = usersFromDb.stream().map(user -> new UserDto(user)).collect(Collectors.toList());
        userChangesDto.setUsers(usersDto);
        model.addAttribute("id", id);
        model.addAttribute("userChangesDto", userChangesDto);
        return "baseTemplate";
    }

    @PostMapping(value = "/admin/assignPatients/{id}")
    public String assignPatients(Model model, @PathVariable Long id, @ModelAttribute("userChangesDto") UserChangesDto userChangesDto) {
        Doctor doctor = (Doctor) userService.findById(id).orElseThrow(() -> new RuntimeException
                (String.format("Пользователь с параметром id= %s не найден", id)));
        userChangesDto.getUsers().forEach(userDto -> {
            if (userDto.isAssignedToDoctor()) {
                Patient byId = patientService.findById(userDto.getId())
                        .orElseThrow(() -> new RuntimeException
                                (String.format("Пользователь с параметром id= %s не найден", userDto.getId())));


                byId.setDoctor(doctor);
                patientService.save(byId);
                doctor.getPatients().add(byId);
            }
        });
        userService.save(doctor);
        showDoctor(model, doctor);
        model.addAttribute("message", "Изменения успешно сохранены!");
        return "baseTemplate";
    }

    private void showDoctor(Model model, User user) {
        model.addAttribute("user", user);
        model.addAttribute("PageTitle", "Информация о враче");
        model.addAttribute("PageBody", "doctor.jsp");
    }

    private void showPatient(Model model, User user) {
        model.addAttribute("user", user);
        model.addAttribute("PageTitle", "Информация о пациенте");
        model.addAttribute("PageBody", "patient.jsp");
        List<PassedAnketaDto> passedAnketaDtoList = passedAnketaService.getPassedAnketaDtoListByEmail(user.getEmail());
        model.addAttribute("passedAnketaDtoList", passedAnketaDtoList);
        model.addAttribute("passedAnketaTable", "passed-anketa-list.jsp");
    }
}