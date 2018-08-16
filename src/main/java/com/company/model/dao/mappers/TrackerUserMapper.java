package com.company.model.dao.mappers;

import com.company.model.entities.TrackerUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class TrackerUserMapper implements GenericMapper<TrackerUser> {
    @Override
    public TrackerUser extractFromResultSet(ResultSet set) throws SQLException {
        int id = set.getInt("user_id");
        String nickname = set.getString("nickname");
        String login = set.getString("login");
        String password = set.getString("password");
        return new TrackerUser.Builder(nickname, login, password).id(id).build();
    }

    @Override
    public TrackerUser makeUnique(Map<Integer, TrackerUser> cache, TrackerUser item) {
        cache.putIfAbsent(item.getId(), item);
        return cache.get(item.getId());
    }
}
