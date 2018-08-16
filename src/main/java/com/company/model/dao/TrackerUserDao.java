package com.company.model.dao;

import com.company.model.entities.Activity;
import com.company.model.entities.TrackerUser;

public interface TrackerUserDao extends GenericDao<TrackerUser>{
    void updateSpentTime(TrackerUser trackerUser, Activity activity);
    void addActivityToTrackerUser(TrackerUser trackerUser, Activity activity);
    void removeActivityFromTrackerUser(TrackerUser trackerUser, Activity activity);
}
