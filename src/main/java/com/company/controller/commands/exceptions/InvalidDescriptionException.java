package com.company.controller.commands.exceptions;

public class InvalidDescriptionException extends Exception{
    private String description;

    public InvalidDescriptionException(String description) {
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
