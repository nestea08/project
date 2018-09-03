package com.company.model.entities;


import com.company.model.entities.interfaces.TimeTracking;
import com.company.model.entities.interfaces.Tracker;
import com.company.model.exceptions.UnknownTrackingException;

import java.util.*;

/**
 * Represents a web site user that can manage time trackings
 *
 * Is a wrapper for <code>List</code> of trackings trackings
 */
public class TrackerUser extends User implements Tracker{
    private List<TimeTracking> timeTrackings;

    /**
     * Extends user builder creating tracker user objects
     */
    public static class Builder extends User.Builder {
        public Builder(String nickname, String login, String password) {
            super(nickname, login, password, Role.USER);
        }

        @Override
        public Builder id(int val) {
            id = val;
            return this;
        }

        @Override
        public TrackerUser build() {
            return new TrackerUser(this);
        }

    }

    /**
     * Constructs a tracker user from builder
     *
     * Initializes time trackings list
     */
    private TrackerUser(Builder builder) {
        super(builder);
        timeTrackings = new ArrayList<>();
    }

    public void addTracking(TimeTracking timeTracking) {
        timeTrackings.add(timeTracking);
    }

    public void removeTracking(TimeTracking timeTracking) {
        timeTrackings.remove(timeTracking);
    }

    public TimeTracking getTrackingById(int id) {
        Optional<TimeTracking> trackingOptional = timeTrackings.stream().
                filter(item -> item.getId() == id).findFirst();
        if (!trackingOptional.isPresent()) {
            throw new UnknownTrackingException(id);
        }
        return trackingOptional.get();
    }

    public List<TimeTracking> getTimeTrackings() {
        return timeTrackings;
    }

}
