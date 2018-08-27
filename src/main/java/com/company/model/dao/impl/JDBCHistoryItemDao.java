package com.company.model.dao.impl;

import com.company.model.dao.DaoFactory;
import com.company.model.dao.HistoryItemDao;
import com.company.model.dao.mappers.HistoryItemMapper;
import com.company.model.dao.mappers.TrackerUserMapper;
import com.company.model.entities.HistoryItem;
import com.company.model.entities.TrackerUser;
import com.company.model.entities.interfaces.Tracker;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class JDBCHistoryItemDao implements HistoryItemDao {
    private Connection connection;
    private ResourceBundle bundle = DaoFactory.getBundle();

    JDBCHistoryItemDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(HistoryItem item) {
        throw new RuntimeException();
        /*try (PreparedStatement statement = connection.prepareStatement
                (bundle.getString("histItem.create"))) {
            statement.setString(1, item.getTitle());
            statement.setInt(2, item.getSpentTime());
            statement.setDate(3, Date.valueOf(item.getEndDate()));
            statement.setInt(4, item.getTracker().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }*/
    }

    @Override
    public HistoryItem findById(int id) {
        HistoryItemMapper historyItemMapper = new HistoryItemMapper();
        try (PreparedStatement statement = connection.prepareStatement
                (bundle.getString("histItem.findById"))) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
            return historyItemMapper.extractFromResultSet(set);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<HistoryItem> findAll() {
        List<HistoryItem> result = new ArrayList<>();
        Map<Integer, TrackerUser> trackers = new HashMap<>();
        HistoryItemMapper historyItemMapper = new HistoryItemMapper();
        TrackerUserMapper trackerUserMapper = new TrackerUserMapper();
        try (Statement statement = connection.createStatement()) {
            ResultSet set = statement.executeQuery(bundle.getString("histItem.findAll"));
            while (set.next()) {
                TrackerUser tracker = trackerUserMapper.extractFromResultSet(set);
                tracker = trackerUserMapper.makeUnique(trackers, tracker);
                result.add(historyItemMapper.extractWithSpecifiedReferences(set, tracker));
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return result;
    }

    @Override
    public void update(HistoryItem item) {
        try (PreparedStatement statement = connection.prepareStatement
                (bundle.getString("histItem.update"))) {
            statement.setString(1, item.getTitle());
            statement.setInt(2, item.getSpentTime());
            statement.setDate(3, Date.valueOf(item.getEndDate()));
            statement.setInt(4, item.getTracker().getId());
            statement.setInt(5, item.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(HistoryItem item) {
        try (PreparedStatement statement = connection.prepareStatement
                (bundle.getString("histItem.delete"))) {
            statement.setInt(1, item.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void close() throws Exception {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
