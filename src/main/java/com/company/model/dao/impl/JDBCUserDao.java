package com.company.model.dao.impl;

import com.company.model.dao.DaoFactory;
import com.company.model.dao.UserDao;
import com.company.model.dao.mappers.UserMapper;
import com.company.model.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class JDBCUserDao implements UserDao {
    private Connection connection;
    private ResourceBundle bundle = DaoFactory.getBundle();

    JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User item) {
        try (PreparedStatement statement = connection.prepareStatement
                (bundle.getString("user.create"))) {
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
                (bundle.getString("user.findById"))) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
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
            ResultSet set = statement.executeQuery(bundle.getString("user.findAll"));
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
                (bundle.getString("user.findByLogAndPas"))) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet set = statement.executeQuery();
            if (!set.next()) {
                return null;
            }
            return userMapper.extractFromResultSet(set);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void update(User item) {
        try (PreparedStatement statement = connection.prepareStatement
                (bundle.getString("user.update"))) {
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
                (bundle.getString("user.delete"))) {
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
