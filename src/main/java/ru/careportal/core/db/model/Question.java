package ru.careportal.core.db.model;

import java.util.List;

public class Question {

    private Integer id;
    private String text;
    private String type;
    private List<Answer> answerList;

    public Question() {

    }

    public Question(Integer id, String text, String type, List<Answer> answerList) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.answerList = answerList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }
}
