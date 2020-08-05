package ru.careportal.core.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * UserChangesDto
 * created by Ksenya_Ushakova at 04.08.2020
 */
@Data
public class UserChangesDto {
    private List<UserDto> users = new ArrayList<>();

}
