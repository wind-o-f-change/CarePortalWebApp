package ru.careportal.core.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.careportal.core.db.model.Role;
import ru.careportal.core.db.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findByEmail(String email);

    List<User> findByRoleNot(Role role);

    List<User> findByRole(Role role);

    List<User> findByEnabled(boolean enabled);

    @Modifying
    @Transactional
    @Query("update User u set u.enabled = :enabled where u.id = :id")
    void updateUserEnabledStatus(@Param("enabled") boolean enabled, @Param("id") Long id);


}