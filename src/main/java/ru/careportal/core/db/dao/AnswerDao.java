package ru.careportal.core.db.dao;

import ru.careportal.core.db.model.Answer;

public interface AnswerDao {
    void addAnswer(Answer answer);

    Answer getAnswerById(Integer id);
}
