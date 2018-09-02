package com.company.model.dao;

import com.company.model.entities.Request;

import java.sql.SQLException;

public interface RequestsDao
        extends GenericDao<Request> {
     void executeAdditionRequest(Request request);
     void executeRemovingRequest(Request request);
}
