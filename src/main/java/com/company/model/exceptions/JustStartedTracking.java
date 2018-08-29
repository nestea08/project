package com.company.model.exceptions;

/**
 * Created by Asus on 29.08.2018.
 */
public class JustStartedTracking extends Exception {
    private int trackedItemId;

    public JustStartedTracking(int trackedItemId) {
        this.trackedItemId = trackedItemId;
    }

    @Override
    public String getMessage() {
        return String.format("");
    }
}
