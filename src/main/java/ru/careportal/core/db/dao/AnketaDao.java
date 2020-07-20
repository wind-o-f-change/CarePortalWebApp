package ru.careportal.core.db.dao;

import ru.careportal.core.db.model.Anketa;
import ru.careportal.core.db.model.Question;

import java.util.List;

public interface AnketaDao {
    void addAnketa(Anketa anketa);
    Anketa getAnketaById(Integer id);
    List<Question> getAnketaQuestionsById(Integer id);
}

