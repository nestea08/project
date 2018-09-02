package com.company.model.services.guests;

import com.company.model.dao.UserDao;
import com.company.model.dao.impl.JDBCDaoFactory;
import com.company.model.entities.User;

import java.util.List;

public class GuestsUtils {

    public void createUser(User user) {
        try (UserDao dao = JDBCDaoFactory.getInstance().createUserDao()) {
            dao.create(user);
        }
    }

    public User getUserByLoginAndPassword(String login, String password) {
        try (UserDao dao = JDBCDaoFactory.getInstance().createUserDao()) {
            return dao.findUserByLoginAndPassword(login, password);
        }
    }

    public List<User> getAllUsers() {
        try (UserDao dao = JDBCDaoFactory.getInstance().createUserDao()) {
            return dao.findAll();
        }
    }

}
