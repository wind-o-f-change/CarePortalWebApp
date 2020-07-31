package ru.careportal.core.db.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "answers")
@Data
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Integer id;

    @Column(name = "answer_text")
    private String text;

    @ManyToMany(mappedBy= "answerList")
    private List<Question> questionList = new ArrayList<>();

    @OneToMany(mappedBy="answer", cascade = CascadeType.ALL)
    private List<PassedQuestion> passedQuestionList = new ArrayList<>();
}
