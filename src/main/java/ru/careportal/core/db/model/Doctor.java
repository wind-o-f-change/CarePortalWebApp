package ru.careportal.core.db.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("Doctor")
@Data
@NoArgsConstructor
public class Doctor extends User {
    @OneToMany
    @Column(name = "patients")
    private List<Patient> patients = new ArrayList<>();

    public Doctor(String email, String password) {
        super(email, password);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + getId() +
                ", email=" + getEmail()  +
                ", password=" + (getPassword().length() > 0 ? "notEmpty" : "isEmpty") +
                ", role=" + getRole() +
                ", created=" + getCreated() +
                ", fullName=" + getFullName() +
                ", sex=" + getSex() +
                ", approved=" + isEnabled() +
                ", patients=" + patients +
                '}';
    }
}