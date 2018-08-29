package com.company.controller.commands.exceptions;

public class InvalidNicknameException extends Exception {
    private String nickname;

    public InvalidNicknameException(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String getMessage() {
        return String.format("Nickname %s is not valid", nickname);
    }

    @Override
    public String getLocalizedMessage() {
        return "exceptions.invalidNickname";
    }
}
