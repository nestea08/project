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
                    ds.setUrl(bundle.getString("connection.url"));
                    ds.setUsername(bundle.getString("connection.username"));
                    ds.setPassword(bundle.getString("connection.password"));
                    ds.setMinIdle(Integer.parseInt(bundle.getString("connection.minIdle")));
                    ds.setMaxIdle(Integer.parseInt(bundle.getString("connection.maxIdle")));
                    ds.setMaxOpenPreparedStatements(Integer.parseInt(bundle.getString("connection.maxOpenPS")));
                    ds.setDriverClassName(bundle.getString("connection.driver"));
                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }
}