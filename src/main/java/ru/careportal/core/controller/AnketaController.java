package ru.careportal.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.careportal.core.dto.PassDto;
import ru.careportal.core.service.*;

import java.security.Principal;

@Slf4j
@Controller
public class AnketaController {
    private PassService passService;

    @Autowired
    public AnketaController(PassService passService) {
        this.passService = passService;
    }

    @GetMapping("/anketa/{id}")
    public String showAnketa(Model model, @PathVariable("id") Integer id) {
        PassDto passDto = passService.getPassDtoByAnketaId(id);

        model.addAttribute("passDto", passDto);
        model.addAttribute("PageBody", "anketa.jsp");
        return "baseTemplate";
    }

    @PostMapping("/passed-anketa")
    public String saveAnketa(Model model, Principal principal, @ModelAttribute("passDto") PassDto passDto) {
        passService.savePassedAnketa(passDto, principal.getName());

        model.addAttribute("PageTitle", passDto.getAnketaName());
        model.addAttribute("PageBody", "anketa-saved.jsp");
        return "baseTemplate";
    }
}
