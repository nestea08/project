package com.company.controller.commands.exceptions;

public class NotValidEmailException extends Exception {
    private String email;

    public NotValidEmailException(String email) {
        this.email = email;
    }

    @Override
    public String getMessage() {
        return "Email " + email + " is not valid";
    }
}
