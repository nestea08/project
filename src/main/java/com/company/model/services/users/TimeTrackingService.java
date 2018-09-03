package com.company.model.services.users;

import com.company.model.entities.interfaces.TimeTracking;
import com.company.model.entities.interfaces.Tracker;
import com.company.model.exceptions.InvalidSpentTimeException;

import java.util.List;


public class TimeTrackingService {
    private UsersUtils utils;

    public TimeTrackingService() {
        utils = new UsersUtils();
    }

    public TimeTrackingService(UsersUtils utils) {
        this.utils = utils;
    }

    public List<TimeTracking> getTrackings(int userId) {
        Tracker tracker = utils.getTrackerUserById(userId);
        return tracker.getTimeTrackings();
    }

    public TimeTracking getTrackingsById(int userId, int trackedId) {
        Tracker tracker = utils.getTrackerUserById(userId);
        return tracker.getTrackingById(trackedId);
    }

    public TimeTracking trackTime(int userId, int trackedId, int spentTime)
            throws InvalidSpentTimeException {
        Tracker tracker = utils.getTrackerUserById(userId);
        TimeTracking timeTracking = tracker.getTrackingById(trackedId).plusSpentTime(spentTime);
        utils.updateSpentTime(tracker, timeTracking);
        return timeTracking;
    }

    public void finishTracking(int userId, int trackedId) {
        Tracker tracker = utils.getTrackerUserById(userId);
        TimeTracking timeTracking = tracker.getTrackingById(trackedId);
        if (timeTracking.getSpentTime() == 0) {
            throw new IllegalStateException("exceptions.invalidTrackingItemFinishing");
        }
        utils.transformTrackingIntoHistoryItem(tracker, timeTracking);
    }

}
