package com.company.model.services.trackers;

import com.company.model.dao.*;
import com.company.model.dao.impl.JDBCDaoFactory;
import com.company.model.entities.*;
import com.company.model.entities.interfaces.Tracked;
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

    public void updateSpentTime(Tracker tracker, Tracked tracked) {
        try (TrackerUserDao dao = JDBCDaoFactory.getInstance().createTrackerUserDao()) {
            dao.updateSpentTime(tracker, tracked);
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
}
