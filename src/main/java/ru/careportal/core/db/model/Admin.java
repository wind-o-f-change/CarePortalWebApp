package ru.careportal.core.db.model;

import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Admin")
@NoArgsConstructor
public class Admin extends User {
    public Admin(String email, String password) {
        super(email, password);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + getId() +
                ", email=" + getEmail() +
                ", password=" + (getPassword().length() > 0 ? "'notEmpty'" : "'isEmpty'") +
                ", role=" + getRole() +
                ", created=" + getCreated() +
                ", fullName=" + getFullName() +
                ", sex=" + getSex() +
                ", approved=" + isEnabled() +
                '}';
    }
}
