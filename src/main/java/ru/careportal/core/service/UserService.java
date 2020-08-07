package ru.careportal.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.careportal.core.data.UserRepo;
import ru.careportal.core.db.model.Role;
import ru.careportal.core.db.model.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserRepo userRepo;

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    public List<User> findByRoleNot(Role role){
        return userRepo.findByRoleNot(role);
    }

    public List<User> findByRole(Role role){
        return userRepo.findByRole(role);
    }

    public List<User> findByEnabled(Boolean enabled){
        return userRepo.findByEnabled(enabled);
    }

    public void save(User user) {
        userRepo.save(user);
    }

    public void updateEnabledStatus(boolean isEnabled, Long id){
        userRepo.updateUserEnabledStatus(isEnabled, id);
    }

    public void saveWithNewPassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepo.save(user);
    }

    public boolean validateOldPassword(User user, String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }
}
