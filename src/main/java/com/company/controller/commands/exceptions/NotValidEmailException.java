package com.company.controller.commands.exceptions;

import java.util.Locale;
import java.util.ResourceBundle;

public class NotValidEmailException extends Exception {
    private String email;

    public NotValidEmailException(String email) {
        this.email = email;
    }

    @Override
    public String getMessage() {
        return String.format("Email %s is not valid", email);
    }

    @Override
    public String getLocalizedMessage() {
        return "exceptions.notValidEmail";
    }
}
