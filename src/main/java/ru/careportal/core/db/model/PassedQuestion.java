package ru.careportal.core.db.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "pass_contents")
@Data
public class PassedQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pass_content_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="pass_id", nullable=false)
    private Pass pass;

    @ManyToOne
    @JoinColumn(name="question_id", nullable=false)
    private Question question;

    @ManyToOne
    @JoinColumn(name="answer_id", nullable=false)
    private Answer answer;
}
