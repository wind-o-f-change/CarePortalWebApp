package ru.careportal.core.db.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@DiscriminatorValue("Patient")
@Data
@NoArgsConstructor
public class Patient extends User {
    @ManyToOne
    private Doctor doctor;
    @Column(name = "birth_day")
    private Calendar birthDay;

    @OneToMany(mappedBy="patient", cascade = CascadeType.ALL)
    private List<PassedAnketa> passedAnketaList = new ArrayList<>();

    public Patient(String email, String password) {
        super(email, password);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + getId() +
                ", email=" + getEmail() +
                ", password=" + (getPassword().length() > 0 ? "'notEmpty'" : "'isEmpty'") +
                ", role=" + getRole() +
                ", created=" + getCreated() +
                ", fullName=" + getFullName() +
                ", sex=" + getSex() +
                ", approved=" + isEnabled() +
                ", doctor=" + doctor +
                ", birthDay=" + birthDay +
                ", passedAnketaList=" + passedAnketaList +
                '}';
    }
}