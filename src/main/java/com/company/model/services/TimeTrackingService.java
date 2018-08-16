package com.company.model.services;

import com.company.model.entities.Activity;
import com.company.model.entities.TrackerUser;


public class TimeTrackingService {

    public void trackTime(int userId, String activityTitle, int time) {
        TrackerUser trackerUser = ServiceUtils.getTrackerUserById(userId);
        Activity activity = trackerUser.getActivity(activityTitle);
        trackerUser.putSpentTimeOnActivity(activity, trackerUser.getSpentTimeOnActivity(activity) + time);
        ServiceUtils.updateSpentTime(trackerUser, activity);
    }

}
