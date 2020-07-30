package ru.careportal.core.data;

import org.springframework.data.repository.CrudRepository;
import ru.careportal.core.db.model.Anketa;

public interface AnketaRepo extends CrudRepository<Anketa, Integer> {
}

