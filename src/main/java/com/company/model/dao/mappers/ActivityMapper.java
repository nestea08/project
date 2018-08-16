package com.company.model.dao.mappers;

import com.company.model.entities.Activity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ActivityMapper implements GenericMapper<Activity>{
    @Override
    public Activity extractFromResultSet(ResultSet set) throws SQLException {
        int id = set.getInt("activity_id");
        String title = set.getString("title");
        String description = set.getString("description");
        return new Activity(id, title, description);
    }

    @Override
    public Activity makeUnique(Map<Integer, Activity> cache, Activity item) {
        cache.putIfAbsent(item.getId(), item);
        return cache.get(item.getId());
    }
}
