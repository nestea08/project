package com.company.model.services;

import com.company.model.entities.*;

public class UserRequestsService {
    private ServiceUtils utils;

    public UserRequestsService() {
        utils = new ServiceUtils();
    }

    public UserRequestsService(ServiceUtils utils) {
        this.utils = utils;
    }

    public void sendUserRequest(int userId, int activityId, UserRequest.RequestType requestType) {
        TrackerUser user = utils.getTrackerUserById(userId);
        Activity activity = utils.getActivityById(activityId);
        utils.createUserRequest(new UserRequest(user, activity, requestType));
    }
}
