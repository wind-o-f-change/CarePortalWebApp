package ru.careportal.core.data;

import org.springframework.data.repository.CrudRepository;
import ru.careportal.core.db.model.Anketa;
import ru.careportal.core.db.model.Pass;

public interface PassRepo extends CrudRepository<Pass, Integer> {
}
