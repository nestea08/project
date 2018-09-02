package com.company.model.services.admins;

import com.company.model.dao.*;
import com.company.model.dao.impl.JDBCDaoFactory;
import com.company.model.dto.LocalizedActivityDto;
import com.company.model.entities.Activity;
import com.company.model.entities.HistoryItem;
import com.company.model.entities.Request;
import com.company.model.exceptions.NotUniqueActivityException;
import com.company.model.exceptions.UnknownRequestException;

import java.util.List;

public class AdminsUtils {

    public void executeAdditionRequest(Request request) {
        try (RequestsDao dao = JDBCDaoFactory.getInstance().createRequestDao()) {
            dao.executeAdditionRequest(request);
        }
    }

    public void executeRemovingRequest(Request request) {
        try (RequestsDao dao = JDBCDaoFactory.getInstance().createRequestDao()) {
            dao.executeRemovingRequest(request);
        }
    }

    public void createActivity(LocalizedActivityDto activity) {
        try (ActivityDao dao = JDBCDaoFactory.getInstance().createActivityDao()) {
            dao.createLocalized(activity);
        }
    }

    public Request getUserRequestById(int id) {
        try (RequestsDao dao = JDBCDaoFactory.getInstance().createRequestDao()) {
            return dao.findById(id);
        }
    }

    public List<Request> getAllUserRequests() {
        try (RequestsDao dao = JDBCDaoFactory.getInstance().createRequestDao()) {
            return dao.findAll();
        }
    }

    public List<HistoryItem> getAllHistoryItems() {
        try (HistoryItemDao dao = JDBCDaoFactory.getInstance().createHistoryItemDao()) {
            return dao.findAll();
        }
    }

    public void deleteUserRequest(Request request) {
        try (RequestsDao dao = JDBCDaoFactory.getInstance().createRequestDao()) {
            dao.delete(request);
        }
    }
}
