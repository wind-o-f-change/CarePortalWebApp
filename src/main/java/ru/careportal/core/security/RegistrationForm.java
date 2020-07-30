package ru.careportal.core.security;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.careportal.core.db.model.*;

@Slf4j
@Data
public class RegistrationForm {

    private String email;
    private String fullName;
    private String password;
    private String roleName;
    private String sex;


    public User toUser(PasswordEncoder passwordEncoder) {
        if (roleName.equalsIgnoreCase(Role.ROLE_PATIENT.name())) {
            return setUserParameters(new Patient(email, passwordEncoder.encode(password)));
        }

        if (roleName.equalsIgnoreCase(Role.ROLE_DOCTOR.name())) {
            return setUserParameters(new Doctor(email, passwordEncoder.encode(password)));
        }

        if (roleName.equalsIgnoreCase(Role.ROLE_ADMIN.name())) {
            return setUserParameters(new Admin(email, passwordEncoder.encode(password)));
        }

        String message = String.format("Роль '%s' не предусмотрена", roleName);
        log.error(message);
        throw new RuntimeException(message);
    }

    private User setUserParameters(User user){
        user.setFullName(fullName);
        user.setRole(Role.valueOf(roleName));
        user.setSex(Sex.valueOf(sex));
        return user;
    }
}
