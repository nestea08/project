package com.company.model.dao.impl;

import com.company.model.dao.ActivityDao;
import com.company.model.dao.DaoFactory;
import com.company.model.dao.mappers.*;
import com.company.model.dto.LocalizedActivityDto;
import com.company.model.entities.*;
import com.company.model.exceptions.NotUniqueActivityException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;

public class JDBCActivityDao implements ActivityDao {
    private Connection connection;
    private ResourceBundle bundle = DaoFactory.getBundle();
    private Logger logger = LogManager.getLogger(JDBCActivityDao.class);

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
            logger.error(e);
            throw new RuntimeException();
        }
    }

    @Override
    public void createLocalized(LocalizedActivityDto item) {
        try (PreparedStatement statement = connection.prepareStatement
                (bundle.getString("activity.createLocalized"))) {
            statement.setString(1, item.getEnTitle());
            statement.setString(2, item.getRuTitle());
            statement.setString(3, item.getEnDescription());
            statement.setString(4, item.getRuDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new NotUniqueActivityException(item);
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
            logger.error(e);
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
            logger.error(e);
            throw new RuntimeException();
        }
        return result;
    }

    @Override
    public List<Activity> findPossibleForTrackerActivities(int trackerId) {
        List<Activity> result = new ArrayList<>();
        ActivityMapper activityMapper = new ActivityMapper();
        try (PreparedStatement statement = connection.prepareStatement
                (bundle.getString("activity.findPossibleForTracker"))) {
            statement.setInt(1, trackerId);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                result.add(activityMapper.extractFromResultSet(set));
            }
        } catch (SQLException e) {
            logger.error(e);
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
            logger.error(e);
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
