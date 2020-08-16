package ru.careportal.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.careportal.core.dto.AnketaDto;
import ru.careportal.core.dto.PassedAnketaDto;
import ru.careportal.core.dto.QuestionDto;
import ru.careportal.core.service.AnketaService;
import ru.careportal.core.service.PassedAnketaService;
import ru.careportal.core.service.QuestionService;

import java.security.Principal;
import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
public class AnketaController {
    private PassedAnketaService passedAnketaService;
    private QuestionService questionService;
    private AnketaService anketaService;

    @Autowired
    public AnketaController(PassedAnketaService passedAnketaService, QuestionService questionService, AnketaService anketaService) {
        this.passedAnketaService = passedAnketaService;
        this.questionService = questionService;
        this.anketaService = anketaService;
    }

    @GetMapping("/anketa/{id}")
    public String showAnketa(Model model, @PathVariable("id") Integer id) {
        PassedAnketaDto passedAnketaDto = passedAnketaService.getPassDtoByAnketaId(id);

        model.addAttribute("passedAnketaDto", passedAnketaDto);
        model.addAttribute("PageTitle", passedAnketaDto.getAnketaName());
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
        model.addAttribute("PageTitle", "Список заполненных анкет");
        model.addAttribute("PageBody", "passed-anketa-list.jsp");
        return "baseTemplate";
    }

    @GetMapping("/passed-anketa/{id}")
    public String showPassedAnketa(Model model, @PathVariable("id") Integer id) {
        PassedAnketaDto passedAnketaDto = passedAnketaService.getPassedAnketaDtoById(id);

        model.addAttribute("passedAnketaDto", passedAnketaDto);
        model.addAttribute("PageTitle", passedAnketaDto.getAnketaName());
        model.addAttribute("PageBody", "passed-anketa.jsp");
        return "baseTemplate";
    }

    @GetMapping("/anketa-constr")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String anketaConstr(Model model) {
        model.addAttribute("questionDtoList", questionService.getAllQuestionsDto());
        model.addAttribute("anketaDto", new AnketaDto());
        model.addAttribute("PageTitle", "Конструктор анкеты");
        model.addAttribute("PageBody", "anketa-constr.jsp");
        return "baseTemplate";
    }

    @PostMapping("/anketa-constr")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String anketaConstrSave(Model model, Principal principal, @ModelAttribute("anketaDto") AnketaDto anketaDto) {
        if (anketaDto.getQuestionIdList().size() == 0) {
            model.addAttribute("message", "Не выбран ни 1 вопрос");
            model.addAttribute("questionDtoList", questionService.getAllQuestionsDto());
            model.addAttribute("anketaDto", anketaDto);
            model.addAttribute("PageBody", "anketa-constr.jsp");
        } else if (anketaDto.getName().equals("")) {
            model.addAttribute("message", "Не указано название анкеты");
            model.addAttribute("questionDtoList", questionService.getAllQuestionsDto());
            model.addAttribute("anketaDto", anketaDto);
            model.addAttribute("PageBody", "anketa-constr.jsp");
        }
        else {
            anketaService.saveAnketaByDto(anketaDto);
            model.addAttribute("PageBody", "anketa-constr-success.jsp");
        }

        model.addAttribute("PageTitle", "Конструктор анкеты");
        return "baseTemplate";
    }

    @GetMapping("/new-question")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String createNewQuestion(Model model) {
        model.addAttribute("questionDto", questionService.createNewQuestionDto());
        model.addAttribute("PageTitle", "Новый вопрос");
        model.addAttribute("PageBody", "new-question.jsp");
        return "baseTemplate";
    }

    @PostMapping("/new-question")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String saveNewQuestion(Model model, Principal principal, @ModelAttribute("questionDto") QuestionDto questionDto) {
        if(questionDto.getAnswerDtoList().get(0).getText().equals("")) {
            model.addAttribute("message", "Не указан вариант ответа");
            model.addAttribute("questionDto", questionDto);
        } else {
            questionService.saveQuestionByDto(questionDto);
            model.addAttribute("message", "Новый вопрос успешно сохранен");
            model.addAttribute("questionDto", questionService.createNewQuestionDto());
        }

        model.addAttribute("PageTitle", "Новый вопрос");
        model.addAttribute("PageBody", "new-question.jsp");
        return "baseTemplate";
    }
}
