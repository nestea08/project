package com.company.model.services.trackers;

import com.company.model.entities.*;

public class RequestsSaverService {
    private TrackersUtils utils;

    public RequestsSaverService() {
        utils = new TrackersUtils();
    }

    public RequestsSaverService(TrackersUtils utils) {
        this.utils = utils;
    }

    public void saveTrackerUserRequest(int userId, int activityId, Request.RequestType requestType) {
        TrackerUser user = utils.getTrackerUserById(userId);
        Activity activity = utils.getActivityById(activityId);
        utils.createUserRequest(new Request(user, activity, requestType));
    }
}
