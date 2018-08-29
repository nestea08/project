package com.company.controller.exceptions;

public class InvalidEmailException extends Exception {
    private String email;

    public InvalidEmailException(String email) {
        this.email = email;
    }

    @Override
    public String getMessage() {
        return String.format("Email %s is not valid", email);
    }

    @Override
    public String getLocalizedMessage() {
        return "exceptions.invalidEmail";
    }
}
