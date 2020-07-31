package ru.careportal.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.careportal.core.data.AnswerRepo;
import ru.careportal.core.data.QuestionRepo;
import ru.careportal.core.db.model.Answer;
import ru.careportal.core.db.model.Question;

@Service
public class QuestionService {
    private QuestionRepo questionRepo;
    private AnswerRepo answerRepo;

    @Autowired
    public QuestionService(QuestionRepo questionRepo, AnswerRepo answerRepo) {
        this.questionRepo = questionRepo;
        this.answerRepo = answerRepo;
    }

    public Question getQuestion(Integer id) {
        return questionRepo.findById(id).orElseThrow(NoEntityException::new);
    }

    public Answer getAnswer(Integer id) {
        return answerRepo.findById(id).orElseThrow(NoEntityException::new);
    }
}
