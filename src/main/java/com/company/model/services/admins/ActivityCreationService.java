package com.company.model.services.admins;

import com.company.model.dto.LocalizedActivityDto;
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

    public void createActivity(String enTitle, String ruTitle,
                               String enDescription, String ruDescription)
            throws NotUniqueActivityException {
        utils.createActivity(new LocalizedActivityDto(enTitle, ruTitle, enDescription, ruDescription));
    }
}
