package com.company.model.dao.impl;

import com.company.model.dao.*;
import com.company.model.dao.mappers.*;
import com.company.model.entities.*;
import com.company.model.entities.interfaces.TrackedItem;
import com.company.model.entities.interfaces.Tracker;
import com.company.model.exceptions.InvalidSpentTimeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;

public class JDBCTrackerUserDao implements TrackerUserDao {
    private Connection connection;
    private ResourceBundle bundle = DaoFactory.getBundle();
    private Logger logger = LogManager.getLogger(JDBCTrackerUserDao.class);

    JDBCTrackerUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(TrackerUser item) {
        try (UserDao dao = JDBCDaoFactory.getInstance().createUserDao()){
            dao.create(item);
        } catch (Exception e) {
            logger.error(e);
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
                if (activity.getId() != 0) {
                    int spentTime = set.getInt("spent_time");
                    trackerUser.addTrackedItem(new TimeTrackedItem(activity, spentTime));
                }
            }
            return trackerUser;
        } catch (SQLException | InvalidSpentTimeException e) {
            logger.error(e);
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
                user.addTrackedItem(new TimeTrackedItem(activity, spentTime));
                result.add(user);
            }
        } catch (SQLException | InvalidSpentTimeException e) {
            logger.error(e);
            throw new RuntimeException();
        }
        return result;
    }

    @Override
    public void update(TrackerUser item) {
        try (UserDao dao = JDBCDaoFactory.getInstance().createUserDao()){
            dao.update(item);
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(TrackerUser item) {
        try (UserDao dao = JDBCDaoFactory.getInstance().createUserDao()){
            dao.delete(item);
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException();
        }
    }

    @Override
    public void updateSpentTime(Tracker tracker, TrackedItem trackedItem) {
        try (PreparedStatement statement = connection.prepareStatement
                (bundle.getString("tracker.updateTime"))) {
            statement.setInt(1, trackedItem.getSpentTime());
            statement.setInt(2, tracker.getId());
            statement.setInt(3, trackedItem.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException();
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException();
        }
    }
}
