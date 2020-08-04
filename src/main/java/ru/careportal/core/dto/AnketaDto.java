package ru.careportal.core.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AnketaDto {
    private Integer id;
    private String name;
    private List<Integer> questionIdList= new ArrayList<>();
}
