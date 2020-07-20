package ru.careportal.core.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/doctor")
@PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR')")
public class DoctorController {
    @GetMapping
    public String doctorPage(){
        return "doctor";
    }
}
