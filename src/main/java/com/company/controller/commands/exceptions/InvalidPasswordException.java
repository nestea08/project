package com.company.controller.commands.exceptions;

public class InvalidPasswordException extends Exception {
    private String password;

    public InvalidPasswordException(String password) {
        this.password = password;
    }

    @Override
    public String getMessage() {
        return "Password " + password + " is not valid";
    }

    @Override
    public String getLocalizedMessage() {
        return "exceptions.invalidPassword";
    }
}
