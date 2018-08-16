package com.company.model.services;


import com.company.model.entities.Activity;
import com.company.model.entities.UserRequest;

public class RequestProcessingService {
    private ServiceUtils utils;

    public RequestProcessingService() {
        utils = new ServiceUtils();
    }

    public RequestProcessingService(ServiceUtils utils) {
        this.utils = utils;
    }

    public void executeUserRequest(int requestId) {
        UserRequest request = utils.getUserRequestById(requestId);
        if (request.getType() == UserRequest.RequestType.ADD) {
            addActivity(request);
        }
        else {
            deleteActivity(request);
        }
    }

    public void refuseUserRequest(int requestId) {
        UserRequest request = utils.getUserRequestById(requestId);
        utils.deleteUserRequest(request);
    }

    private void addActivity(UserRequest request) {
        utils.addActivityToTrackerUser(request.getUser(), request.getActivity());
    }

    private void deleteActivity(UserRequest request) {
        utils.removeActivityFromTrackerUser(request.getUser(), request.getActivity());
    }

}