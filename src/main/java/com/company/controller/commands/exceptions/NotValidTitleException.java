package com.company.controller.commands.exceptions;

public class NotValidTitleException extends Exception {
    private String title;

    public NotValidTitleException(String title) {
        this.title = title;
    }

    @Override
    public String getMessage() {
        return String.format("Title %s is not valid", title);
    }

    @Override
    public String getLocalizedMessage() {
        return "exceptions.invalidTitle";
    }
}
