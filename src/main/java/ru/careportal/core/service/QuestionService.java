package ru.careportal.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.careportal.core.data.AnswerRepo;
import ru.careportal.core.data.QuestionRepo;
import ru.careportal.core.db.model.Answer;
import ru.careportal.core.db.model.Question;
import ru.careportal.core.dto.AnswerDto;
import ru.careportal.core.dto.QuestionDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    private QuestionRepo questionRepo;
    private AnswerRepo answerRepo;
    private static final int answersMaxCount = 4;

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

    private QuestionDto createQuestionDto(Question question) {
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
        return getAllQuestions().stream().map(this::createQuestionDto).collect(Collectors.toList());
    }


    public QuestionDto createNewQuestionDto() {
        QuestionDto questionDto = new QuestionDto();
        List<AnswerDto> answerDtoList = new ArrayList<>();
        for(int i = 1; i <= answersMaxCount; i++) {
            answerDtoList.add(new AnswerDto());
        }
        questionDto.getAnswerDtoList().addAll(answerDtoList);
        return questionDto;
    }

    public void saveQuestionByDto(QuestionDto questionDto) {
        Question question = new Question();
        question.setText(questionDto.getText());
        for(AnswerDto answerDto : questionDto.getAnswerDtoList()) {
            String answerText = answerDto.getText();
            if (!answerText.equals("")) {
                Answer answer = new Answer();
                answer.setText(answerDto.getText());
                question.addAnswer(answer);
            }
        }

        save(question);
    }
}
