package com.company.model.services.users;

import com.company.model.entities.*;

public class RequestsSaverService {
    private UsersUtils utils;

    public RequestsSaverService() {
        utils = new UsersUtils();
    }

    public RequestsSaverService(UsersUtils utils) {
        this.utils = utils;
    }

    public void saveTrackerUserRequest(int userId, int activityId, Request.RequestType requestType) {
        TrackerUser user = utils.getTrackerUserById(userId);
        Activity activity = utils.getActivityById(activityId);
        utils.createUserRequest(new Request(user, activity, requestType));
    }
}
