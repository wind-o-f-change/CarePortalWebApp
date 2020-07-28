package ru.careportal.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.careportal.core.data.AnswerRepo;
import ru.careportal.core.db.model.Answer;

@Service
public class AnswerService {
    private AnswerRepo answerRepo;

    @Autowired
    public AnswerService(AnswerRepo answerRepo) {
        this.answerRepo = answerRepo;
    }

    public Answer getAnswer(Integer id) {
        return answerRepo.findById(id).orElseThrow(NoEntityException::new);
    }
}
