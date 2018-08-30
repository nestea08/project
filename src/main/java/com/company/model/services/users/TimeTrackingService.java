package com.company.model.services.users;

import com.company.model.entities.HistoryItem;
import com.company.model.entities.interfaces.TrackedItem;
import com.company.model.entities.interfaces.Tracker;
import com.company.model.exceptions.InvalidSpentTimeException;

import java.time.LocalDate;
import java.util.List;


public class TimeTrackingService {
    private UsersUtils utils;

    public TimeTrackingService() {
        utils = new UsersUtils();
    }

    public TimeTrackingService(UsersUtils utils) {
        this.utils = utils;
    }

    public List<TrackedItem> getTrackedItems(int userId) {
        Tracker tracker = utils.getTrackerUserById(userId);
        return tracker.getTrackedItems();
    }

    public TrackedItem getTrackedItemById(int userId, int trackedId) {
        Tracker tracker = utils.getTrackerUserById(userId);
        return tracker.getTrackedItemById(trackedId);
    }

    public TrackedItem trackTime(int userId, int trackedId, int spentTime)
            throws InvalidSpentTimeException {
        Tracker tracker = utils.getTrackerUserById(userId);
        TrackedItem trackedItem = tracker.getTrackedItemById(trackedId).plusSpentTime(spentTime);
        utils.updateSpentTime(tracker, trackedItem);
        return trackedItem;
    }

    public void finishTracking(int userId, int trackedId) {
        Tracker tracker = utils.getTrackerUserById(userId);
        TrackedItem trackedItem = tracker.getTrackedItemById(trackedId);
        if (trackedItem.getSpentTime() == 0) {
            throw new IllegalStateException("exceptions.invalidTrackingItemFinishing");
        }
        utils.transformTrackedIntoHistoryItem(tracker, trackedItem);
    }

}
