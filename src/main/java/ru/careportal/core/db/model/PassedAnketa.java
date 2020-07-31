package ru.careportal.core.db.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "passes")
@Data
@NoArgsConstructor(access= AccessLevel.PROTECTED, force=true)
@RequiredArgsConstructor
public class PassedAnketa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pass_id")
    private Integer id;

    @Column(name = "create_dtm")
    private Date created;

    @Column(name = "status_id")
    private Integer statusId;

    @ManyToOne
    @JoinColumn(name="anketa_id", nullable=false)
    @NonNull
    private Anketa anketa;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    @NonNull
    private Patient patient;

    @OneToMany(mappedBy="passedAnketa", cascade = CascadeType.ALL)
    private List<PassedQuestion> passedQuestionList = new ArrayList<>();

    @PrePersist
    private void setCreated() {
        this.created = new Date();
    }

    public void addPassedQuestion(PassedQuestion passedQuestion) {
        passedQuestionList.add(passedQuestion);
        passedQuestion.setPassedAnketa(this);
    }

}
