package ru.careportal.core.db.dao;

import org.springframework.stereotype.Repository;
import ru.careportal.core.db.model.Answer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AnswerDaoImpl implements AnswerDao {
    private Map<Integer, Answer> answers = new ConcurrentHashMap<>();

    @Override
    public void addAnswer(Answer answer) {
        answers.put(answer.getId(), answer);
    }

    @Override
    public Answer getAnswerById(Integer id) {
        return answers.get(id);
    }
}
