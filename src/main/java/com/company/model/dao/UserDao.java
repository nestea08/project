package com.company.model.dao;

import com.company.model.entities.User;

public interface UserDao
        extends GenericDao<User>{
    User findUserByLoginAndPassword(String login, String password);
}
