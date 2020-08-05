package ru.careportal.core.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;


@Data
public class UserChangesDto {
    private List<UserDto> users = new ArrayList<>();

}
