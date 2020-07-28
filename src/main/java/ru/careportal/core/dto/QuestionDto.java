package ru.careportal.core.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionDto {
    private Integer id;
    private String text;
    private Integer chosenAnswerId;
    private List<AnswerDto> answerDtoList = new ArrayList<>();
}
