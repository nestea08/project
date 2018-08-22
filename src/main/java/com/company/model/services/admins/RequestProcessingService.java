package com.company.model.services.admins;


import com.company.model.entities.Request;

import java.util.List;

public class RequestProcessingService {
    private AdminsUtils utils;

    public RequestProcessingService() {
        utils = new AdminsUtils();
    }

    public RequestProcessingService(AdminsUtils utils) {
        this.utils = utils;
    }

    public List<Request> getUserRequests() {
        return utils.getAllUserRequests();
    }

    public void executeUserRequest(int requestId) {
        Request request = utils.getUserRequestById(requestId);
        if (request.getType() == Request.RequestType.ADD) {
            addActivity(request);
        }
        else {
            deleteActivity(request);
        }
        utils.deleteUserRequest(request);
    }

    public void refuseUserRequest(int requestId) {
        Request request = utils.getUserRequestById(requestId);
        utils.deleteUserRequest(request);
    }

    private void addActivity(Request request) {
        utils.addActivityToTracker(request.getTracker(), request.getActivity());
    }

    private void deleteActivity(Request request) {
        utils.removeActivityFromTracker(request.getTracker(), request.getActivity());
    }

}
