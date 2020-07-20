package ru.careportal.core.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.careportal.core.db.model.Answer;
import ru.careportal.core.db.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class QuestionDaoImpl implements QuestionDao {
    private Map<Integer, Question> questions = new ConcurrentHashMap<>();

    AnswerDao answerDao;

    @Autowired
    public QuestionDaoImpl(AnswerDao answerDao) {
        this.answerDao = answerDao;
    }

    @Override
    public void addQuestion(Question question) {
        questions.put(question.getId(), question);
    }

    @Override
    public List<Answer> getQuestionAnswersById(int i) {
        List<Answer> answerList = new ArrayList<Answer>();

        answerList.add(answerDao.getAnswerById(1));
        answerList.add(answerDao.getAnswerById(2));

        return answerList;
    }

    @Override
    public Question getQuestionById(Integer id) {
        return questions.get(id);
    }

}
