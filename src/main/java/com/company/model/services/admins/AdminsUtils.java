package com.company.model.services.admins;

import com.company.model.dao.*;
import com.company.model.dao.impl.JDBCDaoFactory;
import com.company.model.entities.Activity;
import com.company.model.entities.HistoryItem;
import com.company.model.entities.Request;
import com.company.model.entities.interfaces.TrackedItem;
import com.company.model.entities.interfaces.Tracker;

import java.util.List;

public class AdminsUtils {

    public void executeAdditionRequest(Request request) {
        try (TransactionsDao dao = JDBCDaoFactory.getInstance().createTransactionsDao()) {
            dao.executeAdditionRequest(request);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void executeRemovingRequest(Request request) {
        try (TransactionsDao dao = JDBCDaoFactory.getInstance().createTransactionsDao()) {
            dao.executeRemovingRequest(request);
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

    public List<Request> getAllUserRequests() {
        try (UserRequestDao dao = JDBCDaoFactory.getInstance().createUserRequestDao()) {
            return dao.findAll();
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
