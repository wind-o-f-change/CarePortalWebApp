package ru.careportal.core.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;

@Data
public class AnswerDto {
    private Integer id;
    private String text;
}
