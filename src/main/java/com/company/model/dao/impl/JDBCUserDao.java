package com.company.model.dao.impl;

import com.company.model.dao.UserDao;
import com.company.model.dao.mappers.UserMapper;
import com.company.model.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserDao implements UserDao {
    private Connection connection;

    JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User item) {
        try (PreparedStatement statement = connection.prepareStatement
                ("insert into users (nickname, login, password, role) values (?, ?, ?, ?)")) {
            statement.setString(1, item.getNickname());
            statement.setString(2, item.getLogin());
            statement.setString(3, item.getPassword());
            statement.setString(4, item.getRole().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public User findById(int id) {
        UserMapper userMapper = new UserMapper();
        try (PreparedStatement statement = connection.prepareStatement
                ("select * from users where user_id = ?")) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            return userMapper.extractFromResultSet(set);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        UserMapper userMapper = new UserMapper();
        try (Statement statement = connection.createStatement()) {
            ResultSet set = statement.executeQuery("select * from users");
            while (set.next()) {
                result.add(userMapper.extractFromResultSet(set));
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return result;
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) {
        UserMapper userMapper = new UserMapper();
        try (PreparedStatement statement = connection.prepareStatement
                ("select * from users where login = ? & password = ?")) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet set = statement.executeQuery();
            return userMapper.extractFromResultSet(set);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void update(User item) {
        try (PreparedStatement statement = connection.prepareStatement
                ("update users set nickname = ?, login = ?, password = ? where user_id = ?")) {
            statement.setString(1, item.getNickname());
            statement.setString(2, item.getLogin());
            statement.setString(3, item.getPassword());
            statement.setInt(4, item.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(User item) {
        try (PreparedStatement statement = connection.prepareStatement
                ("delete from users where user_id = ?")) {
            statement.setInt(1, item.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

}
