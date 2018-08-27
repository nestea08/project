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
            utils.executeAdditionRequest(request);
        }
        else {
            utils.executeRemovingRequest(request);
        }
    }

    public void refuseUserRequest(int requestId) {
        Request request = utils.getUserRequestById(requestId);
        utils.deleteUserRequest(request);
    }



}
