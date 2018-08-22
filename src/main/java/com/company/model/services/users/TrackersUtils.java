package com.company.model.services.users;

import com.company.model.dao.*;
import com.company.model.dao.impl.JDBCDaoFactory;
import com.company.model.entities.*;
import com.company.model.entities.interfaces.TrackedItem;
import com.company.model.entities.interfaces.Tracker;

public class TrackersUtils {

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

    public void updateSpentTime(Tracker tracker, TrackedItem trackedItem) {
        try (TrackerUserDao dao = JDBCDaoFactory.getInstance().createTrackerUserDao()) {
            dao.updateSpentTime(tracker, trackedItem);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void createUserRequest(Request request) {
        try (UserRequestDao dao = JDBCDaoFactory.getInstance().createUserRequestDao()) {
            dao.create(request);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void removeTrackedFromTracker(Tracker tracker, TrackedItem trackedItem) {
        try (ActivityDao dao = JDBCDaoFactory.getInstance().createActivityDao()) {
            dao.removeActivityFromTracker(tracker,
                    new Activity(trackedItem.getId(), trackedItem.getTitle(), trackedItem.getDescription()));
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
