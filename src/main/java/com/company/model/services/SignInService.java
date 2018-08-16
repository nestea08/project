package com.company.model.services;

import com.company.model.entities.User;

public class SignInService {
    private ServiceUtils utils;

    public SignInService() {
        utils = new ServiceUtils();
    }

    public SignInService(ServiceUtils utils) {
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
