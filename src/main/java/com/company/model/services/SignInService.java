package com.company.model.services;

import com.company.model.entities.User;

public class SignInService {

    public User findUser(String login, String password) {
        User user = ServiceUtils.getUserByLoginAndPassword(login, password);
        if (user == null) {
            throw new RuntimeException();
        }
        return user;
    }

}
