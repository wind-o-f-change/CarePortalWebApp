package ru.careportal.core.data;

import org.springframework.data.repository.CrudRepository;
import ru.careportal.core.db.model.Patient;
import ru.careportal.core.db.model.Role;

import java.util.List;

/**
 * PatientRepo
 * created by Ksenya_Ushakova at 10.08.2020
 */
public interface PatientRepo extends CrudRepository<Patient, Long> {
    List<Patient> findByRoleAndDoctorIsNull(Role role);
}
