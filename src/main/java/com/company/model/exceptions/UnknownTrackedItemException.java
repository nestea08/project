package com.company.model.exceptions;

public class UnknownTrackedItemException extends RuntimeException{
    private int trackedItemId;

    public UnknownTrackedItemException(int trackedItemId) {
        this.trackedItemId = trackedItemId;
    }

    @Override
    public String getMessage() {
        return String.format("Tracked item with id %d does not exist", trackedItemId);
    }

    @Override
    public String getLocalizedMessage() {
        return "exceptions.unknownTrackedItem";
    }
}
