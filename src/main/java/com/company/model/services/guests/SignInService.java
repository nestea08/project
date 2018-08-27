package com.company.model.services.guests;

import com.company.model.entities.User;
import com.company.model.exceptions.UnknownUserException;

public class SignInService {
    private GuestsUtils utils;

    public SignInService() {
        utils = new GuestsUtils();
    }

    public SignInService(GuestsUtils utils) {
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
