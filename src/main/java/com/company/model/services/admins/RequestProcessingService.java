package com.company.model.services.admins;


import com.company.model.entities.Request;

public class RequestProcessingService {
    private AdminsUtils utils;

    public RequestProcessingService() {
        utils = new AdminsUtils();
    }

    public RequestProcessingService(AdminsUtils utils) {
        this.utils = utils;
    }

    public void executeUserRequest(int requestId) {
        Request request = utils.getUserRequestById(requestId);
        if (request.getType() == Request.RequestType.ADD) {
            addActivity(request);
        }
        else {
            deleteActivity(request);
        }
    }

    public void refuseUserRequest(int requestId) {
        Request request = utils.getUserRequestById(requestId);
        utils.deleteUserRequest(request);
    }

    private void addActivity(Request request) {
        utils.addTrackedToTracker(request.getTracker(), request.getTracked());
    }

    private void deleteActivity(Request request) {
        utils.removeTrackedFromTracker(request.getTracker(), request.getTracked());
    }

}
