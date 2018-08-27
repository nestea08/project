package com.company.model.dao;

import com.company.model.entities.Request;
import com.company.model.entities.interfaces.TrackedItem;
import com.company.model.entities.interfaces.Tracker;

import java.sql.SQLException;

public interface TransactionsDao extends AutoCloseable {
    void transformTrackedIntoHistoryItem(Tracker tracker, TrackedItem trackedItem) throws SQLException;
    void executeAdditionRequest(Request request) throws SQLException;
    void executeRemovingRequest(Request request) throws SQLException;
}
