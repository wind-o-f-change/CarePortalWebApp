package ru.careportal.core.data;

import org.springframework.data.repository.CrudRepository;
import ru.careportal.core.db.model.Role;
import ru.careportal.core.db.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findByRoleNot(Role role);

    List<User> findByRole(Role role);

    List<User> findByEnabled(boolean enabled);
}