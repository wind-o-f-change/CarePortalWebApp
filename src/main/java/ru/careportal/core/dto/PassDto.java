package ru.careportal.core.dto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.careportal.core.db.model.PassQuestion;

import java.util.ArrayList;
import java.util.List;

@Data
public class PassDto {
    private List<QuestionDto> questionDtoList = new ArrayList<>();
}
