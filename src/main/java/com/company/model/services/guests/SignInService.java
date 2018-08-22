package com.company.model.services.guests;

import com.company.model.entities.User;
import com.company.model.exceptions.UnknownUserException;

public class SignInService {
    private UsersUtils utils;

    public SignInService() {
        utils = new UsersUtils();
    }

    public SignInService(UsersUtils utils) {
        this.utils = utils;
    }

    public User findUser(String login, String password) throws UnknownUserException {
        User user = utils.getUserByLoginAndPassword(login, password);
        if (user == null) {
            throw new UnknownUserException();
        }
        return user;
    }

}
