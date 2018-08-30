package com.company.model.services.users;

import com.company.model.entities.*;
import com.company.model.exceptions.DuplicateRequestException;

public class RequestsSaverService {
    private UsersUtils utils;

    public RequestsSaverService() {
        utils = new UsersUtils();
    }

    public RequestsSaverService(UsersUtils utils) {
        this.utils = utils;
    }

    public void saveTrackerRequest(int userId, int activityId,
                                   Request.RequestType requestType) throws DuplicateRequestException {
        TrackerUser user = utils.getTrackerUserById(userId);
        Activity activity = utils.getActivityById(activityId);
        utils.createUserRequest(new Request(user, activity, requestType));
    }
}
