package com.company.controller.commands.admin;

import com.company.controller.PagesPaths;
import com.company.controller.Pagination;
import com.company.controller.commands.Command;
import com.company.controller.commands.CommandUtils;
import com.company.model.entities.Request;
import com.company.model.services.admins.RequestProcessingService;

import javax.servlet.http.HttpServletRequest;

public class ShowUserRequestsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        RequestProcessingService service = new RequestProcessingService();
        Pagination<Request> pagination = new Pagination<>(service.getUserRequests(), 6);
        CommandUtils.setCurrentPageForPagination(request, pagination);
        request.setAttribute("requests", pagination.getItemsForCurrentPage());
        request.setAttribute("pagesCount", pagination.getPagesCount());
        return "/WEB-INF/" + PagesPaths.REQUESTS;
    }

}
