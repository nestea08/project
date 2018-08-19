package com.company.model.dao.impl;

import com.company.model.dao.UserRequestDao;
import com.company.model.dao.mappers.*;
import com.company.model.entities.*;

import java.sql.*;
import java.util.*;

public class JDBCUserRequestDao implements UserRequestDao {
    private Connection connection;

    JDBCUserRequestDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Request item) {
        try (PreparedStatement statement = connection.prepareStatement
                ("insert into request (user_id, activity_id, command) values (?, ?, ?)")) {
            statement.setInt(1, item.getTracker().getId());
            statement.setInt(2, item.getTracked().getId());
            statement.setString(3, item.getType().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Request findById(int id) {
        RequestMapper mapper = new RequestMapper();
        try (PreparedStatement statement = connection.prepareStatement
                ("select * from request join users using(user_id) join activities using(activity_id) where request_id = ?")) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            return mapper.extractFromResultSet(set);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Request> findAll() {
        List<Request> result = new ArrayList<>();
        Map<Integer, TrackerUser> users = new HashMap<>();
        Map<Integer, Activity> activities = new HashMap<>();
        ActivityMapper activityMapper = new ActivityMapper();
        TrackerUserMapper trackerUserMapper = new TrackerUserMapper();
        RequestMapper requestMapper = new RequestMapper();
        try (Statement statement = connection.createStatement()) {
            ResultSet set = statement.executeQuery("select * from request join users using(user_id) join activities using(activity_id)");
            while (set.next()) {
                Activity activity = activityMapper.extractFromResultSet(set);
                activity = activityMapper.makeUnique(activities, activity);
                TrackerUser user = trackerUserMapper.extractFromResultSet(set);
                user = trackerUserMapper.makeUnique(users, user);
                user.addTracked(activity);
                result.add(requestMapper.extractWithSpecifiedReferences(set, user, activity));
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return result;
    }

    @Override
    public void update(Request item) {
        try (PreparedStatement statement = connection.prepareStatement
                ("update request set command = ? where user_id = ? & activity_id = ?")) {
            statement.setString(1, item.getType().toString());
            statement.setInt(2, item.getTracker().getId());
            statement.setInt(3, item.getTracked().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(Request item) {
        try (PreparedStatement statement = connection.prepareStatement
                ("delete from request where user_id = ? & activity_id = ?")) {
            statement.setInt(1, item.getTracker().getId());
            statement.setInt(2, item.getTracked().getId());
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
