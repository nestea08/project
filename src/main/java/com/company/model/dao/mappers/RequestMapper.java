package com.company.model.dao.mappers;

import com.company.model.entities.Activity;
import com.company.model.entities.TrackerUser;
import com.company.model.entities.Request;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class RequestMapper implements GenericMapper<Request>{

    @Override
    public Request extractFromResultSet(ResultSet set) throws SQLException {
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

    public Request extractWithSpecifiedReferences(ResultSet set, TrackerUser trackerUser, Activity activity)
            throws SQLException {
        int id = set.getInt("request_id");
        String type = set.getString("type");
        Request.RequestType requestType = type.equals("ADD") ?
                Request.RequestType.ADD : Request.RequestType.REMOVE;
        return new Request(id, trackerUser, activity, requestType);
    }

    @Override
    public Request makeUnique(Map<Integer, Request> cache, Request item) {
        cache.putIfAbsent(item.getId(), item);
        return cache.get(item.getId());
    }
}
