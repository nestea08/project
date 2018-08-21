package com.company.model.services.users;

import com.company.model.entities.Activity;
import com.company.model.entities.HistoryItem;
import com.company.model.entities.interfaces.Tracked;
import com.company.model.entities.interfaces.Tracker;

import java.time.LocalDate;
import java.util.Map;


public class TimeTrackingService {
    private TrackersUtils utils;

    public TimeTrackingService() {
        utils = new TrackersUtils();
    }

    public TimeTrackingService(TrackersUtils utils) {
        this.utils = utils;
    }

    public Map<Tracked, Integer> getTrackedItems(int userId) {
        Tracker tracker = utils.getTrackerUserById(userId);
        return tracker.getTrackedItems();
    }

    public Map.Entry<Tracked, Integer> getTrackedItemById(int userId, int trackedId) {
        Tracker tracker = utils.getTrackerUserById(userId);
        return tracker.getTrackedById(trackedId);
    }

    public void trackTime(int userId, int trackedId, int time) {
        Tracker tracker = utils.getTrackerUserById(userId);
        Tracked tracked = tracker.getTrackedById(trackedId).getKey();
        tracker.setSpentTime(tracked, tracker.getSpentTime(tracked) + time);
        utils.updateSpentTime(tracker, tracked);
    }

    public void finishTracking(int userId, int trackedId) {
        Tracker tracker = utils.getTrackerUserById(userId);
        Map.Entry<Tracked, Integer> entry = tracker.getTrackedById(trackedId);
        if (entry.getValue() == 0) {
            throw new RuntimeException();
        }
        Tracked activity = entry.getKey();
        HistoryItem item = new HistoryItem(activity.getTitle(), tracker, entry.getValue(), LocalDate.now());
        utils.removeTrackedFromTracker(tracker, activity);
        utils.createHistoryItem(item);
    }

}
