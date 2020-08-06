package ru.careportal.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.careportal.core.data.AnswerRepo;
import ru.careportal.core.data.QuestionRepo;
import ru.careportal.core.db.model.Anketa;
import ru.careportal.core.db.model.Answer;
import ru.careportal.core.db.model.Question;
import ru.careportal.core.dto.AnketaDto;
import ru.careportal.core.dto.AnswerDto;
import ru.careportal.core.dto.QuestionDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    private QuestionRepo questionRepo;
    private AnswerRepo answerRepo;

    @Autowired
    public QuestionService(QuestionRepo questionRepo, AnswerRepo answerRepo) {
        this.questionRepo = questionRepo;
        this.answerRepo = answerRepo;
    }

    public Question save(Question question) {
        return  questionRepo.save(question);
    }

    public Question getQuestion(Integer id) {
        return questionRepo.findById(id).orElseThrow(NoEntityException::new);
    }

    public Answer getAnswer(Integer id) {
        return answerRepo.findById(id).orElseThrow(NoEntityException::new);
    }

    private QuestionDto getQuestionDto(Question question) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setId(question.getId());
        questionDto.setText(question.getText());
        return questionDto;
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        questionRepo.findAll().forEach(questionList::add);
        return questionList;
    }

    public List<QuestionDto> getAllQuestionsDto() {
        List<QuestionDto> questionDtoList = new ArrayList<>();
        getAllQuestions().forEach(question -> {questionDtoList.add(getQuestionDto(question));});
        return questionDtoList;
    }


    public QuestionDto getNewQuestionDto() {
        QuestionDto questionDto = new QuestionDto();
        for(int i = 1; i <= 4; i++) {
            questionDto.getAnswerDtoList().add(new AnswerDto());
        }
        return questionDto;
    }

    public void saveQuestionByDto(QuestionDto questionDto) {
        Question question = new Question();
        question.setText(questionDto.getText());
        for(AnswerDto answerDto : questionDto.getAnswerDtoList()) {
            Answer answer = new Answer();
            answer.setText(answerDto.getText());
            question.addAnswer(answer);
        }

        save(question);
    }
}
