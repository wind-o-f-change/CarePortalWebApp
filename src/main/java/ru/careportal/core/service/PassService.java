package ru.careportal.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.careportal.core.data.PassedAnketaRepo;
import ru.careportal.core.db.model.*;
import ru.careportal.core.dto.AnswerDto;
import ru.careportal.core.dto.PassDto;
import ru.careportal.core.dto.QuestionDto;

@Service
public class PassService {
    private PassedAnketaRepo passedAnketaRepo;
    private AnketaService anketaService;
    private UserService userService;
    private QuestionService questionService;

    @Autowired
    public PassService(PassedAnketaRepo passedAnketaRepo, AnketaService anketaService, UserService userService, QuestionService questionService) {
        this.passedAnketaRepo = passedAnketaRepo;
        this.anketaService = anketaService;
        this.userService = userService;
        this.questionService = questionService;
    }

    public PassedAnketa save(PassedAnketa passedAnketa) {
        return  passedAnketaRepo.save(passedAnketa);
    }

    public PassDto getPassDtoByAnketaId(Integer id) {
        Anketa anketa = anketaService.getAnketa(id);
        PassDto passDto = new PassDto();
        passDto.setAnketaName(anketa.getName());
        passDto.setAnketaId(id);
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
        return passDto;
    }

    public void savePassedAnketa(PassDto passDto, String userName) {
        Anketa anketa = anketaService.getAnketa(passDto.getAnketaId());
        User user = userService.findByUsername(userName);

        PassedAnketa passedAnketa = new PassedAnketa(anketa, user);
        for (QuestionDto questionDto : passDto.getQuestionDtoList()) {
            PassedQuestion passedQuestion = new PassedQuestion();
            passedQuestion.setQuestion(questionService.getQuestion(questionDto.getId()));
            passedQuestion.setAnswer(questionService.getAnswer(questionDto.getChosenAnswerId()));
            passedAnketa.addPassedQuestion(passedQuestion);
        }

        save(passedAnketa);
    }
}
