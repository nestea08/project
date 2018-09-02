package com.company.model.exceptions;


public class DuplicateRequestException extends RuntimeException {

    @Override
    public String getMessage() {
        return "This request already exists";
    }

    @Override
    public String getLocalizedMessage() {
        return "exceptions.duplicateRequest";
    }
}
