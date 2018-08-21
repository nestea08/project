package com.company.model.dao.impl;

import com.company.model.dao.*;
import com.company.model.dao.mappers.*;
import com.company.model.entities.*;
import com.company.model.entities.interfaces.Tracked;
import com.company.model.entities.interfaces.Tracker;

import java.sql.*;
import java.util.*;

public class JDBCTrackerUserDao implements TrackerUserDao {
    private Connection connection;
    private ResourceBundle bundle = DaoFactory.getBundle();

    JDBCTrackerUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(TrackerUser item) {
        try (UserDao dao = JDBCDaoFactory.getInstance().createUserDao()){
            dao.create(item);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public TrackerUser findById(int id) {
        Map<Integer, TrackerUser> users = new HashMap<>();
        ActivityMapper activityMapper = new ActivityMapper();
        TrackerUserMapper trackerUserMapper = new TrackerUserMapper();
        try (PreparedStatement statement = connection.prepareStatement
                (bundle.getString("tracker.findById"))) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            TrackerUser trackerUser = null;
            while(set.next()) {
                trackerUser = trackerUserMapper.extractFromResultSet(set);
                trackerUser = trackerUserMapper.makeUnique(users, trackerUser);
                Activity activity = activityMapper.extractFromResultSet(set);
                trackerUser.addTracked(activity);
                int spentTime = set.getInt("spent_time");
                trackerUser.setSpentTime(activity, spentTime);
            }
            return trackerUser;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<TrackerUser> findAll() {
        List<TrackerUser> result = new ArrayList<>();
        Map<Integer, TrackerUser> users = new HashMap<>();
        Map<Integer, Activity> activities = new HashMap<>();
        ActivityMapper activityMapper = new ActivityMapper();
        TrackerUserMapper trackerUserMapper = new TrackerUserMapper();
        try (Statement statement = connection.createStatement()) {
            ResultSet set = statement.executeQuery(bundle.getString("tracker.findAll"));
            while (set.next()) {
                Activity activity = activityMapper.extractFromResultSet(set);
                activity = activityMapper.makeUnique(activities, activity);
                TrackerUser user = trackerUserMapper.extractFromResultSet(set);
                user = trackerUserMapper.makeUnique(users, user);
                int spentTime = set.getInt("spent_time");
                user.setSpentTime(activity, spentTime);
                result.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return result;
    }

    @Override
    public void update(TrackerUser item) {
        try (UserDao dao = JDBCDaoFactory.getInstance().createUserDao()){
            dao.update(item);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(TrackerUser item) {
        try (UserDao dao = JDBCDaoFactory.getInstance().createUserDao()){
            dao.delete(item);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void updateSpentTime(Tracker tracker, Tracked tracked) {
        try (PreparedStatement statement = connection.prepareStatement
                (bundle.getString("tracker.updateTime"))) {
            statement.setInt(1, tracker.getSpentTime(tracked));
            statement.setInt(2, tracker.getId());
            statement.setInt(3, tracked.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void addTrackedToTracker(Tracker tracker, Tracked tracked) {
        try (PreparedStatement statement = connection.prepareStatement
                (bundle.getString("tracker.addTracked"))) {
            statement.setInt(1, tracker.getId());
            statement.setInt(2, tracked.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void removeTrackedFromTracker(Tracker tracker, Tracked tracked) {
        try (PreparedStatement statement = connection.prepareStatement
                (bundle.getString("tracker.removeTracked"))) {
            statement.setInt(1, tracker.getId());
            statement.setInt(2, tracked.getId());
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
