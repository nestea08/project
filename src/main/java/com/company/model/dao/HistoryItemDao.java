package com.company.model.dao;

import com.company.model.entities.HistoryItem;
import com.company.model.entities.interfaces.TimeTracking;
import com.company.model.entities.interfaces.Tracker;

public interface HistoryItemDao
        extends GenericDao<HistoryItem> {
    void transformTrackedIntoHistoryItem(Tracker tracker, TimeTracking timeTracking);
}
