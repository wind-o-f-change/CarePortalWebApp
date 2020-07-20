package ru.careportal.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.careportal.core.db.dao.AnketaDao;

@Controller
public class AnketaController {
    @Autowired
    AnketaDao anketaDao;

    @GetMapping("/anketa/1")
    public String showAnketa(Model model) {
        model.addAttribute("anketa", anketaDao.getAnketaById(1));
        return "anketa";
    };
}
