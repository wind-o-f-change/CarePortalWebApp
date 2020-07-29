package ru.careportal.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@Controller

public class CustomErrorController {
    @GetMapping("/accessDenied")
    public String errorPage(Model model){
        log.debug("accessDenied");
        model.addAttribute("PageTitle", "Forbidden");
        model.addAttribute("PageBody", "accessDenied.jsp");
        return "baseTemplate";
    }

    @RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
    public String loginFailure(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null && error.isEmpty()) {
            model.addAttribute("error", "Неверный e-mail или пароль!");
        }
        return "login";
    }
}
