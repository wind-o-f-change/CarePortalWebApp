package ru.careportal.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.careportal.core.db.model.FindAction;
import ru.careportal.core.db.model.Role;
import ru.careportal.core.db.model.User;
import ru.careportal.core.dto.UserChangesDto;
import ru.careportal.core.dto.UserDto;
import ru.careportal.core.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/admin/**")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/admin")
    public String adminPage(Model model, @AuthenticationPrincipal User admin) {
        log.debug("adminPage");
        model.addAttribute("PageTitle", "Администратор");
        model.addAttribute("PageBody", "admin.jsp");
        model.addAttribute("admin_name", admin.getFullName());
        model.addAttribute("userChangesDto", new UserChangesDto());
        return "baseTemplate";
    }

    @PostMapping
    public String showUsers(Model model, String find_action, @AuthenticationPrincipal User admin,
                            @ModelAttribute("userChangesDto") UserChangesDto userChangesDto) {
        model.addAttribute("PageTitle", "Администратор");
        model.addAttribute("PageBody", "admin.jsp");
        model.addAttribute("list_body", "usersTable.jsp");
        model.addAttribute("admin_name", admin.getFullName());

        List<User> usersFromDb = new ArrayList<>();
        List<UserDto> users = new ArrayList<>();
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
        for (User user : usersFromDb) {
            users.add(new UserDto(user));
        }
        userChangesDto.setUsers(users);
        model.addAttribute("userChangesDto", userChangesDto);
        return "baseTemplate";
    }

    @RequestMapping(value = "/admin/saveUsersChanges")
    public String saveChanges(Model model,   @ModelAttribute("userChangesDto") UserChangesDto userChangesDto,
                              @AuthenticationPrincipal User admin) {

        for (UserDto user : userChangesDto.getUsers()) {
            userService.updateEnabledStatus(user.isEnabled(), user.getId());
        }
        model.addAttribute("PageTitle", "Администратор");
        model.addAttribute("PageBody", "admin-saved.jsp");

        return "baseTemplate";
    }

}