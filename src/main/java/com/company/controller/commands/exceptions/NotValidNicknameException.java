package com.company.controller.commands.exceptions;

public class NotValidNicknameException extends Exception {
    private String nickname;

    public NotValidNicknameException(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }
}
