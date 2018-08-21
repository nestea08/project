package com.company.model.services.admins;

import com.company.model.entities.HistoryItem;

import java.util.List;

public class LogsHolderService {
    private AdminsUtils utils;

    public LogsHolderService() {
        utils = new AdminsUtils();
    }

    public LogsHolderService(AdminsUtils utils) {
        this.utils = utils;
    }

    public List<HistoryItem> getLogs() {
        return utils.getAllHistoryItems();
    }
}
