package com.company.model.services.guests;

import com.company.model.entities.TrackerUser;
import com.company.model.entities.User;
import com.company.model.exceptions.*;

import java.util.List;

public class SignUpService {
    private GuestsUtils utils;

    public SignUpService() {
        utils = new GuestsUtils();
    }

    public SignUpService(GuestsUtils utils) {
        this.utils = utils;
    }

    public User createUser(String nickname, String login, String password) throws Exception{
        User user = new TrackerUser.Builder(nickname, login, password).build();
        checkUserUnique(user);
        utils.createUser(user);
        return user;
    }

    private void checkUserUnique(User user) throws Exception{
        List<User> users = utils.getAllUsers();
        if (users.stream().anyMatch(u -> u.getNickname().equals(user.getNickname()))) {
            throw new NotUniqueNicknameException(user.getNickname());
        }
        else if (users.stream().anyMatch(u -> u.getLogin().equals(user.getLogin()))) {
            throw new NotUniqueLoginException(user.getLogin());
        }
        else if (users.stream().anyMatch(u -> u.getPassword().equals(user.getPassword()))) {
            throw new NotUniquePasswordException(user.getPassword());
        }
    }
}
