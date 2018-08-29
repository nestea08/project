package com.company.model.exceptions;

import com.company.model.entities.Activity;

public class NotUniqueActivityException extends Exception {
    private Activity activity;

    public NotUniqueActivityException(Activity activity) {
        this.activity = activity;
    }

    @Override
    public String getMessage() {
        return String.format("Activity with title %s already exists", activity.getTitle());
    }

    @Override
    public String getLocalizedMessage() {
        return "exceptions.notUniqueActivity";
    }
}
