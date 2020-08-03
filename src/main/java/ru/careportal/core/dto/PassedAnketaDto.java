package ru.careportal.core.dto;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class PassedAnketaDto {
    private Integer passId;
    private String anketaName;
    private Integer anketaId;
    private Date created;
    private List<QuestionDto> questionDtoList = new ArrayList<>();
}
