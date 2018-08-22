package com.company.controller.commands.admin;

import com.company.controller.Pagination;
import com.company.controller.commands.Command;
import com.company.model.entities.Request;
import com.company.model.services.admins.RequestProcessingService;

import javax.servlet.http.HttpServletRequest;

public class ShowUserRequestsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        RequestProcessingService service = new RequestProcessingService();
        Pagination<Request> pagination = new Pagination<>(service.getUserRequests(), 6);
        setCurrentPageForPagination(request, pagination);
        request.setAttribute("requests", pagination.getItemsForCurrentPage());
        request.setAttribute("pagesCount", pagination.getPagesCount());
        return "/admin/requests.jsp";
    }

    private void setCurrentPageForPagination(HttpServletRequest request, Pagination<Request> pagination) {
        String pageParameter = request.getParameter("page");
        if (pageParameter != null) {
            pagination.setCurrentPage(Integer.parseInt(pageParameter));
        }
    }
}
