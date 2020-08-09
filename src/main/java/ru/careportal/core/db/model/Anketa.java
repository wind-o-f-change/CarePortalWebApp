package ru.careportal.core.db.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ankets")
@Data
public class Anketa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "anketa_id")
    private Integer id;

    @Column
    private String name;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "anketa_contents",
            joinColumns= {@JoinColumn(name ="anketa_id")},
            inverseJoinColumns= {@JoinColumn(name = "question_id")})
    private List<Question> questionList = new ArrayList<>();

    @OneToMany(mappedBy="anketa", cascade = CascadeType.ALL)
    private List<PassedAnketa> passedAnketaList = new ArrayList<>();

    @Override
    public String toString() {
        return "Anketa{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void addQuestion(Question question) {
        questionList.add(question);
        question.getAnketaList().add(this);
    }

}
