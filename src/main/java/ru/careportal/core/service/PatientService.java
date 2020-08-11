package ru.careportal.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.careportal.core.data.PatientRepo;
import ru.careportal.core.db.model.Patient;
import ru.careportal.core.db.model.Role;

import java.util.List;
import java.util.Optional;


@Service
public class PatientService {
    PatientRepo patientRepo;
    @Autowired
    public void setUserRepo(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

    public List<Patient> findByRoleAndDoctorIsNull(Role rolePatient) {
        return patientRepo.findByRoleAndDoctorIsNull(rolePatient);
    }

    public Optional<Patient> findById(Long id) {
        return patientRepo.findById(id);
    }

    public void save(Patient byId) {
        patientRepo.save(byId);
    }
}
