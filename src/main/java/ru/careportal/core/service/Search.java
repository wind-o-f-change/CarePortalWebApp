package ru.careportal.core.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Data
@RequiredArgsConstructor
public class Search {
    public List<Boolean> userStatus;
    public List<String> userRole;
    public List<String> userSex;

}
