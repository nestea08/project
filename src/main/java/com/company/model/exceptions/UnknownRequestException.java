package com.company.model.exceptions;

public class UnknownRequestException extends Exception {
    private int requestId;

    public UnknownRequestException(int requestId) {
        this.requestId = requestId;
    }

    @Override
    public String getMessage() {
        return String.format("Request with id %d does not exist", requestId);
    }

    @Override
    public String getLocalizedMessage() {
        return "exceptions.unknownRequest";
    }
}
