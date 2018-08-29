package com.company.model.dao.impl;

import com.company.model.dao.DaoFactory;
import com.company.model.dao.RequestsDao;
import com.company.model.dao.mappers.*;
import com.company.model.entities.*;

import java.sql.*;
import java.util.*;

public class JDBCRequestsDao implements RequestsDao {
    private Connection connection;
    private ResourceBundle bundle = DaoFactory.getBundle();

    JDBCRequestsDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Request item) {

        try (PreparedStatement statement = connection.prepareStatement
                (bundle.getString("request.create"))) {
            statement.setInt(1, item.getTracker().getId());
            statement.setInt(2, item.getActivity().getId());
            statement.setString(3, item.getType().toString());
            if (item.getType() == Request.RequestType.REMOVE) {
                statement.setInt(4, item.getActivity().getId());
            }
            else {
                statement.setNull(4, Types.INTEGER);
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Request findById(int id) {
        RequestMapper mapper = new RequestMapper();
        try (PreparedStatement statement = connection.prepareStatement
                (bundle.getString("request.findById"))) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
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
            ResultSet set = statement.executeQuery(bundle.getString("request.findAll"));
            while (set.next()) {
                Activity activity = activityMapper.extractFromResultSet(set);
                activity = activityMapper.makeUnique(activities, activity);
                TrackerUser user = trackerUserMapper.extractFromResultSet(set);
                user = trackerUserMapper.makeUnique(users, user);
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
                (bundle.getString("request.update"))) {
            statement.setString(1, item.getType().toString());
            statement.setInt(2, item.getTracker().getId());
            statement.setInt(3, item.getActivity().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(Request item) {
        try (PreparedStatement statement = connection.prepareStatement
                (bundle.getString("request.delete"))) {
            statement.setInt(1, item.getTracker().getId());
            statement.setInt(2, item.getActivity().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void executeAdditionRequest(Request request) throws SQLException {
        connection.setAutoCommit(false);
        addTrackedItem(request.getTracker().getId(), request.getActivity().getId());
        delete(request);
        connection.commit();
    }

    @Override
    public void executeRemovingRequest(Request request) throws SQLException {
        connection.setAutoCommit(false);
        removeTrackedItem(request.getTracker().getId(), request.getActivity().getId());
        delete(request);
        connection.commit();
    }

    private void addTrackedItem(int trackerId, int trackedItemId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement
                (bundle.getString("trackingItem.add"))) {
            statement.setInt(1, trackerId);
            statement.setInt(2, trackedItemId);
            statement.executeUpdate();
        }
    }

    private void removeTrackedItem(int trackerId, int trackedItemId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement
                (bundle.getString("trackingItem.remove"))) {
            statement.setInt(1, trackerId);
            statement.setInt(2, trackedItemId);
            statement.executeUpdate();
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
