package com.company.model.dao.mappers;

import com.company.model.entities.TrackerUser;
import com.company.model.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements GenericMapper<User> {
    @Override
    public User extractFromResultSet(ResultSet set) throws SQLException {
        int id = set.getInt("user_id");
        String nickname = set.getString("nickname");
        String login = set.getString("login");
        String password = set.getString("password");
        String role = set.getString("role");
        User.Role userRole = role.equals("USER") ? User.Role.USER : User.Role.ADMIN;
        return new User.Builder(nickname, login, password, userRole).id(id).build();
    }

    @Override
    public User makeUnique(Map<Integer, User> cache, User item) {
        cache.putIfAbsent(item.getId(), item);
        return cache.get(item.getId());
    }
}
