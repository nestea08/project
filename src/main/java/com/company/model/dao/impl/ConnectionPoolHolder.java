package com.company.model.dao.impl;

import com.company.model.dao.DaoFactory;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.tomcat.jdbc.pool.DataSourceFactory;

import javax.sql.DataSource;
import java.util.ResourceBundle;

class ConnectionPoolHolder {
    private static volatile DataSource dataSource;

    static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    ResourceBundle bundle = DaoFactory.getBundle();
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl("jdbc:mysql://localhost:3306/projectdb?serverTimezone=UTC");
                    ds.setUsername("root");
                    ds.setPassword("1111");
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
                    ds.setDriverClassName("com.mysql.jdbc.Driver");
                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }
}