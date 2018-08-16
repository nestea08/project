package com.company.model.exceptions;

public class NotUniqueNicknameException extends Exception {
    private String nickname;

    public NotUniqueNicknameException(String nickname) {
        super();
        this.nickname = nickname;
    }

    @Override
    public String getMessage() {
        return "Nickname " + nickname + " is not unique.";
    }
}
