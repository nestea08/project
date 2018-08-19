package com.company.model.dao;

import com.company.model.entities.interfaces.Tracked;
import com.company.model.entities.interfaces.Tracker;
import com.company.model.entities.TrackerUser;

public interface TrackerUserDao extends GenericDao<TrackerUser>{
    void updateSpentTime(Tracker tracker, Tracked tracked);
    void addTrackedToTracker(Tracker tracker, Tracked tracked);
    void removeTrackedFromTracker(Tracker tracker, Tracked tracked);
}
