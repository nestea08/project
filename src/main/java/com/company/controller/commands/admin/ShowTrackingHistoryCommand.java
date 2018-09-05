package com.company.controller.commands.admin;

import com.company.controller.PagesPaths;
import com.company.controller.Pagination;
import com.company.controller.commands.Command;
import com.company.controller.commands.CommandUtils;
import com.company.model.entities.HistoryItem;
import com.company.model.services.admins.LogsHolderService;

import javax.servlet.http.HttpServletRequest;

public class ShowTrackingHistoryCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        LogsHolderService service = new LogsHolderService();
        Pagination<HistoryItem> pagination = new Pagination<>(service.getLogs(), 10);
        CommandUtils.setCurrentPageForPagination(request, pagination);
        request.setAttribute("historyItems", pagination.getItemsForCurrentPage());
        request.setAttribute("pagesCount", pagination.getPagesCount());
        return "/WEB-INF/" + PagesPaths.HISTORY;
    }

}
