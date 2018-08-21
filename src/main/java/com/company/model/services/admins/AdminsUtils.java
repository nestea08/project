package com.company.model.services.admins;

import com.company.model.dao.DaoFactory;
import com.company.model.dao.HistoryItemDao;
import com.company.model.dao.TrackerUserDao;
import com.company.model.dao.UserRequestDao;
import com.company.model.dao.impl.JDBCDaoFactory;
import com.company.model.entities.HistoryItem;
import com.company.model.entities.Request;
import com.company.model.entities.interfaces.Tracked;
import com.company.model.entities.interfaces.Tracker;

import java.util.List;

public class AdminsUtils {

    public void addTrackedToTracker(Tracker tracker, Tracked tracked) {
        try (TrackerUserDao dao = JDBCDaoFactory.getInstance().createTrackerUserDao()) {
            dao.addTrackedToTracker(tracker, tracked);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void removeTrackedFromTracker(Tracker tracker, Tracked tracked) {
        try (TrackerUserDao dao = JDBCDaoFactory.getInstance().createTrackerUserDao()) {
            dao.removeTrackedFromTracker(tracker, tracked);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public Request getUserRequestById(int id) {
        try (UserRequestDao dao = JDBCDaoFactory.getInstance().createUserRequestDao()) {
            return dao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public List<HistoryItem> getAllHistoryItems() {
        try (HistoryItemDao dao = JDBCDaoFactory.getInstance().createHistoryItemDao()) {
            return dao.findAll();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void deleteUserRequest(Request request) {
        try (UserRequestDao dao = JDBCDaoFactory.getInstance().createUserRequestDao()) {
            dao.delete(request);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
