package com.company.controller.commands.exceptions;

public class NotValidPasswordException extends Exception {
    private String password;

    public NotValidPasswordException(String password) {
        this.password = password;
    }

    @Override
    public String getMessage() {
        return "Password " + password + " is not valid";
    }
}
