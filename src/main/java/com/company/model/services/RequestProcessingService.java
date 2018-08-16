package com.company.model.services;


import com.company.model.entities.Activity;
import com.company.model.entities.UserRequest;

public class RequestProcessingService {

    public void executeUserRequest(int requestId) {
        UserRequest request = ServiceUtils.getUserRequestById(requestId);
        if (request.getType() == UserRequest.RequestType.ADD) {
            addActivity(request);
        }
        else {
            deleteActivity(request);
        }
    }

    public void refuseUserRequest(int requestId) {
        UserRequest request = ServiceUtils.getUserRequestById(requestId);
        ServiceUtils.deleteUserRequest(request);
    }

    private void addActivity(UserRequest request) {
        ServiceUtils.addActivityToTrackerUser(request.getUser(), request.getActivity());
    }

    private void deleteActivity(UserRequest request) {
        ServiceUtils.removeActivityFromTrackerUser(request.getUser(), request.getActivity());
    }

}
