package com.company.model.exceptions;

public class UnknownTrackingException extends RuntimeException{
    private int trackingId;

    public UnknownTrackingException(int trackingId) {
        this.trackingId = trackingId;
    }

    @Override
    public String getMessage() {
        return String.format("Tracking with id %d does not exist", trackingId);
    }

    @Override
    public String getLocalizedMessage() {
        return "exceptions.unknownTrackedItem";
    }
}
