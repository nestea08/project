package com.company.model.services.trackers;

import com.company.model.entities.interfaces.Tracked;
import com.company.model.entities.interfaces.Tracker;

import java.util.Set;

public class TimeTrackingService {
    private TrackersUtils utils;

    public TimeTrackingService() {
        utils = new TrackersUtils();
    }

    public TimeTrackingService(TrackersUtils utils) {
        this.utils = utils;
    }

    public Set<Tracked> getTrackedItems(int userId) {
        Tracker tracker = utils.getTrackerUserById(userId);
        return tracker.getAllTracked();
    }

    public void trackTime(int userId, int activityId, int time) {
        Tracker tracker = utils.getTrackerUserById(userId);
        Tracked tracked = tracker.getTrackedById(activityId);
        tracker.setSpentTimeOnTracked(tracked, tracker.getSpentTimeOnTracked(tracked) + time);
        utils.updateSpentTime(tracker, tracked);
    }

}
