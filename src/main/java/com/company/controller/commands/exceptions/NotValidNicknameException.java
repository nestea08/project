package com.company.controller.commands.exceptions;

import java.util.Locale;
import java.util.ResourceBundle;

public class NotValidNicknameException extends Exception {
    private String nickname;

    public NotValidNicknameException(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String getMessage() {
        return String.format("Nickname %s is not valid", nickname);
    }

    @Override
    public String getLocalizedMessage() {
        return"exceptions.notValidNickname";
    }
}
