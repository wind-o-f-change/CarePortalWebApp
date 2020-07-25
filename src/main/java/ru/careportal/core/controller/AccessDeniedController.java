package ru.careportal.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/accessDenied")
public class AccessDeniedController {
    @GetMapping
    public String errorPage(Model model){
        log.debug("accessDenied");
        model.addAttribute("PageTitle", "Forbidden");
        model.addAttribute("PageBody", "accessDenied.jsp");
        return "baseTemplate";
    }
}
