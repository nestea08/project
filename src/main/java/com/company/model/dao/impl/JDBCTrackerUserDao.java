package com.company.model.dao.impl;

import com.company.model.dao.*;
import com.company.model.dao.mappers.*;
import com.company.model.entities.*;

import java.sql.*;
import java.util.*;

public class JDBCTrackerUserDao implements TrackerUserDao {
    private Connection connection;

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
        Map<Integer, User> users = new HashMap<>();
        ActivityMapper activityMapper = new ActivityMapper();
        UserMapper userMapper = new UserMapper();
        try (PreparedStatement statement = connection.prepareStatement
                ("select * from users left join user_activity using(user_id) left join activities using(activity_id) where user_id = ?")) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            TrackerUser trackerUser = null;
            while(set.next()) {
                trackerUser = (TrackerUser) userMapper.extractFromResultSet(set);
                trackerUser = (TrackerUser) userMapper.makeUnique(users, trackerUser);
                trackerUser.addActivity(activityMapper.extractFromResultSet(set));
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
            ResultSet set = statement.executeQuery("select * from users left join user_activity using(user_id) left join activities using(activity_id)");
            while (set.next()) {
                Activity activity = activityMapper.extractFromResultSet(set);
                activity = activityMapper.makeUnique(activities, activity);
                TrackerUser user = trackerUserMapper.extractFromResultSet(set);
                user = trackerUserMapper.makeUnique(users, user);
                user.addActivity(activity);
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
    public void updateSpentTime(TrackerUser trackerUser, Activity activity) {
        try (PreparedStatement statement = connection.prepareStatement
                ("update user_activity set spent_time = ? where user_id = ? & activity_id = ?")) {
            statement.setInt(1, trackerUser.getSpentTimeOnActivity(activity));
            statement.setInt(2, trackerUser.getId());
            statement.setInt(3, activity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void addActivityToTrackerUser(TrackerUser trackerUser, Activity activity) {
        try (PreparedStatement statement = connection.prepareStatement
                ("insert into user_activity (user_id, activity_id) values(?, ?)")) {
            statement.setInt(1, trackerUser.getId());
            statement.setInt(2, activity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void removeActivityFromTrackerUser(TrackerUser trackerUser, Activity activity) {
        try (PreparedStatement statement = connection.prepareStatement
                ("delete from user_activity where user_id = ? & activity_id = ?")) {
            statement.setInt(1, trackerUser.getId());
            statement.setInt(2, activity.getId());
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
