package com.company.model.dao;

import com.company.model.entities.HistoryItem;
import com.company.model.entities.interfaces.TrackedItem;
import com.company.model.entities.interfaces.Tracker;

import java.sql.SQLException;

public interface HistoryItemDao
        extends GenericDao<HistoryItem> {
    void transformTrackedIntoHistoryItem(Tracker tracker, TrackedItem trackedItem) throws SQLException;
}
