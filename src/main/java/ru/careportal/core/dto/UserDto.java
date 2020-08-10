package ru.careportal.core.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.careportal.core.db.model.Role;
import ru.careportal.core.db.model.Sex;
import ru.careportal.core.db.model.User;

import java.util.Calendar;
import java.util.Date;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    private boolean enabled;
    private String email;
    private String fullName;
    private Sex sex;
    private Role role;
    private Date created;
    private String birthDay;
    private boolean assignedToDoctor;

    public UserDto(User user) {
        this.id = user.getId();
        this.enabled = user.isEnabled();
        this.email = user.getEmail();
        this.fullName = user.getFullName();
        this.sex = user.getSex();
        this.role = user.getRole();
        this.created = user.getCreated();
    }
}
