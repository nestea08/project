package com.company.model.services.admins;

import com.company.model.entities.Activity;
import com.company.model.exceptions.NotUniqueActivityException;

public class ActivityCreationService {
    private AdminsUtils utils;

    public ActivityCreationService() {
        utils = new AdminsUtils();
    }

    public ActivityCreationService(AdminsUtils utils) {
        this.utils = utils;
    }

    public void createActivity(String title, String description)
            throws NotUniqueActivityException {
        utils.createActivity(new Activity(title, description));
    }
}
