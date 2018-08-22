package com.company.model.dao;

import com.company.model.entities.Activity;
import com.company.model.entities.interfaces.Tracker;

public interface ActivityDao extends GenericDao<Activity> {
    void addActivityToTracker(Tracker tracker, Activity activity);
    void removeActivityFromTracker(Tracker tracker, Activity activity);
}
