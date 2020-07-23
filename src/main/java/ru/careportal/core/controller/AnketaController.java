package ru.careportal.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.careportal.core.db.dao.AnketaDao;

@Controller
public class AnketaController {
    private AnketaDao anketaDao;

    @Autowired
    public AnketaController(AnketaDao anketaDao) {
        this.anketaDao = anketaDao;
    }

    @GetMapping("/anketa/{id}")
    public String showAnketa(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("anketa", anketaDao.getAnketaById(id));
        return "anketa";
    };
}
