package ru.careportal.core.service;

import lombok.Data;

import java.util.List;

@Data
public class SearchCriteria {
    private String key;
    private String operation;
    private List<Object> value;

    public SearchCriteria(String key, String operation, List<Object> value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }
}