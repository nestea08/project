package com.company.model.services;

import com.company.model.entities.Activity;
import com.company.model.entities.TrackerUser;


public class TimeTrackingService {
    private ServiceUtils utils;

    public TimeTrackingService() {
        utils = new ServiceUtils();
    }

    public TimeTrackingService(ServiceUtils utils) {
        this.utils = utils;
    }

    public void trackTime(int userId, String activityTitle, int time) {
        TrackerUser trackerUser = utils.getTrackerUserById(userId);
        Activity activity = trackerUser.getActivity(activityTitle);
        trackerUser.putSpentTimeOnActivity(activity, trackerUser.getSpentTimeOnActivity(activity) + time);
        utils.updateSpentTime(trackerUser, activity);
    }

}
