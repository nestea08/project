package com.company.model.dao.impl;

import com.company.model.dao.ActivityDao;
import com.company.model.dao.mappers.*;
import com.company.model.entities.*;

import java.sql.*;
import java.util.*;

public class JDBCActivityDao implements ActivityDao {
    private Connection connection;

    JDBCActivityDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Activity item) {
        try (PreparedStatement statement = connection.prepareStatement
                ("insert into activities (title, description) values (?, ?)")) {
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
                ("select * from activities left join user_activity using(activity_id) left join users using(user_id) where activity_id = ?")) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            Activity activity = null;
            while(set.next()) {
                activity = activityMapper.extractFromResultSet(set);
                activity = activityMapper.makeUnique(activities, activity);
                activity.addTrackerUser(trackerUserMapper.extractFromResultSet(set));
            }
            return activity;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Activity> findAll() {
        List<Activity> result = new ArrayList<>();
        Map<Integer, TrackerUser> users = new HashMap<>();
        Map<Integer, Activity> activities = new HashMap<>();
        ActivityMapper activityMapper = new ActivityMapper();
        TrackerUserMapper trackerUserMapper = new TrackerUserMapper();
        try (Statement statement = connection.createStatement()) {
            ResultSet set = statement.executeQuery("select * from activities left join user_activity using(activity_id) left join users using(user_id)");
            while (set.next()) {
                Activity activity = activityMapper.extractFromResultSet(set);
                activity = activityMapper.makeUnique(activities, activity);
                TrackerUser user = trackerUserMapper.extractFromResultSet(set);
                user = trackerUserMapper.makeUnique(users, user);
                activity.addTrackerUser(user);
                result.add(activity);
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return result;
    }

    @Override
    public void update(Activity item) {
        try (PreparedStatement statement = connection.prepareStatement
                ("update activities set title = ?, description = ? where activity_id = ?")) {
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
                ("delete from activities where activity_id = ?")) {
            statement.setInt(1, item.getId());
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
