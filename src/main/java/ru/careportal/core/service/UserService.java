package ru.careportal.core.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.careportal.core.data.UserRepo;
import ru.careportal.core.db.model.Doctor;
import ru.careportal.core.db.model.Role;
import ru.careportal.core.db.model.Sex;
import ru.careportal.core.db.model.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public List<User> findByFilter(Search search) {
        List<Boolean> statusList = search.getUserStatus();
        List<String> roleList = search.getUserRole();
        List<String> sexList = search.getUserSex();

        UserSpecifications specStatus = new UserSpecifications();
        if (statusList.size() > 0) {
            specStatus.add(new SearchCriteria("enabled", "in", Collections.singletonList(statusList)));
        }
        if (roleList.size() > 0) {
            specStatus.add(new SearchCriteria("role", "in", Collections.singletonList(Role.getEnumListByRusNameList(roleList))));
        }
        if (sexList.size() > 0) {
            specStatus.add(new SearchCriteria("sex", "in", Collections.singletonList(Sex.getEnumListByRusNameList(sexList))));
        }

        List<User> results = userRepo.findAll(specStatus);

        return results;
    }

}
