package ru.careportal.core.data;

import org.springframework.data.repository.CrudRepository;
import ru.careportal.core.db.model.Anketa;
import ru.careportal.core.db.model.Answer;
import ru.careportal.core.db.model.Question;

import java.util.List;

public interface QuestionRepo extends CrudRepository<Question, Integer> {
}
