package com.company.model.dao.mappers;

import com.company.model.entities.Activity;
import com.company.model.entities.TrackerUser;
import com.company.model.entities.UserRequest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class RequestMapper implements GenericMapper<UserRequest>{

    @Override
    public UserRequest extractFromResultSet(ResultSet set) throws SQLException {
        return extractWithSpecifiedReferences(set, getTrackerUser(set), getActivity(set));
    }

    private TrackerUser getTrackerUser(ResultSet set) throws SQLException {
        TrackerUserMapper mapper = new TrackerUserMapper();
        return mapper.extractFromResultSet(set);
    }

    private Activity getActivity(ResultSet set) throws SQLException {
        ActivityMapper mapper = new ActivityMapper();
        return mapper.extractFromResultSet(set);
    }

    public UserRequest extractWithSpecifiedReferences(ResultSet set, TrackerUser trackerUser, Activity activity)
            throws SQLException {
        int id = set.getInt("request_id");
        String type = set.getString("command");
        UserRequest.RequestType requestType = type.equals("ADD") ?
                UserRequest.RequestType.ADD : UserRequest.RequestType.REMOVE;
        return new UserRequest(id, trackerUser, activity, requestType);
    }

    @Override
    public UserRequest makeUnique(Map<Integer, UserRequest> cache, UserRequest item) {
        cache.putIfAbsent(item.getId(), item);
        return cache.get(item.getId());
    }
}
