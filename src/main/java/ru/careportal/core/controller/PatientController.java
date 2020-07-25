package ru.careportal.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/patient")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_PATIENT')")
public class PatientController {
    @GetMapping
    public String clientPage(Model model){
        log.debug("clientPage");
        model.addAttribute("PageTitle", "Страница пациента");
        model.addAttribute("PageBody", "patient.jsp");
        return "baseTemplate";
    }
}
