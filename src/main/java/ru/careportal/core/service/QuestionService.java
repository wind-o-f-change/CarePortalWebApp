package ru.careportal.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.careportal.core.data.QuestionRepo;
import ru.careportal.core.db.model.Question;

@Service
public class QuestionService {
    private QuestionRepo questionRepo;

    @Autowired
    public QuestionService(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
    }

    public Question getQuestion(Integer id) {
        return questionRepo.findById(id).orElseThrow(NoEntityException::new);
    }
}
