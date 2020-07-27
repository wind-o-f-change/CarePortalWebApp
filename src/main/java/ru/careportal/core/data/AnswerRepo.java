package ru.careportal.core.data;

import org.springframework.data.repository.CrudRepository;
import ru.careportal.core.db.model.Answer;

public interface AnswerRepo extends CrudRepository<Answer, Integer> {

}
