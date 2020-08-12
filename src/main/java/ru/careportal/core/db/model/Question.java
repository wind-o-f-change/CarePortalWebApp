package ru.careportal.core.db.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Integer id;

    @Column(name = "question_text")
    private String text;

    @ManyToMany(mappedBy= "questionList")
    private List<Anketa> anketaList = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "question_contents",
            joinColumns= {@JoinColumn(name ="question_id")},
            inverseJoinColumns= {@JoinColumn(name = "answer_id")})
    private List<Answer> answerList = new ArrayList<>();

    @OneToMany(mappedBy="question", cascade = CascadeType.ALL)
    private List<PassedQuestion> passedQuestionList = new ArrayList<>();

    public void addAnswer(Answer answer) {
        answerList.add(answer);
        answer.getQuestionList().add(this);
    }

}
