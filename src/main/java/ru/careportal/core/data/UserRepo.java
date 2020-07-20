package ru.careportal.core.data;

import org.springframework.data.repository.CrudRepository;
import ru.careportal.core.dto.User;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);
}