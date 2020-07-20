package ru.careportal.core.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT')")
public class ClientController {
    @GetMapping
    public String clientPage(){
        return "client";
    }
}
