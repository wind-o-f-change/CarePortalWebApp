package ru.careportal.core.data;

import org.springframework.data.repository.CrudRepository;
import ru.careportal.core.db.model.Question;

public interface QuestionRepo extends CrudRepository<Question, Integer> {
}
