package com.company.model.services.users;

import com.company.model.entities.User;

public class SignInService {
    private UsersUtils utils;

    public SignInService() {
        utils = new UsersUtils();
    }

    public SignInService(UsersUtils utils) {
        this.utils = utils;
    }

    public User findUser(String login, String password) {
        User user = utils.getUserByLoginAndPassword(login, password);
        if (user == null) {
            throw new RuntimeException();
        }
        return user;
    }

}
