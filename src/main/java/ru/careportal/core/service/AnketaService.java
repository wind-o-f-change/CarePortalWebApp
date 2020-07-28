package ru.careportal.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.careportal.core.data.AnketaRepo;
import ru.careportal.core.db.model.Anketa;

import java.util.Optional;

@Service
public class AnketaService {
    private AnketaRepo anketaRepo;

    @Autowired
    public AnketaService(AnketaRepo anketaRepo) {
        this.anketaRepo = anketaRepo;
    }

    public Anketa getAnketa(Integer id) {
        return anketaRepo.findById(id).orElseThrow(NoEntityException::new);
    }
}
