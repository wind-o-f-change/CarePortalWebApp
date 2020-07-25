package ru.careportal.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/doctor")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_DOCTOR')")
public class DoctorController {
    @GetMapping
    public String doctorPage(Model model){
        log.debug("doctorPage");
        model.addAttribute("PageTitle", "Страница врач");
        model.addAttribute("PageBody", "doctor.jsp");
        return "baseTemplate";
    }
}
