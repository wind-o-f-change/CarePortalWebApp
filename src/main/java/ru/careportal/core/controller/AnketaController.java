package ru.careportal.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.careportal.core.db.model.Anketa;
import ru.careportal.core.service.AnketaService;
import ru.careportal.core.service.NoEntityException;

import java.util.Optional;

@Slf4j
@Controller
public class AnketaController {
    private AnketaService anketaService;

    @Autowired
    public AnketaController(AnketaService anketaService) {
        this.anketaService = anketaService;
    }

    @GetMapping("/anketa/{id}")
    public String showAnketa(Model model, @PathVariable("id") Integer id) throws NoEntityException {
        Anketa anketa = anketaService.getAnketa(id);

        model.addAttribute("anketa", anketa);
        model.addAttribute("PageTitle", anketa.getName());
        model.addAttribute("PageBody", "anketa.jsp");
        return "baseTemplate";
    }
}
