package com.company.model.services;

import com.company.model.entities.*;

public class UserRequestsService {

    public void sendUserRequest(int userId, int activityId, UserRequest.RequestType requestType) {
        TrackerUser user = ServiceUtils.getTrackerUserById(userId);
        Activity activity = ServiceUtils.getActivityById(activityId);
        ServiceUtils.createUserRequest(new UserRequest(user, activity, requestType));
    }

}
