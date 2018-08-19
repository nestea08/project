package com.company.model.services.users;

import com.company.model.dao.DaoFactory;
import com.company.model.dao.UserDao;
import com.company.model.dao.impl.JDBCDaoFactory;
import com.company.model.entities.User;

import java.util.List;

public class UsersUtils {

    public void createUser(User user) {
        try (UserDao dao = JDBCDaoFactory.getInstance().createUserDao()) {
            dao.create(user);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public User getUserByLoginAndPassword(String login, String password) {
        try (UserDao dao = JDBCDaoFactory.getInstance().createUserDao()) {
            return dao.findUserByLoginAndPassword(login, password);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public List<User> getAllUsers() {
        try (UserDao dao = JDBCDaoFactory.getInstance().createUserDao()) {
            return dao.findAll();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
