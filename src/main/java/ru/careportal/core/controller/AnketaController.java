package ru.careportal.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.careportal.core.db.model.*;
import ru.careportal.core.dto.AnswerDto;
import ru.careportal.core.dto.PassDto;
import ru.careportal.core.dto.QuestionDto;
import ru.careportal.core.service.*;

import java.security.Principal;
import java.util.Optional;

@Slf4j
@Controller
public class AnketaController {
    private AnketaService anketaService;
    private PassService passService;
    private UserService userService;
    private QuestionService questionService;
    private AnswerService answerService;

    @Autowired
    public AnketaController(AnketaService anketaService, PassService passService, UserService userService, QuestionService questionService, AnswerService answerService) {
        this.anketaService = anketaService;
        this.passService = passService;
        this.userService = userService;
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @GetMapping("/anketa/{id}")
    public String showAnketa(Model model, @PathVariable("id") Integer id) {
        Anketa anketa = anketaService.getAnketa(id);
        model.addAttribute("anketa", anketa);

        PassDto passDto = new PassDto();
        for (Question question: anketa.getQuestionList()) {
            QuestionDto questionDto = new QuestionDto();
            questionDto.setId(question.getId());
            questionDto.setText(question.getText());
            for (Answer answer : question.getAnswerList()) {
                AnswerDto answerDto = new AnswerDto();
                answerDto.setId(answer.getId());
                answerDto.setText(answer.getText());
                questionDto.getAnswerDtoList().add(answerDto);
            }
            passDto.getQuestionDtoList().add(questionDto);
        }
        model.addAttribute("passDto", passDto);
        
        model.addAttribute("PageTitle", anketa.getName());
        model.addAttribute("PageBody", "anketa.jsp");
        return "baseTemplate";
    }

    @PostMapping("/anketa/{id}")
    public String saveAnketa(Model model, Principal principal, @PathVariable("id") Integer id, @ModelAttribute("passDto") PassDto passDto) {
        Anketa anketa = anketaService.getAnketa(id);

        String username = principal.getName();
        User user = userService.findByUsername(username);

        Pass pass = new Pass(anketa, user);
        pass = passService.save(pass);

        for (QuestionDto questionDto : passDto.getQuestionDtoList()) {
            PassQuestion passQuestion = new PassQuestion();
            passQuestion.setPass(pass);
            passQuestion.setQuestion(questionService.getQuestion(questionDto.getId()));
            passQuestion.setAnswer(answerService.getAnswer(questionDto.getChosenAnswerId()));
            pass.getPassQuestionList().add(passQuestion);
        }

        passService.save(pass);

        model.addAttribute("PageTitle", anketa.getName());
        model.addAttribute("PageBody", "anketa-saved.jsp");
        return "baseTemplate";
    }
}
