package ru.careportal.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.careportal.core.data.PassedAnketaRepo;
import ru.careportal.core.db.model.*;
import ru.careportal.core.dto.AnswerDto;
import ru.careportal.core.dto.PassedAnketaDto;
import ru.careportal.core.dto.QuestionDto;

import java.util.*;

@Service
public class PassedAnketaService {
    private PassedAnketaRepo passedAnketaRepo;
    private AnketaService anketaService;
    private UserService userService;
    private QuestionService questionService;

    @Autowired
    public PassedAnketaService(PassedAnketaRepo passedAnketaRepo, AnketaService anketaService, UserService userService, QuestionService questionService) {
        this.passedAnketaRepo = passedAnketaRepo;
        this.anketaService = anketaService;
        this.userService = userService;
        this.questionService = questionService;
    }

    public PassedAnketa save(PassedAnketa passedAnketa) {
        return  passedAnketaRepo.save(passedAnketa);
    }

    //получаем dto для заполнения формы анкеты
    //в этом dto ещё не хранится полученный ответ
    public PassedAnketaDto getPassDtoByAnketaId(Integer id) {
        Anketa anketa = anketaService.getAnketa(id);
        PassedAnketaDto passedAnketaDto = new PassedAnketaDto();
        passedAnketaDto.setAnketaName(anketa.getName());
        passedAnketaDto.setAnketaId(id);
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
            passedAnketaDto.getQuestionDtoList().add(questionDto);
        }
        return passedAnketaDto;
    }

    public void savePassedAnketa(PassedAnketaDto passedAnketaDto, String email) {
        Anketa anketa = anketaService.getAnketa(passedAnketaDto.getAnketaId());
        Patient user = (Patient) userService.findByEmail(email).get();

        PassedAnketa passedAnketa = new PassedAnketa(anketa, user);
        for (QuestionDto questionDto : passedAnketaDto.getQuestionDtoList()) {
            PassedQuestion passedQuestion = new PassedQuestion();
            passedQuestion.setQuestion(questionService.getQuestion(questionDto.getId()));
            passedQuestion.setAnswer(questionService.getAnswer(questionDto.getChosenAnswerId()));
            passedAnketa.addPassedQuestion(passedQuestion);
        }

        save(passedAnketa);
    }

    //получаем список пройденных юзером анкет
    public List<PassedAnketaDto> getPassedAnketaDtoListByEmail(String email) {
        Patient user = (Patient) userService.findByEmail(email).orElseThrow(NoEntityException::new);
        List<PassedAnketa> passedAnketaList =  passedAnketaRepo.findByPatient(user);

        List<PassedAnketaDto> passedAnketaDtoList = new ArrayList<>();
        for (PassedAnketa passedAnketa : passedAnketaList) {
            PassedAnketaDto passedAnketaDto = new PassedAnketaDto();
            passedAnketaDto.setPassId(passedAnketa.getId());
            passedAnketaDto.setAnketaId(passedAnketa.getAnketa().getId());
            passedAnketaDto.setAnketaName(passedAnketa.getAnketa().getName());
            passedAnketaDto.setCreated(passedAnketa.getCreated());
            passedAnketaDtoList.add(passedAnketaDto);
        }
        return passedAnketaDtoList;
    }

    public PassedAnketaDto getPassedAnketaDtoById(Integer passId) {
        PassedAnketa passedAnketa = passedAnketaRepo.findById(passId).orElseThrow(NoEntityException::new);
        PassedAnketaDto passedAnketaDto = new PassedAnketaDto();
        passedAnketaDto.setPassId(passedAnketa.getId());
        passedAnketaDto.setCreated(passedAnketa.getCreated());
        passedAnketaDto.setAnketaName(passedAnketa.getAnketa().getName());
        passedAnketaDto.setAnketaId(passedAnketa.getAnketa().getId());
        for (PassedQuestion passedQuestion : passedAnketa.getPassedQuestionList()) {
            QuestionDto questionDto = new QuestionDto();
            questionDto.setId(passedQuestion.getId());
            questionDto.setText(passedQuestion.getQuestion().getText());
            questionDto.setChosenAnswerText(passedQuestion.getAnswer().getText());
            passedAnketaDto.getQuestionDtoList().add(questionDto);
        }
        Collections.sort(passedAnketaDto.getQuestionDtoList(), Comparator.comparingInt(QuestionDto::getId));

        return passedAnketaDto;
    }

}
