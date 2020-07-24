package ru.careportal.core.data;

import org.springframework.data.repository.CrudRepository;
import ru.careportal.core.dto.User;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}