package com.company.model.dao.impl;

import com.company.model.dao.DaoFactory;
import com.company.model.dao.TransactionsDao;
import com.company.model.entities.HistoryItem;
import com.company.model.entities.Request;
import com.company.model.entities.interfaces.TrackedItem;
import com.company.model.entities.interfaces.Tracker;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class JDBCTransactionsDao implements TransactionsDao {
    private Connection connection;
    private ResourceBundle bundle = DaoFactory.getBundle();

    JDBCTransactionsDao(Connection connection) {
        this.connection = connection;
    }

    public void transformTrackedIntoHistoryItem(Tracker tracker, TrackedItem trackedItem)
            throws SQLException {
        connection.setAutoCommit(false);
        removeTrackedItem(tracker.getId(), trackedItem.getId());
        HistoryItem historyItem = new HistoryItem(trackedItem.getTitle(),
                tracker, trackedItem.getSpentTime(), LocalDate.now());
        createHistoryItem(historyItem);
        connection.commit();

    }

    public void executeAdditionRequest(Request request) throws SQLException {
        connection.setAutoCommit(false);
        addTrackedItem(request.getTracker().getId(), request.getActivity().getId());
        deleteRequest(request);
        connection.commit();
    }

    public void executeRemovingRequest(Request request) throws SQLException {
        connection.setAutoCommit(false);
        removeTrackedItem(request.getTracker().getId(), request.getActivity().getId());
        deleteRequest(request);
        connection.commit();
    }


    private void addTrackedItem(int trackerId, int trckedItemId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement
                (bundle.getString("transactions.addTracker"))) {
            statement.setInt(1, trackerId);
            statement.setInt(2, trckedItemId);
            statement.executeUpdate();
        }
    }

    private void removeTrackedItem(int trackerId, int trckedItemId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement
                (bundle.getString("transactions.removeTracker"))) {
            statement.setInt(1, trackerId);
            statement.setInt(2, trckedItemId);
            statement.executeUpdate();
        }
    }

    private void deleteRequest(Request request) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement
                (bundle.getString("request.delete"))) {
            statement.setInt(1, request.getTracker().getId());
            statement.setInt(2, request.getActivity().getId());
            statement.executeUpdate();
        }
    }

    private void createHistoryItem(HistoryItem historyItem) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement
                (bundle.getString("histItem.create"))) {
            statement.setString(1, historyItem.getTitle());
            statement.setInt(2, historyItem.getSpentTime());
            statement.setDate(3, Date.valueOf(historyItem.getEndDate()));
            statement.setInt(4, historyItem.getTracker().getId());
            statement.executeUpdate();
        }
    }


    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
