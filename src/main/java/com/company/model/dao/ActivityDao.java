package com.company.model.dao;

import com.company.model.dto.LocalizedActivityDto;
import com.company.model.entities.Activity;
import com.company.model.entities.interfaces.Tracker;

import java.util.List;

public interface ActivityDao extends GenericDao<Activity> {
    void createLocalized(LocalizedActivityDto item);
    List<Activity> findPossibleForTrackerActivities(int trackerId);
}
