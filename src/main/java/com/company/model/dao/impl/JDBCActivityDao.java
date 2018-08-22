package com.company.model.dao.impl;

import com.company.model.dao.ActivityDao;
import com.company.model.dao.DaoFactory;
import com.company.model.dao.mappers.*;
import com.company.model.entities.*;
import com.company.model.entities.interfaces.Tracker;

import java.sql.*;
import java.util.*;

public class JDBCActivityDao implements ActivityDao {
    private Connection connection;
    private ResourceBundle bundle = DaoFactory.getBundle();

    JDBCActivityDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Activity item) {
        try (PreparedStatement statement = connection.prepareStatement
                (bundle.getString("activity.create"))) {
            statement.setString(1, item.getTitle());
            statement.setString(2, item.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Activity findById(int id) {
        Map<Integer, Activity> activities = new HashMap<>();
        ActivityMapper activityMapper = new ActivityMapper();
        TrackerUserMapper trackerUserMapper = new TrackerUserMapper();
        try (PreparedStatement statement = connection.prepareStatement
                (bundle.getString("activity.findById"))) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
            return activityMapper.extractFromResultSet(set);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Activity> findAll() {
        List<Activity> result = new ArrayList<>();
        ActivityMapper activityMapper = new ActivityMapper();
        try (Statement statement = connection.createStatement()) {
            ResultSet set = statement.executeQuery(bundle.getString("activity.findAll"));
            while (set.next()) {
                result.add(activityMapper.extractFromResultSet(set));
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return result;
    }

    @Override
    public void update(Activity item) {
        try (PreparedStatement statement = connection.prepareStatement
                (bundle.getString("activity.update"))) {
            statement.setString(1, item.getTitle());
            statement.setString(2, item.getDescription());
            statement.setInt(3, item.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(Activity item) {
        try (PreparedStatement statement = connection.prepareStatement
                (bundle.getString("activity.delete"))) {
            statement.setInt(1, item.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void addActivityToTracker(Tracker tracker, Activity activity) {
        try (PreparedStatement statement = connection.prepareStatement
                (bundle.getString("activity.addTracker"))) {
            statement.setInt(1, tracker.getId());
            statement.setInt(2, activity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void removeActivityFromTracker(Tracker tracker, Activity activity) {
        try (PreparedStatement statement = connection.prepareStatement
                (bundle.getString("activity.removeTracker"))) {
            statement.setInt(1, tracker.getId());
            statement.setInt(2, activity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
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
