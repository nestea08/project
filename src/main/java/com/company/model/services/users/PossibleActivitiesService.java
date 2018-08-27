package com.company.model.services.users;

import com.company.model.entities.Activity;

import java.util.List;

public class PossibleActivitiesService {
    private UsersUtils utils;

    public PossibleActivitiesService() {
        utils = new UsersUtils();
    }

    public PossibleActivitiesService(UsersUtils utils) {
        this.utils = utils;
    }

    public List<Activity> getPossibleActivities(int trackerId) {
        return utils.getPossibleActivities(trackerId);
    }
}
