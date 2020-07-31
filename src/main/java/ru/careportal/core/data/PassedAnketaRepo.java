package ru.careportal.core.data;

import org.springframework.data.repository.CrudRepository;
import ru.careportal.core.db.model.PassedAnketa;

public interface PassedAnketaRepo extends CrudRepository<PassedAnketa, Integer> {
}
