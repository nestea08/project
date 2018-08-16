package com.company.model.services;

import com.company.model.dao.*;
import com.company.model.dao.impl.JDBCDaoFactory;
import com.company.model.entities.*;

import java.util.List;

class ServiceUtils {
    static void addActivityToTrackerUser(TrackerUser trackerUser, Activity activity) {
        try (TrackerUserDao dao = JDBCDaoFactory.getInstance().createTrackerUserDao()) {
            dao.addActivityToTrackerUser(trackerUser, activity);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    static void removeActivityFromTrackerUser(TrackerUser trackerUser, Activity activity) {
        try (TrackerUserDao dao = JDBCDaoFactory.getInstance().createTrackerUserDao()) {
            dao.removeActivityFromTrackerUser(trackerUser, activity);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    static void createUserRequest(UserRequest request) {
        try (UserRequestDao dao = JDBCDaoFactory.getInstance().createUserRequestDao()) {
            dao.create(request);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    static void createUser(User user) {
        try (UserDao dao = JDBCDaoFactory.getInstance().createUserDao()) {
            dao.create(user);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    static User getUserByLoginAndPassword(String login, String password) {
        try (UserDao dao = JDBCDaoFactory.getInstance().createUserDao()) {
            return dao.findUserByLoginAndPassword(login, password);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    static List<User> getAllUsers() {
        try (UserDao dao = JDBCDaoFactory.getInstance().createUserDao()) {
            return dao.findAll();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    static TrackerUser getTrackerUserById(int id) {
        try (TrackerUserDao dao = JDBCDaoFactory.getInstance().createTrackerUserDao()) {
            return dao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    static Activity getActivityById(int id) {
        try (ActivityDao dao = JDBCDaoFactory.getInstance().createActivityDao()) {
            return dao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    static UserRequest getUserRequestById(int id) {
        try (UserRequestDao dao = JDBCDaoFactory.getInstance().createUserRequestDao()) {
            return dao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    static void updateSpentTime(TrackerUser trackerUser, Activity activity) {
        try (TrackerUserDao dao = JDBCDaoFactory.getInstance().createTrackerUserDao()) {
            dao.updateSpentTime(trackerUser, activity);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    static void deleteUserRequest(UserRequest request) {
        try (UserRequestDao dao = JDBCDaoFactory.getInstance().createUserRequestDao()) {
            dao.delete(request);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }


}
