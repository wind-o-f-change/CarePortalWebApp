package ru.careportal.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.careportal.core.data.AnketaRepo;
import ru.careportal.core.data.QuestionRepo;
import ru.careportal.core.db.model.Anketa;
import ru.careportal.core.db.model.PassedAnketa;
import ru.careportal.core.db.model.Question;
import ru.careportal.core.dto.AnketaDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnketaService {
    private AnketaRepo anketaRepo;
    private QuestionRepo questionRepo;

    @Autowired
    public AnketaService(AnketaRepo anketaRepo, QuestionRepo questionRepo) {
        this.anketaRepo = anketaRepo;
        this.questionRepo = questionRepo;
    }

    public Anketa save(Anketa anketa) {
        return  anketaRepo.save(anketa);
    }

    public Anketa getAnketa(Integer id) {
        return anketaRepo.findById(id).orElseThrow(NoEntityException::new);
    }

    public List<Anketa> getAllAnkets(){
        List<Anketa> result = new ArrayList<>();
        anketaRepo.findAll().forEach(result::add);
        return result;
    }

    public void saveAnketaByDto(AnketaDto anketaDto) {
        Anketa anketa = new Anketa();
        anketa.setName(anketaDto.getName());
        for(Integer questionId : anketaDto.getQuestionIdList()) {
            anketa.addQuestion(questionRepo.findById(questionId).orElseThrow(NoEntityException::new));
        }

        save(anketa);
    }
}
