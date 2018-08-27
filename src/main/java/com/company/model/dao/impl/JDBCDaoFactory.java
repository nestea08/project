package com.company.model.dao.impl;

import com.company.model.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public TrackerUserDao createTrackerUserDao() {
        return new JDBCTrackerUserDao(getConnection());
    }

    @Override
    public ActivityDao createActivityDao() {
        return new JDBCActivityDao(getConnection());
    }

    @Override
    public UserRequestDao createUserRequestDao() {
        return new JDBCUserRequestDao(getConnection());
    }

    @Override
    public HistoryItemDao createHistoryItemDao() {
        return new JDBCHistoryItemDao(getConnection());
    }

    @Override
    public TransactionsDao createTransactionsDao() {
        return new JDBCTransactionsDao(getConnection());
    }

    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
