package ru.careportal.core.data;

import org.springframework.data.repository.CrudRepository;
import ru.careportal.core.db.model.Anketa;
import ru.careportal.core.db.model.Question;
import ru.careportal.core.dto.User;

import java.util.List;
import java.util.Optional;

public interface AnketaRepo extends CrudRepository<Anketa, Integer> {
    Optional<Anketa> getAnketaById(Integer id);
}

