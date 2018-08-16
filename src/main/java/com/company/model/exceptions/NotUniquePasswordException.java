package com.company.model.exceptions;

public class NotUniquePasswordException extends Exception{
    private String password;

    public NotUniquePasswordException(String password) {
        super();
        this.password = password;
    }

    @Override
    public String getMessage() {
        return "Password " + password + " is not unique.";
    }
}
