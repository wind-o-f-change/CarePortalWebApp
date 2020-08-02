package ru.careportal.core.data;

import org.springframework.data.repository.CrudRepository;
import ru.careportal.core.db.model.PassedAnketa;
import ru.careportal.core.db.model.Patient;

import java.util.List;

public interface PassedAnketaRepo extends CrudRepository<PassedAnketa, Integer> {
    public List<PassedAnketa> findByPatient(Patient patient);
}
