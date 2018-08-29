package com.company.model.services.users;

import com.company.model.dao.*;
import com.company.model.dao.impl.JDBCDaoFactory;
import com.company.model.entities.*;
import com.company.model.entities.interfaces.TrackedItem;
import com.company.model.entities.interfaces.Tracker;
import com.company.model.exceptions.DuplicateRequestException;

import java.util.List;

public class UsersUtils {

    public TrackerUser getTrackerUserById(int id) {
        try (TrackerUserDao dao = JDBCDaoFactory.getInstance().createTrackerUserDao()) {
            return dao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public Activity getActivityById(int id) {
        try (ActivityDao dao = JDBCDaoFactory.getInstance().createActivityDao()) {
            return dao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public List<Activity> getPossibleActivities(int trackerId) {
        try (ActivityDao dao = JDBCDaoFactory.getInstance().createActivityDao()) {
            return dao.findPossibleForTrackerActivities(trackerId);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void updateSpentTime(Tracker tracker, TrackedItem trackedItem) {
        try (TrackerUserDao dao = JDBCDaoFactory.getInstance().createTrackerUserDao()) {
            dao.updateSpentTime(tracker, trackedItem);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void createUserRequest(Request request) throws DuplicateRequestException {
        try (RequestsDao dao = JDBCDaoFactory.getInstance().createRequestDao()) {
            dao.create(request);
        } catch (Exception e) {
            throw new DuplicateRequestException();
        }
    }

    public void transformTrackedIntoHistoryItem(Tracker tracker, TrackedItem trackedItem) {
        try (HistoryItemDao dao = JDBCDaoFactory.getInstance().createHistoryItemDao()) {
            dao.transformTrackedIntoHistoryItem(tracker, trackedItem);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void createHistoryItem(HistoryItem item) {
        try (HistoryItemDao dao = JDBCDaoFactory.getInstance().createHistoryItemDao()) {
            dao.create(item);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
