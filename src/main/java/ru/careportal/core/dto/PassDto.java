package ru.careportal.core.dto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PassDto {
    private String anketaName;
    private Integer anketaId;
    private List<QuestionDto> questionDtoList = new ArrayList<>();
}
