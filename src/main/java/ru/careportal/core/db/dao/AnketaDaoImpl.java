package ru.careportal.core.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.careportal.core.db.model.Anketa;
import ru.careportal.core.db.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AnketaDaoImpl implements AnketaDao {
    private Map<Integer, Anketa> ankets = new ConcurrentHashMap<>();

    private QuestionDao questionDao;

    @Autowired
    public AnketaDaoImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public void addAnketa(Anketa anketa) {
        ankets.put(anketa.getId(), anketa);
    }

    @Override
    public Anketa getAnketaById(Integer id) {
        return ankets.get(id);
    }

    @Override
    public List<Question> getAnketaQuestionsById(Integer id) {
        List<Question> questionList = new ArrayList<Question>();
        for(int i = 1; i <= 5; i++) {
            questionList.add(questionDao.getQuestionById(i));
        }
        return questionList;
    }
}
