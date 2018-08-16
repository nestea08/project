package com.company.model.dao;

import com.company.model.dao.impl.JDBCDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory instance;

    public abstract UserDao createUserDao();
    public abstract TrackerUserDao createTrackerUserDao();
    public abstract ActivityDao createActivityDao();
    public abstract UserRequestDao createUserRequestDao();

    public static DaoFactory getInstance() {
        if (instance == null) {
            synchronized (DaoFactory.class) {
                if (instance == null) {
                    instance = new JDBCDaoFactory();
                }
            }
        }
        return instance;
    }
}
