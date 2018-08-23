package com.company.controller.commands.exceptions;

import java.util.Locale;
import java.util.ResourceBundle;

public class NotValidPasswordException extends Exception {
    private String password;

    public NotValidPasswordException(String password) {
        this.password = password;
    }

    @Override
    public String getMessage() {
        return "Password " + password + " is not valid";
    }

    @Override
    public String getLocalizedMessage() {
        return "exceptions.notValidPassword";
    }
}
