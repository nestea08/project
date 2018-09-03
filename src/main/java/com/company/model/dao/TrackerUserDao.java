package com.company.model.dao;

import com.company.model.entities.interfaces.TimeTracking;
import com.company.model.entities.interfaces.Tracker;
import com.company.model.entities.TrackerUser;

public interface TrackerUserDao extends GenericDao<TrackerUser>{
    void updateSpentTime(Tracker tracker, TimeTracking timeTracking);
}
