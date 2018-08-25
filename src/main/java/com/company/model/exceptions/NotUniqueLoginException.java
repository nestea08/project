package com.company.model.exceptions;

public class NotUniqueLoginException extends Exception {
    private String login;

    public NotUniqueLoginException(String login) {
        super();
        this.login = login;
    }

    @Override
    public String getMessage() {
        return "Login " + login + " is not unique.";
    }

    @Override
    public String getLocalizedMessage() {
        return "exceptions.notUniqueLogin";
    }
}
