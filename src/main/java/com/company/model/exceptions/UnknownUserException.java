package com.company.model.exceptions;

public class UnknownUserException extends Exception {
    @Override
    public String getMessage() {
        return "Unknown email or password";
    }
}
