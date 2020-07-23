package ru.careportal.core.db.dao;

import ru.careportal.core.db.model.Answer;
import ru.careportal.core.db.model.Question;

import java.util.List;

public interface QuestionDao {
    void addQuestion(Question question);

    List<Answer> getQuestionAnswersById(int i);

    Question getQuestionById(Integer id);
}
