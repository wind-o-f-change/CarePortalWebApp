package ru.careportal.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.careportal.core.db.model.Role;
import ru.careportal.core.db.model.User;
import ru.careportal.core.service.UserService;

@Slf4j
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String adminPage(Model model, @AuthenticationPrincipal User admin) {
        log.debug("adminPage");
        model.addAttribute("PageTitle", "Администратор");
        model.addAttribute("PageBody", "admin.jsp");
        model.addAttribute("admin_name", admin.getFullName());

        return "baseTemplate";
    }

    @GetMapping("/users-table")
    public String showAllUsers(Model model, @AuthenticationPrincipal User admin){
        model.addAttribute("admin_name", admin.getFullName());
        model.addAttribute("list_users",userService.findByRoleNot(Role.ROLE_ADMIN));
        model.addAttribute("PageTitle", "Страница просмотра пользователей");
        model.addAttribute("PageBody", "usersTable.jsp");

        return "baseTemplate";
    }

    @PostMapping
    public String adminFunc(Model model, String find_action){


        return "";
    }
}