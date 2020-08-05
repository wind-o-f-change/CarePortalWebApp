package ru.careportal.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class CustomErrorController {
    @GetMapping("/accessDenied")
    public String errorPage(Model model){
        model.addAttribute("PageTitle", "Forbidden");
        model.addAttribute("PageBody", "accessDenied.jsp");
        return "baseTemplate";
    }
}
