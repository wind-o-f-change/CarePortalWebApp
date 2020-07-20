package ru.careportal.core.db.dao;

import org.springframework.stereotype.Repository;
import ru.careportal.core.db.model.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserDaoImpl implements UserDao {

    private Map<Integer, User> users = new ConcurrentHashMap<>();

    @Override
    public void addUser(User user) {
        users.put(user.getId(), user);
    }
}
