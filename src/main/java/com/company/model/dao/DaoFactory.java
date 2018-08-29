package com.company.model.dao;

import com.company.model.dao.impl.JDBCDaoFactory;

import java.util.Locale;
import java.util.ResourceBundle;

public abstract class DaoFactory {
    private static DaoFactory instance;
    private static ResourceBundle bundle = ResourceBundle.getBundle("dao");

    public abstract UserDao createUserDao();
    public abstract TrackerUserDao createTrackerUserDao();
    public abstract ActivityDao createActivityDao();
    public abstract RequestsDao createUserRequestDao();
    public abstract HistoryItemDao createHistoryItemDao();
    public abstract TransactionsDao createTransactionsDao();

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

    public static ResourceBundle getBundle() {
        return bundle;
    }

    public static void changeBundleLocale(Locale locale) {
        bundle = ResourceBundle.getBundle("dao", locale);
    }
}
