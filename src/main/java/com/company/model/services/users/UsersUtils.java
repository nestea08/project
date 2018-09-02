package com.company.model.services.users;

import com.company.model.dao.*;
import com.company.model.dao.impl.JDBCDaoFactory;
import com.company.model.entities.*;
import com.company.model.entities.interfaces.TrackedItem;
import com.company.model.entities.interfaces.Tracker;
import com.company.model.exceptions.DuplicateRequestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UsersUtils {

    public TrackerUser getTrackerUserById(int id) {
        try (TrackerUserDao dao = JDBCDaoFactory.getInstance().createTrackerUserDao()) {
            return dao.findById(id);
        }
    }

    public Activity getActivityById(int id) {
        try (ActivityDao dao = JDBCDaoFactory.getInstance().createActivityDao()) {
            return dao.findById(id);
        }
    }

    public List<Activity> getPossibleActivities(int trackerId) {
        try (ActivityDao dao = JDBCDaoFactory.getInstance().createActivityDao()) {
            return dao.findPossibleForTrackerActivities(trackerId);
        }
    }

    public void updateSpentTime(Tracker tracker, TrackedItem trackedItem) {
        try (TrackerUserDao dao = JDBCDaoFactory.getInstance().createTrackerUserDao()) {
            dao.updateSpentTime(tracker, trackedItem);
        }
    }

    public void createUserRequest(Request request) {
        try (RequestsDao dao = JDBCDaoFactory.getInstance().createRequestDao()) {
            dao.create(request);
        }
    }

    public void transformTrackedIntoHistoryItem(Tracker tracker, TrackedItem trackedItem) {
        try (HistoryItemDao dao = JDBCDaoFactory.getInstance().createHistoryItemDao()) {
            dao.transformTrackedIntoHistoryItem(tracker, trackedItem);
        }
    }

    public void createHistoryItem(HistoryItem item) {
        try (HistoryItemDao dao = JDBCDaoFactory.getInstance().createHistoryItemDao()) {
            dao.create(item);
        }
    }
}
