package ru.careportal.core.db.model;

import java.util.List;

public class Anketa {

    private Integer id;
    private String name;
    private List<Question> questionList;

    public Anketa() {
    }

    public Anketa(Integer id, String name, List<Question> questionList) {
        this.id = id;
        this.name = name;
        this.questionList = questionList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    @Override
    public String toString() {
        return "Anketa{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
