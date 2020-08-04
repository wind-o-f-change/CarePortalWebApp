package ru.careportal.core.dto;

import lombok.Data;
import ru.careportal.core.db.model.Role;
import ru.careportal.core.db.model.Sex;
import ru.careportal.core.db.model.User;

import java.util.Date;

/**
 * UserDto
 * created by Ksenya_Ushakova at 04.08.2020
 */

@Data
public class UserDto {
    private Long id;
    private boolean enabled;
    private String email;
    private String fullName;
    private Sex sex;
    private Role role;
    private Date created;
    public UserDto(){

    }

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
