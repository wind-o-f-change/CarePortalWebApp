package ru.careportal.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.careportal.core.dto.PassedAnketaDto;
import ru.careportal.core.service.*;

import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
public class AnketaController {
    private PassedAnketaService passedAnketaService;

    @Autowired
    public AnketaController(PassedAnketaService passedAnketaService) {
        this.passedAnketaService = passedAnketaService;
    }

    @GetMapping("/anketa/{id}")
    public String showAnketa(Model model, @PathVariable("id") Integer id) {
        PassedAnketaDto passedAnketaDto = passedAnketaService.getPassDtoByAnketaId(id);

        model.addAttribute("passedAnketaDto", passedAnketaDto);
        model.addAttribute("PageBody", "anketa.jsp");
        return "baseTemplate";
    }

    @PostMapping("/passed-anketa")
    public String saveAnketa(Model model, Principal principal, @ModelAttribute("passedAnketaDto") PassedAnketaDto passedAnketaDto) {
        passedAnketaService.savePassedAnketa(passedAnketaDto, principal.getName());

        model.addAttribute("PageTitle", passedAnketaDto.getAnketaName());
        model.addAttribute("PageBody", "anketa-saved.jsp");
        return "baseTemplate";
    }

    @GetMapping("/passed-anketa-list")
    public String showPassedAnketa(Model model, Principal principal) {
        List<PassedAnketaDto> passedAnketaDtoList = passedAnketaService.getPassedAnketaDtoListByEmail(principal.getName());

        model.addAttribute("passedAnketaDtoList", passedAnketaDtoList);
        model.addAttribute("PageBody", "passed-anketa-list.jsp");
        return "baseTemplate";
    }

    @GetMapping("/passed-anketa/{id}")
    public String showPassedAnketa(Model model, @PathVariable("id") Integer id) {
        PassedAnketaDto passedAnketaDto = passedAnketaService.getPassedAnketaDtoById(id);

        model.addAttribute("passedAnketaDto", passedAnketaDto);
        model.addAttribute("PageBody", "passed-anketa.jsp");
        return "baseTemplate";
    }
}
