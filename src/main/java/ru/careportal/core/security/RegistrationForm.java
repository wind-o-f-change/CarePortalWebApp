package ru.careportal.core.security;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.careportal.core.dto.Role;
import ru.careportal.core.dto.User;

import javax.validation.constraints.NotBlank;

@Data
public class RegistrationForm {

  @NotBlank(message="Имя обязательно")
  private String username;
  @NotBlank(message="Пароль обязателен")
  private String password;
  @NotBlank(message="Пароль обязателен")
  private String roleName;

  public User toUser(PasswordEncoder passwordEncoder) {

    User user = new User(username, passwordEncoder.encode(password));
    Role role = Role.valueOf(roleName);
    user.setRole(role);

    return user;
  }
}
