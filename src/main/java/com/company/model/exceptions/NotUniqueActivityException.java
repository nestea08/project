package com.company.model.exceptions;

import com.company.model.dto.LocalizedActivityDto;

public class NotUniqueActivityException extends RuntimeException {
    private LocalizedActivityDto activity;

    public NotUniqueActivityException(LocalizedActivityDto activity) {
        this.activity = activity;
    }

    @Override
    public String getMessage() {
        return String.format("Activity with title %s already exists", activity.getEnTitle());
    }

    @Override
    public String getLocalizedMessage() {
        return "exceptions.notUniqueActivity";
    }
}
