package com.company.controller.commands.exceptions;

public class NotValidDescriptionException extends Exception{
    private String description;

    public NotValidDescriptionException(String description) {
        this.description = description;
    }

    @Override
    public String getMessage() {
        return String.format("Description %s is not valid", description);
    }

    @Override
    public String getLocalizedMessage() {
        return "exceptions.invalidDescription";
    }
}
