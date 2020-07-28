package ru.careportal.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.careportal.core.data.AnketaRepo;
import ru.careportal.core.data.PassRepo;
import ru.careportal.core.db.model.Pass;

@Service
public class PassService {
    private PassRepo passRepo;

    @Autowired
    public PassService(PassRepo passRepo) {
        this.passRepo = passRepo;
    }

    public Pass save(Pass pass) {
        return  passRepo.save(pass);
    }
}
