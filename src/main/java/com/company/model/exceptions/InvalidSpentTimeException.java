package com.company.model.exceptions;

public class InvalidSpentTimeException extends Exception {
    private int spentTime;

    public InvalidSpentTimeException(int spentTime) {
        this.spentTime = spentTime;
    }

    public InvalidSpentTimeException() {}

    @Override
    public String getMessage() {
        return String.format("Spent time %d is not valid", spentTime);
    }

    @Override
    public String getLocalizedMessage() {
        return "exceptions.invalidSpentTime";
    }
}
