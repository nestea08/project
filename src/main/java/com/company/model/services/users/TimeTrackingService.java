package com.company.model.services.users;

import com.company.model.entities.HistoryItem;
import com.company.model.entities.interfaces.TrackedItem;
import com.company.model.entities.interfaces.Tracker;

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

    public void trackTime(int userId, int trackedId, int time) {
        Tracker tracker = utils.getTrackerUserById(userId);
        TrackedItem trackedItem = tracker.getTrackedItemById(trackedId);
        utils.updateSpentTime(tracker, trackedItem.plusSpentTime(time));
    }

    public void finishTracking(int userId, int trackedId) {
        Tracker tracker = utils.getTrackerUserById(userId);
        TrackedItem trackedItem = tracker.getTrackedItemById(trackedId);
        if (trackedItem.getSpentTime() == 0) {
            throw new RuntimeException();
        }
        utils.transformTrackedIntoHistoryItem(tracker, trackedItem);
    }

}
